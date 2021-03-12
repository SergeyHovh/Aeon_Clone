package com.example.demo.repo;

import com.example.demo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Sergey Hovhannisyan
 * @since : 12/15/2020, Tuesday, 7:18 PM
 **/
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
