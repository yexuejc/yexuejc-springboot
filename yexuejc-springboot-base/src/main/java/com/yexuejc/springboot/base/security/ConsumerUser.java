package com.yexuejc.springboot.base.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 登录用户信息 response
 *
 * @ClassName: ConsumerUser
 * @Description:登录用户信息
 * @author: maxf
 * @date: 2017年11月22日 下午4:39:45
 */
public class ConsumerUser extends User {
    private static final long serialVersionUID = 1L;

    /**
     * 消费者用户主键ID
     */
    private String id;
    /**
     * 登录方式
     */
    private String logType;
    /**
     * 登录时间
     */
    private Long logTime;

    public ConsumerUser(String username, String password, boolean enabled, boolean accountNonExpired,
                        boolean credentialsNonExpired, boolean accountNonLocked,
                        Collection<? extends GrantedAuthority> authorities,
                        String id, String logType, Long logTime) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.logType = logType;
        this.logTime = logTime;
    }

    /**
     * 仅做保存登录用户信息时使用
     *
     * @param username
     * @param id
     * @param logType
     * @param logTime
     */
    public ConsumerUser(String username, String id, String logType, Long logTime) {
        super(username, "", true, true, true, true, null);
        this.id = id;
        this.logType = logType;
        this.logTime = logTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(": ");

        sb.append("id: ").append(this.id).append("; ");

        return sb.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public Long getLogTime() {
        return logTime;
    }

    public void setLogTime(Long logTime) {
        this.logTime = logTime;
    }
}
