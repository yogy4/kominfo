package net.kominfo.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.kominfo.demo.models.Course;
import net.kominfo.demo.repositories.CourseRepository;
@Service("courseService")
public class CourseService {
    

    @Autowired
    CourseRepository courseRepository;

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public Course findById(Long id){
        return courseRepository.findById(id).get();
    }

    public Course saveCourse(Course course){
        return courseRepository.save(course);
    }

    public Course updateCourse(Course course, Long id){
        Course update = courseRepository.findById(id).orElse(null);
        if(update != null){
            update.setCourse(course.getCourse());
            update.setMentor(course.getMentor());
            update.setTitle(course.getTitle());
        }
        final Course newCourse = courseRepository.save(update);
        return newCourse;
    }

    public Boolean deleteCourse(Long id){
        Course deleteCourse = courseRepository.findById(id).orElse(null);
        if(deleteCourse != null){
            courseRepository.delete(deleteCourse);
            return true;
        }
        return false;
    }
}
