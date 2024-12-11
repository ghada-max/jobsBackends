package com.ghada.microservice.job.controller;

import com.ghada.microservice.job.Dao.job;
import com.ghada.microservice.job.service.JobServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/job")
@RequiredArgsConstructor
public class jobController {

    // Use the correct field name for jobService (jobServiceInterface) and make it final
     @Autowired
    JobServiceImpl jobServ;



    @PostMapping("/createjob")
    public String createJob(@RequestBody job job) {
        jobServ.createJob(job);
        return "Job added successfully";
    }
    @GetMapping("/getJobById/{id}")
    public Optional<job> findJobById(@PathVariable Long id){
        return jobServ.findJobById(id);
    }

    @DeleteMapping("/deleteJob/{id}")
    public boolean deleteJobById(@PathVariable  Long id){
     return jobServ.deleteJobById(id);
    }
    @PostMapping("/updateJob/{id}")
    public boolean updateJob(@PathVariable Long id, @RequestBody job updatedJob){
       return jobServ.updateJob(id,updatedJob);
    }

    @GetMapping("/getAll")
    public List<job> findAll(){
        return jobServ.findAll();
    }



}
