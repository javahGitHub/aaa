package com.fazliddin.fullyme.payload.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReqDto {

    private String firstName;

    private String lastName;

    @NotBlank
    private String phoneNumber;

    @Pattern(regexp = "\\+[9]{2}[8][0-9]{9}", message = "{WRONG_PHONE_NUMBER_FORMAT}")
    @NotBlank(message = "{PHONE_NUMBER_REQUIRED}")
    private Long districtId;

    private UUID photoId;
}
