//package com.bootstart.myFirstProject.controller;
//import com.bootstart.myFirstProject.entity.entry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/app")
//public class entryController {
//    private Map<Long, entry> mp=new HashMap<>();
//    @GetMapping
//    public List<entry> getAll(){
//        ArrayList<entry> lt=new ArrayList<>(mp.values());
//        return lt;
//    }
//    @PostMapping
//    public boolean createEntry(@RequestBody entry user){
//        mp.put(user.getId(),user);
//        return true;
//    }
//
//    @GetMapping("/check")
//    public String healthCheck(){
//        return "Every thing OK. ";
//    }
//    @DeleteMapping("/id/{myId}")
//    public entry deleteEntryById(@PathVariable Long myId){
//         entry removed=mp.remove(myId);
//         return removed;
//    }
//    @PutMapping("/id/{myId}")
//    public entry updateById(@PathVariable Long myId,@RequestBody entry user){
//         mp.put(myId,user);
//         return mp.get(myId);
//    }
//
//    @GetMapping("/id/{myId}")
//    public entry getEntryById(@PathVariable Long myId){
//        return mp.get(myId);
//    }
//
//    @GetMapping("/who")
//    public String check2(){
//        return "Hi i am SP";
//    }
//}
