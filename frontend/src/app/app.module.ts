import { NgModule } from '@angular/core';
import {  BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { BookFormComponent } from './components/book-form/book-form.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { SignupFormComponent } from './components/signup-form/signup-form.component';
import { SearchBookFormComponent } from './components/search-book-form/search-book-form.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    BookFormComponent,
    LoginFormComponent,
    SignupFormComponent,
    SearchBookFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
