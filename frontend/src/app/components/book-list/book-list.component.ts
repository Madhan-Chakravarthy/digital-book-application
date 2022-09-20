import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from 'src/app/services/api.service';
import { Book, SearchBook } from 'src/entity/book';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.scss']
})
export class BookListComponent implements OnInit {

  constructor(private apiService:ApiService,private route:ActivatedRoute,private router: Router) { }
search:SearchBook={
  tittle:'NA',
  authorName:'NA',
  category:'NA',
  price:0,
  publisher:'NA'
}
books:any=[];
searchParam:string='';
key:string='';
  ngOnInit(): void {
   this.route.queryParams.subscribe((params)=>{
    this.search.tittle=params['tittle'];
    this.search.authorName=params['authorName'];
    this.search.publisher=params['publisher'];
    this.search.category=params['category'];
    this.search.price=params['price'];
    this.searchParam=params['searchParam'];
     this.key=params['key'];
     if(this.key==='search')
     this.searchBookHandler(this.search);

     if(this.key==='simple-search')
     this.simpleSearchBookHandler(this.searchParam);

     if(this.key==='author'){
      console.log('else author')
      this.authorsBook();
     }
     if(this.key==='reader'){
      console.log('else part')
      this.readersBook();
     }
   })

  }
  readersBook() {
    const observable = this.apiService.raedersBook();
    observable.subscribe(
      (response) =>{ console.log(response)
        this.books=response},
      (error) => alert('something went wrong')
    );
  }

  searchBookHandler(search: SearchBook) {
    console.log(search);
    const observable = this.apiService.searchBook(search);
    observable.subscribe(
      (response) =>{ console.log(response)
        this.books=response},
      (error) => alert('something went wrong')
    );
  }
  simpleSearchBookHandler(searchParam:string) {
    console.log(searchParam);
    const observable = this.apiService.SimplesearchBook(searchParam);
    observable.subscribe(
      (response) =>{ console.log(response)
        this.books=response},
      (error) => alert('something went wrong')
    );
  }
  authorsBook(){
    const observable = this.apiService.authorsBook();
    observable.subscribe(
      (response) =>{ console.log(response)
        this.books=response},
      (error) => alert('something went wrong')
    );
  }
  getBookById(id:number){
  this.router.navigate(['books/'+id],{queryParams:{
      key:this.key
    }})
  }

}
