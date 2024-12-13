package com.ghada.microservice.job.service;

import com.ghada.microservice.job.Dao.job;
import com.ghada.microservice.job.external.company;
import com.ghada.microservice.job.external.jobWithCompanyDto;
import com.ghada.microservice.job.external.review;
import com.ghada.microservice.job.feignClients.feignCompanyClient;
import com.ghada.microservice.job.repository.jobRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class JobServiceImpl {
    @Autowired
    jobRepository repository;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private feignCompanyClient companyClient;

    public List<jobWithCompanyDto> findAll() {
        List<job> jobs= repository.findAll();
        List<jobWithCompanyDto> jobsWithCompany=new ArrayList<>();

        for (job job : jobs) {
            jobWithCompanyDto jobWithCompany=new jobWithCompanyDto();

            jobWithCompany.setJob(job);
          company comp=  restTemplate.getForObject("http://companymicroservice:8023/api/company/getcompanyById/"+job.getCompanyId(), company.class);


            jobWithCompany.setCompany(comp);
            jobsWithCompany.add(jobWithCompany);

        }
        return jobsWithCompany;
    }

    public void createJob(job job) {

        repository.save(job);
    }

    public jobWithCompanyDto findJobById(Long id){
        jobWithCompanyDto jobWithCompany=new jobWithCompanyDto();
        Optional<job> jobOptional = repository.findById(id);

        job job = jobOptional.orElse(null); // This will return null if the job is not found
        jobWithCompany.setJob(job);
        //company comp=  restTemplate.getForObject("http://companymicroservice:8023/api/company/getcompanyById/"+job.getCompanyId(), company.class);
        Optional<company> compOp=companyClient.getCompany(job.getCompanyId());
        company comp=compOp.orElse(null);

                jobWithCompany.setCompany(comp);
        ResponseEntity<List<review>> reviewResponse=restTemplate.exchange("http://reviewmicroservice:2024/api/reviews/getAll?companyId=" + job.getCompanyId()
                , HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<review>>() {
                });
        List<review> reviewList=reviewResponse.getBody();
        jobWithCompany.setReview(reviewList);
        return jobWithCompany;
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
