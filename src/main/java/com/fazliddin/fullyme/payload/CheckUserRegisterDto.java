package com.fazliddin.fullyme.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class CheckUserRegisterDto {

    private String accessToken;
    private String refreshToken;
    private boolean registered;

    public CheckUserRegisterDto() {
        this.accessToken = null;
        this.refreshToken = null;
        this.registered = false;
    }

    public CheckUserRegisterDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.registered = true;
    }
}
