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
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("api/rental")
@RestController
public class RentalController {
    @Autowired
    RentalService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<RentalDTO> dtos = this.service.findAll();
        if (dtos != null && !dtos.isEmpty()) {
            return ResponseEntity.ok(dtos);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        RentalDTO dto = this.service.findById(id);
        if (dto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveRental(@Validated @RequestBody Rental rental, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        RentalDTO newDtoRental = this.service.saveRental(rental);
        if (newDtoRental != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newDtoRental);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("El alquiler ya existe con ese nombre y ubicación");
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateRentalById(@PathVariable Integer id,
                                              @Validated @RequestBody Rental updatedRental,
                                              BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        RentalDTO foundRental = this.service.updateRental(id, updatedRental);
        if (foundRental != null) {
            return ResponseEntity.status(HttpStatus.OK).body(foundRental);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el alquiler con id: " + id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRentalById(@PathVariable Integer id) {
        this.service.deleteRentalById(id);
        return ResponseEntity.ok("Alquiler eliminado exitosamente");
    }

    @GetMapping("/search/name")
    public ResponseEntity<?> searchByName(@RequestParam String name) {
        List<RentalDTO> dtos = this.service.searchByName(name);
        if (dtos != null && !dtos.isEmpty()) {
            return ResponseEntity.ok(dtos);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/location")
    public ResponseEntity<?> searchByLocation(@RequestParam String location) {
        List<RentalDTO> dtos = this.service.searchByLocation(location);
        if (dtos != null && !dtos.isEmpty()) {
            return ResponseEntity.ok(dtos);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/price")
    public ResponseEntity<?> searchByMaxPrice(@RequestParam Double price) {
        List<RentalDTO> dtos = this.service.searchByMaxPrice(price);
        if (dtos != null && !dtos.isEmpty()) {
            return ResponseEntity.ok(dtos);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/capacity")
    public ResponseEntity<?> searchByMinCapacity(@RequestParam Integer capacity) {
        List<RentalDTO> dtos = this.service.searchByMinCapacity(capacity);
        if (dtos != null && !dtos.isEmpty()) {
            return ResponseEntity.ok(dtos);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<?> findByOwner(@PathVariable Integer ownerId) {
        List<RentalDTO> dtos = this.service.findByOwner(ownerId);
        if (dtos != null && !dtos.isEmpty()) {
            return ResponseEntity.ok(dtos);
        }
        return ResponseEntity.noContent().build();
    }
}