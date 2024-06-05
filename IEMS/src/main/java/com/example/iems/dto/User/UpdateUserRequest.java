package com.example.iems.dto.User;

import com.example.iems.model.Company;
import com.example.iems.model.Enum.Role;
import lombok.*;

import java.util.Set;


@Builder
@Getter
@Setter
public class UpdateUserRequest {
    
    private String name;
    private String username;
    private String password;
    private String town;
    private String city;
    private Set<Role> authorities;
    private Company company;
}

