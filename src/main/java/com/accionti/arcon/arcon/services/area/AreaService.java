package com.accionti.arcon.arcon.services.area;

import com.accionti.arcon.arcon.models.area.Area;
import com.accionti.arcon.arcon.models.area.AreaRepository;
import com.accionti.arcon.arcon.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AreaService {
    @Autowired
    private AreaRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Area>> getAll(){
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "OK"
        );
    }

    public CustomResponse<Area> getOne(Long id) {
        Optional<Area> area = repository.findById(id);
        if (area.isEmpty()) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Registro no existe."
            );
        }
        return new CustomResponse<>(
                area.get(),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Area> insert(Area area) {
        return new CustomResponse<>(
                this.repository.saveAndFlush(area),
                false,
                200,
                "Área registrada"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Area> update(Area area) {
        if (!this.repository.existsById(area.getId())) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "El area no existe"
            );
        }
        return new CustomResponse<>(
                this.repository.saveAndFlush(area),
                false,
                200,
                "Area Actualizada!"
        );
    }

}
//Developed by: Jose Eduardo Arroyo Palafox and Alejandro Morellano Alvarez