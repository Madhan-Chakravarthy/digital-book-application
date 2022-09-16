import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from 'src/app/services/api.service';
import { Book, SearchBook } from 'src/entity/book';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.scss']
})
export class BookListComponent implements OnInit {

  constructor(private apiService:ApiService,private route:ActivatedRoute) { }
search:SearchBook={
  tittle:'NA',
  authorName:'NA',
  category:'NA',
  price:0,
  publisher:'NA'
}
books:any=[];
  ngOnInit(): void {
   this.route.queryParams.subscribe((params)=>{
    this.search.tittle=params['tittle'];
    this.search.authorName=params['authorName'];
    this.search.publisher=params['publisher'];
    this.search.category=params['category'];
    this.search.price=params['price'];
   })
   if(this.search.category !=undefined)
   this.searchBookHandler(this.search);
   else{
    console.log('else part')
    this.authorsBook(1);
   }
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
  authorsBook(id:number){
    const observable = this.apiService.authorsBook(id);
    observable.subscribe(
      (response) =>{ console.log(response)
        this.books=response},
      (error) => alert('something went wrong')
    );
  }


}
