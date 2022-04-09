package com.tez.SmartNotePad.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMailDto {
    private int userId;
    private String nameSurname;
    private String mail;
}
