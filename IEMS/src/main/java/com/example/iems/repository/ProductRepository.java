package com.example.iems.repository;

import com.example.iems.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);

    Optional<Product> findByBarcode(String barcode);
    List<Product> findByCompanyId(Long companyId);
}
