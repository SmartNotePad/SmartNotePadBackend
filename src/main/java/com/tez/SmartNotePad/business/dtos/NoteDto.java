package com.tez.SmartNotePad.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDto {
    private int noteId;
    private String title;
    private int userUserId;
    private String userMail;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private List<ContentDto> contentsContentDtos;
    private List<UserDtoList> participantUsersUserId;
}
