package com.example.mongatest.filter;

import com.example.mongatest.model.ApplicationUser;
import com.example.mongatest.repos.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Optional;

@Component
@AllArgsConstructor
public class MyFilter extends OncePerRequestFilter {
    UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String userId = request.getHeader("USER_ID");
        //NO USER_ID HEADER
        if (userId == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        //USER NOT FOUND
        Optional<ApplicationUser> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        ApplicationUser found = user.get();
        //ADMIN PERMIT ANYTHING
        if (!found.getPermissions().contains("ADMIN")) {
            //NOT ADMIN ADMINPANEL REJECT
            if (request.getRequestURI().contains("/adminpanel/")) {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                return;
            }
            if (!found.getPermissions().contains(request.getMethod())) {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
