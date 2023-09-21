package com.hgyr.multi.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

public class LogFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();
        String uuid = UUID.randomUUID().toString();

        try{
            logger.info("[Port:1888]REQUEST [{}], [{}]", uuid, requestURI);
            filterChain.doFilter(servletRequest, servletResponse);
        }catch(Exception e){
            logger.info("[Port:1888]REQUEST EXCEPTION [{}], [{}], [{}]", uuid, requestURI, e.getMessage());
        }
    }
}
