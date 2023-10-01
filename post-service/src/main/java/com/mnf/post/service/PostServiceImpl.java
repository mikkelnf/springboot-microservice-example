package com.mnf.post.service;

import com.mnf.component.ABaseService;
import com.mnf.component.CustomQueryBuilder;
import com.mnf.component.dto.GetPaginationRequestDto;
import com.mnf.component.dto.GetPaginationResponseDto;
import com.mnf.component.dto.ResponseDto;
import com.mnf.component.dto.ResponseStatusOnlyDto;
import com.mnf.component.enumeration.ResponseDtoStatusEnum;
import com.mnf.post.dto.PostRequestDto;
import com.mnf.post.dto.PostResponseDto;
import com.mnf.post.entity.PostEntity;
import com.mnf.post.exception.PostException;
import com.mnf.post.repository.IPostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostServiceImpl extends ABaseService<PostEntity> implements IPostService {
    @Autowired
    IPostRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseStatusOnlyDto add(PostRequestDto requestDto) {
        Optional<PostEntity> optionalExistingEntity = findOneBySlugQuery(requestDto).getOne();

        ResponseStatusOnlyDto responseStatusOnlyDto = new ResponseStatusOnlyDto();

        if(optionalExistingEntity.isPresent()){
            responseStatusOnlyDto.setStatus(ResponseDtoStatusEnum.ERROR);
            responseStatusOnlyDto.setMessage(PostException.DATA_EXISTED);
        }else{
            PostEntity entity = new PostEntity();

            entity.setContent(requestDto.getContent());
            entity.setTitle(requestDto.getTitle());

            userRepository.save(entity);

            responseStatusOnlyDto.setStatus(ResponseDtoStatusEnum.SUCCESS);
        }

        return responseStatusOnlyDto;
    }

    @Override
    public ResponseDto<PostResponseDto> getOneById(String id) {
        Optional<PostEntity> optionalExistingEntity = userRepository.findById(id);

        ResponseDto<PostResponseDto> responseDto = new ResponseDto<>();

        if(optionalExistingEntity.isPresent()){
            responseDto.setStatus(ResponseDtoStatusEnum.SUCCESS);
            responseDto.setContent(modelMapper.map(optionalExistingEntity.get(), PostResponseDto.class));
        }else{
            responseDto.setStatus(ResponseDtoStatusEnum.ERROR);
            responseDto.setMessage(PostException.NOT_FOUND);
        }

        return responseDto;
    }

    @Override
    public ResponseDto<PostResponseDto> getOneBySlug(String slug) {
        Optional<PostEntity> optionalExistingEntity = findOneBySlugQuery(new PostRequestDto(slug)).getOne();

        ResponseDto<PostResponseDto> responseDto = new ResponseDto<>();

        if(optionalExistingEntity.isPresent()){
            responseDto.setStatus(ResponseDtoStatusEnum.SUCCESS);
            responseDto.setContent(modelMapper.map(optionalExistingEntity.get(), PostResponseDto.class));
        }else{
            responseDto.setStatus(ResponseDtoStatusEnum.ERROR);
            responseDto.setMessage(PostException.NOT_FOUND);
        }

        return responseDto;
    }

    @Override
    public ResponseDto<GetPaginationResponseDto<PostResponseDto>> getPagination(GetPaginationRequestDto<PostRequestDto> getPaginationRequestDto) {
        GetPaginationResponseDto<PostEntity> queryResultPagination =
                findAllPaginationQuery(getPaginationRequestDto.getDto()).getAllWithPagination(
                        getPaginationRequestDto.getPage(),
                        getPaginationRequestDto.getSize(),
                        getPaginationRequestDto.getSortBy(),
                        getPaginationRequestDto.getSortType()
                );

        List<PostResponseDto> mappedResponseList = queryResultPagination.getResults().stream()
                .map(entity -> {
                    return modelMapper.map(entity, PostResponseDto.class);
                }).collect(Collectors.toList());

        GetPaginationResponseDto<PostResponseDto> getPaginationResponseDto =
                new GetPaginationResponseDto<>(mappedResponseList, queryResultPagination.getPaginationInfo());

        return new ResponseDto<>(ResponseDtoStatusEnum.SUCCESS, null, getPaginationResponseDto);
    }

    @Override
    public ResponseStatusOnlyDto update(PostRequestDto requestDto) {
        Optional<PostEntity> optionalExistingEntity = userRepository.findById(requestDto.getId());

        ResponseStatusOnlyDto responseStatusOnlyDto = new ResponseStatusOnlyDto();

        if(optionalExistingEntity.isPresent()){
            PostEntity entity = optionalExistingEntity.get();

            entity.setContent(requestDto.getContent());
            entity.setTitle(requestDto.getTitle());

            userRepository.save(entity);

            responseStatusOnlyDto.setStatus(ResponseDtoStatusEnum.SUCCESS);
        }else{
            responseStatusOnlyDto.setStatus(ResponseDtoStatusEnum.ERROR);
            responseStatusOnlyDto.setMessage(PostException.NOT_FOUND);
        }

        return responseStatusOnlyDto;
    }

    @Override
    public ResponseStatusOnlyDto delete(PostRequestDto requestDto) {
        Optional<PostEntity> optionalExistingEntity = userRepository.findById(requestDto.getId());

        ResponseStatusOnlyDto responseStatusOnlyDto = new ResponseStatusOnlyDto();

        if(optionalExistingEntity.isPresent()){
            PostEntity entity = optionalExistingEntity.get();
            entity.setIsActive(0);

            userRepository.save(entity);

            responseStatusOnlyDto.setStatus(ResponseDtoStatusEnum.SUCCESS);
        }else{
            responseStatusOnlyDto.setStatus(ResponseDtoStatusEnum.ERROR);
            responseStatusOnlyDto.setMessage(PostException.NOT_FOUND);
        }

        return responseStatusOnlyDto;
    }

    protected Class<PostEntity> getPostEntityClass(){
        return PostEntity.class;
    }

    public CustomQueryBuilder<PostEntity> findOneBySlugQuery(PostRequestDto requestDto) {
        return getQueryBuilder()
                .buildQuery(getPostEntityClass())
                .start()
                    .equals("slug", requestDto.getSlug())
                .end();
    }

    public CustomQueryBuilder<PostEntity> findAllPaginationQuery(PostRequestDto requestDto) {
        return getQueryBuilder()
                .buildQuery(getPostEntityClass())
                .start()
                    .equals("isActive", requestDto.getIsActive())
                .end();
    }
}
