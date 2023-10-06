package com.accionti.arcon.arcon.services.site;

import com.accionti.arcon.arcon.models.site.Site;
import com.accionti.arcon.arcon.models.site.SiteRepository;
import com.accionti.arcon.arcon.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class SiteService {
    @Autowired
    private SiteRepository repository;
    @Lazy
    @Autowired
    private PasswordEncoder encoder;

    @Transactional(readOnly = true)
    public CustomResponse<List<Site>> getAll(){
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "OK"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<Site> getOne(Long id){
        if (this.repository.findById(id).isEmpty()){
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Site inexistente"
            );
        }
        return new CustomResponse<>(
                this.repository.findById(id).get(),
                false,
                200,
                "OK"
        );
    }

    @Transactional(readOnly = true)
    public Site getSiteByName(String userSite){
        Optional<Site> site = this.repository.findByUserSite(userSite);
        return site.orElse(null);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Site> insert(Site site){
        Optional<Site> exists = this.repository.findByUserSite(site.getUserSite());
        if (exists.isPresent()){
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Site ya existente"
            );
        }
//        site.setPassword(
//                encoder.encode(site.getPassword())
//        );
        return new CustomResponse<>(
                this.repository.saveAndFlush(site),
                false,
                200,
                "Site registrado"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Site> update(Site site){
        if (!this.repository.existsById(site.getId())){
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Site inexistente"
            );
        }

        return new CustomResponse<>(
                this.repository.saveAndFlush(site),
                false,
                200,
                "Site actualizado"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> changeStatus(Site site){
        if (!this.repository.existsById(site.getId())){
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Site inexistente"
            );
        }
        return new CustomResponse<>(
                this.repository.updateStatusById(
                        site.getStatus(), site.getId()) == 1,
                false,
                200,
                "Status Actualizado"
        );
    }
}
//Developed by: Jose Eduardo Arroyo Palafox and Alejandro Morellano Alvarez