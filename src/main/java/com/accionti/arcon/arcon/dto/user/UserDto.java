package com.accionti.arcon.arcon.dto.user;

import com.accionti.arcon.arcon.models.area.Area;
import com.accionti.arcon.arcon.models.role.Role;
import com.accionti.arcon.arcon.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;
    private MultipartFile ProfilePhoto;
    @NotEmpty(message = "Campo obligatorio")
    @Size(min = 1, max = 100)
    private String name;
    @NotEmpty(message = "Campo obligatorio")
    @Size(min = 1, max = 100)
    private String surname;
    private String lastname;
    private Date birthdate;
    @NotEmpty(message = "Campo obligatorio")
    private String phone;
    @NotEmpty(message = "Campo obligatorio")
    private String email;
    @NotEmpty(message = "Campo obligatorio")
    private String password;
    private String urlMeet;
    private Boolean status;
    private String token;
    private String secretPass;
    private Role role;
    private Area area;
    //    private List<Archive> archive;

    public User getUser() {
        byte[] profilePhotoBytes = null;

        if (getProfilePhoto() != null) {
            profilePhotoBytes = getProfilePhoto().getOriginalFilename().getBytes();
        }

        return new User(
                getId(),
                profilePhotoBytes,
                getName(),
                getSurname(),
                getLastname(),
                getBirthdate(),
                getPhone(),
                getEmail(),
                getPassword(),
                getUrlMeet(),
                getStatus(),
                getToken(),
                getSecretPass(),
                getRole(),
                getArea()
        );
    }
}
