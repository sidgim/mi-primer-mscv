package org.glara.springcloud.msvc.courses.clients;

import org.glara.springcloud.msvc.courses.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "msvc-users", url = "localhost:8001/api")
public interface UserClientRest {
    @GetMapping("/user/{id}")
    User findUserById(@PathVariable UUID id);
    @PostMapping("/user")
    User saveUser (@RequestBody User user);
}
