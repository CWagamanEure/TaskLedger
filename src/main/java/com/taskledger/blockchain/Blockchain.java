package com.taskledger.blockchain;

import com.taskledger.model.Task;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;


public class Blockchain {
    private List<Block> chain;
    private final String FILE_NAME = "blockchain.json";
    private final ObjectMapper mapper = new ObjectMapper();

    public Blockchain() {
        List<Block> loaded = loadFromFile();
        if (loaded != null && !loaded.isEmpty()){
            chain = loaded;
        }
        else{
            chain = new ArrayList<>();
            chain.add(new Block("0", new Task("Genesis Block", true)));
            saveToFile();
        }
    }

    public void addTask(Task task) {
        Block lastBlock = getLastBlock();
        String previousHash = (lastBlock == null) ? "0" : lastBlock.getCurrentHash();
        Block newBlock = new Block(previousHash, task);
        chain.add(newBlock);
        saveToFile();
}


    public Block getLastBlock() {
        if (chain == null || chain.isEmpty()) return null;
        return chain.get(chain.size() - 1);

    }

    public List<Block> getBlockChain() {
        return chain;
    }

    public boolean validateBlock(Block currentBlock){
        Block previousBlock = chain.get(chain.size() -1);
        if (previousBlock == null){
            if (chain.indexOf(currentBlock) != 0) return false;
            if (currentBlock.getPreviousHash() != null) return false;
            if (currentBlock.getCurrentHash() == null || !currentBlock.calculateHash().equals(currentBlock.getCurrentHash())) return false;
            return true; 
        } 
        else{
            if (currentBlock != null){
                if (chain.indexOf(previousBlock)+ 1 != chain.indexOf(currentBlock)) return false;
                if (currentBlock.getPreviousHash() == null || (!currentBlock.getPreviousHash().equals(previousBlock.getCurrentHash()))) return false;
                if (currentBlock.getCurrentHash() == null || !currentBlock.calculateHash().equals(currentBlock.getCurrentHash())) return false;
            } return true;
        }
    }

    public boolean isChainValid(){

        for (int i=1; i < chain.size(); i++){


            Block current = chain.get(i);
            Block previous = chain.get(i -1);


            if (!current.getCurrentHash().equals(current.calculateHash())){
                System.err.println("Hash mismatch at block" + i);
                System.err.println("Expected: " + current.getCurrentHash());
                System.err.println("Actual:   " + current.calculateHash());

                return false;
            }
            if (!current.getPreviousHash().equals(previous.getCurrentHash())){
                System.err.println("Previous hash mismatch at block" + i);

                return false;}
        }
        return true;
    }



    public List<Block> loadFromFile(){
        try{
            File file = new File(FILE_NAME);
            if (file.exists()){
                List<Block> loaded = mapper.readValue(file, new TypeReference<List<Block>>() {});
                System.out.println("The Blockchain lives on");
                return loaded;
            } else{
                System.out.println("No blockchain file found, starting fresh");
                return null;

            }

        } catch (IOException e){
            System.err.println("Failed to load blockchain: " + e.getMessage());
            return null;
        }
    }
    public void saveToFile(){
        try{
            mapper.writeValue(new File(FILE_NAME), chain);
        } catch(IOException e){
            System.err.println("Failed to save blockchain:" + e.getMessage());

        }
        
    }
}
