package com.example.iems.dto.Supply;

import com.example.iems.model.Company;
import com.example.iems.model.Enum.OrderStatus;
import com.example.iems.model.Enum.QuantityUnit;
import com.example.iems.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSupplyRequest {
    private String productName;

    private String contactPersonName;

    private String contactPersonPhone;

    private String city;

    @NotBlank(message = "Categories cannot be blank")
    private String categories;

    @NotNull(message = "Quantity cannot be null")
    private String quantity;

    @NotNull(message = "Quantity units cannot be null")
    private Set<QuantityUnit> quantityUnits;


    private User supplier;

    private String orderNotes;
    private String companyAddress;

    private LocalDate orderDate;
    private int deliveryTimeInDays;

    private boolean isOpen;

    @NotNull(message = "Order Status cannot be null")
    private Set<OrderStatus> orderStatus;

    private Company company;

}
