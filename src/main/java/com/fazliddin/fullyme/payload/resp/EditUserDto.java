package com.fazliddin.fullyme.payload.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EditUserDto {
    @NotBlank(message = "{FIRST_NAME_REQUIRED}")
    private String firstName;

    private String lastName;

    @Pattern(regexp = "\\+[9]{2}[8][0-9]{9}", message = "{WRONG_PHONE_NUMBER_FORMAT}")
    @NotBlank(message = "{PHONE_NUMBER_REQUIRED}")
    private String phoneNumber;

    private UUID photoId;

}
