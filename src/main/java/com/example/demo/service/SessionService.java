package com.example.demo.service;

import com.example.demo.domain.Session;
import com.example.demo.domain.User;
import com.example.demo.repo.SessionRepository;
import com.example.demo.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author : Sergey Hovhannisyan
 * @since : 12/18/2020, Friday, 3:44 AM
 **/
@Service
public class SessionService {
    final SessionRepository sessionRepository;
    final UserRepository userRepository;

    public SessionService(SessionRepository sessionRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    public void startSession(User user) {
        Session session = new Session(user.getId(), LocalDateTime.now());
        if(user.isInside()) return;
        sessionRepository.save(session);
        user.getSession_ids().add(session.getId());
        user.setInside(true);
        userRepository.save(user);
    }

    public void endSession(User user) {
        Session session = sessionRepository.findByUserIdAndExitDateIsNull(user.getId());
        if(null == session) return;
        session.setExitDate(LocalDateTime.now());
        session.setInside(false);
        sessionRepository.save(session);
        user.setInside(false);
        userRepository.save(user);
    }

    public Set<Session> findAllByUserId(String userId) {
        return new TreeSet<>(sessionRepository.findAllByUserId(userId));
    }
}
