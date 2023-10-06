package com.accionti.arcon.arcon.models.user;
import com.accionti.arcon.arcon.models.archive.Archive;
import com.accionti.arcon.arcon.models.area.Area;
import com.accionti.arcon.arcon.models.role.Role;
import com.accionti.arcon.arcon.models.site.Site;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false) // Evita que se actualice en ediciones
    private byte[] ProfilePhoto;

    @Column(nullable = false,length = 45)
    private String name;

    @Column(nullable = false,length = 45)
    private String surname;

    @Column(nullable = false,length = 45)
    private String lastname;

    @Column(nullable = false,length = 45)
    private Date birthdate;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false,length = 45)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String urlMeet;

    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 1")
    private Boolean status;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String token;

    @Column(columnDefinition = "VARCHAR(8)")
    private String secretPass;

    @ManyToOne
    @JoinColumn(name = "role_id",nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "area_id",nullable = true)
    private Area area;

//    @OneToMany(mappedBy = "user")
//    private List<Archive> archive;

}
//Developed by: Jose Eduardo Arroyo Palafox and Alejandro Morellano Alvarez