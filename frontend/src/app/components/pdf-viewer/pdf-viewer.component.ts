import { Component, OnInit } from '@angular/core';
import { pdfDefaultOptions } from 'ngx-extended-pdf-viewer';
@Component({
  selector: 'app-pdf-viewer',
  templateUrl: './pdf-viewer.component.html',
  styleUrls: ['./pdf-viewer.component.scss']
})
export class PdfViewerComponent implements OnInit {

  constructor() { }
  pdfSrc:string="https://vadimdez.github.io/ng2-pdf-viewer/assets/pdf-test.pdf";
  title = 'sampleapp';

  public page = 2;

  public pageLabel!: string;

  ngOnInit(): void {
  }

}
