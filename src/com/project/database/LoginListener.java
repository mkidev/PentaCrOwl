package com.project.database;

/**
 * Created by arash on 09.09.2015.
 */
import com.project.crawler.util.HibernateUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class LoginListener extends WebSecurityConfigurerAdapter implements javax.servlet.Filter {
    public static final String AUTHENTICATION_HEADER = "Authorization";
    FilterConfig filterConfig = null;


    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        this.filterConfig = arg0;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filter) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String authCredentials = httpServletRequest
                    .getHeader(AUTHENTICATION_HEADER);

            // better injected
            LoginService authenticationService = new LoginServiceImpl(HibernateUtil.getSessionFactory());

            boolean authenticationStatus = false;
            try {
                authenticationStatus = authenticationService
                        .authenticate(authCredentials);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("authenticationStatus = " + authenticationStatus);

            if (authenticationStatus) {
                filter.doFilter(request, response);
            } else {
                if (response instanceof HttpServletResponse) {
                    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                    httpServletResponse
                            .setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }
        }
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll().anyRequest().fullyAuthenticated()
                .and()
                .httpBasic();

        http.formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll();
    }
}
