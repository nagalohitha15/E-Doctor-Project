package Outpatient.example.Intership_Backend.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String doctorEmail;

    @ManyToOne
    @JoinColumn(name = "patient_email")
    private Patient patient;

    private LocalDate appointmentDate;

    private String reason;

    private String status;

    private String remarks;

    //add
    @Pattern(regexp = "^(CASH|ONLINE_PAY)$", message = "Payment mode can be Cash or Online Pay")
    private String paymentmode;


//add new

//    // Mapping to Doctor entity (optional, if you want to directly access the Doctor's details)
//    @ManyToOne
//    @JoinColumn(name = "doctor_email", referencedColumnName = "email", insertable = false, updatable = false) // Do not insert/update via doctorEmail
//    private Doctor doctor;
}
