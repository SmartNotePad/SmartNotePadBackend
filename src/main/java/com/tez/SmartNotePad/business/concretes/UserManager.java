package com.tez.SmartNotePad.business.concretes;

import com.tez.SmartNotePad.business.abstracts.UserService;
import com.tez.SmartNotePad.business.dtos.UserDto;
import com.tez.SmartNotePad.business.dtos.UserDtoList;
import com.tez.SmartNotePad.business.requests.createRequests.CreateUserRequest;
import com.tez.SmartNotePad.business.requests.loginRequest.LoginUserRequest;
import com.tez.SmartNotePad.business.requests.updateRequests.UpdateUserRequest;
import com.tez.SmartNotePad.core.utilities.exceptions.BusinessException;
import com.tez.SmartNotePad.core.utilities.mapping.ModelMapperService;
import com.tez.SmartNotePad.core.utilities.results.Result;
import com.tez.SmartNotePad.core.utilities.results.SuccessDataResult;
import com.tez.SmartNotePad.core.utilities.results.SuccessResult;
import com.tez.SmartNotePad.dataAccess.UserDao;
import com.tez.SmartNotePad.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManager implements UserService {

    private final UserDao userDao;
    private final ModelMapperService modelMapperService;

    @Autowired
    public UserManager(UserDao userDao, ModelMapperService modelMapperService) {
        this.userDao = userDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreateUserRequest createUserRequest) throws BusinessException {
        checkMailExist(createUserRequest.getMail());
        User user=this.modelMapperService.forRequest().map(createUserRequest,User.class);

        this.userDao.save(user);
        return new SuccessResult("User created");
    }

    @Override
    public SuccessDataResult<UserDtoList> getAll() {
        List<User> result = userDao.findAll();

        List<UserDtoList> response = result.stream()
                .map(user -> this.modelMapperService.forDto().map(user, UserDtoList.class))
                .collect(Collectors.toList());

        return new SuccessDataResult(response, "Users are listed successfuly.");

    }

    @Override
    public SuccessDataResult<UserDto> login(LoginUserRequest loginUserRequest) throws BusinessException {

       User user= checkUserExist(loginUserRequest);
       UserDto userDto=this.modelMapperService.forDto().map(user,UserDto.class);

        return new SuccessDataResult<>(userDto,"User login succesfully");
    }

    @Override
    public SuccessDataResult<UserDto> getUserById(int id) throws BusinessException {
        checkUserExistById(id);

        User user=userDao.getById(id);
        UserDto userDto=this.modelMapperService.forDto().map(user,UserDto.class);

        return new SuccessDataResult<>(userDto,"User get successfully");
    }

    @Override
    public Result deleteById(int id) throws BusinessException {
        checkUserExistById(id);
        userDao.deleteById(id);
        return new SuccessResult("User deleted Succesfully");
    }

    @Override
    public Result update(UpdateUserRequest updateUserRequest) {

        User user=userDao.getById(updateUserRequest.getUserId());
        user.setPassword(updateUserRequest.getPassword());
        user.setNameSurname(updateUserRequest.getNameSurname());
        //validi düşünün nerde yapcağınızı
        userDao.save(user);

        return new SuccessResult("Updated");
    }

    @Override
    public User getUserByIdForDev(int id) throws BusinessException {
        checkUserExistById(id);
        return userDao.getById(id);
    }

    @Override
    public User getUserByEmail(String mail) throws BusinessException {
        if(!userDao.existsUserByMail(mail)){
            throw new BusinessException("Mail Should be register the system");
        }

        return userDao.findUserByMail(mail);
    }


    private void checkUserExistById(int id) throws BusinessException {
        if(!userDao.existsById(id)){
            throw new BusinessException("There is no user by id: "+id);
        }
    }


    private User checkUserExist(LoginUserRequest loginUserRequest) throws BusinessException {
        User user=userDao.findUserByMailAndPassword(loginUserRequest.getMail(), loginUserRequest.getPassword());
        if(user== null){
            throw new BusinessException("There is no user");
        }else {
            return user;
        }
    }

    private void checkMailExist(String mail) throws BusinessException {
        if(userDao.existsUserByMail(mail)){
           throw new BusinessException("Mail Should be Unique!");
        }
    }


}
