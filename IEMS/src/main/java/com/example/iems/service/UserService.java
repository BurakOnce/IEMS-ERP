package com.example.iems.service;

import com.example.iems.dto.User.CreateUserRequest;
import com.example.iems.dto.User.UpdateUserRequest;
import com.example.iems.model.Enum.Role;
import com.example.iems.model.User;
import com.example.iems.model.Company;
import com.example.iems.repository.CompanyRepository;
import com.example.iems.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.companyRepository=companyRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findUsersByUsername(username);
        return user.orElseThrow(EntityNotFoundException::new);
    }

    public Optional<User> getByUsername(String username) {
        return userRepository.findUsersByUsername(username);
    }

    public ResponseEntity<Map<String, String>> createUser(CreateUserRequest request) {
        String username = request.username();
        if (userRepository.findUsersByUsername(username).isPresent()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "User already exists");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        User newUser = User.builder()
                .name(request.name())
                .username(username)
                .password(passwordEncoder.encode(request.password()))
                .town(request.town())
                .city(request.city())
                .authorities(request.authorities())
                .company(request.company())
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .isEnabled(true)
                .accountNonLocked(true)
                .build();

        userRepository.save(newUser);

        Map<String, String> response = new HashMap<>();
        response.put("message", "User successfully added");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public User updateUser(String username, UpdateUserRequest request) {
        Optional<User> optionalUser = userRepository.findUsersByUsername(username);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            existingUser.setName(request.getName() != null ? request.getName() : existingUser.getName());
            existingUser.setUsername(request.getUsername() != null ? request.getUsername() : existingUser.getUsername());
            existingUser.setPassword(request.getPassword() != null ? passwordEncoder.encode(request.getPassword()) : existingUser.getPassword());
            existingUser.setTown(request.getTown() != null ? request.getTown() : existingUser.getTown());
            existingUser.setCity(request.getCity() != null ? request.getCity() : existingUser.getCity());
            existingUser.setAuthorities(request.getAuthorities() != null ? request.getAuthorities() : existingUser.getAuthorities());
            existingUser.setCompany(request.getCompany() !=null ? request.getCompany() : existingUser.getCompany());
            return userRepository.save(existingUser);
        } else {
            throw new EntityNotFoundException("User not found with username: " + username);
        }
    }


    public void deleteUser(String username){
        Optional<User> optionalUser = userRepository.findUsersByUsername(username);

        if (optionalUser.isPresent()){
            User existingUser = optionalUser.get();
            userRepository.delete(existingUser);
            ResponseEntity.ok(userRepository.findAuthoritiesByUsername(username) + " has deleted from database");

        }else {
            ResponseEntity.ok(userRepository.findAuthoritiesByUsername(username) + " has not found in database");

        }
    }






    public List<User> getUserByCity(String city){
        return userRepository.findUsersByCity(city);
    }

    public List<User> getUserByTown(String town){
        return userRepository.findUsersByTown(town);
    }

    public Optional<User> getUserByRole(Role role) {
        return userRepository.findUserByRole(role);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);}


    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public Long countUsers(){return (long) userRepository.findAll().size();
    }

    public Optional<Long> getCompanyId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(value -> Optional.ofNullable(value.getCompany()).map(Company::getId).orElse(null));
    }





}