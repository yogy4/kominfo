package net.kominfo.demo.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import net.kominfo.demo.entities.SignupRequest;
import net.kominfo.demo.models.Course;
import net.kominfo.demo.models.User;
import net.kominfo.demo.repositories.CourseRepository;
import net.kominfo.demo.repositories.UserRepository;
import net.kominfo.demo.services.UserService;

@RestController

@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired 
    private CourseRepository courseRepository;
    
    @GetMapping("/users")
    public List<User> all(){
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity <User> getOneUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@Validated @RequestBody SignupRequest request){
        User user = new User(request.getUsername(), request.getEmail(), request.getPassword());
        String course = request.getCourse();
        Set<Course> addcourse = new HashSet<>();
        Course usercCourse = courseRepository.findByCourse(course).get();
        if(usercCourse != null){
            addcourse.add(usercCourse);
        }
        user.setCourse(addcourse);
        userRepository.save(user);
        return ResponseEntity.status(201).body(user);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateuser(@Validated @RequestBody User user, @PathVariable(value = "id") Long id){
        return ResponseEntity.ok(userService.updateUser(user, id));
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        Map<String, String> response = new HashMap<String, String>();
        if(userService.deleteUser(id)){
            response.put("status", "success");
            response.put("message", "user deleted successfully");
            return ResponseEntity.ok(response);   
        }else{
            response.put("status", "error");
            response.put("message", "something wrong with deleted user");
            return ResponseEntity.status(500).body(response);
        }
    }   

}
