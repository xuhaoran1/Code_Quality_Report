package com.save.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 1.该表的字段可以通过后端自由管理
 * 2.一条记录是一个走失的老人
 * (Task)实体类
 *
 * @author makejava
 * @since 2021-01-05 15:38:58
 */
public class Task implements Serializable {
    private static final long serialVersionUID = 898705177420000779L;

    private Integer id;

    private String targetName;
    /**
     * 1-女，0-男
     */
    private Integer targetGender;

    private Integer targetAge;

    private String targetHealth;

    private String targetAppearance;

    private String targetCloth;

    private Date lostTime;
    /**
     * 经度,维度
     */
    private String lostPosition;
    /**
     * 家属联系方式
     */
    private String familyContact;
    /**
     * 备勤等级
     */
    private Integer level;

    private String videoUrl;

    private String imageUrl;
    /**
     * 已完成1，进行中0, 未开始-1
     */
    private Integer state;

    private Integer taskAcceptNum;

    private Date taskStartTime;

    private Date taskEndTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public Integer getTargetGender() {
        return targetGender;
    }

    public void setTargetGender(Integer targetGender) {
        this.targetGender = targetGender;
    }

    public Integer getTargetAge() {
        return targetAge;
    }

    public void setTargetAge(Integer targetAge) {
        this.targetAge = targetAge;
    }

    public String getTargetHealth() {
        return targetHealth;
    }

    public void setTargetHealth(String targetHealth) {
        this.targetHealth = targetHealth;
    }

    public String getTargetAppearance() {
        return targetAppearance;
    }

    public void setTargetAppearance(String targetAppearance) {
        this.targetAppearance = targetAppearance;
    }

    public String getTargetCloth() {
        return targetCloth;
    }

    public void setTargetCloth(String targetCloth) {
        this.targetCloth = targetCloth;
    }

    public Date getLostTime() {
        return lostTime;
    }

    public void setLostTime(Date lostTime) {
        this.lostTime = lostTime;
    }

    public String getLostPosition() {
        return lostPosition;
    }

    public void setLostPosition(String lostPosition) {
        this.lostPosition = lostPosition;
    }

    public String getFamilyContact() {
        return familyContact;
    }

    public void setFamilyContact(String familyContact) {
        this.familyContact = familyContact;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getTaskAcceptNum() {
        return taskAcceptNum;
    }

    public void setTaskAcceptNum(Integer taskAcceptNum) {
        this.taskAcceptNum = taskAcceptNum;
    }

    public Date getTaskStartTime() {
        return taskStartTime;
    }

    public void setTaskStartTime(Date taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public Date getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(Date taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

}