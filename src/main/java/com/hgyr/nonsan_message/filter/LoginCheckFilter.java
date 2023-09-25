package com.hgyr.nonsan_message.filter;

import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCheckFilter implements Filter {

    private static final String[] whiteList = {"/", "/hgyr", "/hgyr/join", "/hgyr/login", "/error", "/style.css","/img/*"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String requestURI = httpServletRequest.getRequestURI();
        if(isLoginCheckPath(requestURI)){
            HttpSession session = httpServletRequest.getSession();
            if(session.getAttribute("user") == null){
                httpServletResponse.setContentType("text/html");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().println("<script>alert('로그인이 필요한 서비스 입니다.'); history.back();</script>");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean isLoginCheckPath(String requestURI){
        return !PatternMatchUtils.simpleMatch(whiteList, requestURI);
    }
}
