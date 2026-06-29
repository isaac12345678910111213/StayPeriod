package ucr.ac.cr.StayPeriod.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.StayPeriod.model.DTO.PublicationDTO;
import ucr.ac.cr.StayPeriod.model.Publication;
import ucr.ac.cr.StayPeriod.model.Rental;
import ucr.ac.cr.StayPeriod.model.User;
import ucr.ac.cr.StayPeriod.repository.PublicationRepository;
import ucr.ac.cr.StayPeriod.repository.RentalRepository;
import ucr.ac.cr.StayPeriod.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PublicationService {
    @Autowired
    PublicationRepository repository;

    @Autowired
    RentalRepository rentalRepository;

    @Autowired
    UserRepository userRepository;

    public PublicationDTO convertPublicationToDTO(Publication publication) {
        PublicationDTO dto = new PublicationDTO();
        dto.setPublication_id(publication.getId());
        dto.setPublication_publicationDate(publication.getPublicationDate());

        if (publication.getPublisher() != null) {
            dto.setPublication_publisher_id(publication.getPublisher().getId());
            dto.setPublication_publisher_name(publication.getPublisher().getName());
        }

        if (publication.getRental() != null) {
            dto.setPublication_rental_id(publication.getRental().getId());
            dto.setPublication_rental_name(publication.getRental().getName());
        }

        if (publication.getRequest() != null) {
            dto.setPublication_request_id(publication.getRequest().getId());
        }

        return dto;
    }

    public List<PublicationDTO> convertListToDTO(List<Publication> publicationList) {
        List<PublicationDTO> dtoList = new ArrayList<>();
        if (publicationList == null || publicationList.isEmpty()) {
            return dtoList;
        }
        for (Publication publication : publicationList) {
            dtoList.add(convertPublicationToDTO(publication));
        }
        return dtoList;
    }

    public List<PublicationDTO> findAll() {
        return this.convertListToDTO(this.repository.findAll());
    }

    public PublicationDTO findById(Integer id) {
        Optional<Publication> opt = this.repository.findById(id);
        if (opt.isPresent()) {
            return this.convertPublicationToDTO(opt.get());
        }
        return null;
    }

    public PublicationDTO savePublication(Publication publication) {
        if (publication.getPublisher() == null || publication.getPublisher().getId() == null) {
            return null;
        }

        Optional<User> publisherOpt = this.userRepository.findById(publication.getPublisher().getId());
        if (!publisherOpt.isPresent()) {
            return null;
        }

        if (publication.getRental() == null || publication.getRental().getId() == null) {
            return null;
        }

        Optional<Rental> rentalOpt = this.rentalRepository.findById(publication.getRental().getId());
        if (!rentalOpt.isPresent()) {
            return null;
        }

        if (publication.getPublicationDate() == null) {
            publication.setPublicationDate(LocalDate.now());
        }

        List<Publication> existing = this.repository.findByRentalId(publication.getRental().getId());
        if (!existing.isEmpty()) {
            return null;
        }

        publication.setId(null);
        Publication savedPublication = this.repository.save(publication);
        return this.convertPublicationToDTO(savedPublication);
    }

    public PublicationDTO updatePublicationById(Integer id, Publication updatedPublication) {
        Optional<Publication> opt = this.repository.findById(id);
        if (opt.isPresent()) {
            Publication existingPublication = opt.get();

            if (updatedPublication.getPublicationDate() != null) {
                existingPublication.setPublicationDate(updatedPublication.getPublicationDate());
            }

            if (updatedPublication.getPublisher() != null && updatedPublication.getPublisher().getId() != null) {
                Optional<User> userOpt = this.userRepository.findById(updatedPublication.getPublisher().getId());
                if (userOpt.isPresent()) {
                    existingPublication.setPublisher(userOpt.get());
                }
            }

            if (updatedPublication.getRental() != null && updatedPublication.getRental().getId() != null) {
                Optional<Rental> rentalOpt = this.rentalRepository.findById(updatedPublication.getRental().getId());
                if (rentalOpt.isPresent()) {
                    existingPublication.setRental(rentalOpt.get());
                }
            }

            if (updatedPublication.getRequest() != null && updatedPublication.getRequest().getId() != null) {
                existingPublication.setRequest(updatedPublication.getRequest());
            }

            Publication savedPublication = this.repository.save(existingPublication);
            return this.convertPublicationToDTO(savedPublication);
        }
        return null;
    }

    public void deletePublication(Integer id) {
        this.repository.deleteById(id);
    }

    public List<PublicationDTO> findByDate(LocalDate date) {
        List<Publication> publications = this.repository.findByPublicationDate(date);
        return this.convertListToDTO(publications);
    }

    public List<PublicationDTO> findByPublisher(Integer publisherId) {
        List<Publication> publications = this.repository.findByPublisherId(publisherId);
        return this.convertListToDTO(publications);
    }

    public List<PublicationDTO> findByRental(Integer rentalId) {
        List<Publication> publications = this.repository.findByRentalId(rentalId);
        return this.convertListToDTO(publications);
    }

    public List<PublicationDTO> findByDateRange(LocalDate start, LocalDate end) {
        List<Publication> publications = this.repository.findByPublicationDateBetween(start, end);
        return this.convertListToDTO(publications);
    }
}