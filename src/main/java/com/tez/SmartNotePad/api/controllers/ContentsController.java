package com.tez.SmartNotePad.api.controllers;

import com.tez.SmartNotePad.business.abstracts.ContentService;
import com.tez.SmartNotePad.business.dtos.ContentDto;
import com.tez.SmartNotePad.business.dtos.NoteDto;
import com.tez.SmartNotePad.business.dtos.NoteDtoList;
import com.tez.SmartNotePad.business.requests.createRequests.CreateContentRequest;
import com.tez.SmartNotePad.business.requests.deleteRequests.DeleteNoteRequest;
import com.tez.SmartNotePad.business.requests.updateRequests.UpdateContentRequest;
import com.tez.SmartNotePad.core.utilities.exceptions.BusinessException;
import com.tez.SmartNotePad.core.utilities.results.DataResult;
import com.tez.SmartNotePad.core.utilities.results.Result;
import com.tez.SmartNotePad.core.utilities.results.SuccessDataResult;
import com.tez.SmartNotePad.entities.concretes.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/contents")
public class ContentsController {

    private final ContentService contentService;

    @Autowired
    public ContentsController(ContentService contentService) {
        this.contentService = contentService;
    }

    @PostMapping("/add")
    public DataResult<ContentDto> add(@RequestBody CreateContentRequest createContentRequest) throws BusinessException {
        return contentService.add(createContentRequest);
    }

    @PutMapping("/update")
    public DataResult<ContentDto> update(@RequestBody UpdateContentRequest updateContentRequest) throws BusinessException {
        return contentService.update(updateContentRequest);
    }

    @GetMapping("/get-all")
    public DataResult<List<ContentDto>> getAll(){
        return contentService.getAll();
    }

    @GetMapping("/get-by-id{id}")
    public DataResult<ContentDto> getById(@RequestParam int id) throws BusinessException {
        return contentService.getContentById(id);
    }

    @DeleteMapping("/delete")
    public DataResult<ContentDto> delete(@RequestBody int id ) throws BusinessException {
        return contentService.deleteById(id);
    }
}
