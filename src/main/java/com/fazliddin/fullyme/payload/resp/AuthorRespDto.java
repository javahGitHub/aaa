package com.fazliddin.fullyme.payload.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthorRespDto {
    private String firstName;
    private String lastName;
    private String about;
    private String websiteLink;
    private String githubLink;
    private String linkedInLink;
}
