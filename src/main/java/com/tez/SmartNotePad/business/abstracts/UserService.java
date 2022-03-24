package com.tez.SmartNotePad.business.abstracts;

import com.tez.SmartNotePad.business.dtos.UserDto;
import com.tez.SmartNotePad.business.dtos.UserDtoList;
import com.tez.SmartNotePad.business.requests.createRequests.CreateUserRequest;
import com.tez.SmartNotePad.business.requests.loginRequest.LoginUserRequest;
import com.tez.SmartNotePad.business.requests.updateRequests.UpdateUserRequest;
import com.tez.SmartNotePad.core.utilities.exceptions.BusinessException;
import com.tez.SmartNotePad.core.utilities.results.Result;
import com.tez.SmartNotePad.core.utilities.results.SuccessDataResult;
import com.tez.SmartNotePad.entities.concretes.User;

public interface UserService {

    Result add(CreateUserRequest createUserRequest) throws BusinessException;

    SuccessDataResult<UserDtoList> getAll();

    SuccessDataResult<UserDto>login(LoginUserRequest loginUserRequest) throws BusinessException;

    SuccessDataResult<UserDto> getUserById(int id) throws BusinessException;

    Result deleteById(int id) throws BusinessException;

    Result update(UpdateUserRequest updateUserRequest);

    User getUserByIdForDev(int id) throws BusinessException;

}
