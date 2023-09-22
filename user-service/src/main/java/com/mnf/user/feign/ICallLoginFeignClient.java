package com.mnf.user.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "login", url = "localhost:8081/login")
public interface ICallLoginFeignClient {
}
