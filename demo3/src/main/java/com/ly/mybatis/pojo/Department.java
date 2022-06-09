package com.ly.mybatis.pojo;

import java.util.List;

/**
 * @FileName:Department.class
 * @Author:ly
 * @Date:2022/6/9
 * @Description:
 */
public class Department {
    private Integer did;
    private String deptName;
    private List<Employee> employeeList;

    public Department() {
    }

    @Override
    public String toString() {
        return "Department{" +
                "did=" + did +
                ", deptName='" + deptName + '\'' +
                ", employeeList=" + employeeList +
                '}';
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public Department(Integer did, String deptName) {
        this.did = did;
        this.deptName = deptName;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

}
