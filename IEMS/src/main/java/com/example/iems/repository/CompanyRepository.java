package com.example.iems.repository;

import com.example.iems.model.Company;
import com.example.iems.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByName(String Name);

    Company findAllByName(String Name);

    List<Company> findCompanyByCity(String city);

    List<Company> findCompanyByTown(String town);

    List<Company> findCompanyBySector(String sector);


}
