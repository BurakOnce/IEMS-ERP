package com.example.iems.dto.User;

import com.example.iems.model.Company;
import com.example.iems.model.Enum.Role;
import lombok.Builder;

import java.util.Set;


@Builder
public record CreateUserRequest(
        String name,
        String username,
        String password,
        String town,
        String city,
        Set<Role> authorities,

        Company company
){
}
