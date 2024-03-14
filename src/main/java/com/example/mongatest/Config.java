package com.example.mongatest;

import com.example.mongatest.filter.MyFilter;
import com.example.mongatest.repos.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.filter.ApplicationContextHeaderFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.logging.Filter;

@Configuration
public class Config {
    @Autowired
    UserRepository userRepository;
    public FilterRegistrationBean<MyFilter> authFilter() {
        FilterRegistrationBean<MyFilter> filterBean = new FilterRegistrationBean<>();
        filterBean.setFilter(new MyFilter(userRepository));
        return filterBean;
    }
}
