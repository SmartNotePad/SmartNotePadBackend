package com.tez.SmartNotePad.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDto {
    private int noteId;
    private String title;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private List<ContentDto> contentsContentDtos;
    private List<UserDtoList> participantUsersUserId;
}
