package org.glara.springcloud.msvc.users.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "msvc-courses", url = "localhost:8002/api")
public interface CourseClientRest {
    @DeleteMapping("/course/delete-userCourse/{id}")
    void deleteCourseUserByIdController(@PathVariable UUID id);

}
