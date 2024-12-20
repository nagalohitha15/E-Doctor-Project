package com.Intership_Project2.E_Doctor.Controller;

import com.Intership_Project2.E_Doctor.DTO.AdminDTO;
import com.Intership_Project2.E_Doctor.DTO.DoctorDTO;
import com.Intership_Project2.E_Doctor.DTO.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @PostMapping("/login/user")
    public String loginUser(@ModelAttribute("user") UserDTO user, Model model) {
        // Login logic for user (add your authentication code here)
        if (authenticateUser(user)) {
            return "redirect:/user/home";
        } else {
            model.addAttribute("msg", "Invalid user credentials");
            return "login";
        }
    }

    @PostMapping("/login/doctor")
    public String loginDoctor(@ModelAttribute("doctor") DoctorDTO doctor, Model model) {
        // Login logic for doctor (add your authentication code here)
        if (authenticateDoctor(doctor)) {
            return "redirect:/doctor/home";
        } else {
            model.addAttribute("msg", "Invalid doctor credentials");
            return "login";
        }
    }

    @PostMapping("/login/admin")
    public String loginAdmin(@ModelAttribute("admin") AdminDTO admin, Model model) {
        // Login logic for admin (add your authentication code here)
        if (authenticateAdmin(admin)) {
            return "redirect:/admin/home";
        } else {
            model.addAttribute("msg", "Invalid admin credentials");
            return "login";
        }
    }

    // Sample authentication methods for each role (replace with real logic)
    private boolean authenticateUser(UserDTO user) {
        // Implement user authentication logic here
        return true;
    }

    private boolean authenticateDoctor(DoctorDTO doctor) {
        // Implement doctor authentication logic here
        return true;
    }

    private boolean authenticateAdmin(AdminDTO admin) {
        // Implement admin authentication logic here
        return true;
    }
}

