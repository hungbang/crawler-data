package com.tripadvisor.crawlerdata.crawl;
/*
 * GetDataByPriceFilter.java
 *
 * Project: crawler-data
 *
 * Copyright 2017 by Canton de Vaud
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Canton de Vaud. ("Confidential Information"). You
 * shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license
 * agreement you entered into with Canton de Vaud.
 */


/*
 *@author xhbq
 */

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectData {

    private static final String url = "https://www.tripadvisor.com.sg";
    private static final String fileName = "restaurant";
    private static final String detailFileName = "restaurant_detail";
    private static final String dir = "E:\\projects\\crawlet-data\\src\\main\\resources\\";

    public static void loadData() throws IOException {

        for (int i = 0; i <= 78; i++) {
            Document doc = Jsoup.connect("https://www.tripadvisor.com.sg/RestaurantSearch?Action=PAGE&geo=294229&ajax=1&pid=14&sortOrder=relevance&o=a" + i + "&availSearchEnabled=false").get();
            File file = new File(fileName + "_" + i + ".html");
            if (file.exists()) {
                file.mkdir();
            }
            FileUtils.writeStringToFile(file, doc.outerHtml(), "UTF-8");
        }

    }

    public static List<Restaurant> parseDataToObject() throws IOException {
        List<String> links = new ArrayList<>();
        for (int i = 0; i <= 78; i++) {
            File file = new File(fileName + "_" + i + ".html");
            Document doc = Jsoup.parse(file, "UTF-8");
            Element eatery_search_results = doc.getElementById("EATERY_SEARCH_RESULTS");
            Elements elements = eatery_search_results.select("div.title a.property_title");
            List<String> detailLinks = elements.stream().map(element -> url + element.attr("href")).collect(Collectors.toList());

            links.addAll(detailLinks);
            Restaurant restaurant = Restaurant.builder().
                    build();
        }
        System.out.println("links.size()" + links.size());
        for (int j = 0; j < links.size(); j++) {
            try {
                Document docDetail = Jsoup.connect(links.get(j)).get();
                File detailFile = new File(detailFileName + "_" + j + ".html");
                if (detailFile.exists()) {
                    detailFile.mkdir();
                }

                FileUtils.writeStringToFile(detailFile, docDetail.outerHtml(), "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Loaded page " + j);
        }


        return null;
    }


    public static void loadDataDetail() throws IOException {
        for (int i = 0; i < 8; i++) {

            File detailFile = new File(detailFileName + "_" + i + ".html");
            Document doc = Jsoup.parse(detailFile, "UTF-8");

            Element restaurant_details = doc.getElementById("RESTAURANT_DETAILS");
            String address = restaurant_details.select(".detailsContent span.format_address span.street-address").text()
                    + restaurant_details.select(".detailsContent span.format_address span.extended-address").text()
                    + restaurant_details.select(".detailsContent span.format_address span.locality").text()
                    + restaurant_details.select(".detailsContent span.format_address span.country-name").text();

        }
    }
}
