package com.ghada.reviewmicroservice.Review.repository;

import com.ghada.reviewmicroservice.Review.Dao.review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface reviewRepository extends JpaRepository<review,Long> {
    List<review> findByCompanyId(Long companyId);
}
