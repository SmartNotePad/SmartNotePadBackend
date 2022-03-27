package com.tez.SmartNotePad.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShareNoteRequest {

    private int ownerUserId;

    private int noteId;

    private String mailToShare;

}
