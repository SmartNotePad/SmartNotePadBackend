package com.tez.SmartNotePad.business.concretes;

import com.tez.SmartNotePad.business.abstracts.ContentService;
import com.tez.SmartNotePad.business.abstracts.NoteService;
import com.tez.SmartNotePad.business.abstracts.UserService;
import com.tez.SmartNotePad.business.dtos.ContentDto;
import com.tez.SmartNotePad.business.dtos.NoteDto;
import com.tez.SmartNotePad.business.requests.ShareNoteRequest;
import com.tez.SmartNotePad.business.requests.createRequests.CreateNoteRequest;
import com.tez.SmartNotePad.business.requests.deleteRequests.DeleteNoteRequest;
import com.tez.SmartNotePad.business.requests.updateRequests.UpdateNoteRequest;
import com.tez.SmartNotePad.business.requests.updateRequests.UpdateNoteWithOutTitleRequest;
import com.tez.SmartNotePad.core.utilities.exceptions.BusinessException;
import com.tez.SmartNotePad.core.utilities.mapping.ModelMapperService;
import com.tez.SmartNotePad.core.utilities.results.DataResult;
import com.tez.SmartNotePad.core.utilities.results.Result;
import com.tez.SmartNotePad.core.utilities.results.SuccessDataResult;
import com.tez.SmartNotePad.core.utilities.results.SuccessResult;
import com.tez.SmartNotePad.dataAccess.NoteDao;
import com.tez.SmartNotePad.entities.concretes.Note;
import com.tez.SmartNotePad.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteManager implements NoteService {

    private final NoteDao noteDao;
    private final ModelMapperService modelMapperService;
    private final UserService userService;
    private final ContentService contentService;


    @Autowired
    public NoteManager(NoteDao noteDao, ModelMapperService modelMapperService, @Lazy UserService userService,@Lazy ContentService contentService) {
        this.noteDao = noteDao;
        this.modelMapperService = modelMapperService;
        this.userService = userService;
        this.contentService = contentService;

    }

    @Override
    public DataResult<NoteDto> createNote(CreateNoteRequest createNoteRequest) throws BusinessException {
        Note note=this.modelMapperService.forRequest().map(createNoteRequest,Note.class);
        userService.getUserByIdForDev(createNoteRequest.getUserUserUd());
        note.setCreatedDate(Timestamp.from(Instant.now()));
        note.setModifiedDate(Timestamp.from(Instant.now()));

        note.setNoteId(0);
        this.noteDao.save(note);
        NoteDto noteDto=this.modelMapperService.forDto().map(note,NoteDto.class);
        return new SuccessDataResult<>(noteDto,"Note created");
    }

    @Override
    public SuccessDataResult<List<NoteDto>> getAll() {
        List<Note> result = noteDao.findAll();

        List<NoteDto> response = result.stream()
                .map(note -> this.modelMapperService.forDto().map(note, NoteDto.class))
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
        note.setTitle(updateNoteRequest.getTitle());
        note.setModifiedDate(Timestamp.from(Instant.now()));

        noteDao.save(note);

        return new SuccessResult("Note Updated Succesfully");
    }

    @Override
    public DataResult<NoteDto> shareNote(ShareNoteRequest shareNoteRequest) throws BusinessException {
        checkNoteExist(shareNoteRequest.getNoteId());
        Note note=noteDao.getById(shareNoteRequest.getNoteId());
        User user=userService.getUserByEmail(shareNoteRequest.getMailToShare());
        checkNoteOwner(note,shareNoteRequest.getOwnerUserId());

        User userOwner=userService.getUserByIdForDev(shareNoteRequest.getOwnerUserId());

       // user.getSharedNotes().add(note);
        //duruma göre ekle bu satırı
        note.getParticipantUsers().add(user);
        note.getParticipantUsers().add(userOwner);
        noteDao.save(note);

        NoteDto noteDto=modelMapperService.forDto().map(note,NoteDto.class);

        return new SuccessDataResult<>(noteDto,"Note shared successfully");
    }

    @Override
    public DataResult<List<NoteDto>> getNotesByOwnerUserId(int id) throws BusinessException {
        User user=userService.getUserByIdForDev(id);
        List<Note> result=noteDao.findAllByUserUserId(user.getUserId());

        List<NoteDto> response = result.stream()
                .map(note -> this.modelMapperService.forDto().map(note, NoteDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(response,"Notes are listed");
    }

    @Override
    public DataResult<List<ContentDto>> getAllContentInNoteByNoteId(int id) throws BusinessException {
        checkNoteExist(id);
        return contentService.getContentsByNoteId(id);
    }

    @Override
    public void checkParticipantUsers(Note note, int userId) throws BusinessException {
        checkParticipantUser(note,userId);
    }

    @Override
    public void checkNoteOwners(Note note, int userId) throws BusinessException {
        checkNoteOwner(note,userId);
    }

    @Override
    public void checkNoteExists(int id) throws BusinessException {
        checkNoteExist(id);
    }

    @Override
    public void updateNote(UpdateNoteWithOutTitleRequest updateNoteWithOutTitleRequest) throws BusinessException {
        checkNoteExist(updateNoteWithOutTitleRequest.getNoteId());
        Note note=noteDao.getById(updateNoteWithOutTitleRequest.getNoteId());
        noteDao.save(note);
    }

    @Override
    public Note getById(int id) throws BusinessException {
        checkNoteExist(id);
        return noteDao.getById(id);
    }

    @Override
    public DataResult<List<NoteDto>> getAllSorted(String ascOrDesc) throws BusinessException {
        Sort sort;
        String value=checkIsAvailable(ascOrDesc);

        sort = Sort.by(Sort.Direction.valueOf(value),"createdDate");

        List<Note> result = noteDao.findAll(sort);

        List<NoteDto> response = result.stream()
                .map(note -> this.modelMapperService.forDto().map(note, NoteDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(response,"Notes are listed successfully!");
    }

    private String checkIsAvailable(String ascOrDesc)throws BusinessException {
        if(!(ascOrDesc.equals("asc") || ascOrDesc.equals("desc"))){
            throw new BusinessException("Please select available sort ");
        }
        return ascOrDesc.toUpperCase();
    }

    private void checkParticipantUser(Note note,int userId)throws BusinessException{
        User user=userService.getUserByIdForDev(userId);

       if(!note.getParticipantUsers().contains(user)|| note.getUser().getUserId()!=userId){
           throw new BusinessException("This user is not Participant of this note");
       }
    }

    private void checkNoteOwner(Note note,int userId) throws BusinessException {
        if(note.getUser().getUserId()!=userId){
            throw new BusinessException("This user is not Owner of this note");
        }
    }

    private void checkNoteExist(int id) throws BusinessException {
        if (!noteDao.existsById(id)){
            throw new BusinessException("This note is not valid");
        }
    }

}
