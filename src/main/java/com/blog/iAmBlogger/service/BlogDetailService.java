package com.blog.iAmBlogger.service;

import com.blog.iAmBlogger.dto.MyBlogs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogDetailService {
    public MyBlogs findByBlogId(String request);
    public void save(String  myBlogsReq,String userName);
    public List<MyBlogs> findAll(int pageNo,int pageSize,String userId);
    public String updateMyBlog(String updateRequest,String userName);

}
