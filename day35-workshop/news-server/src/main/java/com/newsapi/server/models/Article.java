package com.newsapi.server.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Article {
    String title;
    String author;
    String description;
    String url;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Article [title=" + title + ", author=" + author + ", description=" + description + ", url=" + url + "]";
    }

    // TODO: update getValue() to get description as well
    public static Article toArticle(JsonObject obj) {
        Article a = new Article();
        a.setAuthor(getValue("author", obj));
        a.setTitle(getValue("title", obj));
        a.setDescription(getValue("description", obj));
        a.setUrl(getValue("url", obj));
        // System.out.println("Article: " + a);
        return a;
    }

    // get values that may be null
    public static String getValue(String label, JsonObject o) {
        if(o.containsKey(label) && !o.isNull(label)) {
            return o.getString(label);
        }
        return "No Value";
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("author", author)
            .add("title", title)
            .add("description", description)
            .add("url", url)
            .build();
    }
}
