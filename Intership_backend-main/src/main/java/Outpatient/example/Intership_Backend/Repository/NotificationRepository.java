package Outpatient.example.Intership_Backend.Repository;

import Outpatient.example.Intership_Backend.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

}

