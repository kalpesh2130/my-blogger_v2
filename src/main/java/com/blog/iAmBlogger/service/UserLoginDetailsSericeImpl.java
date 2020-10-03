package com.blog.iAmBlogger.service;

import com.blog.iAmBlogger.dto.User;
import com.blog.iAmBlogger.repositories.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserLoginDetailsSericeImpl implements UserDetailsService {

    @Autowired
    UserLoginRepository userLoginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userLoginRepository.findByUsername(username);
        if(user ==null)
            throw new UsernameNotFoundException("User not available");
        return new UserDetailImpl(user) ;
    }


}
