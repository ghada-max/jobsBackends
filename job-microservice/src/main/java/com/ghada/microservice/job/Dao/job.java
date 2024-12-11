package com.ghada.microservice.job.Dao;

import jakarta.persistence.*;
import lombok.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
@Entity
public class job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String minSalary;
    @Column
    private String maxSalary;
    @Column
    private String location;

    private Long companyId;
}
