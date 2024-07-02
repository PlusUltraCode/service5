package com.example.filter.filter;


import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class LoggerFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //진입전
        log.info("진입전");
        filterChain.doFilter(servletRequest,servletResponse);

        log.info("진입후");
        //진입후
    }
}
