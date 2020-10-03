package com.blog.iAmBlogger.repositories;

import com.blog.iAmBlogger.dto.MyBlogs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyBlogRepository extends JpaRepository<MyBlogs,Long> {

    public MyBlogs saveAndFlush(MyBlogs myBlogs);
    MyBlogs findOneByBlogId(Long id);

    Page<MyBlogs> findAll(Pageable pageable);

    Optional<MyBlogs> findByBlogIdAndUserId(Long blogId, String userId);
}
