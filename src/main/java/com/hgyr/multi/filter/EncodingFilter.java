package com.hgyr.multi.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    
    /* 굳이 필요 없긴한데 필터 적용해보기 위해 추가한 인코딩 필터*/
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        filterChain.doFilter(request, response);
    }
}
