package com.mnf.post.service;

import com.mnf.compos.*;
import com.mnf.compos.dto.*;
import com.mnf.compos.enumeration.ResponseDtoStatusEnum;
import com.mnf.post.dto.*;
import com.mnf.post.entity.PostEntity;
import com.mnf.post.exception.PostException;
import com.mnf.post.repository.IPostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostServiceImpl extends ABaseService<PostEntity> implements IPostService {
    @Autowired
    IPostRepository postRepository;
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

            ///add data
            entity.setTitle(requestDto.getTitle());
            entity.setSlug(requestDto.getSlug());
            entity.setCategories(requestDto.getCategories());

            postRepository.save(entity);

            responseStatusOnlyDto.setStatus(ResponseDtoStatusEnum.SUCCESS);
        }

        return responseStatusOnlyDto;
    }
    @Override
    public ResponseDto<PostResponseDto> getOneById(String id) {
        Optional<PostEntity> optionalExistingEntity = postRepository.findById(id);

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
            findAllPaginationQuery(getPaginationRequestDto.getDto()).getAllWithPagination(getPaginationRequestDto);

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
        Optional<PostEntity> optionalExistingEntity = postRepository.findById(requestDto.getId());

        ResponseStatusOnlyDto responseStatusOnlyDto = new ResponseStatusOnlyDto();

        if(optionalExistingEntity.isPresent()){
            PostEntity entity = optionalExistingEntity.get();

            ///update data
            entity.setTitle(requestDto.getTitle());
            entity.setSlug(requestDto.getSlug());
            entity.setCategories(requestDto.getCategories());

            postRepository.save(entity);

            responseStatusOnlyDto.setStatus(ResponseDtoStatusEnum.SUCCESS);
        }else{
            responseStatusOnlyDto.setStatus(ResponseDtoStatusEnum.ERROR);
            responseStatusOnlyDto.setMessage(PostException.NOT_FOUND);
        }

        return responseStatusOnlyDto;
    }
    @Override
    public ResponseStatusOnlyDto delete(PostRequestDto requestDto) {
        Optional<PostEntity> optionalExistingEntity = postRepository.findById(requestDto.getId());

        ResponseStatusOnlyDto responseStatusOnlyDto = new ResponseStatusOnlyDto();

        if(optionalExistingEntity.isPresent()){
            PostEntity entity = optionalExistingEntity.get();

            entity.setIsActive(0);

            postRepository.save(entity);

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

    public CustomQueryBuilder<PostEntity> findAllPaginationQuery(PostRequestDto requestDto) {
        return getQueryBuilder()
                .buildQuery(getPostEntityClass())
                .start()
                    .equals("isActive", requestDto.getIsActive())
                .end();
    }

    public CustomQueryBuilder<PostEntity> findOneBySlugQuery(PostRequestDto requestDto) {
        return getQueryBuilder()
                .buildQuery(getPostEntityClass())
                .start()
                    .equals("slug", requestDto.getSlug())
                .end();
    }
}
