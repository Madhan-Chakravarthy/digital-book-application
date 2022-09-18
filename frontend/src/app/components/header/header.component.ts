import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(private route: Router) { }
 searchParam:string='';
  ngOnInit(): void {
  }
  authorsBook(){
    this.route.navigate(['books'],{queryParams:{
      key:'author'
    }})
  }
  readersBook(){
    this.route.navigate(['books'],{queryParams:{
      key:'reader'
    }})
  }
  simpleSearchBook(SearchBook:any){
    this.searchParam=SearchBook.search
    console.log(this.searchParam + 'hiii');
    this.route.navigate(['books'],{queryParams:{
      searchParam: this.searchParam,
      key:'simple-search'
    }})
  }

}
