export interface Book {
  id:number;
  tittle: string;
  category:String,
  price: number;
  publishDate: any;
  active: Boolean;
  content: String;
  purchased:boolean;
  pdf:any;
  image:any;
  author:any;
}
export interface SearchBook {
  tittle: string;
  category:string,
  price: number;
  publisher:string;
  authorName: string;
}
