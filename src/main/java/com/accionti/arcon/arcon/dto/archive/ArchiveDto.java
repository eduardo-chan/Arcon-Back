package com.accionti.arcon.arcon.dto.archive;

import com.accionti.arcon.arcon.models.archive.Archive;
import com.accionti.arcon.arcon.models.area.Area;
import com.accionti.arcon.arcon.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArchiveDto {
    private Long id;
    private String name;
    private String description;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date FechaCarga;
    private String drive;
    private MultipartFile archivo; // Cambiar el tipo a MultipartFile en lugar de String
    private Area area;
    private Boolean status;
//    private User user;

    public Archive getArchive() {
        return new Archive(
                getId(),
                getName(),
                getDescription(),
                getFechaCarga(),
                getDrive(),
                getArchivo().getOriginalFilename().getBytes(), // Obtener el nombre del archivo con getOriginalFilename()
                getArea(),
                getStatus()
        );
    }
}
//Developed by: Jose Eduardo Arroyo Palafox and Alejandro Morellano Alvarez