package com.bootstart.myFirstProject.controller;

import com.bootstart.myFirstProject.entity.User;
import com.bootstart.myFirstProject.service.userService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/users")
public class userController {
    @Autowired
    private userService usService;


   // A SINGLE USER CANT VIEW ALL OTHER USERS
//    @GetMapping
//    public ResponseEntity<List<User>> get_users(){
//        List<User> lt=usService.getAllUser();
//        return new ResponseEntity<>(lt,HttpStatus.OK);
//    }

//    @GetMapping("/id/{myId}")
//    public ResponseEntity<User> delete_user(@PathVariable ObjectId myId){
//        User deleted=usService.getById(myId);
//        usService.deleteUser(myId);
//        return new ResponseEntity<>(deleted,HttpStatus.OK);
//    }

//    @GetMapping("/id/{myId}")
//    public ResponseEntity<User> get_by_Id(@PathVariable ObjectId myId){
//       User get= usService.getById(myId);
//       if(get!=null){
//           return new ResponseEntity<>(get,HttpStatus.OK);
//       }
//       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    // OLD
//    @PutMapping("{username}")
//    public ResponseEntity<?> update_by_id(@PathVariable String username,@RequestBody User user){
//        User userInDb=usService.getByUserName(username);
//        if(userInDb!=null){
//            userInDb.setUserName((user.getUserName()));
//            userInDb.setPassword(user.getPassword());
//            usService.saveUser(userInDb);
//            return new ResponseEntity<>(userInDb,HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDb=usService.getByUserName(username);
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getUserName());
        usService.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @DeleteMapping
    public ResponseEntity<?> delete_user(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        usService.delete_by_username(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
