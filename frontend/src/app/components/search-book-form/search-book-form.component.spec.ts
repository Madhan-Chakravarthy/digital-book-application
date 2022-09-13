import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchBookFormComponent } from './search-book-form.component';

describe('SearchBookFormComponent', () => {
  let component: SearchBookFormComponent;
  let fixture: ComponentFixture<SearchBookFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchBookFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchBookFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
