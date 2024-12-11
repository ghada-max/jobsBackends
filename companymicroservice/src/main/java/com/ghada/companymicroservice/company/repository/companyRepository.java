package com.ghada.companymicroservice.company.repository;

import com.ghada.companymicroservice.company.Dao.company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface companyRepository extends JpaRepository<company,Long> {
}
