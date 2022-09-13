import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';
import { Book } from 'src/entity/book';

@Component({
  selector: 'app-book-form',
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.scss']
})
export class BookFormComponent  {

  constructor(public apiService:ApiService) { }
  saveBook(book:Book){
    const date= book.publishDate;
    console.log(book);
    const observable= this.apiService.saveBook(book);
    observable.subscribe((response) =>
    console.log(response),
    (error)=>alert("something went wrong")
  );

}
}
