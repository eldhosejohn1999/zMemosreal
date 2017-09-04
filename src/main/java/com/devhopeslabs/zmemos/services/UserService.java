package com.devhopeslabs.zmemos.services;

import com.devhopeslabs.zmemos.models.User;
import com.devhopeslabs.zmemos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getListOfUsers() {//for testing only
        List<User> users=new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }


    public User getUserWithId(String id){
        return userRepository.findOne(id);
    }

    public User addUser(User user) {//adds a user
        if(userRepository.findByEmail(user.getEmail())!=null){
            return null;
        }
        if(user!=null && user.getUser_id()!=null && user.getEmail()!=null && user.getName()!=null && user.getPassword()!=null) {
            user.setUser_id("User000" + (userRepository.count() + 1));
            userRepository.save(user);
        }else{
            return null;
        }
        return user;
    }

    public User updateUser(User user) {//checks if all the entries are correct or not then adds the user
        if(userRepository.exists(user.getUser_id())){
            User existingUser=userRepository.findOne(user.getUser_id());
            if(user!=null && user.getUser_id()!=null && user.getEmail()!=null && user.getName()!=null && user.getPassword()!=null){
                userRepository.save(user);
                return user;
            }else{
                return existingUser;
            }
        }else{
            return null;
        }
    }
}
