package com.ly.mybatis.mapper;

import com.ly.mybatis.pojo.Department;
import com.ly.mybatis.pojo.Employee;
import org.apache.ibatis.annotations.Param;

/**
 * @FileName:CacheMapper.class
 * @Author:ly
 * @Date:2022/6/10
 * @Description:
 */
public interface CacheMapper {

    /**
     *
     */
    Employee getEmployeeById(@Param("eid")Integer eid);
    int insertOne();
}
