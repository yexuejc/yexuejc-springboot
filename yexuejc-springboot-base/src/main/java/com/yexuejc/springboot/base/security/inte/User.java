package com.yexuejc.springboot.base.security.inte;

import java.util.List;

/**
 * 登录用户接口
 *
 * @author maxf
 * @version 1.0
 * @ClassName User
 * @Description
 * @date 2018/11/8 17:19
 */
public interface User {
    /**
     * 角色
     *
     * @return
     */
    List<String> getRoles();


    String getMobile();

    String getPwd();

    boolean getEnable();

    boolean getNonExpire();

    boolean getNonLock();

    String getConsumerId();
}
