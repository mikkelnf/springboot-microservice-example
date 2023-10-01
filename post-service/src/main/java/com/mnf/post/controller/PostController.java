package com.mnf.post.controller;

import com.mnf.component.ABaseController;
import com.mnf.component.dto.GetPaginationRequestDto;
import com.mnf.component.dto.GetPaginationResponseDto;
import com.mnf.component.dto.ResponseDto;
import com.mnf.component.dto.ResponseStatusOnlyDto;
import com.mnf.post.dto.PostRequestDto;
import com.mnf.post.dto.PostResponseDto;
import com.mnf.post.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/post")
public class PostController extends ABaseController {
    @Autowired
    IPostService postService;

    @PostMapping("/add")
    public ResponseEntity<ResponseStatusOnlyDto> add(@RequestBody PostRequestDto requestDto){
        return createResponse(postService.add(requestDto));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseDto<PostResponseDto>> getOneById(@PathVariable String id){
        return createResponse(postService.getOneById(id));
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<ResponseDto<PostResponseDto>> getOneBySlug(@PathVariable String slug){
        return createResponse(postService.getOneBySlug(slug));
    }

    @GetMapping()
    public ResponseEntity<ResponseDto<GetPaginationResponseDto<PostResponseDto>>>
        getPagination(@RequestParam(name = "page", defaultValue = "1") Integer page,
                      @RequestParam(name = "size", defaultValue = "5") Integer size,
                      @RequestParam(name = "sortBy", defaultValue = "createdDate") String sortBy,
                      @RequestParam(name = "sortType", defaultValue = "desc") String sortType,
                      @RequestParam(name = "isActive", required = false) Integer isActive)
    {
        PostRequestDto requestDto = new PostRequestDto();
        requestDto.setIsActive(isActive);

        return createResponse(postService.getPagination(new GetPaginationRequestDto<>(page, size, sortBy, sortType, requestDto)));
    }

    @PatchMapping("/update")
    public ResponseEntity<ResponseStatusOnlyDto> update(@RequestBody PostRequestDto requestDto){
        return createResponse(postService.update(requestDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseStatusOnlyDto> delete(@RequestBody PostRequestDto requestDto){
        return createResponse(postService.delete(requestDto));
    }
}
