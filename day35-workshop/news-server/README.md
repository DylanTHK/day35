# Day35 - News App (Server side)

## API Call
1. Build URL string
```
String url = UriComponentsBuilder
    .fromUriString(NEWS_URL)
    .queryParam("country", country)
    .queryParam("category", category)
    .queryParam("apiKey", newsKey)
    .toUriString();
```
2. Build RequestEntity (for use with RestTemplate)
```
RequestEntity<Void> req = RequestEntity.get(url)
    .accept(Mediatype.APPLICATION_JSON)
    .build();
```

3. Make API Call with RestTemplate
```
RestTemplate restTemplate = new RestTemplate();
ResponseEntity<String> resp = null;
try {
    resp = restTemplate.exchange(req, String.class)
} catch (RestClientException e) {
    e.printStackTrace();
    return Collections.EMPTY_LIST;
}

```

## Json Manipulation (JSON to POJO) - NewsService.java
1. get payload from ResponseEntity
```
String payload = resp.getBody();
```

2. Instantiate JsonReader with StringReader() and Json.createReader()
```
JsonReader reader = Json.createReader(new StringReader(payload));
```
3. Extract JsonArray from Response
```
JsonArray jsonArray = reader
    .readObject() // convert JsonReader to JsonObject
    .getJsonArray("articles"); // extracts JsonArray from JsonObject
```
4. convert JsonArray to POJO List
```
jsonArray.stream()
    .map(a -> a.asJsonObject()) // converts JsonArray elements to JsonObjects
    .map(Article::toArticle) // converts JsonObjects to Article Object with static method toArticle(JsonObject obj)
    .toList() // convert JsonArray to List<Article>
```


## Json Manipulation (POJO to JSON) - NewsController.java
1. convert List<Article> to JsonArray
```
JsonArrayBuilder builder = Json.createArrayBuilder();
articles.stream()
    .forEach(a -> {
        // uses model method to convert POJO to JsonObject
        // adds new JsonObject to JsonArrayBuilder
        builder.add(a.toJson()) 
    })
```
2. toJson()
```
public JsonObject toJson() {
    return Json.createObjectBuilder()
        .add("author", author)
        .add("title", title)
        .add("description", description)
        .build();
}
```



## Java Streams
.map() - transforming elements in stream

```
jsonArray.stream()
    // converts each element in jsonArray to JsonObject
    .map(a -> a.asJsonObject())
    // calls toArticle from Article model to convert JsonObject to Article Object
    .map(Article::toArticle) 
```

.map() - for multiple lines
```
jsonArray.stream()
    .map(x -> {
        String a = x + "additional line";
        return a;
    })
```

.forEach() - for iterating over each element in stream (no need to return any value)
- iterate, and add to another list


.filter() - selecting elements that pass the condition
```
jsonArray.stream()
    .filter(x -> x > 10);
```

## Deployment to railway
1. update application.properties
2. railway up

## Railway up
1. railway login
2. railway init (create new proj) / railway login (link to existing)
3. railway up
4. update railway environment variables
- NEWS_KEY=561d5b45eaac44498db578f1e3dc4c18