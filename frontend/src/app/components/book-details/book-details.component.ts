import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from 'src/app/services/api.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { Book } from 'src/entity/book';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.scss']
})
export class BookDetailsComponent implements OnInit {

  constructor(private apiService:ApiService,private route:ActivatedRoute,private router: Router,private tokenStorage:TokenStorageService) { }
  book:any;
  key:string='';
  isLoggedIn:boolean=false;
  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
    }else{
      this.noUserFound();
    }
    this.route.queryParams.subscribe((params)=>{
       this.key=params['key'];
    });
    this.route.paramMap.subscribe(()=>{
      this.handleBookDetails();
    })
  }
  handleBookDetails(){
    const bookId:number=+this.route.snapshot.paramMap.get('id')!;
    this.apiService.getBookById(bookId).subscribe(
      book=>{
        this.book=book;
      }
    )
  }

  status:string='buy';
  purchaseBook(bookId:number){
   const bookIds =[bookId];
    const observable = this.apiService.puchaseBook(bookIds);
    observable.subscribe(
      (response) => {console.log(response)
      this.status='success';},
      (error) => {
        this.status='fail';
        }
    );
  }
  noUserFound(){
    this.router.navigate(['login']);
  }
}
