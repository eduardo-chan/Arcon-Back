package com.accionti.arcon.arcon.security.controller.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdatePasswordDto {
    @NotEmpty
    private String email;
    @NotEmpty
    private String newPassword;
    @NotEmpty
    private String secretPass;
}
//Developed by: Jose Eduardo Arroyo Palafox and Alejandro Morellano Alvarez