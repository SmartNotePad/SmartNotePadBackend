package com.tez.SmartNotePad.business.requests.updateRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    private int userId;

    private String nameSurname;

    private String password;
}
