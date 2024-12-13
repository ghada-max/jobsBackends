package com.ghada.microservice.job.external;
import com.ghada.microservice.job.Dao.job;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class jobWithCompanyDto {

    private company company;
    private job job;
    private List<review> review;
}
