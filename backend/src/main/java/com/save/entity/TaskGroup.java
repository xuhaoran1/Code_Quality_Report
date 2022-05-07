package com.save.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (TaskGroup)实体类
 *
 * @author makejava
 * @since 2021-01-05 15:39:04
 */
public class TaskGroup implements Serializable {
    private static final long serialVersionUID = 466897769229658782L;

    private Integer id;

    private Integer taskId;

    private Integer mem1Id;

    private Integer mem2Id;

    private Date createTime;


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

    public Integer getMem1Id() {
        return mem1Id;
    }

    public void setMem1Id(Integer mem1Id) {
        this.mem1Id = mem1Id;
    }

    public Integer getMem2Id() {
        return mem2Id;
    }

    public void setMem2Id(Integer mem2Id) {
        this.mem2Id = mem2Id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}