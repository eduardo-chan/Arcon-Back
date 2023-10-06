package com.accionti.arcon.arcon.controllers.archive;

import com.accionti.arcon.arcon.dto.archive.ArchiveDto;
import com.accionti.arcon.arcon.dto.user.UserDto;
import com.accionti.arcon.arcon.models.archive.Archive;
import com.accionti.arcon.arcon.services.archive.ArchiveService;
import com.accionti.arcon.arcon.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api-arcon/archive/")
@CrossOrigin(origins = {"*"})
public class ArchiveController {
    @Autowired
    private ArchiveService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Archive>>> getAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Archive>> getOne(@PathVariable("id")Long id){
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }
    @PutMapping("/")
    public ResponseEntity<CustomResponse<Archive>> update(
            @ModelAttribute ArchiveDto archiveDto, @Valid BindingResult result){
        MultipartFile file = archiveDto.getArchivo();
        if (result.hasErrors()){
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.service.update(archiveDto.getArchive(), file),
                HttpStatus.CREATED
        );
    }


    @PostMapping("/")
    public ResponseEntity<CustomResponse<Archive>> insert(@Valid @ModelAttribute ArchiveDto archiveDto) {
        MultipartFile file = archiveDto.getArchivo();
        return new ResponseEntity<>(
                this.service.insert(archiveDto.getArchive(), file),
                HttpStatus.CREATED
        );
    }


    @DeleteMapping("/")
    public ResponseEntity<CustomResponse<Boolean>> deleteArchiveById(@ModelAttribute ArchiveDto archiveDto) throws IOException {
        return new ResponseEntity<>(
                this.service.deleteArchiveById(archiveDto.getId()),
                HttpStatus.OK
        );
    }






}
//Developed by: Jose Eduardo Arroyo Palafox and Alejandro Morellano Alvarez