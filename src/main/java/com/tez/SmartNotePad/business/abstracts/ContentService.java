package com.tez.SmartNotePad.business.abstracts;

import com.tez.SmartNotePad.business.dtos.ContentDto;
import com.tez.SmartNotePad.business.dtos.UserDto;
import com.tez.SmartNotePad.business.dtos.UserDtoList;
import com.tez.SmartNotePad.business.requests.createRequests.CreateContentRequest;
import com.tez.SmartNotePad.business.requests.createRequests.CreateUserRequest;
import com.tez.SmartNotePad.business.requests.loginRequest.LoginUserRequest;
import com.tez.SmartNotePad.business.requests.updateRequests.UpdateContentRequest;
import com.tez.SmartNotePad.business.requests.updateRequests.UpdateUserRequest;
import com.tez.SmartNotePad.core.utilities.exceptions.BusinessException;
import com.tez.SmartNotePad.core.utilities.results.DataResult;
import com.tez.SmartNotePad.core.utilities.results.SuccessDataResult;
import com.tez.SmartNotePad.entities.concretes.Content;

import java.util.List;

public interface ContentService {
    DataResult<ContentDto> add(CreateContentRequest createContentRequest) throws BusinessException;

    DataResult<List<ContentDto>> getAll();

    DataResult<ContentDto> getContentById(int id) throws BusinessException;

    DataResult<ContentDto> deleteById(int id) throws BusinessException;

    DataResult<ContentDto> update(UpdateContentRequest updateContentRequest);

    DataResult<List<ContentDto>> getContentsByNoteId(int id)throws BusinessException;

    Content getById(int id);

}
