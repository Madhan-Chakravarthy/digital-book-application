import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { ApiService } from 'src/app/services/api.service';
import { Book, SearchBook } from 'src/entity/book';

@Component({
  selector: 'app-search-book-form',
  templateUrl: './search-book-form.component.html',
  styleUrls: ['./search-book-form.component.scss'],
})
export class SearchBookFormComponent {
  constructor(private route: Router) {}
  books: Book[] = [];
  viewSearchBookList(search: SearchBook){
    if(!search.price) search.price =0;
  this.route.navigate(['books'],{queryParams:{
    tittle:search.tittle,
    publisher:search.publisher,
    price:search.price,
    authorName:search.authorName,
    category: search.category,
    key:'search'
  }})
  }

}
