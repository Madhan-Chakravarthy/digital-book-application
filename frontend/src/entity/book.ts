export interface Book {
  id:number,
  tittle: string;
  category:String,
  price: number;
  publishDate: Date;
  active: Boolean;
  content: String;
}
export interface SearchBook {
  tittle: string;
  category:string,
  price: number;
  publisher:string;
  authorName: string;
}
