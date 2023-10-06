package com.accionti.arcon.arcon.security;

import com.accionti.arcon.arcon.security.jwt.JwtEntryPoint;
import com.accionti.arcon.arcon.security.jwt.JwtTokenFilter;
import com.accionti.arcon.arcon.security.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MainSecurity extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthService service;
    @Autowired
    private JwtEntryPoint entryPoint;

    @Bean
    public JwtTokenFilter jwtTokenFilter(){
        return new JwtTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(service).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    public AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }

    //antMatchers() sirve para proteger los path de nuestra aplicación
    //Revisar después
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api-arcon/auth/login","/api-arcon/auth/").permitAll()
                .antMatchers("/api-arcon/contact/**").permitAll()
                .antMatchers("/api-arcon/recovery/*").permitAll()
                .antMatchers(HttpMethod.GET, "/api-arcon/user/*","/api-arcon/user/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api-arcon/user/*","/api-arcon/user/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/api-arcon/user/*","/api-arcon/user/**").permitAll()
                .antMatchers(HttpMethod.PATCH,"/api-arcon/user/*","/api-arcon/user/**").permitAll()

                .antMatchers(HttpMethod.GET, "/api-arcon/site/*", "/api-arcon/site/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api-arcon/site/*", "/api-arcon/site/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api-arcon/site/*", "/api-arcon/site/**").permitAll()
                .antMatchers(HttpMethod.PATCH, "/api-arcon/site/*", "/api-arcon/site/**").permitAll()

                .antMatchers(HttpMethod.GET, "/api-arcon/role/*","/api-arcon/role/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api-arcon/role/*","/api-arcon/role/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/api-arcon/role/*","/api-arcon/role/**").permitAll()
                .antMatchers(HttpMethod.PATCH,"/api-arcon/role/*","/api-arcon/role/**").permitAll()

                .antMatchers(HttpMethod.GET, "/api-arcon/area/*","/api-arcon/area/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api-arcon/area/*","/api-arcon/area/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/api-arcon/area/*","/api-arcon/area/**").permitAll()
                .antMatchers(HttpMethod.PATCH,"/api-arcon/area/*","/api-arcon/area/**").permitAll()

                .antMatchers(HttpMethod.GET, "/api-arcon/archive/*","/api-arcon/archive/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api-arcon/archive/*","/api-arcon/archive/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/api-arcon/archive/*","/api-arcon/archive/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/api-arcon/archive/*","/api-arcon/archive/**").permitAll()

                .antMatchers(HttpMethod.GET, "/api-arcon/apartado/*","/api-arcon/apartado/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api-arcon/apartado/*","/api-arcon/apartado/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/api-arcon/apartado/*","/api-arcon/apartado/**").permitAll()
                .antMatchers(HttpMethod.PATCH,"/api-arcon/apartado/*","/api-arcon/apartado/**").permitAll()

                .antMatchers(HttpMethod.GET, "/api-arcon/access/*","/api-arcon/access/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api-arcon/access/*","/api-arcon/access/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(entryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
//Developed by: Jose Eduardo Arroyo Palafox and Alejandro Morellano Alvarez