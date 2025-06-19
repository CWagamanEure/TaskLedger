package com.taskledger.model;

public class Task {
    private static long nextId = 0;
    private long id =0;
    private String title;
    private boolean completed;
    private long timestamp;

    public Task(){
        this.timestamp = System.currentTimeMillis();
    }

    public Task(String title, boolean completed){
        this.id = nextId++;
        this.title = title;
        this.completed = completed;
        this.timestamp = System.currentTimeMillis();
        if (id >= nextId){
            nextId = id +1;
        }
    }


    public Long getId(){
        return id;
    }

    public String getTitle(){
        return title;

    }
    public void setTitle(String title){
        this.title = title;
    }
    public boolean isCompleted(){
        return completed;
    }
    public void setCompleted(boolean completed){
        this.completed = completed;
    }
    public long getTimestamp(){
        return timestamp;
    }
    public void setTimestamp(long timestamp){
        this.timestamp = timestamp;
    }
}