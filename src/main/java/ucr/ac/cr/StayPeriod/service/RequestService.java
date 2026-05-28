package ucr.ac.cr.StayPeriod.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.StayPeriod.repository.RequestRepository;

@Service
public class RequestService {
    @Autowired
    RequestRepository repository;
}
