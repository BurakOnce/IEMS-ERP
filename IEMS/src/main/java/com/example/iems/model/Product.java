package com.example.iems.model;
import com.example.iems.model.Enum.QuantityUnit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
@Entity
@Table(name = "product")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;
    private String category;


    @Enumerated(EnumType.STRING)
    @Column(name = "quantity_unit", nullable = false)
    private Set<QuantityUnit> quantityUnits;

    private Long stock;
    private Long demand;

    @Column(unique = true, nullable = false,length = 8)
    private String barcode;

    private Long discount;
    private Float price;

    @ManyToOne
    private Company company;


}