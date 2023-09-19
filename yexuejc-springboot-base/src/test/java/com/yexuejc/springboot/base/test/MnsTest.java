package com.yexuejc.springboot.base.test;

import com.aliyun.mns.model.Message;
import com.yexuejc.base.util.JsonUtil;
import com.yexuejc.base.util.StrUtil;
import com.yexuejc.springboot.base.autoconfigure.MnsFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author maxf
 * @version 1.0
 * @ClassName MnsTest
 * @Description
 * @date 2018/11/1 14:25
 */
@SpringBootTest
public class MnsTest {

    @Autowired
    MnsFacade mnsFacade;

    /**
     * 发送MNS
     */
    @Test
    public void sendMms() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", StrUtil.genUUID());
        map.put("type", "shop");
        mnsFacade.sendMsg("SHOP", JsonUtil.obj2Json(map), 0);
    }

    /**
     * 取出消息->删除消息
     */
    public void get() {
        Message msg = mnsFacade.popMsg("SHOP");
        if (msg == null) {
            System.out.println("取出空消息");
        }
        if (msg.getMessageBody() == null) {
            System.out.println("取出消息无内容");
            //删除
            mnsFacade.deleteMsg("SHOP", msg);
        }
        Map<String, Object> map = JsonUtil.json2Obj(msg.getMessageBody(), Map.class);
        System.out.println(String.format("取出消息：%s", JsonUtil.obj2Json(map)));
        //操作完成后删除消息
    }
}
