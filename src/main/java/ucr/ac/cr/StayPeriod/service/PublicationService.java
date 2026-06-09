package ucr.ac.cr.StayPeriod.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.StayPeriod.model.DTO.PublicationDTO;
import ucr.ac.cr.StayPeriod.model.Publication;
import ucr.ac.cr.StayPeriod.repository.PublicationRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PublicationService {
    @Autowired
    PublicationRepository repository;

    public PublicationDTO covertPublicationToDTO(Publication publication) {
        PublicationDTO publicationDTO = new PublicationDTO();
        publicationDTO.setPublication_id(publication.getId());
        publicationDTO.setPublication_request_id(publication.getRequest().getId());
        publicationDTO.setPublication_rental_id(publication.getRental().getId());
        publicationDTO.setPublication_publicationDate(publication.getPublicationDate());
        publicationDTO.setPublication_user_id(publication.getPublisher().getId());
        return publicationDTO;
    }

    public List<PublicationDTO> convertListToDTO(List<Publication> publicationList) {
        List<PublicationDTO> dtoList = new ArrayList<>();
        if (publicationList.isEmpty()) {
            return null;
        }
        for (Publication publication : publicationList) {
            dtoList.add(covertPublicationToDTO(publication));
            return dtoList;
        }
        return null;
    }

    // metodo para encontrar la lista entera
    public List<PublicationDTO> findAll() {
        return this.convertListToDTO(this.repository.findAll());
    }

    //metodo para encontrar una publicacion por id
    public PublicationDTO findById(Integer id) {
        Optional<Publication> opt = this.repository.findById(id);
        if (opt.isPresent()) {
            return this.covertPublicationToDTO(opt.get());
        }
        return null;
    }
//metodo para encontrar una publicacion por fecha - No sé si funcione correctamente


    //metodo para crear una publicacion
    public PublicationDTO savePublication(Publication publication) {
        Optional<Publication> opt = this.repository.findById(publication.getId());
        if (opt.isPresent()) {
            return null;
        }
        return this.covertPublicationToDTO(this.repository.save(publication));
    }

    //metodo para actualizar una publicacion por id
    public PublicationDTO updatePublicationById(Integer id, Publication publication) {
        Optional<Publication> opt = this.repository.findById(id);
        if (opt.isPresent()) {
            Publication publicationToUpdate = opt.get();
            publicationToUpdate.setPublicationDate(publication.getPublicationDate());
            publicationToUpdate.setPublisher(publication.getPublisher());
            publicationToUpdate.setRental(publication.getRental());
            publicationToUpdate.setRequest(publication.getRequest());
            return this.covertPublicationToDTO(this.repository.save(publicationToUpdate));
        }
        return null;
    }

    //metodo para eliminar una publicacion
    public void deletePublication(Integer id) {
        this.repository.deleteById(id);
    }


}