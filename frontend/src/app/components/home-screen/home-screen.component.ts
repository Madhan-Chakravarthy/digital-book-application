import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-home-screen',
  templateUrl: './home-screen.component.html',
  styleUrls: ['./home-screen.component.scss'],
})
export class HomeScreenComponent implements OnInit {
  constructor(
    private route: Router,
    private tokenStorage: TokenStorageService
  ) {}

  isLoggedIn: boolean = false;
  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
    } else {
      this.noUserFound();
    }
  }

  noUserFound() {
    this.route.navigate(['login']);
  }
}
