import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Article } from '../model';
import { NewsService } from '../news.service';

@Component({
  selector: 'app-news-display',
  templateUrl: './news-display.component.html',
  styleUrls: ['./news-display.component.css']
})
export class NewsDisplayComponent implements OnInit, OnDestroy {

  // for html to access
  articles: Article[] = []

  // FIXME: hold subsciption value to unsubscribe?
  newsSub!: Subscription

  // inject weather service
  constructor(private newsSvc: NewsService) { }
  
  // To subscribe to changes in newsSvc
  ngOnInit(): void {
      console.info(">>> subscribing to news")
      this.newsSub = this.newsSvc.onNews.subscribe(
        // update articles with new data
        data => this.articles = data
      )
  }

  // cancel subscription
  ngOnDestroy(): void {
      this.newsSub.unsubscribe()
  }

}
