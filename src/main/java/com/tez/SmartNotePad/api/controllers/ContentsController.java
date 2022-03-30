package com.tez.SmartNotePad.api.controllers;

import com.tez.SmartNotePad.business.abstracts.ContentService;
import com.tez.SmartNotePad.business.dtos.ContentDto;
import com.tez.SmartNotePad.business.dtos.NoteDtoList;
import com.tez.SmartNotePad.business.requests.createRequests.CreateContentRequest;
import com.tez.SmartNotePad.business.requests.updateRequests.UpdateContentRequest;
import com.tez.SmartNotePad.core.utilities.exceptions.BusinessException;
import com.tez.SmartNotePad.core.utilities.results.DataResult;
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
    @PostMapping("/get-all-contents-by-note-id-{id}")
    public DataResult<List<ContentDto>> getAllContentsByNoteId(@RequestParam int id) throws BusinessException {
        return contentService.getContentsByNoteId(id);
    }

    @GetMapping("/get-all")
    public DataResult<List<ContentDto>> getAll(){
        return contentService.getAll();
    }
}
