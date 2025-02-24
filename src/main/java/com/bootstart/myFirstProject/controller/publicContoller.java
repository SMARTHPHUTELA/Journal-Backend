package com.bootstart.myFirstProject.controller;

import com.bootstart.myFirstProject.entity.User;
import com.bootstart.myFirstProject.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class publicContoller {
    @Autowired
    private userService usService;


    @GetMapping("/health-check")
    public String health_check(){
        return "OK";
    }
    @PostMapping("/create-user")
    public ResponseEntity<User> create_user(@RequestBody User user){
        try{
            usService.saveNewUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
