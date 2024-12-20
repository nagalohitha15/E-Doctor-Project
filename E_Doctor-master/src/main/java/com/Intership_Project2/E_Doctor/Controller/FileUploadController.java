package com.Intership_Project2.E_Doctor.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileUploadController {

    // Set the directory where the uploaded file will be stored
    @Value("${upload.dir}")
    private String uploadDir;

    @GetMapping("/uploadimg")
    public String home() {
        return "uploadForm"; // Name of your Thymeleaf template (uploadForm.html)
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/";
        }

        try {
            // Get the filename
            String filename = StringUtils.cleanPath(file.getOriginalFilename());

            // Determine the path to save the uploaded file in static folder
            Path targetLocation = Paths.get(uploadDir + "/assets/img/" + filename);

            // Create necessary directories if not exist
            Files.createDirectories(targetLocation.getParent());

            // Copy the file to the target location
            file.transferTo(targetLocation);

            redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + filename + "'.");

        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Could not upload the file. Please try again!");
        }

        return "redirect:/";
    }
}
