import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from 'src/app/services/api.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { Book } from 'src/entity/book';
import { UploadResponse } from 'aws-s3-upload-ash/dist/types';
import AWSS3UploadAshClient from 'aws-s3-upload-ash';

@Component({
  selector: 'app-book-form',
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.scss'],
})
export class BookFormComponent implements OnInit {
  status: string = 'form';
  key: string = '';
  bookId: number = 0;
  maxDate: any = Date.now;
  book: any = {
    id: 0,
    tittle: '',
    category: '',
    price: 0,
    publishDate: null,
    active: false,
    content: '',
    purchased: false,
    author: null,
  };
  isLoggedIn: boolean = false;
  fileSelected: any = null;
  constructor(
    public apiService: ApiService,
    private route: ActivatedRoute,
    private tokenStorage: TokenStorageService,
    private router: Router
  ) {}
  config = {
    bucketName: 'mybucket-mcm',
    dirName:
      'books' /* optional - when use: e.g BUCKET_ROOT/dirName/fileName.extesion */,
    region: 'us-east-1',
    accessKeyId: 'AKIA6I2SLEYRUZENAEW2',
    secretAccessKey: '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',
    s3Url: 'https://aws-s3-upload-ash.s3.amazonaws.com/',
  };
  S3CustomClient: AWSS3UploadAshClient = new AWSS3UploadAshClient(this.config);
  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
    } else {
      this.noUserFound();
    }
    this.route.paramMap.subscribe(() => {
      this.handleBookDetails();
    });
  }
  saveBook(book: Book) {
    if (this.bookId != 0) {
      const observable = this.apiService.editBook(this.bookId, book);
      observable.subscribe(
        (response) => {
          console.log(response);
          this.status = 'success';
        },
        (error) => {
          this.status = 'error';
        }
      );
    } else {
      console.log(book);
      //  this.handleSendFile();
      const observable = this.apiService.saveBook(book);
      observable.subscribe(
        (response) => {
          console.log(response);
          this.status = 'success';
        },
        (error) => {
          this.status = 'error';
        }
      );
    }
  }
  handleBookDetails() {
    this.bookId = +this.route.snapshot.paramMap.get('id')!;
    if (this.bookId != 0)
      this.apiService.getBookById(this.bookId).subscribe((book) => {
        this.book = book;
      });
    console.log(this.book.tittle);
  }
  noUserFound() {
    this.router.navigate(['login']);
  }
  onChangeFile(event: any) {
    console.log(event.target.files[0]);
    this.fileSelected = event.target.files[0];
  }
  async handleSendFile() {
    console.log('handleSendFile');
    await this.S3CustomClient.uploadFile(
      this.fileSelected,
      this.fileSelected.type,
      undefined,
      this.fileSelected.name,
      'public-read'
    )
      .then((data: UploadResponse) => console.log(data))
      .catch((err: any) => console.error(err));
  }
}
