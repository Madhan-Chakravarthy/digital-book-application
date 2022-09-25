import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { ApiService } from 'src/app/services/api.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { Book, SearchBook } from 'src/entity/book';

@Component({
  selector: 'app-search-book-form',
  templateUrl: './search-book-form.component.html',
  styleUrls: ['./search-book-form.component.scss'],
})
export class SearchBookFormComponent implements OnInit {
  constructor(
    private route: Router,
    private tokenStorage: TokenStorageService
  ) {}
  isLoggedIn: boolean = false;
  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
    } else {
      this.noUserFound();
    }
  }
  books: Book[] = [];
  viewSearchBookList(search: SearchBook) {
    if (!search.price) search.price = 0;
    this.route.navigate(['books'], {
      queryParams: {
        tittle: search.tittle,
        publisher: search.publisher,
        price: search.price,
        authorName: search.authorName,
        category: search.category,
        key: 'search',
      },
    });
  }

  noUserFound() {
    this.route.navigate(['login']);
  }
}
