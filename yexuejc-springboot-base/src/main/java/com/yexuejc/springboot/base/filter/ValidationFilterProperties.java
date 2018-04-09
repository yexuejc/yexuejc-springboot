package com.yexuejc.springboot.base.filter;

import com.yexuejc.base.util.JsonUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤路径
 * @PackageName: com.uselaw.base.filter
 * @Description:
 * @author: maxf
 * @date: 2018/2/7 19:11
 */
@ConfigurationProperties(prefix = "yexuejc.http.filter")
public class ValidationFilterProperties {
    /**
     * 拦截类型：0忽略模式，默认拦截全部；1拦截模式，默认一个都不拦截
     */
    private int type = 1;
    /**
     * 忽略拦截列表、路径
     */
    private List<String> ignored = new ArrayList<>();
    /**
     * 拦截列表、路径
     */
    private List<String> intercepts = new ArrayList<>();

    @Override
    public String toString() {
        return JsonUtil.obj2Json(this);
    }

    public List<String> getIgnored() {
        return ignored;
    }

    public void setIgnored(List<String> ignored) {
        this.ignored = ignored;
    }

    public List<String> getIntercepts() {
        return intercepts;
    }

    public void setIntercepts(List<String> intercepts) {
        this.intercepts = intercepts;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
