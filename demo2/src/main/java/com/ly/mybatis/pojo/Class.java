package com.ly.mybatis.pojo;

/**
 * @FileName:Class.class
 * @Author:ly
 * @Date:2022/6/8
 * @Description:
 */
public class Class {

    private Integer id;
    private String className;

    public Class() {
    }

    public Class(Integer id, String className) {
        this.id = id;
        this.className = className;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", className='" + className + '\'' +
                '}';
    }
}
