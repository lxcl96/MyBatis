package com.ly.mybatis.mapper;

import com.ly.mybatis.pojo.User;

import java.util.List;

/**
 * @FileName:UserMapper.class
 * @Author:ly
 * @Date:2022/6/6
 * @Description:
 */
//MyBatis面向接口编程的的两个一致：
//1、映射文件（如UserMapper.xml）中的mapper标签的属性namespace的值必须为要使用接口的全类名
//2、映射文件中sql语句的id值，要和要实现接口的方法的方法名对应（保证一一对应）
public interface UserMapper {

    /**
     * 添加用户信息
     * @return 影响的行数
     */
    int insertUser();

    /**
     * 修改用户信息
     * @return 返回影响行数
     */
    int updateUser();

    /**
     * 删除用户
     * @return 影响的行数
     */
    int deleteUser();


    /***
     * 查询一个实体类对象
     */
    User selectOneBean();

    /**
     * 查询所有用户数据
     */
    List<User> getAllUser();
}
