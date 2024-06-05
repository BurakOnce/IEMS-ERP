package com.example.iems.dto.Company;


import com.example.iems.model.Product;
import com.example.iems.model.User;
import lombok.Builder;
import java.util.List;

@Builder
public record CreateCompanyRequest(
        String name,
        String sector,
        String town,
        String city

        ) {

}