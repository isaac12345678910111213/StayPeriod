package ucr.ac.cr.StayPeriod.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.StayPeriod.repository.PublicationRepository;

@Service
public class PublicationService {
    @Autowired
    PublicationRepository repository;
}
