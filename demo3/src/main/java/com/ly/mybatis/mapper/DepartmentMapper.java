package com.ly.mybatis.mapper;

import com.ly.mybatis.pojo.Department;
import org.apache.ibatis.annotations.Param;

/**
 * @FileName:DepartmentMapper.class
 * @Author:ly
 * @Date:2022/6/9
 * @Description:
 */
public interface DepartmentMapper {

    /**
     * 分步查询第二步：通过员工关联的did查询部门信息
     */
    Department getDepartmentById(@Param("did") Integer did);

    /**
     * 一对多经典案例：
     * 根据id查询部门信息，并且获取该部门下所有员工信息
     * 解决方法：collection标签
     */
    Department getDepartmentAndEmployeeById(@Param("did") Integer did);

    /**
     * 一对多经典案例：
     * 根据id查询部门信息，并且获取该部门下所有员工信息
     * 解决方法：分步查询
     */
    Department getDepartmentAndEmployeeByStepOne(@Param("did") Integer did);
}
