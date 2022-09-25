import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  constructor(
    private route: Router,
    private tokenStorageService: TokenStorageService
  ) {}
  searchParam: string = '';
  currentUser: any;
  private roles: string[] = [];
  isLoggedIn = false;
  showReaderBoard = false;
  showAuthorBoard = false;
  username?: string;
  ngOnInit(): void {
    this.currentUser = this.tokenStorageService.getUser();
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      this.showReaderBoard = this.roles.includes('ROLE_READER');
      this.showAuthorBoard = this.roles.includes('ROLE_AUTHOR');

      this.username = user.username;
    }
  }
  authorsBook() {
    this.route.navigate(['books'], {
      queryParams: {
        key: 'author',
      },
    });
  }
  readersBook() {
    this.route.navigate(['books'], {
      queryParams: {
        key: 'reader',
      },
    });
  }
  simpleSearchBook(SearchBook: any) {
    this.searchParam = SearchBook.search;
    console.log(this.searchParam + 'hiii');
    this.route.navigate(['books'], {
      queryParams: {
        searchParam: this.searchParam,
        key: 'simple-search',
      },
    });
  }
  logout() {
    this.tokenStorageService.signOut();
    this.route.navigate(['login']);
    this.ngOnInit();
  }
}
