package ucr.ac.cr.StayPeriod.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.StayPeriod.model.DTO.RequestDTO;
import ucr.ac.cr.StayPeriod.model.Request;
import ucr.ac.cr.StayPeriod.model.Rental;
import ucr.ac.cr.StayPeriod.model.User;
import ucr.ac.cr.StayPeriod.repository.RequestRepository;
import ucr.ac.cr.StayPeriod.repository.RentalRepository;
import ucr.ac.cr.StayPeriod.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {
    @Autowired
    RequestRepository repository;

    @Autowired
    RentalRepository rentalRepository;

    @Autowired
    UserRepository userRepository;

    public RequestDTO convertRequestToDTO(Request request) {
        RequestDTO dto = new RequestDTO();
        dto.setRequest_id(request.getId());
        dto.setRequest_startDate(request.getStartDate());
        dto.setRequest_endDate(request.getEndDate());

        if (request.getApplicant() != null) {
            dto.setRequest_applicant_id(request.getApplicant().getId());
            dto.setRequest_applicant_name(request.getApplicant().getName());
        }

        if (request.getRental() != null) {
            dto.setRequest_rental_id(request.getRental().getId());
            dto.setRequest_rental_name(request.getRental().getName());
        }

        dto.setRequest_status(request.getStatus() != null ? request.getStatus() : "PENDIENTE");

        return dto;
    }

    public List<RequestDTO> convertListToDTO(List<Request> requestList) {
        List<RequestDTO> dtoList = new ArrayList<>();
        if (requestList == null || requestList.isEmpty()) {
            return dtoList;
        }
        for (Request request : requestList) {
            dtoList.add(convertRequestToDTO(request));
        }
        return dtoList;
    }

    public List<RequestDTO> findAll() {
        return this.convertListToDTO(this.repository.findAll());
    }

    public RequestDTO findById(Integer id) {
        Optional<Request> opt = this.repository.findById(id);
        if (opt.isPresent()) {
            return this.convertRequestToDTO(opt.get());
        }
        return null;
    }

    public RequestDTO saveRequest(Request request) {
        if (request.getApplicant() == null || request.getApplicant().getId() == null) {
            return null;
        }

        if (request.getRental() == null || request.getRental().getId() == null) {
            return null;
        }

        Optional<User> applicantOpt = this.userRepository.findById(request.getApplicant().getId());
        if (!applicantOpt.isPresent()) {
            return null;
        }

        Optional<Rental> rentalOpt = this.rentalRepository.findById(request.getRental().getId());
        if (!rentalOpt.isPresent()) {
            return null;
        }

        if (request.getStartDate() == null || request.getEndDate() == null) {
            return null;
        }

        if (request.getStartDate().isAfter(request.getEndDate())) {
            return null;
        }

        if (request.getStartDate().isBefore(LocalDate.now())) {
            return null;
        }

        request.setId(null);
        request.setStatus("PENDIENTE");
        Request savedRequest = this.repository.save(request);
        return this.convertRequestToDTO(savedRequest);
    }

    public RequestDTO updateRequestById(Integer id, Request updatedRequest) {
        Optional<Request> opt = this.repository.findById(id);
        if (opt.isPresent()) {
            Request existingRequest = opt.get();

            if (updatedRequest.getStartDate() != null) {
                existingRequest.setStartDate(updatedRequest.getStartDate());
            }
            if (updatedRequest.getEndDate() != null) {
                existingRequest.setEndDate(updatedRequest.getEndDate());
            }
            if (updatedRequest.getApplicant() != null && updatedRequest.getApplicant().getId() != null) {
                Optional<User> userOpt = this.userRepository.findById(updatedRequest.getApplicant().getId());
                if (userOpt.isPresent()) {
                    existingRequest.setApplicant(userOpt.get());
                }
            }
            if (updatedRequest.getRental() != null && updatedRequest.getRental().getId() != null) {
                Optional<Rental> rentalOpt = this.rentalRepository.findById(updatedRequest.getRental().getId());
                if (rentalOpt.isPresent()) {
                    existingRequest.setRental(rentalOpt.get());
                }
            }

            Request savedRequest = this.repository.save(existingRequest);
            return this.convertRequestToDTO(savedRequest);
        }
        return null;
    }

    public void deleteRequestById(Integer id) {
        this.repository.deleteById(id);
    }

    // ============================================
    // MÉTODOS DE BÚSQUEDA Y ACCIONES
    // ============================================

    public List<RequestDTO> findRequestsByApplicant(Integer applicantId) {
        List<Request> allRequests = this.repository.findAll();
        List<Request> filteredRequests = new ArrayList<>();

        for (Request request : allRequests) {
            if (request.getApplicant() != null &&
                    request.getApplicant().getId().equals(applicantId)) {
                filteredRequests.add(request);
            }
        }

        return this.convertListToDTO(filteredRequests);
    }

    public List<RequestDTO> findRequestsByRental(Integer rentalId) {
        List<Request> allRequests = this.repository.findAll();
        List<Request> filteredRequests = new ArrayList<>();

        for (Request request : allRequests) {
            if (request.getRental() != null &&
                    request.getRental().getId().equals(rentalId)) {
                filteredRequests.add(request);
            }
        }

        return this.convertListToDTO(filteredRequests);
    }

    public List<RequestDTO> findRequestsByOwner(Integer ownerId) {
        List<Request> allRequests = this.repository.findAll();
        List<Request> filteredRequests = new ArrayList<>();

        Optional<User> ownerOpt = this.userRepository.findById(ownerId);
        if (!ownerOpt.isPresent()) {
            return new ArrayList<>();
        }

        List<Rental> allRentals = this.rentalRepository.findAll();
        List<Integer> ownerRentalIds = new ArrayList<>();

        for (Rental rental : allRentals) {
            if (rental.getOwner() != null &&
                    rental.getOwner().getId().equals(ownerId)) {
                ownerRentalIds.add(rental.getId());
            }
        }

        for (Request request : allRequests) {
            if (request.getRental() != null &&
                    ownerRentalIds.contains(request.getRental().getId())) {
                filteredRequests.add(request);
            }
        }

        return this.convertListToDTO(filteredRequests);
    }

    public List<RequestDTO> findPendingRequestsByOwner(Integer ownerId) {
        List<Request> allRequests = this.repository.findAll();
        List<Request> pendingRequests = new ArrayList<>();

        List<Rental> allRentals = this.rentalRepository.findAll();
        List<Integer> ownerRentalIds = new ArrayList<>();

        for (Rental rental : allRentals) {
            if (rental.getOwner() != null &&
                    rental.getOwner().getId().equals(ownerId)) {
                ownerRentalIds.add(rental.getId());
            }
        }

        for (Request request : allRequests) {
            if (request.getRental() != null &&
                    ownerRentalIds.contains(request.getRental().getId()) &&
                    "PENDIENTE".equals(request.getStatus())) {
                pendingRequests.add(request);
            }
        }

        return this.convertListToDTO(pendingRequests);
    }

    public RequestDTO acceptRequest(Integer requestId) {
        Optional<Request> opt = this.repository.findById(requestId);
        if (!opt.isPresent()) {
            return null;
        }

        Request request = opt.get();
        request.setStatus("ACEPTADA");
        Request savedRequest = this.repository.save(request);
        return this.convertRequestToDTO(savedRequest);
    }

    public RequestDTO rejectRequest(Integer requestId) {
        Optional<Request> opt = this.repository.findById(requestId);
        if (!opt.isPresent()) {
            return null;
        }

        Request request = opt.get();
        request.setStatus("RECHAZADA");
        Request savedRequest = this.repository.save(request);
        return this.convertRequestToDTO(savedRequest);
    }
}