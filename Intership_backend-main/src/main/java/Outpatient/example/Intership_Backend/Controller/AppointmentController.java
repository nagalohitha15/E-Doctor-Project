package Outpatient.example.Intership_Backend.Controller;

import Outpatient.example.Intership_Backend.Entity.Appointment;
import Outpatient.example.Intership_Backend.Entity.Doctor;
import Outpatient.example.Intership_Backend.Repository.AppointmentRepository;
import Outpatient.example.Intership_Backend.Repository.DoctorRepository;
import Outpatient.example.Intership_Backend.Service.AppointmentService;
import Outpatient.example.Intership_Backend.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private DoctorRepository doctorRepository;
    //change
    @Autowired
    private AppointmentRepository appointmentRepository;

    @PostMapping("/book")
    public ResponseEntity<String> bookAppointment(@RequestBody Appointment appointment) {
        try {
            Optional<Doctor> doctorOptional = doctorRepository.findById(appointment.getDoctorEmail());

            if (!doctorOptional.isPresent()) {
                return new ResponseEntity<>("Doctor not found", HttpStatus.BAD_REQUEST);
            }

            Doctor doctor = doctorOptional.get();
            double chargedPerVisit = doctor.getChargedPerVisit();

            Appointment savedAppointment = appointmentService.bookAppointment(appointment);

            if ("CASH".equalsIgnoreCase(appointment.getPaymentmode())) {


                return new ResponseEntity<>("Appointment booked Successfully", HttpStatus.CREATED);
            } else if ("ONLINE_PAY".equalsIgnoreCase(appointment.getPaymentmode())) {
                // If payment is Online, generate the payment URL
                String paymentUrl = paymentService.createCheckoutSession(savedAppointment.getId().toString(), chargedPerVisit);
                return new ResponseEntity<>(paymentUrl, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Invalid payment mode", HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/payment-success")
    public ResponseEntity<String> paymentSuccess(@RequestParam String appointmentId) {
        URI redirectUri = URI.create("http://localhost:3000/payment-success?appointmentId=" + appointmentId);
        return ResponseEntity.status(HttpStatus.FOUND).location(redirectUri).build();

    }


    @GetMapping("/payment-cancelled")
    public ResponseEntity<String> paymentCancelled() {
        return ResponseEntity.ok("Payment was cancelled.");
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }
}
