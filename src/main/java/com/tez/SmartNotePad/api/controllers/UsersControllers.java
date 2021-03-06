package com.tez.SmartNotePad.api.controllers;

import com.tez.SmartNotePad.business.abstracts.UserService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UsersControllers {

    private final UserService userService;

    @Autowired
    public UsersControllers(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public DataResult<UserDto> add(@RequestBody CreateUserRequest createUserRequest) throws BusinessException {
        return userService.add(createUserRequest);
    }

    @GetMapping("/get-all")
    public SuccessDataResult<List<UserDtoList>>getAll(){
        return userService.getAll();
    }

    @PostMapping("/login")
    public SuccessDataResult<UserDto> login(@RequestBody LoginUserRequest loginUserRequest) throws BusinessException {
        return userService.login(loginUserRequest);
    }
    @GetMapping("/get-by-id{id}")
    public SuccessDataResult<UserDto> getById(@RequestParam int id) throws BusinessException {
        return userService.getUserById(id);
    }
    @DeleteMapping("/delete{id}")
    public Result deleteById( @RequestParam int id ) throws BusinessException {
        return userService.deleteById(id);
    }
    @PutMapping("/update")
    public DataResult<UserDto> update(@Valid @RequestBody UpdateUserRequest updateUserRequest){
        return userService.update(updateUserRequest);
    }
    @GetMapping("get-all-by-participant-user-id{id}")
    public DataResult<List<NoteDtoList>> getNotesByParticipantUserId(@RequestParam int id) throws BusinessException {
        return userService.getNotesByParticipantUserId(id);
    }



}
