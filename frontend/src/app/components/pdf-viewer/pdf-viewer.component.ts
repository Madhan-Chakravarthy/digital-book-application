import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { pdfDefaultOptions } from 'ngx-extended-pdf-viewer';
import { TokenStorageService } from 'src/app/services/token-storage.service';
@Component({
  selector: 'app-pdf-viewer',
  templateUrl: './pdf-viewer.component.html',
  styleUrls: ['./pdf-viewer.component.scss'],
})
export class PdfViewerComponent implements OnInit {
  constructor(
    private route: Router,
    private tokenStorage: TokenStorageService
  ) {}
  pdfSrc: string =
    'https://vadimdez.github.io/ng2-pdf-viewer/assets/pdf-test.pdf';
  title = 'sampleapp';

  public page = 2;

  public pageLabel!: string;
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
