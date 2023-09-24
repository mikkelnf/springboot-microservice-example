package com.mnf.user.feign;

import com.mnf.component.dto.ResponseStatusOnlyDto;
import com.mnf.user.dto.LoginRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${mnf.feign.login.name}", url = "${mnf.feign.login.url}")
public interface ICallLoginFeignClient {
    @PostMapping("/secured/api/login")
    ResponseStatusOnlyDto login(@RequestBody LoginRequestDto requestDto);
}
