package com.backend.server.security.config;

import com.backend.server.service.UserService;
import com.backend.server.filter.AuthenticationFilter;
import com.backend.server.filter.AuthorizationFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
// Http Security
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManagerBean());
        authenticationFilter.setFilterProcessesUrl("/api/login");
        // STATELESS means that spring security will not create any session because we want to use JWT
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilter(new AuthenticationFilter(authenticationManagerBean()));
        http.authorizeRequests().antMatchers("/api/login/**").permitAll();
        http.addFilter(authenticationFilter);
        http.addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable().authorizeRequests().antMatchers("/api/registration/**").permitAll().anyRequest()
                .authenticated().and().formLogin();
            
    }
    
    @Bean
    @Override
    // Collects body from the Auth Request and processes it.
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    } 

    
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }
}
