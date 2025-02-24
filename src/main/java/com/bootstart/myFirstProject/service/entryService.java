package com.bootstart.myFirstProject.service;

import com.bootstart.myFirstProject.entity.User;
import com.bootstart.myFirstProject.entity.entry;
import com.bootstart.myFirstProject.repository.entryRepo;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component

public class entryService {
    @Autowired
    private entryRepo entRepo;
    @Autowired
    private userService usService;
    @Transactional
    public void saveEntry(entry user,String username){
        try {
            User found_user= usService.getByUserName(username);
            user.setDate(LocalDateTime.now());
            entry saved=entRepo.save(user);
            found_user.getJ_entries().add(saved);
            usService.saveUser(found_user);
        }catch (Exception e){
            throw new RuntimeException("its error",e);
        }

    }
    public List<entry> getAll(){
        List<entry> lt=new ArrayList<>(entRepo.findAll());
        return lt;
    }
    public entry getById(ObjectId id){
        return entRepo.findById(id).orElse(null);
    }
    public boolean deleteById(ObjectId id,String username){
        boolean removed=false;
        try{
            User user=usService.getByUserName(username);
            removed = user.getJ_entries().removeIf(x -> x.getId().equals(id));
            if(removed){
                usService.saveUser(user);
                entRepo.deleteById(id);
            }
        }catch (Exception e){
            System.out.println("ERROR FOUND IN deleteById"+e);
        }
        return removed;
    }
    public entry updateById(ObjectId id,entry entBody){
        entry ent=getById(id);
        ent.setTitle(entBody.getTitle());
        ent.setContent(entBody.getContent());
        return entRepo.save(ent);
    }


}
