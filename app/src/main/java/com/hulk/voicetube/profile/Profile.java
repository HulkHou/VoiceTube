package com.hulk.voicetube.profile;

import java.io.Serializable;

/**
 * Created by Administrator on 13/8/2017.
 */

public class Profile implements Serializable {
    //昵称，观看次数，持续天数，复述时间，持续学习时间
    private String Nickname;
    private String Watched;
    private String Streak;
    private String Recorded;
    private String Spent;
    private String Collected;

    /**
     * Constructs a new instance of {@code Object}.
     */
    public Profile(String Nickname, String Watched, String Streak, String Recorded, String Spent, String Collected) {
        this.Nickname = Nickname;
        this.Watched = Watched;
        this.Streak = Streak;
        this.Recorded = Recorded;
        this.Spent = Spent;
        this.Collected = Collected;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public String getWatched() {
        return Watched;
    }

    public void setWatched(String watched) {
        Watched = watched;
    }

    public String getStreak() {
        return Streak;
    }

    public void setStreak(String streak) {
        Streak = streak;
    }

    public String getRecorded() {
        return Recorded;
    }

    public void setRecorded(String recorded) {
        Recorded = recorded;
    }

    public String getSpent() {
        return Spent;
    }

    public void setSpent(String spent) {
        Spent = spent;
    }

    public String getCollected() {
        return Collected;
    }

    public void setCollected(String collected) {
        Collected = collected;
    }
}
