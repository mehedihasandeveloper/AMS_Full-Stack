import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PayableAccountComponent } from './payable-account.component';

describe('PayableAccountComponent', () => {
  let component: PayableAccountComponent;
  let fixture: ComponentFixture<PayableAccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PayableAccountComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PayableAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
