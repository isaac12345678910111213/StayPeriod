package ucr.ac.cr.StayPeriod.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.StayPeriod.model.DTO.LoginDTO;
import ucr.ac.cr.StayPeriod.model.DTO.UserDTO;
import ucr.ac.cr.StayPeriod.model.User;
import ucr.ac.cr.StayPeriod.service.UserService;
import ucr.ac.cr.StayPeriod.validation.ValidationBuilder;
import ucr.ac.cr.StayPeriod.validation.ValidationRules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("api/user")
@RestController
public class UserController {
    @Autowired
    UserService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<UserDTO> dtos = this.service.findAll();
        if (dtos != null && !dtos.isEmpty()) {
            return ResponseEntity.ok(dtos);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/rol/{rol}")
    public ResponseEntity<?> findUserByRol(@PathVariable String rol) {
        List<UserDTO> dtos = this.service.findUserByRol(rol);
        if (dtos != null && !dtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        UserDTO dto = this.service.findById(id);
        if (dto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findUserByEmail(@PathVariable String email) {
        UserDTO dto = this.service.findUserByEmail(email);
        if (dto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@Validated @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        UserDTO newDtoUser = this.service.saveUser(user);
        if (newDtoUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newDtoUser);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("El email ya está registrado");
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable Integer id,
                                            @Validated @RequestBody User updatedUser,
                                            BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        UserDTO foundedDto = this.service.updateUserById(id, updatedUser);
        if (foundedDto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(foundedDto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron coincidencias: " + id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Integer id) {
        this.service.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO dtoLogin,
                                   BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        if (result.hasErrors()) {
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        ValidationRules passwordValidation = ValidationBuilder.validatePassword(dtoLogin.getPassword());
        if (!passwordValidation.isValid()) {
            errors.put("password", String.join(", ", passwordValidation.getErrors()));
        }

        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }

        User user = this.service.login(dtoLogin.getEmail(), dtoLogin.getPassword());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Credenciales incorrectas"));
        }

        UserDTO userDTO = this.service.convertUserToDTO(user);
        return ResponseEntity.ok(userDTO);
    }
}