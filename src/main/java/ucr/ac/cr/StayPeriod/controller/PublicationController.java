package ucr.ac.cr.StayPeriod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.StayPeriod.model.DTO.PublicationDTO;
import ucr.ac.cr.StayPeriod.model.Publication;
import ucr.ac.cr.StayPeriod.service.PublicationService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("api/publication")
public class PublicationController {
    @Autowired
    PublicationService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<PublicationDTO> dtos = service.findAll();
        if (dtos != null && !dtos.isEmpty()) {
            return ResponseEntity.ok(dtos);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        PublicationDTO dto = this.service.findById(id);
        if (dto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/save")
    public ResponseEntity<?> savePublication(@Validated @RequestBody Publication publication, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        PublicationDTO newDtoPublication = this.service.savePublication(publication);
        if (newDtoPublication != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newDtoPublication);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("La publicacion no pudo ser creada. Verifica los datos.");
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updatePublicationById(@PathVariable Integer id,
                                                   @Validated @RequestBody Publication updatedPublication,
                                                   BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        PublicationDTO foundedDto = this.service.updatePublicationById(id, updatedPublication);
        if (foundedDto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(foundedDto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La Publicacion con id " + id + " no existe");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePublicationById(@PathVariable Integer id) {
        this.service.deletePublication(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/date")
    public ResponseEntity<?> findByDate(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        List<PublicationDTO> dtos = this.service.findByDate(localDate);
        if (dtos != null && !dtos.isEmpty()) {
            return ResponseEntity.ok(dtos);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/publisher/{publisherId}")
    public ResponseEntity<?> findByPublisher(@PathVariable Integer publisherId) {
        List<PublicationDTO> dtos = this.service.findByPublisher(publisherId);
        if (dtos != null && !dtos.isEmpty()) {
            return ResponseEntity.ok(dtos);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/rental/{rentalId}")
    public ResponseEntity<?> findByRental(@PathVariable Integer rentalId) {
        List<PublicationDTO> dtos = this.service.findByRental(rentalId);
        if (dtos != null && !dtos.isEmpty()) {
            return ResponseEntity.ok(dtos);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/date-range")
    public ResponseEntity<?> findByDateRange(@RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        List<PublicationDTO> dtos = this.service.findByDateRange(startDate, endDate);
        if (dtos != null && !dtos.isEmpty()) {
            return ResponseEntity.ok(dtos);
        }
        return ResponseEntity.noContent().build();
    }
}