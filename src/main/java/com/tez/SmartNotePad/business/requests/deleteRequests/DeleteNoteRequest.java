package com.tez.SmartNotePad.business.requests.deleteRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteNoteRequest {

    private int userId;
    private int noteId;

}
