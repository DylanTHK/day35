import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CATEGORIES, COUNTRY_CODES } from '../constants';
import { Country } from '../model';
import { NewsService } from '../news.service';

@Component({
  selector: 'app-news-criteria',
  templateUrl: './news-criteria.component.html',
  styleUrls: ['./news-criteria.component.css']
})
export class NewsCriteriaComponent implements OnInit {

  countries: Country[] = []
  codes = COUNTRY_CODES;
  categories = CATEGORIES;
  newsForm!: FormGroup;

  constructor(private fb:FormBuilder, private newsSvc: NewsService) { };

  ngOnInit(): void {
      // call method from service to return Promise, resolve here to extract countries array
      this.newsSvc.getAllCountries()
        .then(result => this.countries = result);

      this.newsForm = this.fb.group({
        country: this.fb.control('sg', [Validators.required]),
        category: this.fb.control('business', [Validators.required])
      });
  }

  getNews() {
    // console.info("Country codes:", COUNTRY_CODES)
    const country = this.newsForm.get("country")?.value;
    const category = this.newsForm.get("category")?.value;
    console.info("Country received: ", country);

    this.newsSvc.getNews(country, category);
  }
}
