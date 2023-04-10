package org.glara.springcloud.msvc.courses.services;

import org.glara.springcloud.msvc.courses.models.User;
import org.glara.springcloud.msvc.courses.models.entity.Course;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseService {

    List<Course> listCourse();
    Course findCourseById(UUID id);
    Course saveCourse(Course course);
    Course updateCourse(UUID id, Course courseDetails);
    void deleteCourseById(UUID id);

    Optional<User> assignUser(User user, UUID courseId);

    Optional<User> createUser(User user, UUID courseId);
    Optional<User> deleteUser(User user, UUID courseId);

}
