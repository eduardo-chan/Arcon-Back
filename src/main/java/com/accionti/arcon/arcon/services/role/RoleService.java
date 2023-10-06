package com.accionti.arcon.arcon.services.role;

import com.accionti.arcon.arcon.models.role.Role;
import com.accionti.arcon.arcon.models.role.RoleRepository;
import com.accionti.arcon.arcon.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository repository;

    public CustomResponse<List<Role>> getAll() {
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "Ok"
        );
    }

    public CustomResponse<Role> getOne(Long id) {
        Optional<Role> role = repository.findById(id);
        if (role.isEmpty()) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Registro no existe."
            );
        }
        return new CustomResponse<>(
                role.get(),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Role> insert(Role role) {
        return new CustomResponse<>(
                this.repository.saveAndFlush(role),
                false,
                200,
                "Rol registrado"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Role> update(Role role){
        if (!this.repository.existsById(role.getId())){
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Role inexistente"
            );
        }
        return new CustomResponse<>(
                this.repository.saveAndFlush(role),
                false,
                200,
                "Role actualizado"
        );
    }
}
//Developed by: Jose Eduardo Arroyo Palafox and Alejandro Morellano Alvarez