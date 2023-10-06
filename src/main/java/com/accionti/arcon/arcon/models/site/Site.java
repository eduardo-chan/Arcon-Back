package com.accionti.arcon.arcon.models.site;

import com.accionti.arcon.arcon.models.apartado.Apartado;
import com.accionti.arcon.arcon.models.area.Area;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sites")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nameClient;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String userSite;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String ubicacion;
    @ManyToOne
    @JoinColumn(name = "apartado_id",nullable = true)
    private Apartado apartado;
    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 1")
    private Boolean status;

}
//Developed by: Jose Eduardo Arroyo Palafox and Alejandro Morellano Alvarez