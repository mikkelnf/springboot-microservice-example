package com.mnf.user.service;

import com.mnf.common.entity.UserEntity;
import com.mnf.common.util.PasswordUtil;
import com.mnf.component.ABaseService;
import com.mnf.component.CustomQueryBuilder;
import com.mnf.component.dto.GetPaginationRequestDto;
import com.mnf.component.dto.GetPaginationResponseDto;
import com.mnf.component.dto.ResponseDto;
import com.mnf.component.dto.ResponseStatusOnlyDto;
import com.mnf.component.enumeration.ResponseDtoStatusEnum;
import com.mnf.user.dto.UserRequestDto;
import com.mnf.user.dto.GetUserResponseDto;
import com.mnf.user.exception.UserException;
import com.mnf.user.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl extends ABaseService<UserEntity> implements IUserService{
    @Autowired
    IUserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseStatusOnlyDto add(UserRequestDto requestDto) {
        Optional<UserEntity> optionalExistingEntity = findOneByUsernameQuery(requestDto).getOne();

        ResponseStatusOnlyDto responseStatusOnlyDto = new ResponseStatusOnlyDto();

        if(optionalExistingEntity.isPresent()){
            responseStatusOnlyDto.setStatus(ResponseDtoStatusEnum.ERROR);
            responseStatusOnlyDto.setMessage(UserException.DATA_EXISTED);
        }else{
            UserEntity entity = new UserEntity();
            entity.setUsername(requestDto.getUsername());
            entity.setPassword(PasswordUtil.hashPassword(requestDto.getPassword()));

            userRepository.save(entity);

            responseStatusOnlyDto.setStatus(ResponseDtoStatusEnum.SUCCESS);
        }

        return responseStatusOnlyDto;
    }

    @Override
    public ResponseDto<GetUserResponseDto> getById(String id) {
        Optional<UserEntity> optionalExistingEntity = userRepository.findById(id);

        ResponseDto<GetUserResponseDto> responseDto = new ResponseDto<>();

        if(optionalExistingEntity.isPresent()){
            responseDto.setStatus(ResponseDtoStatusEnum.SUCCESS);
            responseDto.setContent(modelMapper.map(optionalExistingEntity.get(), GetUserResponseDto.class));
        }else{
            responseDto.setStatus(ResponseDtoStatusEnum.ERROR);
            responseDto.setMessage(UserException.NOT_FOUND);
        }

        return responseDto;
    }

    @Override
    public ResponseDto<GetPaginationResponseDto<GetUserResponseDto>> getPagination(GetPaginationRequestDto<UserRequestDto> getPaginationRequestDto) {
        GetPaginationResponseDto<UserEntity> queryResultPagination =
                findAllPaginationQuery(getPaginationRequestDto.getDto()).getAllWithPagination(
                        getPaginationRequestDto.getPage(),
                        getPaginationRequestDto.getSize(),
                        getPaginationRequestDto.getSortBy(),
                        getPaginationRequestDto.getSortType()
                );

        List<GetUserResponseDto> mappedResponseList = queryResultPagination.getResults().stream()
                .map(entity -> {
                    return modelMapper.map(entity, GetUserResponseDto.class);
                }).collect(Collectors.toList());

        GetPaginationResponseDto<GetUserResponseDto> getPaginationResponseDto =
                new GetPaginationResponseDto<>(mappedResponseList, queryResultPagination.getPaginationInfo());

        return new ResponseDto<>(ResponseDtoStatusEnum.SUCCESS, null, getPaginationResponseDto);
    }

    @Override
    public ResponseStatusOnlyDto update(UserRequestDto requestDto) {
        Optional<UserEntity> optionalExistingEntity = userRepository.findById(requestDto.getId());

        ResponseStatusOnlyDto responseStatusOnlyDto = new ResponseStatusOnlyDto();

        if(optionalExistingEntity.isPresent()){
            UserEntity entity = optionalExistingEntity.get();
            entity.setUsername(requestDto.getUsername());
            entity.setPassword(PasswordUtil.hashPassword(requestDto.getPassword()));

            userRepository.save(entity);

            responseStatusOnlyDto.setStatus(ResponseDtoStatusEnum.SUCCESS);
        }else{
            responseStatusOnlyDto.setStatus(ResponseDtoStatusEnum.ERROR);
            responseStatusOnlyDto.setMessage(UserException.NOT_FOUND);
        }

        return responseStatusOnlyDto;
    }

    @Override
    public ResponseStatusOnlyDto delete(UserRequestDto requestDto) {
        Optional<UserEntity> optionalExistingEntity = userRepository.findById(requestDto.getId());

        ResponseStatusOnlyDto responseStatusOnlyDto = new ResponseStatusOnlyDto();

        if(optionalExistingEntity.isPresent()){
            UserEntity entity = optionalExistingEntity.get();
            entity.setIsActive(0);

            userRepository.save(entity);

            responseStatusOnlyDto.setStatus(ResponseDtoStatusEnum.SUCCESS);
        }else{
            responseStatusOnlyDto.setStatus(ResponseDtoStatusEnum.ERROR);
            responseStatusOnlyDto.setMessage(UserException.NOT_FOUND);
        }

        return responseStatusOnlyDto;
    }

    protected Class<UserEntity> getUserEntityClass(){
        return UserEntity.class;
    }

    public CustomQueryBuilder<UserEntity> findOneByUsernameQuery(UserRequestDto requestDto) {
        return getQueryBuilder()
                .buildQuery(getUserEntityClass())
                .start()
                .equals("username", requestDto.getUsername())
                .end();
    }

    public CustomQueryBuilder<UserEntity> findAllPaginationQuery(UserRequestDto requestDto) {
        return getQueryBuilder()
                .buildQuery(getUserEntityClass())
                .start()
                .equals("username", requestDto.getUsername())
                .and()
                .equals("isLogin", requestDto.getIsLogin())
                .and()
                .equals("isActive", requestDto.getIsActive())
                .end();
    }
}
