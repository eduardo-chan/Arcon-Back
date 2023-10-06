package com.accionti.arcon.arcon.controllers.site;

import com.accionti.arcon.arcon.dto.site.SiteDto;
import com.accionti.arcon.arcon.models.site.Site;
import com.accionti.arcon.arcon.services.site.SiteService;
import com.accionti.arcon.arcon.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api-arcon/site/")
@CrossOrigin(origins = {"*"})
public class SiteController {
    @Autowired
    private SiteService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Site>>> getAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Site>> getOne(@PathVariable("id")Long id){
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Site>> insert(@Valid @RequestBody SiteDto siteDto){
        return new ResponseEntity<>(
                this.service.insert(siteDto.getSite()),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<CustomResponse<Site>> update(
            @RequestBody SiteDto siteDto, @Valid BindingResult result
            ){
        if (result.hasErrors()){
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.service.update(siteDto.getSite()),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/")
    public ResponseEntity<CustomResponse<Boolean>> enableOrDisable(@RequestBody SiteDto siteDto){
        return new ResponseEntity<>(
                this.service.changeStatus(siteDto.getSite()),
                HttpStatus.OK
        );
    }

}
//Developed by: Jose Eduardo Arroyo Palafox and Alejandro Morellano Alvarez