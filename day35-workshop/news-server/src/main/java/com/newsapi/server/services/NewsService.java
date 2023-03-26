package com.newsapi.server.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.newsapi.server.models.Article;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import static com.newsapi.server.models.Constants.*;

import java.io.StringReader;
import java.util.Collections;
import java.util.List;

@Service
public class NewsService {
    
    @Value("${news.key}")
    private String newsKey;

    public List<Article> getNews(String country, String category, Integer pageSize) {
        
        System.out.println("News URL: " + NEWS_URL);
        String url = UriComponentsBuilder
            .fromUriString(NEWS_URL)
            .queryParam("country", country)
            .queryParam("category", category)
            .queryParam("pageSize", pageSize)
            .queryParam("apiKey", newsKey)
            .toUriString();

        System.out.println("News URL (Formatted): " + url);

        // make api call with url string
        RequestEntity<Void> req = RequestEntity.get(url)
            .accept(MediaType.APPLICATION_JSON)
            .build();

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> resp = null;

        try {
            resp = restTemplate.exchange(req, String.class);
        } catch(RestClientException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }

        // String payload = resp.getBody();
        // JsonReader reader = Json.createReader(new StringReader(payload));
        // JsonObject newsResp = reader.readObject();
        // JsonArray jsonArray = newsResp.getJsonArray("articles");

        JsonReader reader = Json.createReader(new StringReader(resp.getBody()));

        JsonArray jsonArray = reader
            .readObject() // JsonObject
            .getJsonArray("articles"); // JsonArray
            

        // System.out.println("\n\nPayload: " + payload);
        // System.out.println("\n\nReader: " + reader);
        // System.out.println("\n\nNews Response: " + newsResp);
        // System.out.println("\n\nNews Array: " + jsonArray);

        // 
        return jsonArray.stream()
            // convert each item to json object
            .map(a -> {
                return a.asJsonObject();
            })
            // maps toArticle to each instance (output should match class)
            .map(Article::toArticle) 
            .toList();

        // System.out.println("\n\nNews Array: " + jsonArray);
    }


}
