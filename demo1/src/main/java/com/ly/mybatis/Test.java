package com.ly.mybatis;

import com.ly.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import sun.misc.ProxyGenerator;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @FileName:Test.class
 * @Author:ly
 * @Date:2022/6/6
 * @Description:
 */
public class Test {
    public static void main(String[] args) throws IOException {
        //1、获取mybatis全局配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //2、创建SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3、构建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = builder.build(is);
        //4、获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //5、根据SqlSession获取Mapper接口的实现类

        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        //System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        byte[] newProxyClass = ProxyGenerator.generateProxyClass("$Proxy6", userMapper.getClass().getInterfaces());
        System.out.println(newProxyClass);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File("$Proxy6.class"));
            try {
                fileOutputStream.write(newProxyClass);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }




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
