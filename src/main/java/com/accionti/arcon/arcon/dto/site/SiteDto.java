package com.accionti.arcon.arcon.dto.site;

import com.accionti.arcon.arcon.models.apartado.Apartado;
import com.accionti.arcon.arcon.models.site.Site;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SiteDto {
    private Long id;
    private String nameClient;
    private String description;
    private String userSite;
    private String password;
    private String ubicacion;
    private Apartado apartado;
    private Boolean status;

    public Site getSite(){
        return new Site(
                getId(),
                getNameClient(),
                getDescription(),
                getUserSite(),
                getPassword(),
                getUbicacion(),
                getApartado(),
                getStatus()
        );
    }

}
//Developed by: Jose Eduardo Arroyo Palafox and Alejandro Morellano Alvarez