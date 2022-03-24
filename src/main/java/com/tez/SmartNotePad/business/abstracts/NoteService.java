package com.tez.SmartNotePad.business.abstracts;

import com.tez.SmartNotePad.business.dtos.NoteDto;
import com.tez.SmartNotePad.business.dtos.NoteDtoList;
import com.tez.SmartNotePad.business.dtos.UserDto;
import com.tez.SmartNotePad.business.dtos.UserDtoList;
import com.tez.SmartNotePad.business.requests.createRequests.CreateNoteRequest;
import com.tez.SmartNotePad.business.requests.createRequests.CreateUserRequest;
import com.tez.SmartNotePad.business.requests.deleteRequests.DeleteNoteRequest;
import com.tez.SmartNotePad.business.requests.updateRequests.UpdateNoteRequest;
import com.tez.SmartNotePad.business.requests.updateRequests.UpdateUserRequest;
import com.tez.SmartNotePad.core.utilities.exceptions.BusinessException;
import com.tez.SmartNotePad.core.utilities.results.Result;
import com.tez.SmartNotePad.core.utilities.results.SuccessDataResult;

import java.util.List;

public interface NoteService {
    Result createNote(CreateNoteRequest createNoteRequest) throws BusinessException;

    SuccessDataResult<List<NoteDtoList>>getAll();
    SuccessDataResult<NoteDto> getNoteById(int id) throws BusinessException;

    Result deleteById(DeleteNoteRequest deleteNoteRequest) throws BusinessException;

    Result update(UpdateNoteRequest updateNoteRequest) throws BusinessException;

}
