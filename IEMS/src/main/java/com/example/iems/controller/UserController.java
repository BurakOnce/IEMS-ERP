package com.example.iems.controller;


import com.example.iems.dto.AuthRequest;
import com.example.iems.dto.User.CreateUserRequest;
import com.example.iems.dto.User.UpdateUserRequest;
import com.example.iems.model.Enum.Role;
import com.example.iems.model.User;
import com.example.iems.service.JwtService;
import com.example.iems.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/appUser")
@Slf4j
public class UserController {

    private final UserService service;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;


    public UserController(UserService service, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.service = service;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome Burak's App";
    }

    @PostMapping("/admin/addNewUser")
    public ResponseEntity<Map<String, String>> addUser(@RequestBody CreateUserRequest request) {
        return service.createUser(request);
    }

    @PostMapping("/admin/updateUser")
    public User updateUser(String username , @RequestBody UpdateUserRequest request) {
        return service.updateUser(username,request);
    }

    @DeleteMapping("/admin/deleteUser")
    public ResponseEntity<String> deleteUser(String username) {
        service.deleteUser(username);
        return ResponseEntity.ok( "User has deleted from database");
    }

    @PostMapping("/generateToken")
    public String generateToken(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(request.username());
        }
        log.info("invalid username " + request.username());
        throw new UsernameNotFoundException("invalid username {} " + request.username());
    }


    @GetMapping("/admin/getUser/city")
    public List<User> getUserByCity(String city) {return service.getUserByCity(city);}

    @GetMapping("/admin/getUser/town")
    public List<User> getUserByTown(String town) {return service.getUserByTown(town);}

    @GetMapping("/admin/getUser/role")
    public Optional<User> getUserByRole(Role role) {return service.getUserByRole(role);}

    @GetMapping("/admin/getUser/username")
    public User getUserByUsername(String username) {return service.getUserByUsername(username);}

    @GetMapping("/admin/getUser/all")
    public List<User> getAllUser(){return service.getAllUser();}

    @GetMapping("/admin/getUser/count")
    public Long countUser(){return service.countUsers();}

    @GetMapping("/free/getCompanyId")
    public Optional<Long> getCompanyId(Long userId){return service.getCompanyId(userId);}


}