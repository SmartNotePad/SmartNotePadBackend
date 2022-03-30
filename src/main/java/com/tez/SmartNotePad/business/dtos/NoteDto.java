package com.tez.SmartNotePad.business.dtos;

import com.tez.SmartNotePad.entities.concretes.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDto {
    private int noteId;
    private int ownerUserId;
    private List<User> participantUsersId;
    private List<ContentDto> contentsContentDtos;
}
