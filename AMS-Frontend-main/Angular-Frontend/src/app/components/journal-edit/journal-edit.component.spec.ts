import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JournalEditComponent } from './journal-edit.component';

describe('JournalEditComponent', () => {
  let component: JournalEditComponent;
  let fixture: ComponentFixture<JournalEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JournalEditComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(JournalEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
