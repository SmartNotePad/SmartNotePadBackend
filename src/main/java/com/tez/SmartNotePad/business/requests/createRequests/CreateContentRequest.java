package com.tez.SmartNotePad.business.requests.createRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateContentRequest {

    private int userId;

    private int noteId;

    private String context;

    @Min(0)
    @Max(2)
    private int type;

}
