package com.save.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Route)实体类
 *
 * @author makejava
 * @since 2021-01-11 14:54:28
 */
public class Route implements Serializable {
    private static final long serialVersionUID = -82636463665317798L;
    
    private Integer id;
    
    private Integer taskId;
    /**
    * 队员的经纬度
    */
    private String content;
    
    private Date createTime;
    
    private Integer volId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getVolId() {
        return volId;
    }

    public void setVolId(Integer volId) {
        this.volId = volId;
    }

}