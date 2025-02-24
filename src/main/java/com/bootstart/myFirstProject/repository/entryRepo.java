package com.bootstart.myFirstProject.repository;

import com.bootstart.myFirstProject.entity.entry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface entryRepo extends MongoRepository<entry, ObjectId> {

}
