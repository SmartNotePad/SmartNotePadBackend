package com.tez.SmartNotePad.business.requests.updateRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateNoteWithOutTitleRequest {
    private int userUserId;
    private int noteId;
}
