package com.example.iems.controller;

import com.example.iems.model.Enum.Role;
import com.example.iems.model.User;
import com.example.iems.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final UserRepository userRepository;

    @Autowired
    public ProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/current-user")
    public User currentUser() {
        // Kullanıcının kimliğini al
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // Kullanıcıyı kullanıcı adına göre bul ve döndür
        return userRepository.findByUsername(currentUsername);
    }

    @GetMapping("/current-user-id")
    public Long getCurrentUserId() {
        // Kullanıcının kimliğini al
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // Kullanıcıyı kullanıcı adına göre bul
        User currentUser = userRepository.findByUsername(currentUsername);

        // Kullanıcı id'sini döndür
        return currentUser.getId();
    }

    @GetMapping("/current-user-role")
    public String getCurrentUserRole() {
        // Kullanıcının kimliğini al
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // Kullanıcıyı kullanıcı adına göre bul
        User currentUser = userRepository.findByUsername(currentUsername);

        // Kullanıcı rollerini al ve virgülle ayrılmış bir string olarak dönüştür
        String rolesAsString = currentUser.getAuthorities().stream()
                .map(Role::getAuthority)
                .collect(Collectors.joining(", "));

        return rolesAsString;
    }


    @GetMapping("/current-user-name")
    public String getCurrentUserName() {
        // Kullanıcının kimliğini al
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Kullanıcı adını döndür
        return authentication.getName();
    }


}
