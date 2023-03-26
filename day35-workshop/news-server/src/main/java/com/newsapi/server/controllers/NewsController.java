package com.newsapi.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.newsapi.server.models.Article;
import com.newsapi.server.services.NewsService;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path="/api")
public class NewsController {
    
    @Autowired
    NewsService newsSvc;

    @GetMapping()
    // @ResponseBody
    public String get() {
        return "Random String";
    }

    @GetMapping(path="/{country}/{category}/{pageSize}")
    public ResponseEntity<String> getNews(@PathVariable String country,
        @PathVariable String category,
        @PathVariable Integer pageSize) {

        System.out.println(category + " " + pageSize);
        List<Article> newsArticles = newsSvc.getNews(country, category, pageSize);
        // System.out.println("Articles Received");

        // initialise builder
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        // convert each element to JsonObject, add to builder
        newsArticles.stream()
            .forEach(a -> {
                JsonObject o = a.toJson();
                arrBuilder.add(o);
            });

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(arrBuilder.build().toString());

        // return newsArticles.toString();
    }


}
