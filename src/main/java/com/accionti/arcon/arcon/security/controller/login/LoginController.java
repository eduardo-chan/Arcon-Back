package com.accionti.arcon.arcon.security.controller.login;

import com.accionti.arcon.arcon.models.user.User;
import com.accionti.arcon.arcon.security.controller.dtos.LoginDto;
import com.accionti.arcon.arcon.security.jwt.JwtProvider;
import com.accionti.arcon.arcon.services.user.UserService;
import com.accionti.arcon.arcon.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api-arcon/auth/")
@CrossOrigin(origins = {"*"})
public class LoginController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtProvider provider;
    @Autowired
    private UserService service;

    @PostMapping("/login")
    public ResponseEntity<CustomResponse<Map<String, Object>>> login(
            @Valid @RequestBody LoginDto login) {
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = provider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User usuario = service.getUserByEmail(login.getEmail());
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("user", userDetails);
        data.put("usuario", usuario);
        return new ResponseEntity<>(
                new CustomResponse<>(
                        data,
                        false,
                        200,
                        "Ok"
                ),
                HttpStatus.OK
        );
    }
}
//Developed by: Jose Eduardo Arroyo Palafox and Alejandro Morellano Alvarez