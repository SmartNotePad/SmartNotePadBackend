package com.tez.SmartNotePad.business.concretes;

import com.tez.SmartNotePad.business.abstracts.ContentService;
import com.tez.SmartNotePad.business.abstracts.NoteService;
import com.tez.SmartNotePad.business.abstracts.UserService;
import com.tez.SmartNotePad.business.dtos.ContentDto;
import com.tez.SmartNotePad.business.dtos.NoteDto;
import com.tez.SmartNotePad.business.requests.createRequests.CreateContentRequest;
import com.tez.SmartNotePad.business.requests.updateRequests.UpdateContentRequest;
import com.tez.SmartNotePad.core.utilities.exceptions.BusinessException;
import com.tez.SmartNotePad.core.utilities.mapping.ModelMapperService;
import com.tez.SmartNotePad.core.utilities.results.DataResult;
import com.tez.SmartNotePad.core.utilities.results.SuccessDataResult;
import com.tez.SmartNotePad.dataAccess.ContentDao;
import com.tez.SmartNotePad.entities.concretes.Content;
import com.tez.SmartNotePad.entities.concretes.Note;
import com.tez.SmartNotePad.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentManager implements ContentService {

    private final ContentDao contentDao;
    private final ModelMapperService modelMapperService;
    private final NoteService noteService;
    private final UserService userService;

    @Autowired
    public ContentManager(ContentDao contentDao, ModelMapperService modelMapperService, NoteService noteService, UserService userService) {
        this.contentDao = contentDao;
        this.modelMapperService = modelMapperService;
        this.noteService = noteService;
        this.userService = userService;
    }

    @Override
    public DataResult<ContentDto> add(CreateContentRequest createContentRequest) throws BusinessException {
      // Note note=noteService.getById(createContentRequest.getNoteId());
       //noteService.checkParticipantUsers(note,createContentRequest.getUserId());
        noteService.getNotesByOwnerUserId(createContentRequest.getUserId());
        Content content=modelMapperService.forRequest().map(createContentRequest,Content.class);

        content.setCreatedDate(Timestamp.from(Instant.now()));
        content.setModifiedDate(Timestamp.from(Instant.now()));

        contentDao.save(content);

        ContentDto result=this.modelMapperService.forDto().map(content,ContentDto.class);

        return new SuccessDataResult<>(result,"Content oluşturuldu");
    }

    @Override
    public DataResult<List<ContentDto>> getAll() {
        List<Content> result = contentDao.findAll();

        List<ContentDto> response = result.stream()
                .map(content -> this.modelMapperService.forDto().map(content, ContentDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(response, "Notes are listed successfuly.");
    }

    @Override
    public DataResult<ContentDto> getContentById(int id) throws BusinessException {
        checkContentExists(id);
        Content content=contentDao.getById(id);
        ContentDto contentDto=this.modelMapperService.forDto().map(content,ContentDto.class);

        return new SuccessDataResult<>(contentDto,"Content get Successfully");
    }

    @Override
    public DataResult<ContentDto> deleteById(int id) throws BusinessException {
        checkContentExists(id);

        this.contentDao.deleteById(id);

        return new SuccessDataResult<>("This content deleted ");
    }

    @Override
    public DataResult<ContentDto> update(UpdateContentRequest updateContentRequest) throws BusinessException {
       
        Content content=contentDao.getById(updateContentRequest.getContentId());
        content.setContext(updateContentRequest.getContext());
        content.setModifiedDate(Timestamp.from(Instant.now()));

        contentDao.save(content);

        ContentDto result=this.modelMapperService.forDto().map(content,ContentDto.class);

        return new SuccessDataResult<>(result,"Content Updated!");
    }

    @Override
    public DataResult<List<ContentDto>> getContentsByNoteId(int id) throws BusinessException {
        noteService.checkNoteExists(id);
        List<Content> result=contentDao.findAllByNote_NoteId(id);

        List<ContentDto> response = result.stream()
                .map(content -> this.modelMapperService.forDto().map(content, ContentDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(response,"Contents Listed");
    }

    @Override
    public Content getById(int id) {
        return contentDao.getById(id);
    }

   //private void

    private void checkContentExists(int id) throws BusinessException{
        if (!contentDao.existsById(id)){
            throw new BusinessException("This content not exist!");
        }
    }




}
