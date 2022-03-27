package com.tez.SmartNotePad.api.controllers;

import com.tez.SmartNotePad.business.abstracts.NoteService;
import com.tez.SmartNotePad.business.dtos.NoteDto;
import com.tez.SmartNotePad.business.dtos.NoteDtoList;
import com.tez.SmartNotePad.business.dtos.UserDto;
import com.tez.SmartNotePad.business.dtos.UserDtoList;
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
    public Result update(UpdateNoteRequest updateNoteRequest) throws BusinessException {
        return noteService.update(updateNoteRequest);
    }
    @DeleteMapping("/delete")
    public Result delete(DeleteNoteRequest deleteNoteRequest) throws BusinessException {
        return noteService.deleteById(deleteNoteRequest);
    }
    @PostMapping("/share")
    public DataResult<NoteDto> shared(ShareNoteRequest shareNoteRequest) throws BusinessException {
        return noteService.shareNote(shareNoteRequest);
    }



}
