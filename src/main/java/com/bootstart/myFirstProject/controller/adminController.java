package com.bootstart.myFirstProject.controller;

import com.bootstart.myFirstProject.entity.User;
import com.bootstart.myFirstProject.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class adminController {
    @Autowired
    private userService usService;

    @GetMapping("/all-user")
    public ResponseEntity<?> getAllUsers(){
        List<User> lt=usService.getAllUser();
        if(lt!=null) {
            return new ResponseEntity<>(lt, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/create-admin-user")
    public void createAdmin(@RequestBody User user){
        usService.saveAdmin(user);
    }
}
