package com.example.iems.dto.Company;

import com.example.iems.model.Product;
import com.example.iems.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Builder
@Getter
@Setter
public class UpdateCompanyRequest {
    private String name;
    private String sector;
    private String town;
    private String city;

}

