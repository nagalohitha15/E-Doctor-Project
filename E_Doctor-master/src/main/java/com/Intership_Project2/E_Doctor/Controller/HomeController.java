package com.Intership_Project2.E_Doctor.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {


    @GetMapping("/")
    public String home(){
        return "welcomepage";
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }

//    @GetMapping("/login")
//    public String login(Model model) {
//
//        return "login";
//    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

//    @PostMapping("/createUser")
//    public String registerUser(@ModelAttribute @Valid RegisterUserDTo userDto,
//                               BindingResult result,
//                               HttpSession session) {
//
//        boolean userExists = userService.checkEmail(userDto.getEmail());
//        if(userExists){
//            session.setAttribute("msg","Email already exists");
//        }else{
//           UserEntity entity= userService.registerNewUser(userDto);
//           if(entity!=null){
//               session.setAttribute("msg", "Register Successfully");
//           }else{
//               session.setAttribute("msg", "Something went wrong");
//           }
//        }
//        return "redirect:/register";
//    }

    @GetMapping("/registration")
    public String registration(){
        return "register";
    }


    @GetMapping("/user/login")
    public String registration1(){
        return "register";
    }


}
