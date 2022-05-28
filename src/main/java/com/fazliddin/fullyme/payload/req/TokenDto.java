package com.fazliddin.fullyme.payload.req;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TokenDto {

    @NotBlank(message = "{ACCESS_TOKEN_REQUIRED}")
    private String accessToken;

    @NotBlank(message = "{REFRESH_TOKEN_REQUIRED}")
    private String refreshToken;

    public TokenDto(String accessToken, String refreshToken) {

        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
