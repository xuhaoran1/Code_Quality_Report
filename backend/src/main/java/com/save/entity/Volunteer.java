package com.save.entity;

import java.io.Serializable;

/**
 * (Volunteer)实体类
 *
 * @author makejava
 * @since 2021-01-05 15:56:43
 */
public class Volunteer implements Serializable {
    private static final long serialVersionUID = 590559742207766802L;

    private Integer id;

    private String name;

    private String username;

    private String pwd;
    /**
     * 1-女 0-男
     */
    private Integer gender;

    private String telephone;
    /**
     * n-不空闲，且status就是组id/0-空闲
     */
    private Integer status;

    private String address;
    /**
     * 0公共交通工具，1可合法载人机动车，2不可载人交通工具
     */
    private Integer transport;
    /**
     * 0不会急救，1有急救技能
     */
    private Integer firstAidSkill;
    /**
     * 1装备齐全，0未装备齐全
     */
    private Integer equipState;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTransport() {
        return transport;
    }

    public void setTransport(Integer transport) {
        this.transport = transport;
    }

    public Integer getFirstAidSkill() {
        return firstAidSkill;
    }

    public void setFirstAidSkill(Integer firstAidSkill) {
        this.firstAidSkill = firstAidSkill;
    }

    public Integer getEquipState() {
        return equipState;
    }

    public void setEquipState(Integer equipState) {
        this.equipState = equipState;
    }

}