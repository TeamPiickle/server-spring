package com.team.piickle.auth.admin;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        var session = request.getSession();
        boolean isAdmin = Optional.ofNullable((Boolean) session.getAttribute("isAdmin")).orElse(false);
        if (isAdmin) {
            return true;
        }
        response.sendRedirect("/admin/login");
        return false;
    }
}
