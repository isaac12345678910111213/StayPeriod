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
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("api/request")
@RestController
public class RequestController {
    @Autowired
    RequestService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<RequestDTO> dtos = this.service.findAll();
        if (dtos != null && !dtos.isEmpty()) {
            return ResponseEntity.ok(dtos);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        RequestDTO dto = this.service.findById(id);
        if (dto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveRequest(@Validated @RequestBody Request request, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        RequestDTO newDtoRequest = this.service.saveRequest(request);
        if (newDtoRequest != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newDtoRequest);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo crear la solicitud. Verifica que el usuario y el alquiler existan.");
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateRequestById(@PathVariable Integer id,
                                               @Validated @RequestBody Request updatedRequest,
                                               BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        RequestDTO foundRequest = this.service.updateRequestById(id, updatedRequest);
        if (foundRequest != null) {
            return ResponseEntity.status(HttpStatus.OK).body(foundRequest);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La Solicitud con id " + id + " no existe");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRequestById(@PathVariable Integer id) {
        this.service.deleteRequestById(id);
        return ResponseEntity.noContent().build();
    }

    // ============================================
    // ENDPOINTS DE BÚSQUEDA Y ACCIONES
    // ============================================

    @GetMapping("/applicant/{applicantId}")
    public ResponseEntity<?> findRequestsByApplicant(@PathVariable Integer applicantId) {
        List<RequestDTO> dtos = this.service.findRequestsByApplicant(applicantId);
        if (dtos != null && !dtos.isEmpty()) {
            return ResponseEntity.ok(dtos);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/rental/{rentalId}")
    public ResponseEntity<?> findRequestsByRental(@PathVariable Integer rentalId) {
        List<RequestDTO> dtos = this.service.findRequestsByRental(rentalId);
        if (dtos != null && !dtos.isEmpty()) {
            return ResponseEntity.ok(dtos);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<?> findRequestsByOwner(@PathVariable Integer ownerId) {
        List<RequestDTO> dtos = this.service.findRequestsByOwner(ownerId);
        if (dtos != null && !dtos.isEmpty()) {
            return ResponseEntity.ok(dtos);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/owner/{ownerId}/pending")
    public ResponseEntity<?> findPendingRequestsByOwner(@PathVariable Integer ownerId) {
        List<RequestDTO> dtos = this.service.findPendingRequestsByOwner(ownerId);
        if (dtos != null && !dtos.isEmpty()) {
            return ResponseEntity.ok(dtos);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/accept/{id}")
    public ResponseEntity<?> acceptRequest(@PathVariable Integer id) {
        RequestDTO dto = this.service.acceptRequest(id);
        if (dto != null) {
            return ResponseEntity.ok(Map.of(
                    "message", "Solicitud aceptada exitosamente",
                    "request", dto
            ));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Solicitud no encontrada");
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<?> rejectRequest(@PathVariable Integer id) {
        RequestDTO dto = this.service.rejectRequest(id);
        if (dto != null) {
            return ResponseEntity.ok(Map.of(
                    "message", "Solicitud rechazada exitosamente",
                    "request", dto
            ));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Solicitud no encontrada");
    }
}