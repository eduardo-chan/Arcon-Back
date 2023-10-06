package com.accionti.arcon.arcon.controllers.apartado;


import com.accionti.arcon.arcon.dto.apartado.ApartadoDto;
import com.accionti.arcon.arcon.dto.area.AreaDto;
import com.accionti.arcon.arcon.models.apartado.Apartado;
import com.accionti.arcon.arcon.models.area.Area;
import com.accionti.arcon.arcon.services.apartado.ApartadoService;
import com.accionti.arcon.arcon.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-arcon/apartado/")
@CrossOrigin(origins = {"*"})
public class ApartadoController {

    @Autowired
    private ApartadoService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Apartado>>> getAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Apartado>> getOne(@PathVariable("id")Long id){
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Apartado>> insert(@Valid @RequestBody ApartadoDto apartadoDto){
        return new ResponseEntity<>(
                this.service.insert(apartadoDto.getApartado()),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<CustomResponse<Apartado>> update(
            @RequestBody ApartadoDto apartadoDto, @Valid BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.service.update(apartadoDto.getApartado()),
                HttpStatus.CREATED
        );
    }
}

//Developed by: Jose Eduardo Arroyo Palafox and Alejandro Morellano Alvarez
