package com.ghada.microservice.job.repository;

import com.ghada.microservice.job.Dao.job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface jobRepository extends JpaRepository<job,Long> {
}
