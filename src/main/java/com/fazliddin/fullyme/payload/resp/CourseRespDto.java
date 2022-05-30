package com.fazliddin.fullyme.payload.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class CourseRespDto {
    private String name;
    private String about;
    private Double price;
    private String requirements;
    private UUID authorId;
    private UUID sectionId;
}
