package Outpatient.example.Intership_Backend.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipientEmail;

    private String message;

    private boolean read = false; // To track if the notification is read

    private LocalDateTime timestamp = LocalDateTime.now();
}
