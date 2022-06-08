package com.ly.mybatis.pojo;

import com.sun.istack.internal.Nullable;

/**
 * @FileName:User.class
 * @Author:ly
 * @Date:2022/6/6
 * @Description:
 */
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private char sex;
    private String email;
    private Integer classId;

    public User() {
    }

    public User(Integer id, String username, String password, Integer age, char sex, String email,  Integer classId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.sex = sex;
        this.email = email;
        this.classId = classId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", email='" + email + '\'' +
                ", classId=" + classId +
                '}';
    }
}
