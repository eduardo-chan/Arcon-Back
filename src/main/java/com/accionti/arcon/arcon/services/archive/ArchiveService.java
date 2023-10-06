package com.accionti.arcon.arcon.services.archive;

import com.accionti.arcon.arcon.models.archive.Archive;
import com.accionti.arcon.arcon.models.archive.ArchiveRepository;
import com.accionti.arcon.arcon.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ArchiveService {
    @Autowired
    private ArchiveRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Archive>> getAll(){
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "OK"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<Archive> getOne(Long id){
        Optional<Archive> archive = repository.findById(id);
        if (archive.isEmpty()){
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Registro no existe"
            );
        }
        return new CustomResponse<>(
                archive.get(),
                false,
                200,
                "OK"
        );
    }

    private String uploadDirectory =  ".//src//main//resources//files//";
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Archive> insert(Archive archive, MultipartFile file) {
        Optional<Archive> exists = repository.findByName(archive.getName());
        if (exists.isPresent()) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Archivo ya registrado"
            );
        }

        if (file == null || file.isEmpty()) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Archivo inválido o vacío"
            );
        }

        String fileName = file.getOriginalFilename();

        Path filePath = Paths.get(uploadDirectory, fileName);
        try {
            Files.write(filePath, file.getBytes());
        } catch (IOException e) {
            return new CustomResponse<>(
                    null,
                    true,
                    500,
                    "Error al guardar el archivo"
            );
        }

        archive.setArchivo(fileName.getBytes());
        Archive savedArchive = repository.save(archive);
        return new CustomResponse<>(
                savedArchive,
                false,
                200,
                "Archivo registrado"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Archive> update(Archive archive, MultipartFile file){
        if (!repository.existsById(archive.getId())){
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "No existe el Archivo"
            );
        }

        if (file == null || file.isEmpty()) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Archivo inválido o vacío"
            );
        }

        String fileName = file.getOriginalFilename();

        Path filePath = Paths.get(uploadDirectory, fileName);
        try {
            Files.write(filePath, file.getBytes());
        } catch (IOException e) {
            return new CustomResponse<>(
                    null,
                    true,
                    500,
                    "Error al guardar el archivo"
            );
        }

        archive.setArchivo(fileName.getBytes());
//        Archive savedArchive = repository.save(archive);


        Archive updatedArchive = repository.saveAndFlush(archive);
        return new CustomResponse<>(
                updatedArchive,
                false,
                200,
                "Archivo actualizado"
        );
    }


    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> deleteArchiveById(Long archiveId) {
        if (!this.repository.existsById(archiveId)) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "El archivo no existe"
            );
        }

        this.repository.deleteById(archiveId);

        return new CustomResponse<>(
                true,
                false,
                200,
                "Archivo eliminado exitosamente"
        );


    }
}
//Developed by: Jose Eduardo Arroyo Palafox and Alejandro Morellano Alvarez