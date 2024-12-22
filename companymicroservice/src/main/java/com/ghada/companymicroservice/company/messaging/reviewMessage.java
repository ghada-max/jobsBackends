package com.ghada.companymicroservice.company.messaging;

import jakarta.persistence.Entity;
import lombok.*;
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
public class reviewMessage {

    private Long id;
    private String title;
    private String description;
    private double rating;
    private Long companyId;


}
