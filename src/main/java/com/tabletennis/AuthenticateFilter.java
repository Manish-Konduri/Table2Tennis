package com.tabletennis;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName="Authentication",urlPatterns = "/authenticate")
public class AuthenticateFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String Email = request.getParameter("Email");
        String pwd = request.getParameter("pwd");
       // System.out.println(Email+"---------");
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            Role role = new Role();
            String s = cookies[0].getValue();
//            System.out.println(cookies[0]);
//            System.out.println(s);
            String roleOfLogin = role.role(s);

            if(roleOfLogin.equalsIgnoreCase("Player")){
                System.out.println(roleOfLogin);
                RequestDispatcher dispatcher = request.getRequestDispatcher("afterLogin.html");
                dispatcher.forward(request, response);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
