package ucr.ac.cr.StayPeriod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.StayPeriod.model.DTO.UserDTO;
import ucr.ac.cr.StayPeriod.model.User;
import ucr.ac.cr.StayPeriod.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("api/user")
@RestController
public class UserController {
    @Autowired
    UserService service;

    @GetMapping
    public ResponseEntity<?> findAll (){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/rol/{rol}")
    public ResponseEntity<?> findUserByRol (@PathVariable String rol){
        UserDTO dto = this.service.findUserByRol(rol);
        if(dto!= null){
            return ResponseEntity.status(HttpStatus.FOUND).body("Usuario encontrado: \n"+dto.toString());
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById (@PathVariable Integer id){
        UserDTO dto = this.service.findById(id);
        if(dto!= null){
            return ResponseEntity.status(HttpStatus.FOUND).body("Usuario encontrado: \n"+dto.toString());
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveUser (@Validated @RequestBody User user, BindingResult result){
        if(result.hasErrors()){
            /*Contenedor de errores*/
            Map<String,String> errors = new HashMap<>();
            /*buscamos los errores*/
            for(FieldError error: result.getFieldErrors()){
                /*metemos los errores en el contenedor*/
                errors.put(error.getField(), error.getDefaultMessage());
            }
            /*retornamos el mensaje con los errores*/
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        UserDTO newDtoUser = this.service.saveUser(user);
        if (newDtoUser != null){
           return ResponseEntity.status(HttpStatus.CREATED).body("Se ha creado el usuario de forma exitosa: \n" +newDtoUser.toString());
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("El id "+user.getId()+" ya existe");
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable Integer id, @Validated @RequestBody User updatedUser, BindingResult result){
        if(result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            for(FieldError error: result.getFieldErrors()){
                errors.put(error.getField(),error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        UserDTO foundedDto = this.service.updateUserById(id, updatedUser);
        if(foundedDto != null){
            return ResponseEntity.status(HttpStatus.OK).body("Se ha actualizado el usuario: \n" +foundedDto.toString());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron coincidencias: " + id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Integer id){
        this.service.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
