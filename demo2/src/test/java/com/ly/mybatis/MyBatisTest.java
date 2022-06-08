package com.ly.mybatis;

import com.ly.mybatis.mapper.ParameterMapper;
import com.ly.mybatis.pojo.User;
import com.mysql.jdbc.Driver;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * @FileName:MyBatisTest.class
 * @Author:ly
 * @Date:2022/6/7
 * @Description:
 */
public class MyBatisTest {

    /**
     * MyBatis获取参数值的两种方式：
     * ${xxx}: 表示字符串拼接 （存在sql注入风险，和需要手动加单引号'）xxx表示任意名称
     * #{xxx}: 表示占位符（推荐使用） xxx表示任意名称
     * MyBatis获取参数的各种情况：
     *  1、mapper接口方法的参数为单个字面量时
     *      ${xxx}和#{}均可以使用，其中名称xxx随意，但是要注意${}必须加上单引号或双引号【字符串拼接】
     *  2、mapper接口方法的形参为多个参数时 【mybatis会将参数放到map集合中】
     *      #{xxx}中xxx必须按照顺序取arg0,arg1,arg2...或param1,param2,param3... 可以交叉着用
     *      ${xxx}中xxx必须按照顺序取arg0,arg1,arg2...或param1,param2,param3... 可以交叉着用,同时需要加上单引号
     *  3、mapper接口方法参数为多个时，手动将参数放入map集合中存储（就不必须用agr/param取值了）
     *      sql中键就是put方法的key
     *  4、mapper接口方法参数为实体类Bean时，和map一样
     *      sql中键就是实体Bean的属性名（必须完全一样，且有get方法）
     *
 *      5、使用@param注解 参数命名参数 (以注解的值，或param1..作为sql参数)
     */


    @Test
    public void getAllUser() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ParameterMapper parameterMapper = sqlSession.getMapper(ParameterMapper.class);
        List<User> allUser = parameterMapper.getAllUser();
        allUser.forEach((u) -> System.out.println(u));

        sqlSession.commit();
    }

    /**
     * 使用JDBC的字符串拼接方式，和占位符方式查询
     */
    @Test
    public void testJDBC() throws Exception {
        Class<?> cls = Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql:///mybatis", "root", "123456");
        String sql = "select * from t_user where id=";
        String sql1 = "select * from t_user where id=?";

        //使用字符串拼接
        PreparedStatement statement = connection.prepareStatement(sql + 14);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        System.out.println(resultSet.getString("username"));

        //使用占位符赋值
        PreparedStatement statement1 = connection.prepareStatement(sql1);
        statement1.setObject(1,13);
        ResultSet resultSet1 = statement1.executeQuery();
        resultSet1.next();
        System.out.println(resultSet1.getString("username"));


    }


    /*
     * 单字面量情况，即一个参数
     */
    @Test
    public void getUserByUsername() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ParameterMapper parameterMapper = sqlSession.getMapper(ParameterMapper.class);
        User user = parameterMapper.getUserByUsername("admin");
        System.out.println(user);

        sqlSession.commit();
    }


    /*
     * 多个字面量时，即多个参数
     */
    @Test
    public void checkLogin() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ParameterMapper parameterMapper = sqlSession.getMapper(ParameterMapper.class);
        User user = parameterMapper.checkLogin("admin","123456",13);
        System.out.println(user);

        sqlSession.commit();
    }

    /*
     * 传递参数为map集合时
     * 即多个字面量时，即多个参数 (手动设置参数Map集合，自己规定参数key不用arg/param)
     */
    @Test
    public void checkLoginByMap() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ParameterMapper parameterMapper = sqlSession.getMapper(ParameterMapper.class);
        Map<String, Object> map = new HashMap<String, Object>();
        //这个键key 必须和mapper.xml文件中的key值一样，才能取到参数
        map.put("username","admin");
        map.put("password","123456");
        map.put("id",13);

        User user = parameterMapper.checkLoginByMap(map);
        System.out.println(user);
        sqlSession.commit();
    }

    /*
     * 传递参数为实体类Bean时
     */
    @Test
    public void checkLoginByBean() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ParameterMapper parameterMapper = sqlSession.getMapper(ParameterMapper.class);
        User root = parameterMapper.checkLoginByBean(new User(14, "root", "123", null, '1', null,null));
        System.out.println(root);
        sqlSession.commit();
    }

    /*
     * 传递多个参数，使用注解@Param，自动将参数放到map集合中 key就为注解的value  值就为标识的参数【还有一个参数就是param1，2，3】
     */
    @Test
    public void checkLoginByAnnotation() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ParameterMapper parameterMapper = sqlSession.getMapper(ParameterMapper.class);
        User root = parameterMapper.checkLoginByAnnotation("root", "123", 14);
        System.out.println(root);
        sqlSession.commit();
    }

    @Test
    public void test() {
        SortedMap<Integer, String> map = new TreeMap<>();
        System.out.println(map);
        String name = String.valueOf(map.size());
        System.out.println(name);
        map.put(0,name);
        SortedMap<Integer, String> names = Collections.unmodifiableSortedMap(map);
        System.out.println(names);
    }
}
