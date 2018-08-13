package com.tripadvisor.crawlerdata;

import com.tripadvisor.crawlerdata.crawl.CollectData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class CrawlerDataApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(CrawlerDataApplication.class, args);
        CollectData.loadDataDetail();
    }

}
