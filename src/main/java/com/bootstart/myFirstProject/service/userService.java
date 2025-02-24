package com.bootstart.myFirstProject.service;
import java.util.*;
import com.bootstart.myFirstProject.entity.User;
import com.bootstart.myFirstProject.repository.userRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class userService {
    @Autowired
    private userRepo usrepo;
    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    public void saveUser(User user){
        usrepo.save(user);
    }
    public void saveNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER"));
        usrepo.save(user);
    }
    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER","ADMIN"));
        usrepo.save(user);
    }
    public List<User> getAllUser(){
        List<User> lt=new ArrayList<>(usrepo.findAll());
        return lt;
    }
    public void deleteUser(ObjectId id){
        usrepo.deleteById(id);
    }
    public User getById(ObjectId id){
        return usrepo.findById(id).orElse(null);
    }
    public User getByUserName(String username) {
        return usrepo.findByUserName(username);

    }

    public void delete_by_username(String username){
        usrepo.deleteByUserName(username);
    }

}
