import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom, Observable, Subject } from "rxjs";
import { COUNTRY_CODES, COUNTRY_URL, NEWS_URL } from "./constants";
import { Article, Country } from "./model";


@Injectable()
export class NewsService {

    countries: Country[] = [];

    onNews = new Subject<Article[]>();

    constructor(private http: HttpClient) { };

    // HTTP Request from newsapi.org
    getNewsAsObservable(country: string, category: string): Observable<Article[]> {
        const params = new HttpParams()
            .set('country', country)
            .set('category', category)
            .set('pageSize', 10)
            // for angular query
            // .set('apiKey', NEWS_KEY);

        return this.http.get<Article[]>(NEWS_URL, { params }).pipe();
    }

    getNews(country: string, category: string): Promise<Article[]> {
        return firstValueFrom(
            this.getNewsAsObservable(country, category)
        )
        // function to listen to resolve
        .then((data: any) => {
            // console.info(">>> extracted data:", data);
            // convert json to articles
            const articles = data.articles as Article[];
            // console.info(">>> extracted articles:", articles);
            return articles;
        })
        .then((data: any) => {
            // firing data to event
            this.onNews.next(data)
            return data;
        })
    }
    // : Promise<Country[]>
    getAllCountries(): Promise<Country[]> {
        // set params
        const params = new HttpParams()
            .set("codes", COUNTRY_CODES);

        // GET Request from API, returns Observable
        const response = this.http.get<Country[]>(COUNTRY_URL, {params})

        // firstValueFrom Request, returns Promise<>
        const promise = firstValueFrom(response)
            .then(data => { // data is an array of countries (raw)
            // console.info("data: ", data)
            // transforms each element in array extract and overwrite existing element value
            // adds new array of Country Objects to local countries array
            this.countries = data.map((country: any) => (
                {
                    name: country.name.official,
                    code: country.cca2.toLowerCase(),
                    flag: country.flags.png
                } as Country
            ))
            // console.info("Countries (after map:", this.countries)
            return this.countries;
        }) // then

        return promise;
        
    }
}