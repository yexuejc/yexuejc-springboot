package com.yexuejc.springboot.base.filter;

import com.yexuejc.base.encrypt.RSA;
import com.yexuejc.base.encrypt.RSA2;
import com.yexuejc.base.http.Resps;
import com.yexuejc.base.util.JsonUtil;
import com.yexuejc.base.util.StrUtil;
import com.yexuejc.springboot.base.util.LogUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.security.interfaces.RSAPrivateKey;
import java.util.List;
import java.util.Map;


/**
 * 返回数据加密
 *
 * @version 1.0.5
 * @ClassName: ParamsResponseBodyAdvice
 * @Description:
 * @author: maxf
 * @date: 2018/5/12 22:50
 */
@ControllerAdvice
@ConditionalOnClass({ResponseBodyAdvice.class, ServerHttpRequest.class, ServerHttpResponse.class, MediaType.class})
@EnableConfigurationProperties(RsaProperties.class)
public class ParamsResponseBodyAdvice implements ResponseBodyAdvice {

    private final RsaProperties properties;

    public ParamsResponseBodyAdvice(RsaProperties properties) {
        this.properties = properties;
    }


    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (returnType.getMethod().isAnnotationPresent(SerializedField.class)) {
            //获取注解配置的包含和去除字段
            SerializedField serializedField = returnType.getMethodAnnotation(SerializedField.class);
            //是否加密
            properties.setEncrypt(serializedField.encode());
        }
        if (properties.isEncrypt()) {
            if (body instanceof Resps) {
                long t = System.currentTimeMillis();
                Resps resps = (Resps) body;
                if (StrUtil.isNotEmpty(resps.getData())) {
                    String data = "";
                    if (resps.getData() instanceof Map) {
                        data = StrUtil.getSignContent((Map) resps.getData());
                    } else if (resps.getData() instanceof List) {
                        data = JsonUtil.obj2Json(resps.getData());
                    } else if (resps.getData() instanceof String) {
                        data = (String) resps.getData();
                    } else {
                        data = JsonUtil.obj2Json(resps.getData());
                    }
                    resps.setSign(StrUtil.toMD5(data));
                    try {
                        RSAPrivateKey rsaPrivateKey = null;
                        if (StrUtil.isEmpty(properties.getPrivateKey())) {
                            rsaPrivateKey = RSA2.getPrivateKey(
                                    this.getClass().getResource(properties.getPrivateKeyPath()).getFile().toString(),
                                    properties.getPrivateAlias(),
                                    properties.getPrivatePwd());
                        } else {
                            rsaPrivateKey = RSA.getPrivateKey(properties.getPrivateKey());
                        }
                        resps.setData(
                                RSA.privateEncrypt(JsonUtil.obj2Json(resps.getData()), rsaPrivateKey)
                        );
                    } catch (Exception e) {
                        e.printStackTrace();
                        LogUtil.accessLogger.error("出参加密错误，进行明文出参{}。\n异常信息：{}", JsonUtil.obj2Json(resps), e.getMessage());
                    }
                }
                LogUtil.accessLogger.debug("加密耗时：{}", System.currentTimeMillis() - t);
            }
        }
        return body;
    }
}