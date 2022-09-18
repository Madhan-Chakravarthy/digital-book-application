import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-signup-form',
  templateUrl: './signup-form.component.html',
  styleUrls: ['./signup-form.component.scss']
})
export class SignupFormComponent implements OnInit {

  constructor(public auth:AuthService) { }
 signupdata:any;
 roleList:string[]=[];
 error:string='';
  ngOnInit(): void {
  }
  signup(userData:any){
    this.roleList=(userData.author && userData.reader) ? ["author","reader"]:userData.author?["author"]:userData.reader?["reader"]:[];
    console.log(userData);
    this.signupdata={
      name:userData.name,
      email:userData.email,
      username:userData.username,
      password:userData.password,
      aboutAuthor:userData.aboutAuthor,
      role: this.roleList
    }

    console.log(this.signupdata);
    const observable = this.auth.signup(this.signupdata);
    observable.subscribe(
      (response) => {console.log('hiii');
      },
      (error) => {
        this.error=error.error;
        console.log(error);
        }
    );
  }

}
