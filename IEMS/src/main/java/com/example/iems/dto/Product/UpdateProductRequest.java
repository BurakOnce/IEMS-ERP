package com.example.iems.dto.Product;

import com.example.iems.model.Company;
import com.example.iems.model.Enum.QuantityUnit;
import com.example.iems.model.Product;
import com.example.iems.model.User;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;


@Builder
@Getter
@Setter
public class UpdateProductRequest {
    private Long id;
    private String name;
    private String description;
    private String category;
    private Set<QuantityUnit> quantityUnits;
    private Long stock;
    private Long demand;
    private String barcode;
    private Long discount;
    private Float price;
    private Company company;
}

