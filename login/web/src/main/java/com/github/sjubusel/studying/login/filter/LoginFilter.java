package com.github.sjubusel.studying.login.filter;

import com.github.sjubusel.studying.login.AuthUserService;
import com.github.sjubusel.studying.login.Util;
import com.github.sjubusel.studying.login.impl.DefAuthUserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(value = {"/resources", "/resources.jsp"})
public class LoginFilter implements Filter {
    private AuthUserService authUserService = DefAuthUserService.getInstance();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        Cookie cookie = Util.getCookieIfExists(request.getCookies(), "logged");
        if (cookie != null && Util.ifCookieIsValid(cookie)) {
            if (session.getAttribute("authUser") == null) {
                session.setAttribute("authUser", authUserService.login(cookie.getValue()));
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            Util.sendRedirect("login", request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
