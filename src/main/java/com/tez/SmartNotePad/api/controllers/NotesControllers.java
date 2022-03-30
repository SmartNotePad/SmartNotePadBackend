package com.tez.SmartNotePad.api.controllers;

import com.tez.SmartNotePad.business.abstracts.NoteService;
import com.tez.SmartNotePad.business.dtos.*;
import com.tez.SmartNotePad.business.requests.ShareNoteRequest;
import com.tez.SmartNotePad.business.requests.createRequests.CreateNoteRequest;
import com.tez.SmartNotePad.business.requests.createRequests.CreateUserRequest;
import com.tez.SmartNotePad.business.requests.deleteRequests.DeleteNoteRequest;
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
    public Result add(@RequestBody CreateNoteRequest createNoteRequest) throws BusinessException {
        return noteService.createNote(createNoteRequest);
    }

    @GetMapping("/get-all")
    public SuccessDataResult<List<NoteDtoList>> getAll(){
        return noteService.getAll();
    }

    @PostMapping("/get-by-id{id}")
    public SuccessDataResult<NoteDto> getById(@RequestParam int id) throws BusinessException {
        return noteService.getNoteById(id);
    }
    @PutMapping("/update")
    public Result update(@RequestBody UpdateNoteRequest updateNoteRequest) throws BusinessException {
        return noteService.update(updateNoteRequest);
    }
    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteNoteRequest deleteNoteRequest) throws BusinessException {
        return noteService.deleteById(deleteNoteRequest);
    }
    @PostMapping("/share")
    public DataResult<NoteDto> shared(@RequestBody ShareNoteRequest shareNoteRequest) throws BusinessException {
        return noteService.shareNote(shareNoteRequest);
    }
    @PostMapping("get-all-by-owner-user-id{id}")
    public DataResult<List<NoteDtoList>> getNotesByOwnerUserId(@RequestParam int id) throws BusinessException {
        return noteService.getNotesByOwnerUserId(id);
    }

   /* @PostMapping("get-all-contens-by-note-id{id}")
    public DataResult<ContentDto> getContentsByNoteId(@RequestParam int id) throws BusinessException {
        return noteService.getContentsByNoteId(id);
    }*/
   @PostMapping("/get-all-contents-by-note-id-{id}")
   public DataResult<List<ContentDto>> getAllContentInNoteByNoteId(@RequestParam int id) throws BusinessException {
       return noteService.getAllContentInNoteByNoteId(id);
   }



}
