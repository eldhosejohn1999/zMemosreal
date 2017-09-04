package com.devhopeslabs.zmemos.controllers;

import com.devhopeslabs.zmemos.models.User;
import com.devhopeslabs.zmemos.services.UserService;
import com.devhopeslabs.zmemos.utilities.MemosErrors;
import com.devhopeslabs.zmemos.utilities.MemosMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value="/signup")//checked
    public User SignUpUser(@RequestBody User user){//add the user info to table
       return userService.addUser(user);
    }

    @RequestMapping(method = RequestMethod.POST, value="/update")//checked
    public User UpdateUserInfo(@RequestBody User user){//update profile
        return userService.updateUser(user);
    }

    @RequestMapping(method = RequestMethod.GET, value="/users")//checked
    public List<User> getListOfUsers(){//gets the user lists
        // for testing only
        return userService.getListOfUsers();
    }


}
