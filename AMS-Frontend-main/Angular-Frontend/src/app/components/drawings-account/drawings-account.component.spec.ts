import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DrawingsAccountComponent } from './drawings-account.component';

describe('DrawingsAccountComponent', () => {
  let component: DrawingsAccountComponent;
  let fixture: ComponentFixture<DrawingsAccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DrawingsAccountComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DrawingsAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
