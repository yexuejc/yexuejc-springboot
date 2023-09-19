package com.yexuejc.springboot.base.test;

import com.aliyun.oss.model.PutObjectResult;
import com.yexuejc.base.util.ImgUtil;
import com.yexuejc.base.util.StrUtil;
import com.yexuejc.springboot.base.autoconfigure.OssFacade;
import com.yexuejc.springboot.base.exception.ImageException;
import com.yexuejc.springboot.base.util.LogUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author maxf
 * @version 1.0
 * @ClassName OssTest
 * @Description
 * @date 2018/11/1 14:58
 */
@SpringBootTest
public class OssTest {
    @Autowired
    OssFacade ossFacade;

    /**
     * 上传图片到oss
     * <p>
     * 这里是模拟微信登录时，获取到微信头像地址，缓存到base64，然后上传到OSS
     * </p>
     */
    @Test
    public void put() {
        putOss4Head(null, "https://avatar.csdn.net/7/8/1/3_wulex.jpg");
    }

    /**
     * 上传网络头像至OSS
     *
     * @param url 网络图片地址
     * @return String 本OSS地址
     */
    public String putOss4Head(String name, String url) throws ImageException {
        if (StrUtil.isEmpty(name)) {
            name = StrUtil.genUUID();
        }
        if (name.indexOf(".") == 0) {
            throw new ImageException("图片名称第一个字符不能为.");
        }
        name = "head/" + name;
        try {
            LogUtil.bizLogger.info("[第三方登录/注册]上传头像开始：{}", url);
            InputStream is = ImgUtil.getImageInputStreamFromUrl(url);
            byte[] b = ImgUtil.getByteArray(is);
            ImgUtil.ImageInfo imageInfo = ImgUtil.getImageInfoFromInputStream(b);
            if (name.indexOf(".") < 0) {
                name = name + "." + imageInfo.getType();
            }
            PutObjectResult head = ossFacade.putObject(name, new ByteArrayInputStream(b));
        } catch (IOException e) {
            LogUtil.bizLogger.error("[第三方登录/注册]读取网络头像为IO异常:{}", url);
            e.printStackTrace();
            throw new ImageException("[第三方登录/注册]读取网络头像为IO异常:" + e.getMessage());
        }
        LogUtil.bizLogger.info("[[第三方登录/注册]上传头像结束：{}", url);
        return name;
    }
}
