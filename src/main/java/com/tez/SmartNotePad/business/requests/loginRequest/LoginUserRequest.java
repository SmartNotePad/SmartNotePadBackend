package com.tez.SmartNotePad.business.requests.loginRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserRequest {
    @Email
    private String mail;

    private String password;
}
