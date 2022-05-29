package com.fazliddin.fullyme.payload.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
@Setter
public class AuthorDto {

    @NotBlank(message = "{AUTHOR_NAME_REQUIRED}")
    private String firstName;
    private String lastName;
    private String about;
    private String websiteLink;
    private String githubLink;
    private String linkedInLink;
}
