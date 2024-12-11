package com.ghada.reviewmicroservice.Review.service;


import com.ghada.reviewmicroservice.Review.Dao.review;
import com.ghada.reviewmicroservice.Review.repository.reviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class reviewServiceImpl {
    @Autowired
    reviewRepository repository;

    public List<review> findAll(Long companyId) {
        List<review> reviews=repository.findByCompanyId(companyId);
        return reviews;
    }

    public boolean createReview(review review,Long companyId) {
        review.setCompanyId(companyId);
        repository.save(review);
        return true;
    }

    public Optional<review> findReviewById(Long id){
      return repository.findById(id);
    }


    public boolean deleteCompanyById(Long id) {
        Optional<review> review = repository.findById(id);

        if (review.isPresent()) {
            repository.deleteById(id);
            return true;
        }

        return false; // Return false if company not found
    }

    public boolean updateReview(Long id, review updatedReview){
        review  rev=new review();
        rev= repository.findById(id).orElseThrow(()-> new EntityNotFoundException("review not found"));

        return repository.findById(id).map(
              existingReview ->
      {
          existingReview.setDescription(updatedReview.getDescription());
          existingReview.setTitle(updatedReview.getTitle());
          existingReview.setRating(updatedReview.getRating());
          existingReview.setCompanyId(updatedReview.getCompanyId());

         repository.save(existingReview);
         return true;
      }).orElse( false);


    }
}
