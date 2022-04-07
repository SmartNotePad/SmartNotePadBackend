package com.tez.SmartNotePad.business.abstracts;

import com.tez.SmartNotePad.business.dtos.NoteDtoList;
import com.tez.SmartNotePad.business.dtos.UserDto;
import com.tez.SmartNotePad.business.dtos.UserDtoList;
import com.tez.SmartNotePad.business.requests.createRequests.CreateUserRequest;
import com.tez.SmartNotePad.business.requests.loginRequest.LoginUserRequest;
import com.tez.SmartNotePad.business.requests.updateRequests.UpdateUserRequest;
import com.tez.SmartNotePad.core.utilities.exceptions.BusinessException;
import com.tez.SmartNotePad.core.utilities.results.DataResult;
import com.tez.SmartNotePad.core.utilities.results.Result;
import com.tez.SmartNotePad.core.utilities.results.SuccessDataResult;
import com.tez.SmartNotePad.entities.concretes.Note;
import com.tez.SmartNotePad.entities.concretes.User;

import java.util.List;

public interface UserService {

    DataResult<UserDto> add(CreateUserRequest createUserRequest) throws BusinessException;

    SuccessDataResult<List<UserDtoList>> getAll();

    SuccessDataResult<UserDto>login(LoginUserRequest loginUserRequest) throws BusinessException;

    SuccessDataResult<UserDto> getUserById(int id) throws BusinessException;

    DataResult<UserDto> deleteById(int id) throws BusinessException;

    DataResult<UserDto> update(UpdateUserRequest updateUserRequest);

    User getUserByIdForDev(int id) throws BusinessException;

    User getUserByEmail(String mail) throws BusinessException;
    DataResult<List<NoteDtoList>> getNotesByParticipantUserId(int id)throws BusinessException;



}
