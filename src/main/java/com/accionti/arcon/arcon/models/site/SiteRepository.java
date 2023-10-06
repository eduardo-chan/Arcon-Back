package com.accionti.arcon.arcon.models.site;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {

    Optional<Site> findByUserSite(String userSite);

    @Modifying
    @Query(value = "UPDATE sites SET status = :status WHERE id = :id",nativeQuery = true)
    int updateStatusById (
            @Param("status") Boolean status,
            @Param("id")Long id
    );
}
//Developed by: Jose Eduardo Arroyo Palafox and Alejandro Morellano Alvarez