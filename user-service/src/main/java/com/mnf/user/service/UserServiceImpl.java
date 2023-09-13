package com.mnf.user.service;

import com.mnf.user.dto.ResponseDto;
import com.mnf.user.entity.UserEntity;
import com.mnf.user.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    IUserRepository userRepository;

    @Override
    public ResponseDto<UserEntity> findOneById(String id) {
        ResponseDto<UserEntity> responseDto = new ResponseDto<UserEntity>();
        responseDto.status = "SUCCESS";
        try{
            Optional<UserEntity> optionalUserEntity = userRepository.findById(id);

            if(optionalUserEntity.isPresent()){
                responseDto.setContent(optionalUserEntity.get());
            }else{
                responseDto.message = "User not found";
            }
        }catch (Exception e){
            responseDto.status = "ERROR";
            responseDto.message = e.getMessage();
        }

        return responseDto;
    }
}
