package ucr.ac.cr.StayPeriod.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.StayPeriod.model.DTO.RequestDTO;
import ucr.ac.cr.StayPeriod.model.Request;
import ucr.ac.cr.StayPeriod.repository.RequestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {
    @Autowired
    RequestRepository repository;

    public RequestDTO convertRequestToDTO (Request request){
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setRequest_id(request.getId());
        requestDTO.setRequest_startDate(request.getStartDate());
        requestDTO.setRequest_endDate(request.getEndDate());
        requestDTO.setRequest_user_id(request.getApplicant().getId());
        requestDTO.setRequest_rental_id(request.getRental().getId());
        return requestDTO;
    }

    public List<RequestDTO> convertListToDTO (List<Request> requestList){
        List<RequestDTO> dtoList = new java.util.ArrayList<>();
        if(requestList.isEmpty()){
           return null;
        }
        for(Request request: requestList){
            dtoList.add(convertRequestToDTO(request));
            return dtoList;
        }
        return null;
    }

    //metodo para encontrar la lista entera
    public List<RequestDTO> findAll () {
        return this.convertListToDTO(this.repository.findAll());
    }
    //metodo para encontrar una solicitud por id
    public RequestDTO findById (Integer id) {
        java.util.Optional<Request> opt = this.repository.findById(id);
        if (opt.isPresent()) {
            return this.convertRequestToDTO(opt.get());
        }
        return null;
    }

    //metodo para crear una solicitud
    public RequestDTO saveRequest (Request request) {
        java.util.Optional<Request> opt = this.repository.findById(request.getId());
        if (opt.isPresent()) {
            return null;
        }
        return this.convertRequestToDTO(this.repository.save(request));
    }

    //metodo para actualizar una solicitud por id
    public RequestDTO updateRequestById (Integer id, Request request) {
        Optional<Request> opt = this.repository.findById(id);
        if (opt.isPresent()) {
            Request requestToUpdate = opt.get();
            requestToUpdate.setStartDate(request.getStartDate());
            requestToUpdate.setEndDate(request.getEndDate());
            requestToUpdate.setApplicant(request.getApplicant());
            requestToUpdate.setRental(request.getRental());
            return this.convertRequestToDTO(this.repository.save(requestToUpdate));
        }
        return null;
    }

    //metodo para eliminar una solicitud

    public void deleteRequestById (Integer id) {
        Optional<Request> opt = this.repository.findById(id);
        if (opt.isPresent()) {
            this.repository.deleteById(id);
        }
    }




}
