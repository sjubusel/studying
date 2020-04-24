package com.github.sjubusel.studying.login.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(value = {"/index", "/index.jsp", "/resources", "/resources.jsp",
        "/login", "/login.jsp", "/register", "/register.jsp"})
public class LanguageFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    /**
     * an overridden method that verifies whether session contains an attribute "language", which is to be used in order
     * to use appropriate (repository path: login/web/src/main/resources/resourceBundle.properties)
     *
     * @param servletRequest  a request that is to be forwarded to servlets/jsps, that are being filtered by this filter
     * @param servletResponse a request that is to be forwarded to servlets/jsps, that are being filtered by this filter
     * @param filterChain     a chain of further filters and servlets / jsps
     * @throws IOException      if something goes wrong
     * @throws ServletException if something goes wrong
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        if (session.getAttribute("language") == null) {
            session.setAttribute("language", "en_GB");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
