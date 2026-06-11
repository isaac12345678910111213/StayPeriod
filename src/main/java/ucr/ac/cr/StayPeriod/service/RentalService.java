package ucr.ac.cr.StayPeriod.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.StayPeriod.model.DTO.RentalDTO;
import ucr.ac.cr.StayPeriod.model.Rental;
import ucr.ac.cr.StayPeriod.repository.RentalRepository;

import java.util.List;

@Service
public class RentalService {
    @Autowired
    RentalRepository repository;
    // añadí los DTO que habia en el Rental DTO
    public RentalDTO convertRentalToDTO (Rental rental) {
        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setRental_id(rental.getId());
        rentalDTO.setRental_location(rental.getLocation());
        rentalDTO.setRental_capacity(rental.getCapacity());
        rentalDTO.setRental_price(rental.getPrice());
        return rentalDTO;

    }

    public List<RentalDTO> convertListToDTO (List<Rental> rentalList){
        List<RentalDTO> dtoList = new java.util.ArrayList<>();
        if(rentalList.isEmpty()){
           return null;
        }
        for(Rental rental: rentalList){
            dtoList.add(convertRentalToDTO(rental));
            return dtoList;
        }
        return null;
    }

    //metodo para encontrar la lista entera
    public List<RentalDTO> findAll (){
        return this.convertListToDTO(this.repository.findAll());
    }

    //metodo para encontrar un alquiler por id
    public RentalDTO findById (Integer id) {
        java.util.Optional<Rental> opt = this.repository.findById(id);
        if (opt.isPresent()) {
            return this.convertRentalToDTO(opt.get());
        }
        return null;
    }

    //metodo para crear un alquiler
    public RentalDTO saveRental (Rental rental) {
        java.util.Optional<Rental> opt = this.repository.findById(rental.getId());
        if (opt.isPresent()) {
            return null;
        }
        return this.convertRentalToDTO(this.repository.save(rental));
    }

    //metodo para actualizar un alquiler por id
    public RentalDTO updateRental (Integer id, Rental rental) {
        java.util.Optional<Rental> opt = this.repository.findById(id);
        if (opt.isPresent()) {
            Rental rentalToUpdate = opt.get();
            rentalToUpdate.setLocation(rental.getLocation());
            rentalToUpdate.setCapacity(rental.getCapacity());
            rentalToUpdate.setPrice(rental.getPrice());
            return this.convertRentalToDTO(this.repository.save(rentalToUpdate));
        }
        return null;
    }

    //metodo para eliminar un alquiler por id
    public void deleteRentalById (Integer id) {
        this.repository.deleteById(id);
    }


}
