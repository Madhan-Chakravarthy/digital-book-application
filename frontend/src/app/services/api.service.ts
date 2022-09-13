import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Book } from 'src/entity/book';

const API_URL='http://localhost:8080/author/1/book';
@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private client:HttpClient) { }

  saveBook(book:Book){
    return this.client.post(API_URL,book);
   }
}
