package com.ghada.reviewmicroservice.Review.controller;

import com.ghada.reviewmicroservice.Review.Dao.review;
import com.ghada.reviewmicroservice.Review.service.reviewServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    // Use the correct field name for companyService (companyServiceInterface) and make it final
     @Autowired
    reviewServiceImpl reviewServ;



    @PostMapping("/createReview")
    public ResponseEntity<String> createCompany(@RequestBody review review, @RequestParam Long companyId) {
        reviewServ.createReview(review,companyId);
        return new ResponseEntity<>("review Created successfully", HttpStatus.OK);
    }
    @GetMapping("/getReviewById/{id}")
    public ResponseEntity<Optional<review>> findcompanyById(@PathVariable Long id){
        return new ResponseEntity<>(reviewServ.findReviewById(id),HttpStatus.OK);
    }

    @DeleteMapping("/deleteReview/{id}")
    public boolean deleteReviewById(@PathVariable  Long id){
     return reviewServ.deleteCompanyById(id);
    }

    @PostMapping("/updateReview/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody review updatedReview){
        reviewServ.updateReview(id,updatedReview);
        return new ResponseEntity<>("review updated successfully", HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<review>> findAll(@RequestParam Long companyId ){

        return new ResponseEntity<>(reviewServ.findAll(companyId), HttpStatus.OK);

    }



}
