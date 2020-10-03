package com.blog.iAmBlogger.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Entity
@Table(name = "my_blogs")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MyBlogs {
    @Id
    @Column(name = "blog_id")
    private Long blogId;

    @Column(name = "blog_details")
    private String blogDetails;

    @Column(name = "created_by")
    private String userId;

    public MyBlogs() {
    }

    public MyBlogs( String blogDetails,String userId) {
        this.blogDetails = blogDetails;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getBlogDetails() {
        return blogDetails;
    }

    public void setBlogDetails(String blogDetails) {
        this.blogDetails = blogDetails;
    }
}

