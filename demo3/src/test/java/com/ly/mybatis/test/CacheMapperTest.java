package com.ly.mybatis.test;

import com.ly.mybatis.mapper.CacheMapper;
import com.ly.mybatis.pojo.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;

/**
 * @FileName:CacheMapperTest.class
 * @Author:ly
 * @Date:2022/6/10
 * @Description:
 */
public class CacheMapperTest {

    /**
     * 测试一级缓存
     */
    @Test
        public void getEmployeeById() throws IOException {
            SqlSession sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml")).openSession();
            CacheMapper cacheMapper = sqlSession.getMapper(CacheMapper.class);
            Employee employee = cacheMapper.getEmployeeById(1);

            System.out.println(employee);
            System.out.println("分隔符 -------------------------------");
            //清空sqlSession缓存
            //sqlSession.clearCache();

            //执行任意一次增删改操作，不是同一个表
            int i = cacheMapper.insertOne();
            System.out.println(i);

            //同一个sqlSession查询同一个id
            CacheMapper cacheMapper2 = sqlSession.getMapper(CacheMapper.class);
            Employee employee2 = cacheMapper.getEmployeeById(1);

            System.out.println(employee2);
            System.out.println("分隔符 -------------------------------");

            //不同的sqlSession 查询同一个id
            SqlSession sqlSession1 = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml")).openSession();
            CacheMapper cacheMapper3 = sqlSession1.getMapper(CacheMapper.class);
            Employee employee3 = cacheMapper3.getEmployeeById(1);
            System.out.println(employee3);
        }


    /**
     * 测试二级缓存
     * 开启二级缓存
     *  1、mybatis全局配置文件中引入 <setting name="cacheEnabled" value="true"/> (默认开启)
     *  2、mapper.xml配置文件中启用 cache  <cache />
     *  3、必须关闭或提交sqlSession后才会生效
     *  4、查询的结果所转化为的实体类类型，必须实现了序列化
     */
    @Test
    public void testSecondCache() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession session1 = sqlSessionFactory.openSession();
        CacheMapper session1Mapper = session1.getMapper(CacheMapper.class);
        Employee employee1 = session1Mapper.getEmployeeById(1);
        System.out.println(employee1);
        session1.commit();
        System.out.println("分隔符 -------------------------------");
        SqlSession session2 = sqlSessionFactory.openSession();
        CacheMapper session2Mapper = session2.getMapper(CacheMapper.class);
        Employee employee2 = session2Mapper.getEmployeeById(1);
        System.out.println(employee2);
        employee2.setEmpName("猪猪公主");
        System.out.println("分隔符 -------------------------------");
        System.out.println(employee1); //readOnly="true" ，employee1也变成猪猪公主了
        session2.commit();

    }

}
