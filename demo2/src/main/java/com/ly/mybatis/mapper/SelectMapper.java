package com.ly.mybatis.mapper;

import com.ly.mybatis.pojo.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @FileName:SelectMapper.class
 * @Author:ly
 * @Date:2022/6/8
 * @Description: mybatis各种查询功能
 */
public interface SelectMapper {

    /**
     * 根据id查询单个用户信息
     */
    List<User> getUserById(@Param("id") Integer id);

    /**
     * 查询用户总量
     */
    Integer getAllUserNum();


    /**
     * 根据用户id，查询用户存放在map集合，键就为sql列名（字段），值就是查询的结果
     * 如果有多条数据，同样需要放在list集合中
     */
    List<Map<String,Object>> getUserToMap(@Param("id") Integer id);


    /**
     * 查询所有用户为map集合
     */
    @MapKey("id")
    Map<String,Object> getAllUserToMap();
}
