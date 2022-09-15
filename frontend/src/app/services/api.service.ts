import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Book, SearchBook } from 'src/entity/book';

const API_URL='http://localhost:8080/';
@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private client:HttpClient) { }

  saveBook(book:Book){
    return this.client.post(API_URL +'author/1/book',book);
   }
   getBookById(id:number){
    return this.client.get(API_URL+'reader/books/'+id);
   }
   searchBook(searchDetails:SearchBook){
    let params = new HttpParams({
     fromObject:{
      "tittle":searchDetails.tittle,
      "price":searchDetails.price,
      "publisher":searchDetails.publisher,
      "category":searchDetails.category,
      "authorName":searchDetails.authorName
     }
    });
    return this.client.get(API_URL + 'reader/books/search',{params:params});
   }
}
