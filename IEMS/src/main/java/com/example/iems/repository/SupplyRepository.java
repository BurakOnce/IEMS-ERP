package com.example.iems.repository;

import com.example.iems.model.Company;
import com.example.iems.model.Enum.OrderStatus;
import com.example.iems.model.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface SupplyRepository extends JpaRepository<Supply, Long> {

    Optional<Supply> findByProductNameAndCompany(String productName, Company company);
    List<Supply> findByOrderStatusIn(Set<OrderStatus> orderStatus);

    List<Supply> findByOrderStatusInAndCompanyId(Set<OrderStatus> orderStatus,Long companyId);


    List<Supply> findByIsOpenTrue();
    List<Supply> findByIsOpenFalse();

    List<Supply> findSupplyBySupplierId(Long supplierId);

    List<Supply> findSupplyByCompanyId(Long companyId);
    List<Supply> findSupplyByCompanyIdIsNull();

    List<Supply> findByIsOpenFalseAndCompanyId(Long companyId);
    List<Supply> findByIsOpenTrueAndCompanyId(Long companyId);



}

