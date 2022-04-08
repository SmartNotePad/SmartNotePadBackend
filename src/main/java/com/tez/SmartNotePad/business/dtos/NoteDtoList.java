package com.tez.SmartNotePad.business.dtos;

import com.tez.SmartNotePad.entities.concretes.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDtoList {
    private int noteId;
    private String title;
    private UserDtoList userDtoList;
    private List<ContentDto> contentsContentDtos;
}
