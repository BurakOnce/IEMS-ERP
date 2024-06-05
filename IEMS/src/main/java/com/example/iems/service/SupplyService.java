package com.example.iems.service;

import com.example.iems.dto.Supply.CreateSupplyRequest;
import com.example.iems.model.Company;
import com.example.iems.model.Enum.OrderStatus;
import com.example.iems.model.Enum.QuantityUnit;
import com.example.iems.model.Product;
import com.example.iems.model.Supply;
import com.example.iems.model.User;
import com.example.iems.repository.SupplyRepository;
import com.example.iems.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SupplyService {

    final private SupplyRepository supplyRepository;
    final private UserRepository userRepository;

    public SupplyService(SupplyRepository supplyRepository, UserRepository userRepository) {
        this.supplyRepository = supplyRepository;
        this.userRepository= userRepository;
    }


    public ResponseEntity<Map<String, String>> createSupply(CreateSupplyRequest request) {
        String contactPersonName = request.getContactPersonName();
        Company company = request.getCompany();
        Optional<Supply> existingSupply = supplyRepository.findByProductNameAndCompany(contactPersonName, company);
        if (existingSupply.isPresent()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Supply already exists");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            Supply newSupply = Supply.builder()
                    .productName(request.getProductName())
                    .contactPersonName(request.getContactPersonName())
                    .contactPersonPhone(request.getContactPersonPhone())
                    .city(request.getCity())
                    .categories(request.getCategories())
                    .quantity(request.getQuantity())
                    .quantityUnits(request.getQuantityUnits())
                    .supplier(request.getSupplier())
                    .orderDate(request.getOrderDate())
                    .deliveryTimeInDays(request.getDeliveryTimeInDays())
                    .isOpen(request.isOpen())
                    .orderStatus(Collections.singleton(OrderStatus.PENDING))
                    .orderNotes(request.getOrderNotes())
                    .companyAddress(request.getCompanyAddress())
                    .company(request.getCompany())
                    .build();

            supplyRepository.save(newSupply);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Supply successfully created");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    public List<Supply> getAllSupplies() {
        return supplyRepository.findAll();
    }


    public ResponseEntity<String> deleteSupply(Long id) {
        Optional<Supply> optionalSupply = supplyRepository.findById(id);

        if (optionalSupply.isPresent()) {
            Supply existingSupply = optionalSupply.get();
            supplyRepository.delete(existingSupply);
            return ResponseEntity.ok("Supply deleted successfully");

        } else {
            return ResponseEntity.ok("Supply has not found in database");

        }
    }

    public Optional<Supply> getSupplyById(Long id) {
        return supplyRepository.findById(id);
    }

    public List<Supply> getSupplyOpen(Long companyId) {
        return supplyRepository.findByIsOpenTrueAndCompanyId(companyId);
    }

    public List<Supply> getSupplyClose(Long companyId) {
        return supplyRepository.findByIsOpenFalseAndCompanyId(companyId)
                .stream()
                .filter(supply -> !supply.getOrderStatus().contains(OrderStatus.CONFIRMED))
                .collect(Collectors.toList());
    }

    public List<Supply> getSupplyCloseAndConfirmed() {
        return supplyRepository.findByIsOpenFalse()
                .stream()
                .filter(supply -> supply.getOrderStatus().contains(OrderStatus.CONFIRMED))
                .collect(Collectors.toList());
    }


    public List<Supply> getSupplyPending(Long companyId) {
        Set<OrderStatus> statuses = new HashSet<>();
        statuses.add(OrderStatus.PENDING);
        return supplyRepository.findByOrderStatusInAndCompanyId(statuses,companyId);

    }

    public List<Supply> getSupplyDuration(Long companyId) {
        Set<OrderStatus> statuses = new HashSet<>();
        statuses.add(OrderStatus.DURATION);
        return supplyRepository.findByOrderStatusInAndCompanyId(statuses,companyId);

    }

    public List<Supply> getSupplyComplete(Long companyId) {
        Set<OrderStatus> statuses = new HashSet<>();
        statuses.add(OrderStatus.COMPLETE);
        return supplyRepository.findByOrderStatusInAndCompanyId(statuses,companyId);

    }

    public List<Supply> getSupplyUnsuccessful(Long companyId) {
        Set<OrderStatus> statuses = new HashSet<>();
        statuses.add(OrderStatus.UNSUCCESSFUL);
        return supplyRepository.findByOrderStatusInAndCompanyId(statuses,companyId);

    }

    public List<Supply> getSupplyConfirmed(Long companyId) {
        Set<OrderStatus> statuses = new HashSet<>();
        statuses.add(OrderStatus.CONFIRMED);
        return supplyRepository.findByOrderStatusInAndCompanyId(statuses,companyId);

    }

    public ResponseEntity<String> publicationSupply(Long id) {
        Optional<Supply> optionalSupply = supplyRepository.findById(id);

        if (optionalSupply.isPresent()) {
            Supply existingSupply = optionalSupply.get();

            existingSupply.setOpen(true);
            supplyRepository.save(existingSupply);

            return ResponseEntity.ok("Supply publication successfully");

        } else {
            return ResponseEntity.ok("Supply has not found in database");

        }
    }


    public ResponseEntity<String> closingSupply(Long id) {
        Optional<Supply> optionalSupply = supplyRepository.findById(id);

        if (optionalSupply.isPresent()) {
            Supply existingSupply = optionalSupply.get();

            existingSupply.setOpen(false);
            supplyRepository.save(existingSupply);

            return ResponseEntity.ok("Supply closing successfully");

        } else {
            return ResponseEntity.ok("Supply has not found in database");

        }
    }

    public ResponseEntity<String> toPendingSupply(Long id) {
        Optional<Supply> optionalSupply = supplyRepository.findById(id);

        if (optionalSupply.isPresent()) {
            Supply existingSupply = optionalSupply.get();

            existingSupply.setOrderStatus(Collections.singleton(OrderStatus.PENDING));

            supplyRepository.save(existingSupply);

            return ResponseEntity.ok("Supply Order to PENDING successfully");

        } else {
            return ResponseEntity.ok("Supply has not found in database");
        }
    }

    public ResponseEntity<String> toDurationSupply(Long id) {
        Optional<Supply> optionalSupply = supplyRepository.findById(id);

        if (optionalSupply.isPresent()) {
            Supply existingSupply = optionalSupply.get();

            existingSupply.setOrderStatus(Collections.singleton(OrderStatus.DURATION));

            supplyRepository.save(existingSupply);

            return ResponseEntity.ok("Supply Order to Duration successfully");

        } else {
            return ResponseEntity.ok("Supply has not found in database");
        }
    }

    public ResponseEntity<String> toCompleteSupply(Long id) {
        Optional<Supply> optionalSupply = supplyRepository.findById(id);

        if (optionalSupply.isPresent()) {
            Supply existingSupply = optionalSupply.get();

            existingSupply.setOrderStatus(Collections.singleton(OrderStatus.COMPLETE));

            supplyRepository.save(existingSupply);

            return ResponseEntity.ok("Supply Order to Complete successfully");

        } else {
            return ResponseEntity.ok("Supply has not found in database");
        }
    }

    public ResponseEntity<String> toUnsuccessfulSupply(Long id) {
        Optional<Supply> optionalSupply = supplyRepository.findById(id);

        if (optionalSupply.isPresent()) {
            Supply existingSupply = optionalSupply.get();

            existingSupply.setOrderStatus(Collections.singleton(OrderStatus.UNSUCCESSFUL));

            supplyRepository.save(existingSupply);

            return ResponseEntity.ok("Supply Order to Unsuccessful successfully");

        } else {
            return ResponseEntity.ok("Supply has not found in database");
        }
    }

    public ResponseEntity<String> toConfirmedSupply(Long id) {
        Optional<Supply> optionalSupply = supplyRepository.findById(id);

        if (optionalSupply.isPresent()) {
            Supply existingSupply = optionalSupply.get();

            existingSupply.setOrderStatus(Collections.singleton(OrderStatus.CONFIRMED));

            supplyRepository.save(existingSupply);

            return ResponseEntity.ok("Supply Order to Confirmed successfully");

        } else {
            return ResponseEntity.ok("Supply has not found in database");
        }
    }


    public List<Supply> getOpenPendingSupplies() {
        Set<OrderStatus> pendingStatuses = new HashSet<>();
        pendingStatuses.add(OrderStatus.PENDING);

        List<Supply> pendingSupplies = supplyRepository.findByOrderStatusIn(pendingStatuses);

        List<Supply> openPendingSupplies = pendingSupplies.stream()
                .filter(supply -> supply.isOpen() && supply.getSupplier() == null)
                .collect(Collectors.toList());

        return openPendingSupplies;
    }


    public ResponseEntity<Map<String, String>> updateSupplierAtSupply(Long supplyId, Long supplierId) {
        Optional<Supply> optionalSupply = supplyRepository.findById(supplyId);
        Optional<User> optionalSupplier = userRepository.findById(supplierId);

        if (optionalSupply.isPresent() && optionalSupplier.isPresent()) {
            Supply supply = optionalSupply.get();
            User supplier = optionalSupplier.get();

            supply.setSupplier(supplier);
            supplyRepository.save(supply);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Supply successfully updated with new supplier");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Supply or supplier not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    public List<Supply> getSuppliesBySupplierDuration(Long supplierId) {
        List<Supply> supplies = supplyRepository.findSupplyBySupplierId(supplierId);
        return supplies.stream()
                .filter(supply -> supply.getOrderStatus().contains(OrderStatus.DURATION))
                .collect(Collectors.toList());
    }

    public List<Supply> getSuppliesBySupplierComplete(Long supplierId) {
        List<Supply> supplies = supplyRepository.findSupplyBySupplierId(supplierId);
        return supplies.stream()
                .filter(supply -> supply.getOrderStatus().contains(OrderStatus.COMPLETE))
                .collect(Collectors.toList());
    }

    public List<Supply> getSuppliesBySupplierUnsuccessful(Long supplierId) {
        List<Supply> supplies = supplyRepository.findSupplyBySupplierId(supplierId);
        return supplies.stream()
                .filter(supply -> supply.getOrderStatus().contains(OrderStatus.UNSUCCESSFUL))
                .collect(Collectors.toList());
    }

    public List<Supply> getSuppliesBySupplierConfirmed(Long supplierId) {
        List<Supply> supplies = supplyRepository.findSupplyBySupplierId(supplierId);
        return supplies.stream()
                .filter(supply -> supply.getOrderStatus().contains(OrderStatus.CONFIRMED))
                .collect(Collectors.toList());
    }

    public ResponseEntity<String> removeSupplierFromSupply(Long supplyId) {
        Optional<Supply> optionalSupply = supplyRepository.findById(supplyId);

        if (optionalSupply.isPresent()) {
            Supply existingSupply = optionalSupply.get();
            existingSupply.setSupplier(null);
            supplyRepository.save(existingSupply);

            return ResponseEntity.ok("Supplier removed from supply successfully");
        } else {
            return ResponseEntity.ok("Supply not found in database");
        }
    }

    public List<Supply> getSuppliesByCompanyId(Long companyId) {
        if (companyId != null) {
            return supplyRepository.findSupplyByCompanyId(companyId);
        } else {
            return supplyRepository.findSupplyByCompanyIdIsNull();
        }
    }

    public String getNote(Long supplyId) {
        Optional<Supply> optionalSupply = supplyRepository.findById(supplyId);

        if (optionalSupply.isPresent()) {
            Supply existingSupply = optionalSupply.get();
            return existingSupply.getOrderNotes();

        } else {
            return "Kullanıcı bulunamadı";
        }
    }





}
