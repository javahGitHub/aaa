package com.fazliddin.fullyme.payload.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class CourseDto {
    @NotBlank(message = "COURSE_NAME_REQUIRED")
    private String name;
    private String about;
    @NotBlank(message = "COURSE_PRICE_REQUIRED")
    private Double price;
    private String requirements;
    private UUID authorId;
    private UUID sectionId;
}
