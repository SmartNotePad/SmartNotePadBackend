package com.tez.SmartNotePad.business.requests.createRequests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    private String nameSurname;

    private String mail;

    private String password;

}
