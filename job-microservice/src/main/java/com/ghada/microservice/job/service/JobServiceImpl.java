package com.ghada.microservice.job.service;

import com.ghada.microservice.job.Dao.job;
import com.ghada.microservice.job.repository.jobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class JobServiceImpl {
    @Autowired
    jobRepository repository;

    public List<job> findAll() {
        return repository.findAll();
    }

    public void createJob(job job) {

        repository.save(job);
    }

    public Optional<job> findJobById(Long id){

        return repository.findById(id);
    }


    public boolean deleteJobById(Long id){
       try {repository.deleteById(id);
       return true;}
       catch(Exception e){
           return false ;
       }
    }

    public boolean updateJob(Long id, job updatedJob){

      return repository.findById(id).map(
              existingJob ->
      {
          existingJob.setDescription(updatedJob.getDescription());
          existingJob.setLocation(updatedJob.getLocation());
          existingJob.setTitle(updatedJob.getTitle());
          existingJob.setMinSalary(updatedJob.getMinSalary());
          existingJob.setMaxSalary(updatedJob.getMaxSalary());

         repository.save(existingJob);
         return true;
      }).orElse( false);


    }
}
