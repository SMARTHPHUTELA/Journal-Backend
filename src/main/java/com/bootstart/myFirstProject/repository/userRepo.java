package com.bootstart.myFirstProject.repository;

import com.bootstart.myFirstProject.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface userRepo extends MongoRepository<User, ObjectId> {
    public User findByUserName(String username);
    public void deleteByUserName(String username);

}