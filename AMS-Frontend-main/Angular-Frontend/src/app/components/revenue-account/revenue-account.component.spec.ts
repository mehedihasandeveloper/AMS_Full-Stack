import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RevenueAccountComponent } from './revenue-account.component';

describe('RevenueAccountComponent', () => {
  let component: RevenueAccountComponent;
  let fixture: ComponentFixture<RevenueAccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RevenueAccountComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RevenueAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
