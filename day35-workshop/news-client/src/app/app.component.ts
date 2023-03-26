import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NewsService } from './news.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit{
  title = 'Day35AM Practice - Full Stack News API';

  newsForm!: FormGroup;

  constructor(private fb: FormBuilder, private newsSvc: NewsService) { }

  ngOnInit(): void {
      this.newsForm = this.fb.group({
        country: this.fb.control('sg', [Validators.required]),
        category: this.fb.control('business', [Validators.required])
      })
  }

  getNews() {
    const country = this.newsForm.value.country;
    const category = this.newsForm.value.category;
    console.info(">>> country: ", country)
    console.info(">>> category: ", category)
    // call new service for API call
    this.newsSvc.getNews(country, category);
  }

}
