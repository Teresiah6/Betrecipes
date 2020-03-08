package com.example.android.foodrecipes;

public class Bet {
    public void setId(String id) {
        this.id = id;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String id;
    public String key;
   // public boolean active;
    public String group;

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getGroup() {
        return group;
    }

    public String getTitle() {
        return title;
    }

    public  String title;

    public Bet(String id, String key, String group, String title) {
        this.id = id;
        this.key = key;
        this.group = group;
        this.title = title;
    }

}
