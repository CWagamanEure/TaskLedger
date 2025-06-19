package com.taskledger.api;

import com.taskledger.blockchain.Block;
import com.taskledger.blockchain.Blockchain;
import com.taskledger.model.Task;
import com.taskledger.crawler.TipCrawler;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final Blockchain blockchain = new Blockchain();
    private final TipCrawler crawler = new TipCrawler();

    @GetMapping("/tips")
    public List<String> getTips(){
        return crawler.getTips();
    }

    @PostMapping
    public String addTask(@RequestBody Task task){
        blockchain.addTask(task);
        return "Task added to blockchain";

    }

    @GetMapping("/chain")
    public List<Block> getFullChain(){
        return blockchain.getBlockChain();

    }
    @GetMapping
    public List<Task> getAllTasks() {
        return blockchain.getBlockChain().stream()
            .map(Block::getTask)
            .collect(Collectors.toList());
}


    @GetMapping("/validate")
    public String isChainValid(){
        return blockchain.isChainValid()
            ? "Blockchain is valid"
            : "Blockchain has been tampered";
    }
    @PutMapping("/{id}/complete")
    public String markTaskAsCompleted(@PathVariable Long id) {
        List<Task> allTasks = blockchain.getBlockChain().stream()
            .map(Block::getTask)
            .collect(Collectors.toList());

        for (Task task : allTasks) {
            if (task.getId().equals(id)) {
                if (task.isCompleted())
                    return "Task already completed";
                Task updatedTask = new Task(task.getTitle(), true);
                blockchain.addTask(updatedTask);
                return "Task marked as completed";
        }
    }

        return "Task with ID " + id + " not found.";
    }



    
}
