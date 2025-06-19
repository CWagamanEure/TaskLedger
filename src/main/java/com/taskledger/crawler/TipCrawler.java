package com.taskledger.crawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class TipCrawler {
    private final List<String> tips = Collections.synchronizedList(new ArrayList<>());
    private final ExecutorService executor = Executors.newFixedThreadPool(3);

    public TipCrawler() {
        crawlSite("https://www.simplilearn.com/best-productivity-hacks-to-get-more-done-article");
        crawlSite("https://chrisbailey.com/100-time-energy-attention-hacks-will-make-productive/");
        crawlSite("https://hive.com/blog/productivity-tips/");
    }

    public void crawlSite(String url){
        executor.submit(() -> {
            try{
                Document doc = Jsoup.connect(url).get();
                Elements headlines = doc.select("h2, h3, li");
                headlines.forEach(element -> {
                    String text = element.text();
                    if (text.length() > 5 && text.length() < 20){
                        tips.add(text);
                    }
                });
                System.out.println("Crawled all over " + url);
            
            } catch (Exception e){
                System.err.println("Crawlless at " + url + ": " + e.getMessage());
            }
        });
    }

    public List<String> getTips(){
        return tips;
    }
    public void shutdown(){
        executor.shutdown();
    }
}
