# Day 35 Spring Boot and Angular Application

localhost:8080
GET /api/news/country/category?pageSize=10

## To log errors?
private Logger logger = Logger.getlogger(NewsController.class.getName());

## SpringBoot
RestController to take in input 

## Postman to test controller methods with end points


## destructuring 
export interface Criteria {
    code: ...
    category: ...
}
const { code, category } = criteria;
SAME AS 
const code = criteria.code;
const category = criteria.category;


## Deployment
1. host on springboot
- put html and spring files in static

2. Web server (vercel)


## Environment variables 

news.key = 
export NEWS_KEY = {insert api key}


