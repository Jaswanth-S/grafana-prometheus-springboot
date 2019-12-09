package com.stackroute.mongodbdemo.repository;

import com.stackroute.mongodbdemo.document.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<Users, Integer>
{}
