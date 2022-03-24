package com.tez.SmartNotePad.business.concretes;

import com.tez.SmartNotePad.business.abstracts.NoteService;
import com.tez.SmartNotePad.business.abstracts.UserService;
import com.tez.SmartNotePad.business.dtos.NoteDto;
import com.tez.SmartNotePad.business.dtos.NoteDtoList;
import com.tez.SmartNotePad.business.requests.createRequests.CreateNoteRequest;
import com.tez.SmartNotePad.business.requests.deleteRequests.DeleteNoteRequest;
import com.tez.SmartNotePad.business.requests.updateRequests.UpdateNoteRequest;
import com.tez.SmartNotePad.core.utilities.exceptions.BusinessException;
import com.tez.SmartNotePad.core.utilities.mapping.ModelMapperService;
import com.tez.SmartNotePad.core.utilities.results.Result;
import com.tez.SmartNotePad.core.utilities.results.SuccessDataResult;
import com.tez.SmartNotePad.core.utilities.results.SuccessResult;
import com.tez.SmartNotePad.dataAccess.NoteDao;
import com.tez.SmartNotePad.entities.concretes.Note;
import com.tez.SmartNotePad.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteManager implements NoteService {

    private final NoteDao noteDao;
    private final ModelMapperService modelMapperService;
    private final UserService userService;

    @Autowired
    public NoteManager(NoteDao noteDao, ModelMapperService modelMapperService, UserService userService) {
        this.noteDao = noteDao;
        this.modelMapperService = modelMapperService;
        this.userService = userService;
    }

    @Override
    public Result createNote(CreateNoteRequest createNoteRequest) throws BusinessException {
        Note note=this.modelMapperService.forRequest().map(createNoteRequest,Note.class);
        User user=userService.getUserByIdForDev(createNoteRequest.getOwnerUserId());
        List<User> ownerAsParticipant=new ArrayList<>();

        ownerAsParticipant.add(user);
        note.setParticipantUsers(ownerAsParticipant);

        note.setNoteId(0);
        this.noteDao.save(note);
        return new SuccessResult("Notes created");
    }

    @Override
    public SuccessDataResult<List<NoteDtoList>> getAll() {
        List<Note> result = noteDao.findAll();

        List<NoteDtoList> response = result.stream()
                .map(note -> this.modelMapperService.forDto().map(note, NoteDtoList.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(response, "Notes are listed successfuly.");
    }

    @Override
    public SuccessDataResult<NoteDto> getNoteById(int id) throws BusinessException {
        checkNoteExist(id);
        NoteDto noteDto=this.modelMapperService.forDto().map(noteDao.getById(id),NoteDto.class);

        return new SuccessDataResult<>(noteDto,"Note get Successfully");
    }

    @Override
    public Result deleteById(DeleteNoteRequest deleteNoteRequest) throws BusinessException {
        checkNoteExist(deleteNoteRequest.getNoteId());
        Note note=noteDao.getById(deleteNoteRequest.getNoteId());
        checkNoteOwner(note,deleteNoteRequest.getUserId());

        noteDao.deleteById(deleteNoteRequest.getNoteId());

        return new SuccessResult("Note Deleted Succesfully");
    }

    @Override
    public Result update(UpdateNoteRequest updateNoteRequest) throws BusinessException {
        checkNoteExist(updateNoteRequest.getNoteId());
        Note note=noteDao.getById(updateNoteRequest.getNoteId());
        checkParticipantUser(note, updateNoteRequest.getUserId());

        note.setContent(updateNoteRequest.getContent());
        noteDao.save(note);

        return new SuccessResult("Note Updated Succesfully");
    }

    private void checkParticipantUser(Note note,int userId)throws BusinessException{
        User user=userService.getUserByIdForDev(userId);

       if(!note.getParticipantUsers().contains(user)){
           throw new BusinessException("This user is not Owner of this note");
       }
    }

    private void checkNoteOwner(Note note,int userId) throws BusinessException {
        if(note.getOwnerUser().getUserId()!=userId){
            throw new BusinessException("This user is not Owner of this note");
        }
    }

    private void checkNoteExist(int id) throws BusinessException {
        if (!noteDao.existsById(id)){
            throw new BusinessException("This note is not valid");
        }
    }

}
