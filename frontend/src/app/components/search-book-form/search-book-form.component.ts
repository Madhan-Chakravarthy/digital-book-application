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
  constructor(public apiService: ApiService,private route: Router) {}
  books: Book[] = [];
  viewSearchBookList(search: SearchBook){
    this.route.navigate(['books'],{queryParams:{search}})
  }
  serachBook(search: SearchBook) {
    const observable = this.apiService.searchBook(search);
    observable.subscribe(
      (response) => console.log(response),
      (error) => alert('something went wrong')
    );
  }
}
