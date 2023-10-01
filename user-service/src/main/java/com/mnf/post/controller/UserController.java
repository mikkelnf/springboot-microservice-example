package com.mnf.post.controller;

import com.mnf.component.ABaseController;
import com.mnf.component.dto.GetPaginationRequestDto;
import com.mnf.component.dto.GetPaginationResponseDto;
import com.mnf.component.dto.ResponseDto;
import com.mnf.component.dto.ResponseStatusOnlyDto;
import com.mnf.post.dto.UserRequestDto;
import com.mnf.post.dto.GetUserResponseDto;
import com.mnf.post.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController extends ABaseController {
    @Autowired
    IUserService userService;

    @PostMapping("/add")
    public ResponseEntity<ResponseStatusOnlyDto> add(@RequestBody UserRequestDto requestDto){
        return createResponse(userService.add(requestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<GetUserResponseDto>> getById(@PathVariable String id){
        return createResponse(userService.getById(id));
    }

    @GetMapping()
    public ResponseEntity<ResponseDto<GetPaginationResponseDto<GetUserResponseDto>>> getPagination(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                                                                                   @RequestParam(name = "size", defaultValue = "5") Integer size,
                                                                                                   @RequestParam(name = "sortBy", defaultValue = "createdDate") String sortBy,
                                                                                                   @RequestParam(name = "sortType", defaultValue = "desc") String sortType,
                                                                                                   @RequestParam(name = "username", required = false) String username,
                                                                                                   @RequestParam(name = "isLogin", required = false) Integer isLogin,
                                                                                                   @RequestParam(name = "isActive", required = false) Integer isActive)
    {
        UserRequestDto requestDto = new UserRequestDto();
        requestDto.setUsername(username);
        requestDto.setIsLogin(isLogin);
        requestDto.setIsActive(isActive);

        return createResponse(userService.getPagination(new GetPaginationRequestDto<>(page, size, sortBy, sortType, requestDto)));
    }

    @PatchMapping("/update")
    public ResponseEntity<ResponseStatusOnlyDto> update(@RequestBody UserRequestDto requestDto){
        return createResponse(userService.update(requestDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseStatusOnlyDto> delete(@RequestBody UserRequestDto requestDto){
        return createResponse(userService.delete(requestDto));
    }
}
