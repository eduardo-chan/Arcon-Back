package com.accionti.arcon.arcon.controllers.user;

import com.accionti.arcon.arcon.dto.user.UserDto;
import com.accionti.arcon.arcon.models.user.User;
import com.accionti.arcon.arcon.services.user.UserService;
import com.accionti.arcon.arcon.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api-arcon/user/")
@CrossOrigin(origins = {"*"})
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<User>>> getAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<User>> getOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<User>> insert(@Valid @ModelAttribute UserDto userDto){
        MultipartFile file = userDto.getProfilePhoto();
        return new ResponseEntity<>(
                this.service.insert(userDto.getUser(), file),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<User>> update(
            @PathVariable("id") Long id,
            @RequestBody UserDto userDto) {
        // Obtén el usuario existente desde la base de datos
        User existingUser = service.getOne(id).getData();

        // Actualiza los campos necesarios
        User updatedUser = userDto.getUser();
        existingUser.setName(updatedUser.getName());
        existingUser.setSurname(updatedUser.getSurname());
        existingUser.setLastname(updatedUser.getLastname());
        existingUser.setBirthdate(updatedUser.getBirthdate());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setUrlMeet(updatedUser.getUrlMeet());
        existingUser.setStatus(updatedUser.getStatus());
        existingUser.setArea(updatedUser.getArea());
        existingUser.setRole(updatedUser.getRole());

        // No actualices el campo ProfilePhoto

        CustomResponse<User> response = service.update(existingUser, null);

        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }



    @PatchMapping("/{id}/status")
    public ResponseEntity<CustomResponse<Boolean>> enableOrDisable(
            @PathVariable("id") Long userId, @RequestBody Map<String, Boolean> request) {
        boolean newStatus = request.get("status");
        CustomResponse<Boolean> response = service.changeStatus(userId, newStatus);

        // Utiliza la nueva ResponseEntityBuilder para construir la respuesta
        return ResponseEntity
                .status(response.getStatusCode()) // Establece el código de estado de la respuesta
                .body(response); // Establece el cuerpo de la respuesta
    }

}
//Developed by: Jose Eduardo Arroyo Palafox and Alejandro Morellano Alvarez