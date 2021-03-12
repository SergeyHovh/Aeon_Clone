package com.example.demo.repo;

import com.example.demo.domain.Session;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author : Sergey Hovhannisyan
 * @since : 12/18/2020, Friday, 3:44 AM
 **/
@Repository
public interface SessionRepository extends MongoRepository<Session, String> {
    Session findByUserIdAndExitDateIsNull(String userId);
    Set<Session> findAllByUserId(String userId);
}
