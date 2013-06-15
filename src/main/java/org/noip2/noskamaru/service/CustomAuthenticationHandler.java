package org.noip2.noskamaru.service;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class CustomAuthenticationHandler extends SimpleUrlAuthenticationSuccessHandler{

@Override
public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws ServletException, IOException {
        String userTargetUrl = "/user/index.html";
        String adminTargetUrl = "/admin/index.html";
        String hrTargetUrl = "/hr/index.html";
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_ADMIN")) {
            getRedirectStrategy().sendRedirect(request, response, adminTargetUrl);
        }
        else if(roles.contains("ROLE_USER")) {
            getRedirectStrategy().sendRedirect(request, response, userTargetUrl);
        }
        else if(roles.contains("ROLE_HR")) {
            getRedirectStrategy().sendRedirect(request, response, hrTargetUrl);
        }
        else {
            super.onAuthenticationSuccess(request, response, authentication);
            return;
        }
 }

}