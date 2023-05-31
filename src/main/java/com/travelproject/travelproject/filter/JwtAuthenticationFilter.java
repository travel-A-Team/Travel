package com.travelproject.travelproject.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.travelproject.travelproject.provider.JwtTokenProvider;
import com.travelproject.travelproject.provider.UserToken;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider=jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            
            String jwt = parseToken(request);

            boolean hasJwt = jwt != null;
            if (!hasJwt) {
                filterChain.doFilter(request, response);
                return;
            }

            UserToken userToken = jwtTokenProvider.validate(jwt);
            
            if (userToken == null) {
                filterChain.doFilter(request, response);
                return;
            }


            AbstractAuthenticationToken authenticationToken = 
                new UsernamePasswordAuthenticationToken(userToken, null, AuthorityUtils.NO_AUTHORITIES);
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authenticationToken);

            SecurityContextHolder.setContext(securityContext);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        filterChain.doFilter(request, response);
       
    }

    private String parseToken(HttpServletRequest request) {

        //# Request Header 중 "Authorization" : "Bearer eyJh..."
        String token = request.getHeader("Authorization");

        boolean hasToken = token != null && !token.equalsIgnoreCase("null");
        if (!hasToken) return null;

        //@ "Bearer eyJh..."의 처음부터 값을 뽑아 내겠다.
        boolean isBearer = token.startsWith("Bearer ");
        if (!isBearer) return null;

        //@ "Bearer eyJh..." : "Bearer " 뒤부터 값을 가져오겠다.
        String jwt = token.substring(7);
        return jwt;

    }
    
}
