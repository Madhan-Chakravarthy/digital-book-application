import { NgModule } from '@angular/core';
import {  BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { HttpClientModule,HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { BookFormComponent } from './components/book-form/book-form.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { SignupFormComponent } from './components/signup-form/signup-form.component';
import { SearchBookFormComponent } from './components/search-book-form/search-book-form.component';
import { BookDetailsComponent } from './components/book-details/book-details.component';
import { BookListComponent } from './components/book-list/book-list.component';
import { HomeScreenComponent } from './components/home-screen/home-screen.component';
import { FooterComponent } from './components/footer/footer.component';
import { PdfViewerComponent } from './components/pdf-viewer/pdf-viewer.component';
import { NgxExtendedPdfViewerModule } from 'ngx-extended-pdf-viewer';
import { TokenInterceptorServiceService } from './services/token-interceptor-service.service';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    BookFormComponent,
    LoginFormComponent,
    SignupFormComponent,
    SearchBookFormComponent,
    BookDetailsComponent,
    BookListComponent,
    HomeScreenComponent,
    FooterComponent,
    PdfViewerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgxExtendedPdfViewerModule
  ],
  providers: [{
    provide : HTTP_INTERCEPTORS,
    useClass: TokenInterceptorServiceService,
    multi   : true,
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
