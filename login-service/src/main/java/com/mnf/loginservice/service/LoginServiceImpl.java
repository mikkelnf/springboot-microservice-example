package com.mnf.loginservice.service;

import com.mnf.loginservice.dto.LoginRequestDto;
import com.mnf.loginservice.dto.ResponseDto;
import com.mnf.loginservice.entity.UserEntity;
import com.mnf.loginservice.enumeration.ResponseDtoStatusEnum;
import com.mnf.loginservice.repository.ILoginRepository;
import com.mnf.loginservice.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements ILoginService{
    @Autowired
    ILoginRepository loginRepository;

    @Override
    public ResponseDto login(LoginRequestDto requestDto){
        ResponseDto response = new ResponseDto();

        Optional<UserEntity> optionalUserEntity = loginRepository.findByUsername(requestDto.getUsername());

        if(optionalUserEntity.isEmpty()){
            response.setStatus(ResponseDtoStatusEnum.ERROR);
            response.setMessage("User not found");
        }else {
            Boolean isValidPassword = PasswordUtil.checkPassword(requestDto.getPassword(), optionalUserEntity.get().getPassword());
            Boolean isNotLogin = optionalUserEntity.get().getIsLogin() != 1;

            if(!isValidPassword){
                response.setStatus(ResponseDtoStatusEnum.ERROR);
                response.setMessage("Password not matched");
            }else if(!isNotLogin){
                response.setStatus(ResponseDtoStatusEnum.ERROR);
                response.setMessage("User is still login");
            }else {
                optionalUserEntity.get().setIsLogin(1);
                loginRepository.save(optionalUserEntity.get());
                response.setStatus(ResponseDtoStatusEnum.SUCCESS);
            }
        }

        return response;
    }
}
