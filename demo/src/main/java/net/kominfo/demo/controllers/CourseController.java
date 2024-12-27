package net.kominfo.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.kominfo.demo.entities.CourseRequest;
import net.kominfo.demo.models.Course;
import net.kominfo.demo.repositories.CourseRepository;
import net.kominfo.demo.services.CourseService;

@RestController

@RequestMapping("/api/v1")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/courses")
    public List<Course> all(){
        return courseService.getAllCourses();
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Course> getOneCourse(@PathVariable Long id){
        return ResponseEntity.ok(courseService.findById(id));

    }

    @PostMapping("/course")
    public ResponseEntity<Course> createCourse(@Validated @RequestBody CourseRequest request){
        Course course = new Course(request.getCourse(), request.getMentor(), request.getTitle());
        courseRepository.save(course);
        return ResponseEntity.status(201).body(course);
    }

    @PutMapping("/course/{id}")
    public ResponseEntity<Course> updateCourse(@Validated @RequestBody Course course, @PathVariable(value = "id") Long id){
        return ResponseEntity.ok(courseService.updateCourse(course, id));

    }

    @DeleteMapping("/course/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id){
        Map<String, String> response = new HashMap<>();
        if(courseService.deleteCourse(id)){
            response.put("status", "success");
            response.put("message", "deleted course successfully");
            return ResponseEntity.ok(response);
        }else{
            response.put("status", "error");
            response.put("message", "something wrong with deleted course");
            return ResponseEntity.status(500).body(response);

        }
    }

    
}
