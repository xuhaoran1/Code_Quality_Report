package com.save.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (UploadImage)实体类
 *
 * @author makejava
 * @since 2021-01-06 15:03:07
 */
public class UploadImage implements Serializable {
    private static final long serialVersionUID = -55187184778307185L;
    
    private Integer id;
    
    private String url;
    
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}