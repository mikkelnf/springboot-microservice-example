package com.mnf.loginservice.service;

import com.mnf.common.entity.UserEntity;
import com.mnf.component.ABaseService;
import com.mnf.component.dto.ResponseStatusOnlyDto;
import com.mnf.component.enumeration.ResponseDtoStatusEnum;
import com.mnf.loginservice.dto.LogoutRequestDto;
import com.mnf.loginservice.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class LogoutServiceImpl extends ABaseService<UserEntity> implements ILogoutService{
    @Autowired
    IUserRepository userRepository;

    @Override
    public ResponseStatusOnlyDto logout(LogoutRequestDto requestDto){
        ResponseStatusOnlyDto response = new ResponseStatusOnlyDto();

        Optional<UserEntity> optionalUserEntity = userRepository.findById(requestDto.getId());

        if(optionalUserEntity.isEmpty()){
            response.setStatus(ResponseDtoStatusEnum.ERROR);
            response.setMessage("User doesn't exist");
        }else {
            UserEntity user = optionalUserEntity.get();

            if(user.getIsLogin() == 1){
                user.setIsLogin(0);
                userRepository.save(user);

                response.setStatus(ResponseDtoStatusEnum.SUCCESS);
            }else{
                response.setStatus(ResponseDtoStatusEnum.ERROR);
                response.setMessage("User already logout");
            }
        }

        return response;
    }
}
