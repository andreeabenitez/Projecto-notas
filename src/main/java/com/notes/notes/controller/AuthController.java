package com.notes.notes.controller;
import com.notes.notes.model.AppUser;
import com.notes.notes.model.Note;
import com.notes.notes.repository.AppUserRepository;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Validated
public class AuthController {

    private final AppUserService repo;
    private final PasswordEncoder encoder;

    public AuthController(AppUserService repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("userDto", new RegisterDto("", ""));
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("userDto") @Validated RegisterDto dto,Model model) {

        if(repo.existsByUsername(dto.username)) {
            model.addAttribute("error", "El usuario ya existe");
            return "register";
        }
        AppUser u = new AppUser();
        u.setUsername(dto.username);
        u.setPassword(encoder.encode(dto.password));
        u.setRole("ROLE_USER");

        repo.save(u);

        return "redirect:/login?registered";
    }

    public record RegisterDto(@NotBlank String username, @NotBlank String password) {


    }


}
