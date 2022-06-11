package com.ly.mybatis.test;

import com.ly.mybatis.mapper.EmpMapper;
import com.ly.mybatis.pojo.Emp;
import com.ly.mybatis.pojo.EmpExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @Author : Ly
 * @Date : 2022/6/11
 * @Description :
 */
public class MBGTest {
    @Test
    public void testMGBSelect() throws IOException {
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml")).openSession();
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        //1、根据条件 查询所有数据 (条件为null)
        //List<Emp> emps = empMapper.selectByExample(null); //null 就是没有条件
        //emps.forEach(emp -> System.out.println(emp));

        //2、根据条件(empName=张三) 查询 【条件可以嵌套一直.下去】
        EmpExample empExample = new EmpExample();
        //createCriteria 表示创建条件 后面根据属性进行相应的操作  【查找张三 32岁 性别不为空 或eid=3的】
        empExample.createCriteria().andEmpNameEqualTo("张三").andAgeEqualTo(32);
        //条件加上or方法 上下两个通过or拼接
        empExample.or().andEidEqualTo(3);

        List<Emp> emps = empMapper.selectByExample(empExample);
        emps.forEach(emp -> System.out.println(emp));
    }

    @Test
    public void testMGBUpdate() throws IOException {
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml")).openSession();
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        //1、根据主键(不能改主键)选择性修改     （属性为null的属性不写入对应字段，原来是啥子还是啥子）
        int i = empMapper.updateByPrimaryKeySelective(new Emp(2, "admin", 22, "女", "admin@admin.com", null));
        System.out.println(i);


        //2、根据主键(不能改主键)修改           （属性为null的属性写入对应字段，原来不为空现在就变为空了）
        //int j = empMapper.updateByPrimaryKey(new Emp(1, "admin", 22, "女", "admin@admin.com", null));
        //System.out.println(j);




        //别忘记commit
        sqlSession.commit();
    }
}
