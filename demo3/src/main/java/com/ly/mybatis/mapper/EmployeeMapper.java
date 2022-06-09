package com.ly.mybatis.mapper;

import com.ly.mybatis.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @FileName:EmployeeMapper.class
 * @Author:ly
 * @Date:2022/6/9
 * @Description:
 */
public interface EmployeeMapper {

    /**
     * 查询所有的员工信息
     */
    List<Employee> getAllEmployee();

    /**
     * 查询员工以及员工对应的部门信息
     * 多对一解决方法：
     * 1、级联赋值
     * 2、使用association标签
     * 3、分步骤查询
     */
    Employee getEmployeeAndDepartmentById(@Param("eid") Integer eid);

    /***
     * 3、分步骤查询
     * 第一步：根据员工id查询到员工信息
     * 第二步：根据查到的员工信息关联的部门id，再查询部门表 [到另一个接口即另一个mapper文件]
     */
    Employee getEmployeeAndDepartmentByIdStepOne(@Param("eid") Integer eid);


    /**
     * 一对多分步查询第二步：根据部门id查找员工信息
     */
    List<Employee> getDepartmentAndEmployeeByStepTwo(@Param("did") Integer did);
}
