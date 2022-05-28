package com.fazliddin.fullyme.payload.resp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fazliddin.fullyme.entity.Attachment;
import com.fazliddin.fullyme.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private UUID id;

    private String firstName;

    private String lastName;

    private String username;

    private Set<String> authorities;

    private Attachment photo;

    private Role role;
}
