package org.glara.springcloud.msvc.courses.services;

import org.glara.springcloud.msvc.courses.clients.UserClientRest;
import org.glara.springcloud.msvc.courses.models.User;
import org.glara.springcloud.msvc.courses.models.entity.Course;
import org.glara.springcloud.msvc.courses.models.entity.CourseUser;
import org.glara.springcloud.msvc.courses.repository.CourseRepository;
import org.glara.springcloud.msvc.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final UserClientRest userClientRest;

    public CourseServiceImpl(CourseRepository courseRepository, UserClientRest userClientRest) {
        this.courseRepository = courseRepository;
        this.userClientRest = userClientRest;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> listCourse() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Course findCourseById(UUID id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            return course.get();
        } else {
            throw new NotFoundException("Course not found for ID: " + id);
        }

    }

    @Override
    @Transactional
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public Course updateCourse(UUID id, Course course) throws NotFoundException {
        findCourseById(id);
        course.setId(id);
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public void deleteCourseById(UUID id) throws NotFoundException {
        findCourseById(id);
        courseRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<User> assignUser(User user, UUID courseId) {
        Course course = findCourseById(courseId);
        User userMsvc = userClientRest.findUserById(user.getId());
        CourseUser courseUser = new CourseUser();
        courseUser.setUserId(userMsvc.getId());
        course.addCourseUser(courseUser);
        courseRepository.save(course);
        return Optional.of(userMsvc);
    }

    @Override
    @Transactional
    public Optional<User> createUser(User user, UUID courseId) {
        Course course = findCourseById(courseId);
        User userMsvc = userClientRest.saveUser(user);
        CourseUser courseUser = new CourseUser();
        courseUser.setUserId(userMsvc.getId());
        course.addCourseUser(courseUser);
        courseRepository.save(course);
        return Optional.of(userMsvc);
    }

    @Override
    @Transactional
    public Optional<User> deleteUser(User user, UUID courseId) {
        Course course = findCourseById(courseId);
        User userMsvc = userClientRest.findUserById(user.getId());
        CourseUser courseUser = new CourseUser();
        courseUser.setUserId(userMsvc.getId());
        course.removeCourseUser(courseUser);
        courseRepository.save(course);
        return Optional.of(userMsvc);
    }


}
