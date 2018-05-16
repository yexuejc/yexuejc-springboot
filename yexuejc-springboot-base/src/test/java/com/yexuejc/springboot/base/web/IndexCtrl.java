package com.yexuejc.springboot.base.web;

import com.yexuejc.base.http.Resps;
import com.yexuejc.base.pojo.PagerVO;
import com.yexuejc.base.util.JsonUtil;
import com.yexuejc.base.util.StrUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: maxf
 * @date: 2018/5/12 14:17
 */
@RestController
public class IndexCtrl {

    @RequestMapping(value = {"/", "/index"})
    public Resps index() {
        return Resps.success("请求成功");
    }

    @RequestMapping(value = {"/2"})
    public Resps a(String name, String pwd) {
        System.out.println("请求参数：name = [" + name + "], pwd = [" + pwd + "]");
        return Resps.success("请求成功").setSucc("id>" + StrUtil.genUUID());
    }

    @RequestMapping(value = {"/4"})
    public Resps e() {
        List<String> list = new ArrayList();
        list.add("asdsad");
        list.add("发生大幅度发");
        list.add("1351615");
        return Resps.success("请求成功").setSucc(list);
    }

    @RequestMapping(value = {"/5"})
    public Resps f() {
        List<PagerVO> list = new ArrayList();
        PagerVO pq = new PagerVO();
        pq.setPage(333);
        pq.setSize(1);
        list.add(new PagerVO());
        PagerVO pw = new PagerVO();
        pw.setPage(23);
        pw.setSize(255);
        list.add(pw);
        PagerVO p = new PagerVO();
        p.setPage(555);
        list.add(p);
        return Resps.success("请求成功").setSucc(list);
    }

    @RequestMapping(value = {"/6"})
    public Resps h() {
        return Resps.success("请求成功").setSucc(1);
    }

    @RequestMapping(value = {"/7"})
    public Resps k() {
        return Resps.success("请求成功").setSucc(true);
    }

    @RequestMapping(value = {"/8"})
    public Resps l() {
        return Resps.success("请求成功").setSucc(05652.154);
    }

    @RequestMapping(value = {"/3"})
    public Resps b(@RequestBody PagerVO pagerVO) {
        System.out.println("请求参数：" + JsonUtil.obj2Json(pagerVO));
        Map map = new HashMap();
        map.put("page", 5);
        map.put("size", 16);
        map.put("content", "定制榻榻米垫竹编客厅茶几垫卧室地毯竹地毯飘窗垫日式榻榻米地毯");
        map.put("content2", map.get("content"));
        map.put("content3", map.get("content"));
        map.put("content4", map.get("content"));
        map.put("content5", map.get("content"));
        map.put("content6", map.get("content"));
        return Resps.success().setSucc(map);
    }

}
