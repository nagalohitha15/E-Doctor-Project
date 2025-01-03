package Outpatient.example.Intership_Backend.Controller;

import Outpatient.example.Intership_Backend.Service.AdminService;
import Outpatient.example.Intership_Backend.Service.DoctorService;
import Outpatient.example.Intership_Backend.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @GetMapping("/doctors")
    public long getDoctorsCount() {
        return adminService.getDoctorsCount();
    }

    @GetMapping("/patients")
    public long getPatientsCount() {
        return adminService.getPatientsCount();
    }

    @GetMapping("/get-welcome-email")
    public ResponseEntity<Map<String, String>> getWelcomeEmail() {
        Map<String, String> response = new HashMap<>();
        response.put("email", adminService.getLoginEmail());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteDoctor(@RequestParam String email) {
        try {
            boolean isDeleted = adminService.deleteDoctorByEmail(email);
            System.out.println(isDeleted);
            if (isDeleted) {
                return ResponseEntity.ok("Doctor deleted successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found with email: " + email);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting doctor.");
        }
    }


    @DeleteMapping("/delete-patient")
    public ResponseEntity<String> deletePatient(@RequestParam String email) {
        try {
            boolean isDeleted = adminService.deletePatientByEmail(email);
            System.out.println(isDeleted);
            if (isDeleted) {
                return ResponseEntity.ok("Patient deleted successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found with email: " + email);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting doctor.");
        }
    }
}
