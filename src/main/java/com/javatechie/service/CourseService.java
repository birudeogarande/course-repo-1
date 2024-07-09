package com.javatechie.service;

import com.javatechie.dto.Course;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CourseService {

    //RDS DB
    private final List<Course> courses = new ArrayList<>();

    @PostConstruct
    public void init(){
        courses.add(new Course(new Random().nextInt(), "Java", 1000));
        courses.add(new Course(new Random().nextInt(), "CSS", 100));
        courses.add(new Course(new Random().nextInt(), "JavaScript", 500));
        courses.add(new Course(new Random().nextInt(), "JSP", 200));
        courses.add(new Course(new Random().nextInt(), "Sprig", 2000));
    }
    // Create a new course
    public void addCourse(Course course) {
        courses.add(course);
    }

    // Retrieve all courses
    public List<Course> getAllCourses() {
        return courses;
    }

    // Retrieve a course by id
    public Optional<Course> getCourseById(int id) {
        return courses.stream()
                .filter(course -> course.getId() == id)
                .findFirst();
    }

    // Update a course
    public boolean updateCourse(int id, Course newCourse) {
        return getCourseById(id).map(existingCourse -> {
            courses.remove(existingCourse);
            courses.add(newCourse);
            return true;
        }).orElse(false);
    }

    // Delete a course by id
    public boolean deleteCourse(int id) {
        return courses
                .removeIf(course -> course.getId() == id);
    }
}
