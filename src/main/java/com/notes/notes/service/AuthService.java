package com.notes.notes.service;

import com.notes.notes.exception.DuplicateUsernameException;
import com.notes.notes.model.AppUser;
import com.notes.notes.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final AppUserRepository repo;
    private final PasswordEncoder encoder;

    @Autowired
    public AuthService(AppUserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public void register(String username, String rawPassword) throws DuplicateUsernameException {

        if (repo.existsByUsername(username)) {
            throw new DuplicateUsernameException("error, El usuario ya existe");
        }
        AppUser u = new AppUser();
        u.setUsername(username);
        u.setPassword(encoder.encode(rawPassword));
        u.setRole("ROLE_USER");
        repo.save(u);
    }
    public boolean login(String username, String rawPassword) {
        Optional<AppUser> optionalUser = repo.findByUsername(username);

        if (optionalUser.isEmpty()) {
            return false;
        }

        AppUser user = optionalUser.get();

        return encoder.matches(rawPassword, user.getPassword());
    }
}
