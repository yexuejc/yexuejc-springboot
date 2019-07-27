package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.demo.security.domain.Consumer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author yexuejc
 * @since 2018-05-27
 */
@Mapper
public interface ConsumerMapper extends BaseMapper<Consumer> {

    /**
     * <p>
     * 插入一条记录
     * </p>
     *
     * @param entity 实体对象
     * @return int
     */
    @Override
    int insert(Consumer entity);


    /**
     * 修改权限
     *
     * @param entity
     * @return
     */
    Integer updateRoles(@Param(Constants.ENTITY) Consumer entity);
}
