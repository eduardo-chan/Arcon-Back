package com.accionti.arcon.arcon.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {
    private final static Logger LOGGER = LoggerFactory.getLogger(JwtTokenFilter.class);
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException
            authException) throws IOException, ServletException {
        LOGGER.error("Error comence -> UNAUTHORIZED");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No autorizado");
    }
}
//Developed by: Jose Eduardo Arroyo Palafox and Alejandro Morellano Alvarez