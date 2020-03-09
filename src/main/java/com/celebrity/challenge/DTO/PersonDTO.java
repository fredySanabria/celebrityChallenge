package com.celebrity.challenge.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonDTO {
    private int id;
    private String name;
    private String job;
    private String image;
    private Long votes;
}
