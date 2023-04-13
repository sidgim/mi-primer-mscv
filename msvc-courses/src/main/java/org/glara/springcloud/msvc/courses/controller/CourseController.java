package org.glara.springcloud.msvc.courses.controller;

import jakarta.validation.Valid;
import org.glara.springcloud.msvc.courses.models.User;
import org.glara.springcloud.msvc.courses.models.entity.Course;
import org.glara.springcloud.msvc.courses.services.CourseService;
import org.glara.springcloud.msvc.exception.Validations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CourseController {

    private CourseService courseService;
    private Course course;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/course")
    public ResponseEntity<List<Course>> listCourseController() {
        return ResponseEntity.ok(courseService.listCourse());
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Course> findCourseByIdController(@PathVariable  UUID id) {
        Course course = courseService.findUserByIdCourse(id);
        return ResponseEntity.ok(course);
    }

    @PostMapping("/course")
    public ResponseEntity<?> saveCourseController(@Valid @RequestBody Course course, BindingResult result) {
        if (result.hasErrors()) {
            return Validations.validateBindingResult(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.saveCourse(course)) ;
    }
    @PutMapping("/course/{id}")
    public ResponseEntity<?> updateCourseController(@PathVariable UUID id,@Valid @RequestBody Course course, BindingResult result) {
        if (result.hasErrors()) {
            return Validations.validateBindingResult(result);
        }
        return ResponseEntity.ok(courseService.updateCourse(id, course));
    }

    @DeleteMapping("/course/{id}")
    public ResponseEntity<Course> deleteCourseByIdController(@PathVariable  UUID id) {
        courseService.deleteCourseById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/course/delete-userCourse/{id}")
    public ResponseEntity<Course> deleteCourseUserByIdController(@PathVariable  UUID id) {
        courseService.deleteCourseUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/course/assign-user/{courseId}")
    public ResponseEntity<?> assignUserController(@PathVariable UUID courseId,@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return Validations.validateBindingResult(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.assignUser(user, courseId));
    }

    @PostMapping("/course/create-user/{courseId}")
    public ResponseEntity<?> createUserController(@PathVariable UUID courseId,@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return Validations.validateBindingResult(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createUser(user, courseId));
    }

    @DeleteMapping("/course/delete-user/{courseId}")
    public ResponseEntity<?> deleteUserController(@PathVariable UUID courseId,@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return Validations.validateBindingResult(result);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(courseService.deleteUser(user, courseId));
    }



}
