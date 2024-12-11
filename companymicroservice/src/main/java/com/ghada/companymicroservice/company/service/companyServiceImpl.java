package com.ghada.companymicroservice.company.service;

import com.ghada.companymicroservice.company.Dao.company;
import com.ghada.companymicroservice.company.repository.companyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class companyServiceImpl {
    @Autowired
    companyRepository repository;
    public List<company> findAll() {
        return repository.findAll();
    }

    public void createCompany(company company) {
        repository.save(company);
    }

    public Optional<company> findCompanyById(Long id){
      return repository.findById(id);
    }


    public boolean deleteCompanyById(Long id) {
        Optional<company> company = repository.findById(id);

        if (company.isPresent()) {
            repository.deleteById(id);
            return true;
        }

        return false; // Return false if company not found
    }

    public boolean updateCompany(Long id, company updatedCompany){

      return repository.findById(id).map(
              existingCompany ->
      {
          existingCompany.setDescription(updatedCompany.getDescription());
          existingCompany.setName(updatedCompany.getName());
       //   existingCompany.setJobs(updatedCompany.getJobs());

         repository.save(existingCompany);
         return true;
      }).orElse( false);


    }
}
