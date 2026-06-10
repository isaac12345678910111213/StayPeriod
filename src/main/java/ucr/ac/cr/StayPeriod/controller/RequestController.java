package ucr.ac.cr.StayPeriod.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.StayPeriod.model.DTO.RequestDTO;
import ucr.ac.cr.StayPeriod.model.Request;
import ucr.ac.cr.StayPeriod.service.RequestService;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("api/Request")
@RestController
public class RequestController {
    @Autowired
    RequestService service;

    @GetMapping
    public ResponseEntity<?> findAll (){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById (@PathVariable Integer id){
        RequestDTO dto = this.service.findById(id);
        if(dto!= null){
            return ResponseEntity.status(HttpStatus.FOUND).body("Solicitud encontrada: \n"+dto.toString());
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveRequest (@Validated @RequestBody Request request, BindingResult result){
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

        RequestDTO newDtoRequest = this.service.saveRequest(request);
        if(newDtoRequest != null){
            return ResponseEntity.status(HttpStatus.CREATED).body("Solicitud creada: \n"+newDtoRequest.toString());
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("La Solicitud"+request.getId()+ "ya existe");
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateRequestById(@PathVariable Integer id, @Validated @RequestBody Request updatedRequest, BindingResult result){
        if(result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            for(FieldError error: result.getFieldErrors()){
                errors.put(error.getField(),error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        RequestDTO foundRequest = this.service.updateRequestById(id, updatedRequest);
        if(foundRequest != null){
            return ResponseEntity.status(HttpStatus.OK).body("Solicitud actualizada: \n"+foundRequest.toString());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La Solicitud con id "+id+" no existe");
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<?> deleteRequestById (@PathVariable Integer id){
        this.service.deleteRequestById(id);
        return ResponseEntity.noContent().build();
    }



}
