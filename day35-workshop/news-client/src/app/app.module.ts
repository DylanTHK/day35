import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'


import { AppComponent } from './app.component';
import { NewsService } from './news.service';
import { NewsDisplayComponent } from './components/news-display.component';
import { NewsCriteriaComponent } from './components/news-criteria.component';

@NgModule({
  declarations: [
    AppComponent,
    NewsDisplayComponent,
    NewsCriteriaComponent
  ],
  imports: [
    BrowserModule, ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [ NewsService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
