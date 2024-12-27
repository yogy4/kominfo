package net.kominfo.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;
import net.kominfo.demo.models.User;
import net.kominfo.demo.repositories.UserRepository;

@Service("userService")
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id).get();
    }

    public User savUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(User user, Long id){
        User updateUser = userRepository.findById(id).orElse(null);
        if(updateUser != null){
            updateUser.setUsername(user.getUsername());
            updateUser.setEmail(user.getEmail());
            updateUser.setPassword(user.getPassword());
        }
        final User newUser = userRepository.save(updateUser);
        return newUser;
    }

    public Boolean deleteUser(Long id){
        User deletUser = userRepository.findById(id).orElse(null);
        if(deletUser != null){
            userRepository.delete(deletUser);
            return true;
        }
        return false;
    }

}
