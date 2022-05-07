package com.save.entity;

import java.io.Serializable;

/**
 * (OutStandard)实体类
 *
 * @author makejava
 * @since 2021-01-05 16:21:14
 */
public class OutStandard implements Serializable {
    private static final long serialVersionUID = -35389792107018098L;

    private Integer age;

    private Integer lostHour;

    private Integer id;


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getLostHour() {
        return lostHour;
    }

    public void setLostHour(Integer lostHour) {
        this.lostHour = lostHour;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}