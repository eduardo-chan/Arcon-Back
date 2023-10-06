package com.accionti.arcon.arcon.models.apartado;

import com.accionti.arcon.arcon.models.site.Site;
import com.accionti.arcon.arcon.models.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "apartados")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Apartado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 45)
    private String name;

    @OneToMany(mappedBy = "apartado")
    @JsonIgnore
    private List<Site> siteList;

    // Constructor que acepta un String para el id y devuelve una instancia de Apartado
    public Apartado(String id) {
        this.id = Long.parseLong(id);
    }
}

//Developed by: Jose Eduardo Arroyo Palafox and Alejandro Morellano Alvarez