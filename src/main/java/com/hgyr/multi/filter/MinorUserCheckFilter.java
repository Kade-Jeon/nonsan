/*
package com.hgyr.multi.filter;

import com.hgyr.multi.dto.UserDto;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

public class MinorUserCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        UserDto userInfo = (UserDto) session.getAttribute("user");

        if(getAge(userInfo.getBirthDate()) < 19){
            httpServletResponse.setContentType("text/html");
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.getWriter().println("<script>alert('미성년자는 이용이 불가능한 서비스 입니다.\n만 19세 이상 입장 가능.'); document.location='/hgyr';</script>");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private int getAge(Date birthDate){
        return Period.between(birthDate.toLocalDate(), LocalDate.now()).getYears();
    }
}
*/
