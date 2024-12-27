package net.kominfo.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.kominfo.demo.models.Course;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
    
    Optional<Course> findByCourse(String name);
}
