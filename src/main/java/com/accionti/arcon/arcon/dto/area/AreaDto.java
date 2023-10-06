package com.accionti.arcon.arcon.dto.area;

import com.accionti.arcon.arcon.models.archive.Archive;
import com.accionti.arcon.arcon.models.area.Area;
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
public class AreaDto {
    private Long id;
    private String name;
    private List<User> users;
    private List<Archive> archives;

    public Area getArea() {
        return new Area(
                getId(),
                getName(),
                getUsers(),
                getArchives()
        );
    }
}
//Developed by: Jose Eduardo Arroyo Palafox and Alejandro Morellano Alvarez