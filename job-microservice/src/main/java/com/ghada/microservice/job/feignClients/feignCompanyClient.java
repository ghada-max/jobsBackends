package com.ghada.microservice.job.feignClients;
import com.ghada.microservice.job.external.company;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name="companymicroservice:8023/api/company")
public interface feignCompanyClient {
    @GetMapping("/getcompanyById/{id}")
    Optional<company> getCompany(@PathVariable("id") Long id);
}
