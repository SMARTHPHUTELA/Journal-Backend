package com.bootstart.myFirstProject.controller;

import com.bootstart.myFirstProject.entity.User;
import com.bootstart.myFirstProject.entity.entry;
import com.bootstart.myFirstProject.service.entryService;
import com.bootstart.myFirstProject.service.userService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class entryController2 {
    @Autowired
    private entryService entService;
    @Autowired
    private userService usService;

    @GetMapping
    public ResponseEntity<List<entry>> getAllEntriesOfUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User user= usService.getByUserName(username);
        List<entry> lt=user.getJ_entries();
        if(lt!=null && !lt.isEmpty()){
            return new ResponseEntity<>(lt,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody entry user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        try{
            entService.saveEntry(user,username);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<entry> getById(@PathVariable ObjectId myId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User user=usService.getByUserName(username);
        List<entry> collect = user.getJ_entries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            entry ent= entService.getById(myId);
            return new ResponseEntity<>(ent,HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId myId){
        boolean removed=false;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user=usService.getByUserName(username);
        List<entry> collect=user.getJ_entries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
        if(!collect.isEmpty()){
             removed = entService.deleteById(myId, username);
        }
        if(removed){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
    }
    @PutMapping("/id/{myId}")
    public ResponseEntity<entry> updateById(@PathVariable ObjectId myId,@RequestBody entry ent){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User user=usService.getByUserName(username);
        List<entry> collect=user.getJ_entries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            return new ResponseEntity<>(entService.updateById(myId,ent),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/check")
    public ResponseEntity<?> healthCheck(){
        return new ResponseEntity<>("Every thing OK",HttpStatus.OK);
    }
//
//
//    @DeleteMapping("/{username}/{myId}")
//    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId, @PathVariable String username){
//
//        entService.deleteById(myId,username);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }


//    @PutMapping("/id/{myId}")
//    public ResponseEntity<entry> updateById(@PathVariable ObjectId myId,@RequestBody entry user){
//        entry old= entService.getById(myId).orElse(null);
//        if(old!=null){
//            old.setTitle(user.getTitle()!=null && !user.getTitle().equals("") ?user.getTitle():old.getTitle());
//            old.setContent(user.getContent()!=null && !user.getContent().equals("")?user.getContent():old.getContent());
//            entService.saveEntry(old);
//            return new ResponseEntity<>(old,HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//    }



    @GetMapping("/who")
    public String check2(){
        return "Hi i am SP";
    }
}
