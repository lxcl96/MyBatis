package com.ly.mybatis.test;

import com.ly.mybatis.mapper.DepartmentMapper;
import com.ly.mybatis.mapper.EmployeeMapper;
import com.ly.mybatis.pojo.Department;
import com.ly.mybatis.pojo.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @FileName:MyTest.class
 * @Author:ly
 * @Date:2022/6/9
 * @Description:
 */
public class MyTest {

    /**
     * 测试实体bean属性和数据库表字段不是完全一样的情况下
     *  使用结果集类型 resultType="Employee" 必定导致名称不是一样的属性无法被赋值 【本质是】
     *  解决方法：
     *      使用结果集映射 ：resultMap
     */
    @Test
    public void getAllEmployee () throws Exception{
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml")).openSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> employees = employeeMapper.getAllEmployee();
        employees.forEach(emp -> System.out.println(emp));
    }


    /***
     * 多对一之 部门属性的属性级联赋值
     */
    @Test
    public void getEmployeeAndDepartmentById () throws Exception{
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml")).openSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.getEmployeeAndDepartmentById(3);
        System.out.println(employee);
    }


    /***
     * 多对一之 分步查询
     */
    @Test
    public void getEmployeeAndDepartmentByStep () throws Exception{
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml")).openSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.getEmployeeAndDepartmentByIdStepOne(1);
        //输出员工性名
        System.out.println(employee.getEmpName());
    }


    /***
     * 一对多查询之 collection标签
     */
    @Test
    public void getDepartmentAndEmployeeById () throws Exception{
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml")).openSession();
        DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
        Department department = departmentMapper.getDepartmentAndEmployeeById(1);
        System.out.println(department);
    }


    @Test
    public void getDepartmentAndEmployeeByStepOne() throws Exception{
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml")).openSession();
        DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
        Department department = departmentMapper.getDepartmentAndEmployeeByStepOne(1);
        System.out.println(department.getDeptName());
    }
}
