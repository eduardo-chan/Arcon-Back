package com.accionti.arcon.arcon.dto.apartado;


import com.accionti.arcon.arcon.models.apartado.Apartado;
import com.accionti.arcon.arcon.models.site.Site;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApartadoDto {
    private Long id;
    private String name;
    private List<Site> sites;

    public Apartado getApartado() {
        return new Apartado(
                getId(),
                getName(),
                getSites()
        );
    }
}

//Developed by: Jose Eduardo Arroyo Palafox and Alejandro Morellano Alvarez