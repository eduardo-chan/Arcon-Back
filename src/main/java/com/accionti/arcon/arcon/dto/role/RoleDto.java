package com.accionti.arcon.arcon.dto.role;

import com.accionti.arcon.arcon.models.role.Role;
import com.accionti.arcon.arcon.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleDto {
    private Long id;
    private String name;
    private List<User> users;

    public Role getRole() {
        return new Role(
                getId(),
                getName(),
                getUsers()
        );
    }
}
//Developed by: Jose Eduardo Arroyo Palafox and Alejandro Morellano Alvarez