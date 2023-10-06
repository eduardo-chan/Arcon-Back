package com.accionti.arcon.arcon.controllers.area;

import com.accionti.arcon.arcon.dto.area.AreaDto;
import com.accionti.arcon.arcon.models.area.Area;
import com.accionti.arcon.arcon.services.area.AreaService;
import com.accionti.arcon.arcon.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-arcon/area/")
@CrossOrigin(origins = {"*"})
public class AreaController {
    @Autowired
    private AreaService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Area>>> getAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Area>> getOne(@PathVariable("id")Long id){
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Area>> insert(@Valid @RequestBody AreaDto areaDto){
        return new ResponseEntity<>(
                this.service.insert(areaDto.getArea()),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<CustomResponse<Area>> update(
            @RequestBody AreaDto areaDto, @Valid BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.service.update(areaDto.getArea()),
                HttpStatus.CREATED
        );
    }

}
//Developed by: Jose Eduardo Arroyo Palafox and Alejandro Morellano Alvarez