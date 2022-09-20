import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from 'src/app/services/api.service';
import { Book } from 'src/entity/book';

@Component({
  selector: 'app-book-form',
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.scss'],
})
export class BookFormComponent implements OnInit {
  status: string = 'form';
  key: string = '';
  bookId: number = 0;
  book: any = {
    id: 0,
    tittle: '',
    category: '',
    price: 0,
    publishDate: null,
    active: false,
    content: '',
    purchased: false,
    author: null,
  };
  constructor(public apiService: ApiService, private route: ActivatedRoute) {}
  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.handleBookDetails();
    });
  }
  saveBook(book: Book) {
    if (this.bookId != 0) {
      const observable = this.apiService.editBook(this.bookId, book);
      observable.subscribe(
        (response) => {
          console.log(response);
          this.status = 'success';
        },
        (error) => {
          this.status = 'error';
        }
      );
    } else {
      console.log(book);
      const observable = this.apiService.saveBook(book);
      observable.subscribe(
        (response) => {
          console.log(response);
          this.status = 'success';
        },
        (error) => {
          this.status = 'error';
        }
      );
    }
  }
  handleBookDetails() {
    this.bookId = +this.route.snapshot.paramMap.get('id')!;
    if (this.bookId != 0)
      this.apiService.getBookById(this.bookId).subscribe((book) => {
        this.book = book;
      });
    console.log(this.book.tittle);
  }
}
