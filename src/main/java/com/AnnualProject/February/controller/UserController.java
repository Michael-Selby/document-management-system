package com.AnnualProject.February.controller;

import com.AnnualProject.February.dto.UserDto;
import com.AnnualProject.February.service.CustomUserDetailsService;
import com.AnnualProject.February.model.User;
import com.AnnualProject.February.repository.UserRepository;
import com.AnnualProject.February.service.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

       private final UserServiceImp userServiceImp;


       @GetMapping("/signup")
       public String showSignupForm(Model model) {
              model.addAttribute("userDto", new UserDto());
              return "signup";
       }

       @PostMapping("/signup")
       public String createAccount(@RequestParam("file") MultipartFile file, UserDto userDto) {
              userServiceImp.createAccount(userDto, file);
              return "redirect:/api/v1/login";
       }


       @GetMapping("/login")
       public String showLoginForm() {
              return "login";
       }


       @GetMapping("/home")
       public String showHomePage() {
              return "home";
       }
}
