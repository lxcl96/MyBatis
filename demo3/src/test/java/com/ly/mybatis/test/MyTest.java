package com.ly.mybatis.test;

import com.ly.mybatis.mapper.DepartmentMapper;
import com.ly.mybatis.mapper.DynamicSQLMapper;
import com.ly.mybatis.mapper.EmployeeMapper;
import com.ly.mybatis.pojo.Department;
import com.ly.mybatis.pojo.Employee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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


    /**
     * 动态SQL之 if
     */
    @Test
    public void getEmployeeByMultiCondition() throws IOException {
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml")).openSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        Employee employee = new Employee(null, "张三", 32, '男', "zs@test.com");
        Employee employee1 = new Employee(null, "张三", 32, ' ', null);
        List<Employee> employeeList = mapper.getEmployeeByMultiCondition(employee1);
        employeeList.forEach(emp -> System.out.println(emp));
    }

    /**
     * choose，when，otherwise
     */
    @Test
    public void getEmployeeByChoose() throws IOException {
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml")).openSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        Employee employee = new Employee(null, "张三", 32, '男', "zs@test.com");
        Employee employee1 = new Employee(null, null, null, ' ', null);
        List<Employee> employeeList = mapper.getEmployeeByChoose(employee1);
        employeeList.forEach(emp -> System.out.println(emp));
    }


    /**
     * 通过数组实现批量 删除
     */
    @Test
    public void deleteMoreByArray() throws IOException {
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml")).openSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        int ret = mapper.deleteMoreByArray(new Integer[]{1, 2, 3, 4});
        System.out.println(ret);
        sqlSession.commit();
    }

    /**
     * 通过List集合实现批量 添加
     */
    @Test
    public void insertMoreByList() throws IOException {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1, "张三", 32, '男', "zs@test.com"));
        employeeList.add(new Employee(2, "李四", 28, '女', "ls@test.com"));
        employeeList.add(new Employee(3, "王五", 30, '男', "ww@test.com"));
        employeeList.add(new Employee(4, "赵六", 27, '男', "zl@test.com"));

        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml")).openSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        int ret = mapper.insertMoreByList(employeeList);
        System.out.println(ret);
        sqlSession.commit();
    }
}
