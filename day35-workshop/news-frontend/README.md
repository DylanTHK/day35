# Practice34PMnews

https://newsapi.org/v2/top-headlines?country=us&category=sports&apiKey=561d5b45eaac44498db578f1e3dc4c18

OverView of Flow
1. root html (app.component.html)
    - host form and display component
2. root ts (app.component.ts)
    - initialise form for validation
    - insert values from form to service method
3. service (weather.service.ts)
    - method to construct url and make API Call (Http Request)
    - convert data to model structure
    - fire data into local Subject
4. display component ts (weather.display.component.ts)
    - initialise subscription
    - add subscription to newsService's Subject (listener)
    - update local variable with data
    - terminate subscription
5. display component html (weather-display.component.html)
    - display half of page
    - extract data from display ts


https://newsapi.org/docs/endpoints/top-headlines
Add data to drop bar (with API calls)
- constants (array of countries, categories)
- add countries
- add categories
- add flag image