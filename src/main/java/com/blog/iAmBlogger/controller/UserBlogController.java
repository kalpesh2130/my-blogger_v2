package com.blog.iAmBlogger.controller;

import com.blog.iAmBlogger.dto.ResponseApi;
import com.blog.iAmBlogger.service.BlogDetailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/blog")
public class UserBlogController {

    @Autowired
    private BlogDetailService blogDetailService;


    @RequestMapping(method = RequestMethod.POST,value = "/add")
    private ResponseEntity<?> addBlog(@RequestBody String request, Principal principal){
        ResponseApi responseApi = new ResponseApi();
            blogDetailService.save(request, principal.getName());
        responseApi.setData("");
        responseApi.setMessage("success");
        responseApi.setStatus("s");
        return new ResponseEntity<>(responseApi,HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/get_blogs")
    private ResponseEntity<?> getBlogs(@RequestParam int pageNo,@RequestParam int pageSize,Principal principal){
        ResponseApi responseApi = new ResponseApi();
        try {
            responseApi.setData(blogDetailService.findAll(pageNo,pageSize,principal.getName()));
            responseApi.setMessage("success");
            responseApi.setStatus("s");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(responseApi,HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/update")
    private ResponseEntity updateBlog(@RequestBody String request, Principal principal){
        ResponseApi responseApi = new ResponseApi();
        responseApi.setData("");
        responseApi.setMessage(blogDetailService.updateMyBlog(request, principal.getName()));
        responseApi.setStatus("s");
        return new ResponseEntity<>(responseApi,HttpStatus.OK);
    }

}
