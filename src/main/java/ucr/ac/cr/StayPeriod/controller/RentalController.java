package ucr.ac.cr.StayPeriod.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.StayPeriod.model.DTO.RentalDTO;
import ucr.ac.cr.StayPeriod.model.Rental;
import ucr.ac.cr.StayPeriod.service.RentalService;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("api/rental")
@RestController
public class RentalController {
    @Autowired
    RentalService service;

    @GetMapping
    public ResponseEntity<?> findAll (){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity <?> findById (@PathVariable Integer id){
        RentalDTO dto = this.service.findById(id);
        if(dto!= null){
            return ResponseEntity.status(HttpStatus.FOUND).body("Alquiler encontrado: \n"+dto.toString());
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveRental (@Validated @RequestBody Rental rental, BindingResult result){
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

        RentalDTO newDtoRental = this.service.saveRental(rental);
        if(newDtoRental != null){
            return ResponseEntity.status(HttpStatus.CREATED).body("Alquiler creado: \n"+newDtoRental.toString());
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("El Alquiler"+rental.getId()+ "ya existe");
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateRentalById (@PathVariable Integer id, @Validated @RequestBody Rental updatedRental, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        RentalDTO foundRental = this.service.updateRental(id, updatedRental);
        if (foundRental != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Alquiler actualizado: \n" + foundRental.toString());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el alquiler con id: " + id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRentalById (@PathVariable Integer id) {
        this.service.deleteRentalById(id);
        return ResponseEntity.noContent().build();
    }
}
