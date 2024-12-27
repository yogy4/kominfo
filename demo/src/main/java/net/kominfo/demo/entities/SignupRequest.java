package net.kominfo.demo.entities;

import java.util.Set;

import net.kominfo.demo.models.Course;

public class SignupRequest {
    
    private String username;
    private String password;
    
    private String email;

    private String course;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    
    

    
}
