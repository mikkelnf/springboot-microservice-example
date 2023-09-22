package com.mnf.loginservice.service;

import com.mnf.common.entity.UserEntity;
import com.mnf.common.util.PasswordUtil;
import com.mnf.component.dto.ResponseStatusOnlyDto;
import com.mnf.component.enumeration.ResponseDtoStatusEnum;
import com.mnf.loginservice.dto.LoginRequestDto;
import com.mnf.loginservice.repository.ILoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class LoginServiceImpl implements ILoginService{
    @Autowired
    ILoginRepository loginRepository;

    @Override
    public ResponseStatusOnlyDto login(LoginRequestDto requestDto){
        ResponseStatusOnlyDto response = new ResponseStatusOnlyDto();

        Optional<UserEntity> optionalUserEntity = loginRepository.findByUsername(requestDto.getUsername());

        if(optionalUserEntity.isEmpty()){
            response.setStatus(ResponseDtoStatusEnum.ERROR);
            response.setMessage("User not found");
        }else {
            boolean isValidPassword = PasswordUtil.checkPassword(requestDto.getPassword(), optionalUserEntity.get().getPassword());
            boolean isNotLogin = optionalUserEntity.get().getIsLogin() != 1;

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
