package com.wy.work.reflex;

/**
 * Created by Administrator on 2018/3/1.
 */

public class Person {

    public String name = "阿三";
    private int age = 20;
    private String sex = "男";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
