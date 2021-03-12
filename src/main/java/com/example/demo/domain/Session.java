package com.example.demo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author : Sergey Hovhannisyan
 * @since : 12/18/2020, Friday, 3:41 AM
 **/
@Document
public class Session implements Comparable<Session> {
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Id
    String id;

    private String userId;
    private LocalDateTime enterDate, exitDate;
    private boolean inside;

    public Session() {
    }

    public Session(String userId, LocalDateTime enterDate) {
        this.userId = userId;
        this.enterDate = enterDate;
        this.inside = true;
    }

    public String getId() {
        return id;
    }

    public String getEnterDateFormatted() {
        return getEnterDate().format(formatter);
    }

    public String getExitDateFormatted() {
        return inside ? "User has not ended this session yet" : getExitDate().format(formatter);
    }

    public String getSessionLength() {
        Map<String, Long> sessionLength = sessionLength(this);
        Long h = sessionLength.get("h");
        Long m = sessionLength.get("m");
        Long s = sessionLength.get("s");
        String hours = getTime(h);
        String min = getTime(m);
        String sec = getTime(s);
        return hours + ":" + min + ":" + sec;
    }

    private String getTime(Long time) {
        return String.format("%s%d", time / 10 == 0 ? "0" : "", time);
    }

    /**
     * @param session current session
     * @return a map with key values: h, m, s and sls for hours, minutes, seconds and overall length of the session
     * in seconds respectively
     */
    public Map<String, Long> sessionLength(Session session) {
        Map<String, Long> map = new HashMap<>();
        long sessionLengthSeconds = session.getEnterDate().until(inside ? LocalDateTime.now() : session.getExitDate(), ChronoUnit.SECONDS);
        map.put("sls", sessionLengthSeconds);
        long hours = TimeUnit.SECONDS.toHours(sessionLengthSeconds);
        sessionLengthSeconds -= TimeUnit.HOURS.toSeconds(hours);
        long minutes = TimeUnit.SECONDS.toMinutes(sessionLengthSeconds);
        sessionLengthSeconds -= TimeUnit.MINUTES.toSeconds(minutes);
        map.put("h", hours);
        map.put("m", minutes);
        map.put("s", sessionLengthSeconds);
        return map;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(LocalDateTime enterDate) {
        this.enterDate = enterDate;
    }

    public LocalDateTime getExitDate() {
        return exitDate;
    }

    public void setExitDate(LocalDateTime exitDate) {
        this.exitDate = exitDate;
    }

    public boolean isInside() {
        return inside;
    }

    public void setInside(boolean inside) {
        this.inside = inside;
    }

    @Override
    public int compareTo(Session session) {
        return session.getEnterDate().compareTo(this.getEnterDate());
    }
}
