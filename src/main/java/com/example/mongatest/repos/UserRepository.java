package com.example.mongatest.repos;

import com.example.mongatest.model.ApplicationUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<ApplicationUser, String> {
    @Query("{ 'permissions' : ?0 }")
    List<ApplicationUser> findUsersByPermission(String permission);
}