package com.accionti.arcon.arcon.models.apartado;

import com.accionti.arcon.arcon.models.area.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ApartadoRepository extends JpaRepository<Apartado, Long> {
    Optional<Area> findByName(String name);

    @Modifying
    @Query(value = "UPDATE apartados SET status = :status WHERE id = :id",nativeQuery = true)
    int updateStatusById (
            @Param("status") Boolean status,
            @Param("id")Long id
    );
}


//Developed by: Jose Eduardo Arroyo Palafox and Alejandro Morellano Alvarez