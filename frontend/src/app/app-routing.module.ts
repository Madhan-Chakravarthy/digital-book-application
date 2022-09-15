import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { BookDetailsComponent } from './components/book-details/book-details.component';
import { BookFormComponent } from './components/book-form/book-form.component';
import { BookListComponent } from './components/book-list/book-list.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { SearchBookFormComponent } from './components/search-book-form/search-book-form.component';
import { SignupFormComponent } from './components/signup-form/signup-form.component';

const routes: Routes = [
  {path:'savebook', component: BookFormComponent},
  {path:'searchbook', component: SearchBookFormComponent},
  {path:'login', component: LoginFormComponent},
  {path:'signup', component: SignupFormComponent},
  {path:'books', component: BookListComponent},
  {path:'books/:id', component: BookDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
