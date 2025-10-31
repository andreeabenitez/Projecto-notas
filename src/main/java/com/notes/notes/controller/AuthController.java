package com.notes.notes.controller;

import com.notes.notes.exception.DuplicateUsernameException;
import com.notes.notes.service.AuthService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Validated
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/login")
    public String login(@RequestParam @NotBlank String username,
                        @RequestParam @NotBlank String password,
                        Model model) {

        boolean success = authService.login(username, password);

        if (success) {
            return "redirect:/index"; // ✅ Muestra index.html
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login"; // 🔁 Vuelve al login con mensaje de error
        }
    }

    @PostMapping("/register")
    public String register(@RequestParam @NotBlank String username, @RequestParam @NotBlank String password, Model model) {

        try {
            authService.register(username, password);
            return "redirect:/login?registered";
        } catch (DuplicateUsernameException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }

    }

}

