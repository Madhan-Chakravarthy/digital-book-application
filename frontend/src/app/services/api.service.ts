import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Book, SearchBook } from 'src/entity/book';
import { TokenStorageService } from './token-storage.service';

const API_URL='http://localhost:8080/';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  userId:number=1;
  constructor(private client:HttpClient, private tokenStorageService: TokenStorageService) { this.userId=tokenStorageService.getUser().id }
  SimplesearchBook(searchParam: string) {
    let params = new HttpParams({
      fromObject:{
       "searchParam":searchParam
      }
     });
     return this.client.get(API_URL + 'reader/books/ssearch',{params:params});
  }
  raedersBook() {
    return this.client.get(API_URL+'reader/'+ this.userId+'/books/buy/');
  }
  puchaseBook(bookIds: number[]) {
   const books={
    bookIds
   }
   console.log(books);
    return this.client.post(API_URL+'reader/'+ this.userId+'/books/buy/',books)
  }
  authorsBook() {
    return this.client.get(API_URL+'author/'+this.userId+'/book/');
  }

  saveBook(book:Book){
    return this.client.post(API_URL +'author/1/book',book);
   }
   getBookById(id:number){
    return this.client.get(API_URL+'reader/'+ this.userId + '/books/'+id);
   }
   searchBook(searchDetails:any){
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
