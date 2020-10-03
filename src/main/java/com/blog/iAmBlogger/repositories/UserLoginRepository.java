package com.blog.iAmBlogger.repositories;

import com.blog.iAmBlogger.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserLoginRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
