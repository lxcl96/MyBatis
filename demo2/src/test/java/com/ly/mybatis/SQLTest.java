package com.ly.mybatis;

import com.ly.mybatis.mapper.SQLMapper;
import com.ly.mybatis.mapper.SelectMapper;
import com.ly.mybatis.pojo.Class;
import com.ly.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.Map;

/**
 * @FileName:SQLTest.class
 * @Author:ly
 * @Date:2022/6/8
 * @Description:
 */
public class SQLTest {
    @Test
    public void getUserByUsername() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(is).openSession();
        SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
        User user = mapper.getUserByUsername("oo");
        System.out.println(user);
    }


    @Test
    public void testInsertUserAndClass() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(is).openSession();
        SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
        Class cls = new Class(null, "终极一班");
        int clsNum = mapper.insertClass(cls);
        System.out.println("受影响的行数：" + clsNum);//1
        System.out.println("班级自动递增后的主键为：" + cls.getId());//自动递增后的主键

        //回显自动递增的班级id，关联到用户表
        User user = new User(null, "里西奥", "lixiao", 35, '男', "lixiao@li.com", cls.getId());
        int userNum = mapper.insertUser(user);
        System.out.println("受影响的行数：" + userNum);//1
        System.out.println("*****班级用户信息*****");
        System.out.println(cls);
        System.out.println(user);

        //提交事务
        sqlSession.commit();
    }
}
