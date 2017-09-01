package com.hulk.voicetube.profile;

import java.io.Serializable;

/**
 * Created by Administrator on 13/8/2017.
 */

public class Profile implements Serializable {
    //昵称，观看次数，持续天数，复述时间，持续学习时间
    private String nickname;
    private String watched;
    private String streak;
    private String recorded;
    private String spent;
    private String collected;

    /**
     * Constructs a new instance of {@code Object}.
     */
    public Profile(String nickname, String watched, String streak, String recorded, String spent, String collected) {
        this.nickname = nickname;
        this.watched = watched;
        this.streak = streak;
        this.recorded = recorded;
        this.spent = spent;
        this.collected = collected;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getWatched() {
        return watched;
    }

    public void setWatched(String watched) {
        this.watched = watched;
    }

    public String getStreak() {
        return streak;
    }

    public void setStreak(String streak) {
        this.streak = streak;
    }

    public String getRecorded() {
        return recorded;
    }

    public void setRecorded(String recorded) {
        this.recorded = recorded;
    }

    public String getSpent() {
        return spent;
    }

    public void setSpent(String spent) {
        this.spent = spent;
    }

    public String getCollected() {
        return collected;
    }

    public void setCollected(String collected) {
        this.collected = collected;
    }
}
