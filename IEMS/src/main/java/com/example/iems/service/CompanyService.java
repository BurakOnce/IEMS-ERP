package com.example.iems.service;

import com.example.iems.dto.Company.CreateCompanyRequest;
import com.example.iems.dto.Company.UpdateCompanyRequest;
import com.example.iems.model.Company;
import com.example.iems.repository.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CompanyService  {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public ResponseEntity<Map<String, String>> createCompany(CreateCompanyRequest request) {
        String name = request.name();
        if (companyRepository.findByName(name).isPresent()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Company already exists");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        Company newCompany = Company.builder()
                .name(request.name())
                .sector(request.sector())
                .town(request.town())
                .city(request.city())
                .build();

        companyRepository.save(newCompany);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Company successfully created");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    public Company updateCompany(Long companyId, UpdateCompanyRequest request) {
        Optional<Company> optionalCompany = companyRepository.findById(companyId);
        if (optionalCompany.isPresent()) {
            Company  existingCompany = optionalCompany.get();

            existingCompany.setName(request.getName() != null ? request.getName() : existingCompany.getName());
            existingCompany.setSector(request.getSector() != null ? request.getSector() : existingCompany.getSector());
            existingCompany.setTown(request.getTown() != null ? request.getTown() : existingCompany.getTown());
            existingCompany.setCity(request.getCity() != null ? request.getCity() : existingCompany.getCity());
            return companyRepository.save(existingCompany);
        } else {
            throw new EntityNotFoundException("Company not found : " + companyId);
        }
    }

    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    public void deleteCompany(String name){
        Optional<Company> optionalCompany = companyRepository.findByName(name);

        if (optionalCompany.isPresent()){
            Company existingCompany = optionalCompany.get();
            companyRepository.delete(existingCompany);
            ResponseEntity.ok(" Company deleted from database");

        }else {
            ResponseEntity.ok( "Company has not found in database");

        }
    }

    public List<Company> getCompanyByCity(String city){
        return companyRepository.findCompanyByCity(city);
    }

    public List<Company> getCompanyByTown(String town){
        return companyRepository.findCompanyByTown(town);
    }

    public List<Company> getCompanyBySector(String sector) {
        return companyRepository.findCompanyBySector(sector);
    }

    public Company getCompanyByName(String name) {return companyRepository.findByName(name).orElseThrow();}

    public Company getCompanyById(Long id) {return companyRepository.findById(id).orElseThrow();}



}

