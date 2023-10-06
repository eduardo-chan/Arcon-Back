package com.accionti.arcon.arcon.security.services;

import com.accionti.arcon.arcon.security.model.UserAuth;
import com.accionti.arcon.arcon.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService implements UserDetailsService {
    @Autowired
    UserService service;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return UserAuth.build(service.getUserByEmail(email));
    }
}
//Developed by: Jose Eduardo Arroyo Palafox and Alejandro Morellano Alvarez