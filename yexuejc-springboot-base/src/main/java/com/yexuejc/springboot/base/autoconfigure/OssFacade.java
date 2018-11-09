package com.yexuejc.springboot.base.autoconfigure;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CopyObjectResult;
import com.aliyun.oss.model.PutObjectResult;

import java.io.InputStream;

/**
 * 阿里云OSS服务MNS操作类
 *
 * @author maxf
 * @version 1.0
 * @ClassName OssFacade
 * @Description
 * @date 2018/11/1 10:30
 */
public class OssFacade {

    private final OssProperties properties;
    private final ClientConfiguration configuration;

    public OssFacade(OssProperties properties, ClientConfiguration configuration) {
        this.properties = properties;
        this.configuration = configuration;
    }

    /**
     * 拷贝对象，在默认Bucket中拷贝
     *
     * @param srcKey  元对象Key
     * @param destKey 目标对象Key
     * @return CopyObjectResult
     */
    public CopyObjectResult copyObject(String srcKey, String destKey) {
        OSSClient client = ossClient();
        CopyObjectResult result = client.copyObject(properties.getBucket(), srcKey, properties.getBucket(), destKey);
        client.shutdown();
        return result;
    }

    /**
     * 上传文件（简单上传，适合小文件）
     *
     * @param key         上传位置
     * @param inputStream
     * @return
     */
    public PutObjectResult putObject(String key, InputStream inputStream) {
        OSSClient client = ossClient();
        PutObjectResult result = client.putObject(properties.getBucket(), key, inputStream);
        client.shutdown();
        return result;
    }

    /**
     * 判断文件是否存在
     *
     * @param key 文件位置
     * @return
     */
    public boolean doesObjectExist(String key) {
        OSSClient client = ossClient();
        boolean isExist = client.doesObjectExist(properties.getBucket(), key);
        client.shutdown();
        return isExist;
    }

    private OSSClient ossClient() {
        return new OSSClient(properties.getEndpoint(), properties.getAccessKeyId(), properties.getAccessKeySecret(),
                configuration);
    }
}
