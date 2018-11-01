
package com.yexuejc.springboot.base.autoconfigure;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.*;
import com.yexuejc.springboot.base.util.LogUtil;

/**
 * 阿里云消息服务MNS操作类
 *
 * @author maxf
 * @version 1.0
 * @ClassName MnsFacade
 * @Description
 * @date 2018/11/1 10:21
 */
public class MnsFacade {
    /**
     * 默认长轮询等待秒数
     */
    private static int DEFAULT_WAIT_SECONDS = 15;

    /**
     * 项目中需要用到的所有队列
     */
    private MNSClient client;
    /**
     * MNS相关配置
     */
    private MnsProperties properties;

    public MnsFacade(MnsProperties properties) {
        CloudAccount account = new CloudAccount(properties.getAccessKeyId(), properties.getAccessKeySecret(),
                properties.getEndpoint());
        this.client = account.getMNSClient();
        this.properties = properties;
    }

    /**
     * 发送消息
     *
     * @param queueName    发送对象队列名
     * @param msg          发送的消息字符串，使用json格式
     * @param delaySeconds 发送成功的Message对象
     * @return
     */
    public Message sendMsg(String queueName, String msg, int delaySeconds) {
        Message message = new Message();
        message.setMessageBody(msg);
        message.setDelaySeconds(delaySeconds);
        LogUtil.bizLogger.debug("[alibaba-MNS]发送消息{}:{}", queueName, msg);
        return client.getQueueRef(queueFullName(queueName)).putMessage(message);
    }


    /**
     * 取出消息
     *
     * @param queueName 接收对象对列名
     * @return 接收成功的Message对象
     */
    public Message popMsg(String queueName) {
        return client.getQueueRef(queueFullName(queueName)).popMessage(DEFAULT_WAIT_SECONDS);
    }

    /**
     * 取出消息 长轮询
     *
     * @param queueName   接收对象对列名
     * @param waitSeconds 接收成功的Message对象
     * @return
     */
    public Message popMsg(String queueName, int waitSeconds) {
        return client.getQueueRef(queueFullName(queueName)).popMessage(waitSeconds);
    }


    /**
     * 删除消息
     *
     * @param queueName 删除对象对列名
     * @param msg       需要删除的Message对象
     */
    public void deleteMsg(String queueName, Message msg) {
        LogUtil.bizLogger.info("删除对象--:queueName{},msg{}", queueName, msg);
        client.getQueueRef(queueFullName(queueName)).deleteMessage(msg.getReceiptHandle());

    }

    /**
     * 返回队列全称
     *
     * @return
     */
    public String queueFullName(String queueName) {
        return properties.getQueueNamePrefix() + "-" + queueName;
    }

    /**
     * 创建主题
     */
    public CloudTopic crtTopic() {
        TopicMeta topicMeta = new TopicMeta();
        topicMeta.setTopicName(properties.getTopicName());
        //是否启用日志
        topicMeta.setLoggingEnabled(true);
        return client.createTopic(topicMeta);
    }

    /**
     * 删除主题
     */
    public void delTopic() {
        getTopic().delete();
    }

    /**
     * 获取主题
     *
     * @return
     */
    public CloudTopic getTopic() {
        return client.getTopicRef(properties.getTopicName());
    }

    /**
     * 创建订阅
     */
    public void subscribe(String subscriptionName, String queueName) {
        CloudTopic topic = getTopic();
        SubscriptionMeta subMeta = new SubscriptionMeta();
        subMeta.setSubscriptionName(subscriptionName);
        subMeta.setNotifyContentFormat(SubscriptionMeta.NotifyContentFormat.SIMPLIFIED);
        subMeta.setEndpoint(topic.generateQueueEndpoint(queueFullName(queueName)));
        topic.subscribe(subMeta);
    }

    /**
     * 发送消息
     *
     * @param message
     * @return
     */
    public TopicMessage publishMessage(String message) {
        CloudTopic topic = getTopic();
        //可以使用TopicMessage结构，选择不进行Base64加密
        TopicMessage msg = new Base64TopicMessage();
        msg.setMessageBody(message);
        msg = topic.publishMessage(msg);
        return msg;
    }


    /**
     * 取出消息
     *
     * @param queueName
     * @return
     */
    public Message popTopicMessage(String queueName) {
        QueueMeta queueMeta = new QueueMeta();
        queueMeta.setQueueName(queueFullName(queueName));
        return client.createQueue(queueMeta).popMessage(DEFAULT_WAIT_SECONDS);
    }

}
