package com.save.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (FamClue)实体类
 *
 * @author makejava
 * @since 2021-01-06 08:42:32
 */
public class FamClue implements Serializable {
    private static final long serialVersionUID = -29320053131424741L;

    private Integer id;

    private Integer taskId;

    private Date createTime;

    private String content;


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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}