package com.accionti.arcon.arcon.models.area;

import com.accionti.arcon.arcon.models.archive.Archive;
import com.accionti.arcon.arcon.models.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "areas")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 45)
    private String name;

    @OneToMany(mappedBy = "area")
    @JsonIgnore
    private List<User> userList;

    @OneToMany(mappedBy = "area")
    @JsonIgnore
    private List<Archive> archiveList;

    // Constructor que acepta un String para el id y devuelve una instancia de Apartado
    public Area(String id) {
        this.id = Long.parseLong(id);
    }




}
//Developed by: Jose Eduardo Arroyo Palafox and Alejandro Morellano Alvarez