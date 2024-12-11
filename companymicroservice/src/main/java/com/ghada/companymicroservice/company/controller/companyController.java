package com.ghada.companymicroservice.company.controller;

import com.ghada.companymicroservice.company.Dao.company;
import com.ghada.companymicroservice.company.service.companyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/company")
@RequiredArgsConstructor
public class companyController {

    // Use the correct field name for companyService (companyServiceInterface) and make it final
     @Autowired
    companyServiceImpl companyServ;



    @PostMapping("/createCompany")
    public ResponseEntity<String> createCompany(@RequestBody company company) {
        companyServ.createCompany(company);
        return new ResponseEntity<>("company Created successfully", HttpStatus.OK);
    }
    @GetMapping("/getcompanyById/{id}")
    public ResponseEntity<Optional<company>> findcompanyById(@PathVariable Long id){
        return new ResponseEntity<>(companyServ.findCompanyById(id),HttpStatus.OK);
    }

    @DeleteMapping("/deletecompany/{id}")
    public boolean deleteCompanyById(@PathVariable  Long id){
     return companyServ.deleteCompanyById(id);
    }
    @PostMapping("/updateCompany/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody company updatedCompany){
        companyServ.updateCompany(id,updatedCompany);
        return new ResponseEntity<>("company updated successfully", HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<company>> findAll(){

        return new ResponseEntity<>(companyServ.findAll(), HttpStatus.OK);

    }



}
