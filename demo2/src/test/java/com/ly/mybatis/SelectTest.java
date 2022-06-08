package com.ly.mybatis;

import com.ly.mybatis.mapper.SelectMapper;
import com.ly.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @FileName:SelectTest.class
 * @Author:ly
 * @Date:2022/6/8
 * @Description:
 */
public class SelectTest {

    @Test
    public void getUserById() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(is).openSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        List<User> user = mapper.getUserById(13);
        System.out.println(user);
    }

    @Test
    public void getAllUserNum() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(is).openSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        Integer num = mapper.getAllUserNum();
        System.out.println("总记录数" + num);
    }

    @Test
    public void getUserToMap() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(is).openSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        List<Map<String, Object>> map = mapper.getUserToMap(13);
        System.out.println(map);
    }

    @Test
    public void getAllUserToMap() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(is).openSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        Map<String, Object> map = mapper.getAllUserToMap();
        System.out.println(map);
    }
}
