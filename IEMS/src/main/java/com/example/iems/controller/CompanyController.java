package com.example.iems.controller;

import com.example.iems.dto.Company.CreateCompanyRequest;
import com.example.iems.dto.Company.UpdateCompanyRequest;
import com.example.iems.model.Company;
import com.example.iems.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/appCompany")
public class CompanyController {

    private final CompanyService companyService;


    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome Burak's App";
    }

    @PostMapping("/admin/createCompany")
    public ResponseEntity<Map<String, String>> createCompany(@RequestBody CreateCompanyRequest request) {
        return companyService.createCompany(request);
    }

    @PostMapping("/admin/updateCompany")
    public Company updateCompany(Long companyId , @RequestBody UpdateCompanyRequest request){
        return companyService.updateCompany(companyId,request);
    }

    @GetMapping("/admin/getCompany/all")
    public List<Company> getAllCompany(){return companyService.getAllCompanies();}

    @DeleteMapping("/admin/deleteCompany")
    public ResponseEntity<String> deleteCompany(String name) {
        companyService.deleteCompany(name);
        return ResponseEntity.ok( "Company has deleted from database");
    }


    @GetMapping("/admin/getCompany/city")
    public List<Company> getCompanyByCity(String city) {return companyService.getCompanyByCity(city);}

    @GetMapping("/admin/getCompany/town")
    public List<Company> getCompanyByTown(String town) {return companyService.getCompanyByTown(town);}

    @GetMapping("/admin/getCompany/sector")
    public List<Company> getCompanyBySector(String sector) {return companyService.getCompanyBySector(sector);}

    @GetMapping("/admin/getCompany/name")
    public Company getCompanyByName(String name){return companyService.getCompanyByName(name);}

    @GetMapping("/admin/getCompany/id")
    public Company getCompanyById(Long id){return companyService.getCompanyById(id);}
}
