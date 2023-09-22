package com.mnf.user.service;

import com.mnf.component.BaseService;
import com.mnf.component.dto.ResponseDto;
import com.mnf.component.dto.ResponseStatusOnlyDto;
import com.mnf.component.enumeration.ResponseDtoStatusEnum;
import com.mnf.user.dto.AddUserRequestDto;
import com.mnf.user.dto.UserResponseDto;
import com.mnf.user.entity.UserEntity;
import com.mnf.user.repository.IUserRepository;
import com.mnf.user.util.PasswordUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl extends BaseService implements IUserService{
    @Autowired
    IUserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseDto<UserResponseDto> findOneById(String id) {
        ResponseDto<UserResponseDto> responseDto = new ResponseDto<>();
        responseDto.status = ResponseDtoStatusEnum.SUCCESS;
        try{
            Optional<UserEntity> optionalUserEntity = userRepository.findById(id);

            if(optionalUserEntity.isPresent()){
                responseDto.setContent(modelMapper.map(optionalUserEntity.get(), UserResponseDto.class));
            }else{
                responseDto.message = "User not found";
                logger.info("user not found");
            }
        }catch (Exception e){
            responseDto.status = ResponseDtoStatusEnum.ERROR;
            responseDto.message = e.getMessage();
        }

        return responseDto;
    }

    @Override
    public ResponseDto<ResponseStatusOnlyDto> addUser(AddUserRequestDto requestDto) {
        ResponseDto<ResponseStatusOnlyDto> responseDto = new ResponseDto<>();
        responseDto.status = ResponseDtoStatusEnum.SUCCESS;

        try{
            Optional<UserEntity> existingUser = userRepository.findByUsername(requestDto.getUsername());
            if(existingUser.isPresent()) throw new Exception("username already exist");

            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(requestDto.getUsername());
            userEntity.setPassword(PasswordUtil.hashPassword(requestDto.getPassword()));

            userRepository.save(userEntity);
        }catch (Exception e){
            responseDto.status = ResponseDtoStatusEnum.ERROR;
            responseDto.message = e.getMessage();
        }

        return responseDto;
    }


}
