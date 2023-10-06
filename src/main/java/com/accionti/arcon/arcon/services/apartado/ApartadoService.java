package com.accionti.arcon.arcon.services.apartado;


import com.accionti.arcon.arcon.models.apartado.Apartado;
import com.accionti.arcon.arcon.models.apartado.ApartadoRepository;
import com.accionti.arcon.arcon.models.area.Area;
import com.accionti.arcon.arcon.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ApartadoService {

    @Autowired
    private ApartadoRepository repository;

    //getall
    @Transactional(readOnly = true)
    public CustomResponse<List<Apartado>> getAll(){
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "OK"
        );
    }

    //get one

    public CustomResponse<Apartado> getOne(Long id) {
        Optional<Apartado> apartado = repository.findById(id);
        if (apartado.isEmpty()) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Registro no existe."
            );
        }
        return new CustomResponse<>(
                apartado.get(),
                false,
                200,
                "Ok"
        );
    }

    //insert

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Apartado> insert(Apartado apartado) {
        return new CustomResponse<>(
                this.repository.saveAndFlush(apartado),
                false,
                200,
                "Área registrada"
        );
    }

    //update

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Apartado> update(Apartado apartado) {
        if (!this.repository.existsById(apartado.getId())) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "El area no existe"
            );
        }
        return new CustomResponse<>(
                this.repository.saveAndFlush(apartado),
                false,
                200,
                "Area Actualizada!"
        );
    }
}

//Developed by: Jose Eduardo Arroyo Palafox and Alejandro Morellano Alvarez
