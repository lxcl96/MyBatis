package com.ly.mybatis.mapper;

import com.ly.mybatis.pojo.Employee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.Arrays;
import java.util.List;

/**
 * @FileName:DynamicSQLMapper.class
 * @Author:ly
 * @Date:2022/6/10
 * @Description: 动态sql查询
 */
public interface DynamicSQLMapper {
    /**
     * 多条件查询:员工姓名，性别，年龄，电子邮箱
     * (不确定查询结果数据就用集合)
     */
    List<Employee> getEmployeeByMultiCondition(Employee employee);


    /**
     * 测试choose，when，otherwise
     */
    List<Employee> getEmployeeByChoose(Employee employee);


    /**
     * 通过数组实现批量 删除
     */
    int deleteMoreByArray(@Param("eids") Integer[] eids);


    /**
     * 通过List集合实现批量 添加
     */
    int insertMoreByList(@Param("employees") List<Employee> employees);
}
