package com.tez.SmartNotePad.business.abstracts;

import com.tez.SmartNotePad.business.dtos.*;
import com.tez.SmartNotePad.business.requests.ShareNoteRequest;
import com.tez.SmartNotePad.business.requests.createRequests.CreateNoteRequest;
import com.tez.SmartNotePad.business.requests.createRequests.CreateUserRequest;
import com.tez.SmartNotePad.business.requests.deleteRequests.DeleteNoteRequest;
import com.tez.SmartNotePad.business.requests.updateRequests.UpdateNoteRequest;
import com.tez.SmartNotePad.business.requests.updateRequests.UpdateNoteWithOutTitleRequest;
import com.tez.SmartNotePad.business.requests.updateRequests.UpdateUserRequest;
import com.tez.SmartNotePad.core.utilities.exceptions.BusinessException;
import com.tez.SmartNotePad.core.utilities.results.DataResult;
import com.tez.SmartNotePad.core.utilities.results.Result;
import com.tez.SmartNotePad.core.utilities.results.SuccessDataResult;
import com.tez.SmartNotePad.entities.concretes.Content;
import com.tez.SmartNotePad.entities.concretes.Note;

import java.util.List;

public interface NoteService {
    DataResult<NoteDto> createNote(CreateNoteRequest createNoteRequest) throws BusinessException;

    SuccessDataResult<List<NoteDto>>getAll();
    SuccessDataResult<NoteDto> getNoteById(int id) throws BusinessException;

    DataResult<NoteDto> deleteById(DeleteNoteRequest deleteNoteRequest) throws BusinessException;

    DataResult<NoteDto> update(UpdateNoteRequest updateNoteRequest) throws BusinessException;

    DataResult<NoteDto> shareNote(ShareNoteRequest shareNoteRequest) throws BusinessException;

    DataResult<List<NoteDto>>  getNotesByOwnerUserId(int id)throws BusinessException;

    DataResult<List<ContentDto>> getAllContentInNoteByNoteId(int id)throws BusinessException;

    void checkParticipantUsers(Note note, int userId)throws BusinessException;
    void checkNoteOwners(Note note,int userId) throws BusinessException;
    void checkNoteExists(int id) throws BusinessException;

    void updateNote(UpdateNoteWithOutTitleRequest updateNoteWithOutTitleRequest)throws BusinessException;
    Note getById(int id)throws BusinessException;



    DataResult<List<NoteDto>> getAllSorted(String ascOrDesc)throws BusinessException;
   // DataResult<ContentDto> getContentsByNoteId(int id)throws BusinessException;

}
