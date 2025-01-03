package Outpatient.example.Intership_Backend.Service;

import Outpatient.example.Intership_Backend.DTO.LoginRequest;
import Outpatient.example.Intership_Backend.Entity.Doctor;
import Outpatient.example.Intership_Backend.Entity.User;
import Outpatient.example.Intership_Backend.Repository.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@Data
public class AdminService {
    String loginEmail;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private PatientRepository patientRepository;

    public boolean deleteDoctorByEmail(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isEmpty()) {
            return false; // Doctor not found
        }

        try {
            if (doctorRepository.existsByEmail(email)) {
                userRepo.deleteByEmail(email);
                appointmentRepository.deleteByDoctorEmail(email);
                doctorRepository.deleteById(email);

                return true;
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the error for debugging
            return false; // Return false if any error occurs during the deletion process
        }

        return false;
    }

    public void loginAdmin(LoginRequest loginRequest) {
        loginEmail = loginRequest.getEmail();
    }


    public boolean deletePatientByEmail(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isEmpty()) {
            return false;
        }

        try {
            if (patientRepository.existsByEmail(email)) {
                userRepo.deleteByEmail(email);
                appointmentRepository.deleteByPatientEmail(email);
                patientRepository.deleteById(email);

                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Return false if any error occurs during the deletion process
        }

        return false;
    }

    public long getDoctorsCount() {
        return doctorRepository.count();
    }

    public long getPatientsCount() {
        return patientRepository.count();
    }
}
