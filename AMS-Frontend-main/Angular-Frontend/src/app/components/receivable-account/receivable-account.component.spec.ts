import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReceivableAccountComponent } from './receivable-account.component';

describe('ReceivableAccountComponent', () => {
  let component: ReceivableAccountComponent;
  let fixture: ComponentFixture<ReceivableAccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReceivableAccountComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReceivableAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
