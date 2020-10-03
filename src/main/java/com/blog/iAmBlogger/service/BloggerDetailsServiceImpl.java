package com.blog.iAmBlogger.service;

import com.blog.iAmBlogger.dto.MyBlogs;
import com.blog.iAmBlogger.dto.User;
import com.blog.iAmBlogger.repositories.MyBlogRepository;
import com.blog.iAmBlogger.repositories.UserLoginRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class BloggerDetailsServiceImpl implements BlogDetailService  {

    private final Logger logger = LoggerFactory.getLogger(BloggerDetailsServiceImpl.class);
    @Autowired
    private MyBlogRepository myBlogRepository;

    @Autowired
    UserLoginRepository userLoginRepository;
    /**
     *Get Blog By ID to edit
     * @param request contains blogId
     **/
    @Override
    public MyBlogs findByBlogId(String request) {
        logger.info("Inside Finding blog by id ");
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String,Object> requestMap = mapper.readValue(request, HashMap.class);
            return  myBlogRepository.findOneByBlogId((Long) requestMap.get("blog_id"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Save user blog
     * @param myBlogsReq contains blog details
     **/
    @Override
    public void save(String myBlogsReq,String userName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            MyBlogs myBlogs = mapper.readValue(myBlogsReq, MyBlogs.class);
            myBlogs.setUserId(userName);
            myBlogRepository.save(myBlogs);
        } catch (Exception e) {
            throw new RuntimeException("Got trouble while saving blog");
        }
    }

    /**
     * Get all the passengers.
     *
     * @param pageNo the pagination information.
     * @param pageSize
     * @return the list of entities.
     */
    @Override
    @Transactional
    public List<MyBlogs> findAll(int pageNo,int pageSize,String userId) {
        logger.info("Request to get all My Blogs");
        try {

            Pageable pageable = PageRequest.of((pageNo<=0&& pageNo==pageSize ? 1 : pageNo)-1,(pageSize<=0?5:pageSize));
            Page<MyBlogs> pagedResult= myBlogRepository.findAll(pageable);
            if(pagedResult.hasContent()) {
                /**
                 * Here we are filtering blogs by user name from session
                 * */
                return pagedResult.getContent().stream().filter(obj -> obj.getUserId().equals(userId)).collect(Collectors.toList());
            } else {
                return new ArrayList<MyBlogs>();
            }
        } catch (Exception e) {
            throw new RuntimeException("Not able to process request");
        }
    }

    /**
     * Method will update user blog based on log in user and created by
     * @param updateRequest
     * @param userName Login user name
     * @return result string
     * */
    @Override
    public String updateMyBlog(String updateRequest,String userName){
        logger.info("Inside update blog by authorised user ");
        try {
            ObjectMapper mapper = new ObjectMapper();
            MyBlogs myBlogsNew = mapper.readValue(updateRequest, MyBlogs.class);

            Optional<MyBlogs> myBlogsOld = myBlogRepository.findByBlogIdAndUserId(myBlogsNew.getBlogId(),userName) ;
            if(myBlogsOld.isPresent()){
                myBlogsNew.setUserId(userName);
                myBlogRepository.save(myBlogsNew);
                return "Blog Updated";
            }
            return "You are not authorized to update this blog";
        } catch (Exception e){
            throw new RuntimeException("Something went wrong");
        }
    }
}
