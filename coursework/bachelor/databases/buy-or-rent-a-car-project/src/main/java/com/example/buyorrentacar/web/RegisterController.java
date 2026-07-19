package com.example.buyorrentacar.web;


import com.example.buyorrentacar.model.Role;
import com.example.buyorrentacar.model.exceptions.InvalidArgumentsException;
import com.example.buyorrentacar.model.exceptions.PassworDoNotMatchException;
import com.example.buyorrentacar.service.AuthService;
import com.example.buyorrentacar.service.CovekService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;
    private final CovekService userService;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(AuthService authService, CovekService userService, PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model)
    {
        if(error!=null && !error.isEmpty())
        {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        model.addAttribute("bodyContent","register");
        return "master-template";
    }
    @PostMapping
    public String register(@RequestParam String ime,
                           @RequestParam String prezime,
                           @RequestParam String telefBroj,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String adresa,
                           @RequestParam Role role)
    {
        try{
            userService.register(ime,prezime,telefBroj,email,passwordEncoder.encode(password),adresa,role);
            return "redirect:/login";
        }
        catch (PassworDoNotMatchException | InvalidArgumentsException exception)
        {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}
