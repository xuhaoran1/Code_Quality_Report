package com.save.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Clue)实体类
 *
 * @author makejava
 * @since 2021-01-05 10:58:00
 */
public class Clue implements Serializable {
    private static final long serialVersionUID = -21794361702465487L;

    private Integer id;

    private Integer groupId;

    private String content;

    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
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

}