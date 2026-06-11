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
import java.util.HashMap;
import java.util.Map;

@RequestMapping("api/publication")
@RestController
public class PublicationController {
    @Autowired
    PublicationService service;

     @GetMapping
    public ResponseEntity<?> findAll (){return ResponseEntity.ok(service.findAll());}


     @GetMapping("/{id}")
    public ResponseEntity<?> findById (@PathVariable Integer id) {
         PublicationDTO dto = this.service.findById(id);
         if (dto != null) {
             return ResponseEntity.status(HttpStatus.FOUND).body("Publicacion encontrada: \n" + dto.toString());
         }
         return ResponseEntity.noContent().build();
     }

     @PostMapping ("/save")
     public ResponseEntity<?> savePublication (@Validated @RequestBody Publication publication, BindingResult result) {
         if (result.hasErrors()) {
             /*Contenedor de errores*/
             Map<String, String> errors = new HashMap<>();
             /*buscamos los errores*/
             for (FieldError error : result.getFieldErrors()) {
                 /*metemos los errores en el contenedor*/
                 errors.put(error.getField(), error.getDefaultMessage());
             }
             /*retornamos el mensaje con los errores*/
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
         }

         PublicationDTO newDtoPublication = this.service.savePublication(publication);
            if (newDtoPublication != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Publicacion creada: \n" + newDtoPublication.toString());
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("La Publicacion " + publication.getId() + " ya existe");
     }

     @PatchMapping ("/update/{id}")
     public ResponseEntity<?> updatePublicationById (@PathVariable Integer id, @Validated @RequestBody Publication updatedPublication, BindingResult result) {
         if (result.hasErrors()) {
             Map<String, String> errors = new HashMap<>();
             for (FieldError error : result.getFieldErrors()) {
                 errors.put(error.getField(), error.getDefaultMessage());
             }
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
         }
         PublicationDTO foundedDto = this.service.updatePublicationById(id, updatedPublication);
         if (foundedDto != null) {
             return ResponseEntity.status(HttpStatus.OK).body("Publicacion actualizada: \n" + foundedDto.toString());
         }
         return ResponseEntity.status(HttpStatus.CONFLICT).body("La Publicacion con id " + id + " no existe");
     }

     @DeleteMapping("/delete/{id}")
     public ResponseEntity<?> deletePublicationById (@PathVariable Integer id) {
         this.service.deletePublication(id);
         return ResponseEntity.noContent().build();
     }

}
