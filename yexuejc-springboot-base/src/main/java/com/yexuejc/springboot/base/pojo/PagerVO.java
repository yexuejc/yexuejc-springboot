package com.yexuejc.springboot.base.pojo;

import com.yexuejc.base.pojo.BaseVO;
import com.yexuejc.base.util.JsonUtil;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 分页 VO
 *
 * @author: maxf
 * @date: 2018/3/28 14:23
 */
public class PagerVO extends BaseVO {

    private static final long serialVersionUID = 3490440129554644587L;

    @NotNull
    @Min(1L)
    private Integer page = 1;
    @NotNull
    @Min(1L)
    private Integer size = 20;

    public int getOffset() {
        return (this.page - 1) * this.size;
    }

    @Override
    public String toString() {
        return JsonUtil.obj2Json(this);
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
