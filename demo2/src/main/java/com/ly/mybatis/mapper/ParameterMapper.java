package com.ly.mybatis.mapper;

import com.ly.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @FileName:ParameterMapper.java
 * @Author:ly
 * @Date:2022/6/7
 * @Description:
 */
public interface ParameterMapper {

    /**
     * 查询所有员工信息
     */
    List<User> getAllUser();

    /**
     * 根据用户名返回用户信息
     */
    User getUserByUsername(String username);

    /**
     * 验证登陆
     */
    User checkLogin(String username,String password,Integer id);

    /**
     * 验证登陆（手动传递参数map集合）
     */
    User checkLoginByMap(Map<String,Object> map);

    /**
     * 验证登陆（手动传递参数实体Bean）
     */
    User checkLoginByBean(User user);

    /**
     * 验证登陆(使用@param注解)
     */
    User checkLoginByAnnotation(@Param("username") String username, @Param("password") String password, @Param("identity") Integer id);
}
