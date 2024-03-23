import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuppliesAccountComponent } from './supplies-account.component';

describe('SuppliesAccountComponent', () => {
  let component: SuppliesAccountComponent;
  let fixture: ComponentFixture<SuppliesAccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SuppliesAccountComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SuppliesAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
