package com.example.iems.dto.Product;


import com.example.iems.model.Company;
import com.example.iems.model.Enum.QuantityUnit;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
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

