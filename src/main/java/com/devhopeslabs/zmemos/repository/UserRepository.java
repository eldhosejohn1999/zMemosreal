package com.devhopeslabs.zmemos.repository;

import com.devhopeslabs.zmemos.models.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User,String> {

    User findById(String username);
    User findByName(String name);
    User findByUsername(String username);
    User findByEmail(String email);
    User findByPassword(String password);

}
