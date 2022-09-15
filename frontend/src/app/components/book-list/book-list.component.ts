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
search:any;
books:any=[];
  ngOnInit(): void {
   this.route.queryParams.subscribe((params)=>{
    console.log(params['search']);
      this.search=params['search'];
      this.serachBook(this.search);
   })
  }

  serachBook(search: SearchBook) {
    const observable = this.apiService.searchBook(search);
    observable.subscribe(
      (response) =>{ console.log(response)
        this.books=response},
      (error) => alert('something went wrong')
    );
  }


}
