package com.ly.mybatis.pojo;

import java.io.Serializable;

/**
 * @FileName:Employee.class
 * @Author:ly
 * @Date:2022/6/9
 * @Description:
 */
public class Employee implements Serializable {
    private Integer eid;
    private String empName;
    private Integer age;
    private char sex;
    private String email;
    //多对一映射关系，在`多`的实体bean中建立`一`的属性，映射
    private Department department;


    public Employee() {
    }

    public Employee(Integer eid, String empName, Integer age, char sex, String email) {
        this.eid = eid;
        this.empName = empName;
        this.age = age;
        this.sex = sex;
        this.email = email;
    }

    public Employee(Integer eid, String empName, Integer age, char sex, String email, Department department) {
        this.eid = eid;
        this.empName = empName;
        this.age = age;
        this.sex = sex;
        this.email = email;
        this.department = department;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eid=" + eid +
                ", empName='" + empName + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", email='" + email + '\'' +
                ", department=" + department +
                '}';
    }
}
