package com.ly.mybatis.mapper;

import com.ly.mybatis.pojo.Class;
import com.ly.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @FileName:SQLMapper.class
 * @Author:ly
 * @Date:2022/6/8
 * @Description:
 */
public interface SQLMapper {

    /**
     * 根据用户名模糊查询 用户信息
     */
    User getUserByUsername(@Param("username") String username);


    /**
     * 新增user信息，同时给user分配新增班级的主键
     * @param user 用户实体Bean
     */
    int insertUser(User user);

    /**
     * 新增class信息，同时给user分配新增班级的主键
     * @param cls 班级实体Bean
     */
    int insertClass(Class cls);
}
