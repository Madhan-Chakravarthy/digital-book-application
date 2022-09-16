import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

const API_URL='http://localhost:8080/';
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private client:HttpClient) { }

  signup(signupData:any){
    return this.client.post(API_URL+'auth/signup',signupData);
  }
}
