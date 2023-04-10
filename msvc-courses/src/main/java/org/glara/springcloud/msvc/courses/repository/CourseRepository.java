package org.glara.springcloud.msvc.courses.repository;

import org.glara.springcloud.msvc.courses.models.entity.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CourseRepository extends CrudRepository<Course, UUID> {
}
