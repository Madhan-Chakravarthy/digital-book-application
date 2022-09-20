import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const API_URL='http://localhost:8080/auth/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})

export class AuthService {

  constructor(private client:HttpClient) { }

  signup(signupData:any){
    return this.client.post(API_URL+'signup',signupData);
  }
  login(username: string, password: string): Observable<any> {
    return this.client.post(API_URL + 'login', {
      username,
      password
    }, httpOptions);
  }
}
