package com.accionti.arcon.arcon.models.archive;

import com.accionti.arcon.arcon.models.area.Area;
import com.accionti.arcon.arcon.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "archives")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Archive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false,length = 45)
    private Date FechaCarga;
    //link
    @Column(nullable = false)
    private String drive;
    private byte[] archivo;


    @ManyToOne
    @JoinColumn(name = "area_id",nullable = false)
    private Area area;






    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 1")
    private Boolean status;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
}
//Developed by: Jose Eduardo Arroyo Palafox and Alejandro Morellano Alvarez