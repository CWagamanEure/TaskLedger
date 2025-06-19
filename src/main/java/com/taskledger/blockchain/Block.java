package com.taskledger.blockchain;
import com.taskledger.model.Task;
import com.taskledger.util.HashUtil;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Block {
    private String previousHash;
    private String currentHash;
    private long timestamp;
    private Task task;

    public Block(){

    }
    public Block(String previousHash,Task task){
        this.task = new Task( task.getTitle(), task.isCompleted());
        this.previousHash = previousHash;
        this.timestamp = System.currentTimeMillis();
        this.currentHash = calculateHash();

    }
    @JsonCreator
    public Block(@JsonProperty("previousHash") String previousHash,@JsonProperty("task") Task task, @JsonProperty("timestamp") long timestamp,
             @JsonProperty("currentHash") String currentHash){
        this.task = task;
        this.previousHash = previousHash;
        this.timestamp = timestamp;
        this.currentHash = currentHash;

    }

    public String calculateHash() {
        String dataToHash = previousHash + timestamp +
                            task.getId() + task.getTitle() +
                            task.isCompleted() + task.getTimestamp();

        return HashUtil.applySha256(dataToHash);
    }




    public String getPreviousHash(){
        return previousHash;
    }
    public String getCurrentHash(){
        return currentHash;
    }
    public long getTimestamp(){
        return timestamp;
    }
    public Task getTask(){
        return task;
    }

}
