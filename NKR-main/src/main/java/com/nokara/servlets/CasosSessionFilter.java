package com.nokara.servlets;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/subPaginas/casos.xhtml")
public class CasosSessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        var session = httpRequest.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            ((HttpServletResponse) response).sendRedirect(httpRequest.getContextPath() + "/login.jsp");
            return;
        }
        chain.doFilter(request, response);
    }
}
