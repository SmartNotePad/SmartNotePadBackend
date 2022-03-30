package com.tez.SmartNotePad.business.requests.createRequests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateNoteRequest {

    private int ownerUserId;

}
