package com.ly.mybatis.test;

import com.ly.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @FileName:MyBatisTest.class
 * @Author:ly
 * @Date:2022/6/6
 * @Description: mybatis测试
 */
public class MyBatisTest {

    @Test
    public void testMyBatis() throws IOException {
        //1、加载核心配置文件 即mybatis-config.xml
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //2、获取SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3、获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = builder.build(is);
        //4、获取SqlSession对象   其为操作数据库的会话对象 【事务默认手动提交，设置为true 则自动提交】
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //5、获取mapper接口对象 【底层代理模式，返回接口的实现类对象】
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //6、执行sql语句
        int i = userMapper.insertUser();
        System.out.println("受影响的行数为：" + i);
        //7、因为mybatis全局配置文件，默认开启事务          <transactionManager type="JDBC"/>
        //sqlSession.commit();
    }

    @Test
    public void testUpdate() throws IOException {
        //1、读取mybatis全局配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //2、获取sqlSessionFactoryBuilder
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3、构建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = builder.build(is);
        //4、获取操作数据库的基础 sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //5、通过sqlSession获取映射mapper接口的实现类
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //6、此实现类实现了sql方法，直接调用即可
        userMapper.updateUser();
        //7、提交事务
        sqlSession.commit();
    }


    @Test
    public void testDelete() throws IOException {
        //1、获取mybatis全局配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //2、创建SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3、构建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = builder.build(is);
        //4、获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //5、根据SqlSession获取Mapper接口的实现类
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //6、直接调用方法即可 因为底层已经封装好了
        userMapper.deleteUser();
        //7、提交事务
        sqlSession.commit();

        System.out.println("===============代理userMapper的字段=============");
        System.out.println(userMapper.getClass());
        Field[] declaredFields = userMapper.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println("字段：" + declaredField);
        }

        Method[] declaredMethods = userMapper.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("方法： " + declaredMethod);
        }

    }


}
