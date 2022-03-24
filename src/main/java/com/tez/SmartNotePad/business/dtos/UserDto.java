package com.tez.SmartNotePad.business.dtos;


import com.tez.SmartNotePad.entities.concretes.Note;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private int userId;

    private String mail;

    private String nameSurname;

}
