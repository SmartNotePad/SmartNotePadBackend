package com.tez.SmartNotePad.business.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentDto {

    private int contentId;

    private int userId;

    private String nameSurname;

    private String mail;

    private int noteId;

    private String context;

    private Timestamp createdDate;

    private Timestamp modifiedDate;

    @Min(0)
    @Max(2)
    private int type;

}
