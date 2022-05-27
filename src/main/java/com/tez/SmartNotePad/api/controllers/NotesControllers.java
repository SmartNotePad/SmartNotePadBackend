package com.tez.SmartNotePad.api.controllers;

import com.tez.SmartNotePad.business.abstracts.NoteService;
import com.tez.SmartNotePad.business.dtos.*;
import com.tez.SmartNotePad.business.requests.ShareNoteRequest;
import com.tez.SmartNotePad.business.requests.createRequests.CreateNoteRequest;
import com.tez.SmartNotePad.business.requests.createRequests.CreateUserRequest;
import com.tez.SmartNotePad.business.requests.deleteRequests.DeleteNoteRequest;
import com.tez.SmartNotePad.business.requests.deleteRequests.DeleteUserFromNoteRequest;
import com.tez.SmartNotePad.business.requests.updateRequests.UpdateNoteRequest;
import com.tez.SmartNotePad.business.requests.updateRequests.UpdateUserRequest;
import com.tez.SmartNotePad.core.utilities.exceptions.BusinessException;
import com.tez.SmartNotePad.core.utilities.results.DataResult;
import com.tez.SmartNotePad.core.utilities.results.Result;
import com.tez.SmartNotePad.core.utilities.results.SuccessDataResult;
import com.tez.SmartNotePad.entities.concretes.Note;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.List;

@RestController
@RequestMapping("api/notes")
public class NotesControllers {

    private final NoteService noteService;

    @Autowired
    public NotesControllers(NoteService noteService) {
        this.noteService = noteService;
    }
    @PostMapping("/add")
    public DataResult<NoteDto> add(@RequestBody CreateNoteRequest createNoteRequest) throws BusinessException {
        return noteService.createNote(createNoteRequest);
    }

    @GetMapping("/get-all")
    public SuccessDataResult<List<NoteDto>> getAll(){
        return noteService.getAll();
    }

    @GetMapping("/get-by-id{id}")
    public SuccessDataResult<NoteDto> getById(@RequestParam int id) throws BusinessException {
        return noteService.getNoteById(id);
    }
    @PutMapping("/update-note-title")
    public DataResult<NoteDto> update(@RequestBody UpdateNoteRequest updateNoteRequest) throws BusinessException {
        return noteService.update(updateNoteRequest);
    }
    @DeleteMapping("/delete")
    public DataResult<NoteDto> delete(@RequestBody DeleteNoteRequest deleteNoteRequest) throws BusinessException {
        return noteService.deleteById(deleteNoteRequest);
    }
    @DeleteMapping("/delete-partipitiantuser-from-note")
    public DataResult<NoteDto> deleteUserFromNote(@RequestBody DeleteUserFromNoteRequest deleteUserFromNoteRequest) throws BusinessException {
        return noteService.deleteUserFromNote(deleteUserFromNoteRequest);
    }
    @PostMapping("/share")
    public DataResult<NoteDto> shared(@RequestBody ShareNoteRequest shareNoteRequest) throws BusinessException {
        return noteService.shareNote(shareNoteRequest);
    }
    @GetMapping("get-all-by-owner-user-id{id}")
    public DataResult<List<NoteDto>> getNotesByOwnerUserId(@RequestParam int id) throws BusinessException {
        return noteService.getNotesByOwnerUserId(id);
    }
    @GetMapping("get-all-by-shared-notes-user-id{id}")
    public DataResult<List<NoteDto>> getNotesBySharedUserId(@RequestParam int id) throws BusinessException {
        return noteService.getNotesBySharedUserId(id);
    }

   /* @PostMapping("get-all-contens-by-note-id{id}")
    public DataResult<ContentDto> getContentsByNoteId(@RequestParam int id) throws BusinessException {
        return noteService.getContentsByNoteId(id);
    }*/
   @GetMapping("/get-all-contents-by-note-id-{id}")
   public DataResult<List<ContentDto>> getAllContentInNoteByNoteId(@RequestParam int id) throws BusinessException {
       return noteService.getAllContentInNoteByNoteId(id);
   }



}
