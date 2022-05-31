package com.tez.SmartNotePad.business.requests.updateRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {


    private int userId;


    private String nameSurname;

    @NotNull(message = "You should have to type your password")
    private String password;
}
