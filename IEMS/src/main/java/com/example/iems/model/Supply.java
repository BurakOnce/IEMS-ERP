package com.example.iems.model;

import com.example.iems.model.Enum.OrderStatus;
import com.example.iems.model.Enum.QuantityUnit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "supply")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Supply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private String contactPersonName;
    private String contactPersonPhone;
    private String city;


    private String categories;
    private String quantity;
    @Enumerated(EnumType.STRING)
    @Column(name = "quantity_unit", nullable = false)
    private Set<QuantityUnit> quantityUnits;

    private String orderNotes;
    private String companyAddress;


    @ManyToOne
    private User supplier;

    private LocalDate orderDate;
    private int deliveryTimeInDays;

    private boolean isOpen;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private Set<OrderStatus> orderStatus;


    @ManyToOne
    private Company company;



}
