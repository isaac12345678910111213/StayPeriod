package ucr.ac.cr.StayPeriod.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.StayPeriod.model.DTO.RentalDTO;
import ucr.ac.cr.StayPeriod.model.Rental;
import ucr.ac.cr.StayPeriod.model.User;
import ucr.ac.cr.StayPeriod.repository.RentalRepository;
import ucr.ac.cr.StayPeriod.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService {
    @Autowired
    RentalRepository repository;

    @Autowired
    UserRepository userRepository;

    public RentalDTO convertRentalToDTO(Rental rental) {
        RentalDTO dto = new RentalDTO();
        dto.setRental_id(rental.getId());
        dto.setRental_name(rental.getName());
        dto.setRental_location(rental.getLocation());
        dto.setRental_capacity(rental.getCapacity());
        dto.setRental_price(rental.getPrice());
        dto.setRental_details(rental.getDetails());

        if (rental.getOwner() != null) {
            dto.setRental_owner_id(rental.getOwner().getId());
            dto.setRental_owner_name(rental.getOwner().getName());
        }

        return dto;
    }

    public List<RentalDTO> convertListToDTO(List<Rental> rentalList) {
        List<RentalDTO> dtoList = new ArrayList<>();
        if (rentalList == null || rentalList.isEmpty()) {
            return dtoList;
        }
        for (Rental rental : rentalList) {
            dtoList.add(convertRentalToDTO(rental));
        }
        return dtoList;
    }

    public List<RentalDTO> findAll() {
        return this.convertListToDTO(this.repository.findAll());
    }

    public RentalDTO findById(Integer id) {
        Optional<Rental> opt = this.repository.findById(id);
        if (opt.isPresent()) {
            return this.convertRentalToDTO(opt.get());
        }
        return null;
    }

    public RentalDTO saveRental(Rental rental) {
        if (rental.getOwner() == null || rental.getOwner().getId() == null) {
            return null;
        }

        Optional<User> ownerOpt = this.userRepository.findById(rental.getOwner().getId());
        if (!ownerOpt.isPresent()) {
            return null;
        }

        Rental existing = this.repository.findByNameAndLocation(
                rental.getName(),
                rental.getLocation()
        );
        if (existing != null) {
            return null;
        }

        rental.setId(null);
        Rental savedRental = this.repository.save(rental);
        return this.convertRentalToDTO(savedRental);
    }

    public RentalDTO updateRental(Integer id, Rental rental) {
        Optional<Rental> opt = this.repository.findById(id);
        if (opt.isPresent()) {
            Rental existingRental = opt.get();

            if (rental.getName() != null) {
                existingRental.setName(rental.getName());
            }
            if (rental.getLocation() != null) {
                existingRental.setLocation(rental.getLocation());
            }
            if (rental.getCapacity() != null) {
                existingRental.setCapacity(rental.getCapacity());
            }
            if (rental.getPrice() != null) {
                existingRental.setPrice(rental.getPrice());
            }
            if (rental.getDetails() != null) {
                existingRental.setDetails(rental.getDetails());
            }
            if (rental.getOwner() != null && rental.getOwner().getId() != null) {
                Optional<User> ownerOpt = this.userRepository.findById(rental.getOwner().getId());
                if (ownerOpt.isPresent()) {
                    existingRental.setOwner(ownerOpt.get());
                }
            }

            Rental savedRental = this.repository.save(existingRental);
            return this.convertRentalToDTO(savedRental);
        }
        return null;
    }

    public void deleteRentalById(Integer id) {
        this.repository.deleteById(id);
    }

    // ============================================
    // MÉTODOS DE BÚSQUEDA
    // ============================================

    public List<RentalDTO> searchByName(String name) {
        List<Rental> rentals = this.repository.findByNameContainingIgnoreCase(name);
        return this.convertListToDTO(rentals);
    }

    public List<RentalDTO> searchByLocation(String location) {
        List<Rental> rentals = this.repository.findByLocationContainingIgnoreCase(location);
        return this.convertListToDTO(rentals);
    }

    public List<RentalDTO> searchByMaxPrice(Double price) {
        List<Rental> rentals = this.repository.findByPriceLessThanEqual(price);
        return this.convertListToDTO(rentals);
    }

    public List<RentalDTO> searchByMinCapacity(Integer capacity) {
        List<Rental> rentals = this.repository.findByCapacityGreaterThanEqual(capacity);
        return this.convertListToDTO(rentals);
    }

    public List<RentalDTO> findByOwner(Integer ownerId) {
        List<Rental> rentals = this.repository.findByOwnerId(ownerId);
        return this.convertListToDTO(rentals);
    }
}