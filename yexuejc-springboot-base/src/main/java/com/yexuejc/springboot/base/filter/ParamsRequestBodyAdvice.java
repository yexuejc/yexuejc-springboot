package com.yexuejc.springboot.base.filter;

import com.yexuejc.base.encrypt.RSA;
import com.yexuejc.base.encrypt.RSA2;
import com.yexuejc.base.pojo.ParamsPO;
import com.yexuejc.base.util.JsonUtil;
import com.yexuejc.base.util.StrUtil;
import com.yexuejc.springboot.base.exception.GatewayException;
import com.yexuejc.springboot.base.util.LogUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.security.interfaces.RSAPrivateKey;

/**
 * 请求数据解密
 *
 * @version 1.0.5
 * @ClassName: ParamsRequestBodyAdvice
 * @Description:
 * @author: maxf
 * @date: 2018/5/12 22:49
 */
@ControllerAdvice
@ConditionalOnClass({RequestBodyAdvice.class, HttpHeaders.class, HttpInputMessage.class, HttpMessageConverter.class})
@EnableConfigurationProperties(RsaProperties.class)
public class ParamsRequestBodyAdvice implements RequestBodyAdvice {

    private final RsaProperties properties;

    public ParamsRequestBodyAdvice(RsaProperties properties) {
        this.properties = properties;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        if (properties.isDecrypt()) {
            ParamsPO paramsPO = JsonUtil.json2Obj(IOUtils.toString(inputMessage.getBody(), "UTF-8"), ParamsPO.class);
            //RSA解密
            try {
                long t = System.currentTimeMillis();
                RSAPrivateKey rsaPrivateKey = null;
                if (StrUtil.isEmpty(properties.getPrivateKey())) {
                    rsaPrivateKey = RSA2.getPrivateKey(
                            this.getClass().getResource(properties.getPrivateKeyPath()).getFile().toString(),
                            properties.getPrivateAlias(),
                            properties.getPrivatePwd());
                } else {
                    rsaPrivateKey = RSA.getPrivateKey(properties.getPrivateKey());
                }
                String data = new String(
                        RSA.privateDecrypt(
                                paramsPO.getData(),
                                rsaPrivateKey
                        )
                );
                //md5 校验
                if (!StrUtil.toMD5(data).equals(paramsPO.getSign())) {
                    LogUtil.accessLogger.error("sign错误,请求内容：{}", JsonUtil.obj2Json(paramsPO));
                    throw new GatewayException("sign错误");
                }
                InputStream body = new ByteArrayInputStream(data.getBytes("UTF-8"));
                LogUtil.accessLogger.debug("解密耗时：{}", System.currentTimeMillis() - t);
                return new MyHttpInputMessage(inputMessage.getHeaders(), body);
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.accessLogger.error("解密失败，直接传递参数{}。\n异常信息：{}", JsonUtil.obj2Json(paramsPO), e);
            }
        }
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    class MyHttpInputMessage implements HttpInputMessage {
        private HttpHeaders headers;

        private InputStream body;

        public MyHttpInputMessage(HttpHeaders headers, InputStream body) throws Exception {
            this.headers = headers;
            this.body = body;
        }

        @Override
        public InputStream getBody() throws IOException {
            return body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }
    }
}