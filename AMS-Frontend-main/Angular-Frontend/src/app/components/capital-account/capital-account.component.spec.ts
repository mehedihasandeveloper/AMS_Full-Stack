import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapitalAccountComponent } from './capital-account.component';

describe('CapitalAccountComponent', () => {
  let component: CapitalAccountComponent;
  let fixture: ComponentFixture<CapitalAccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CapitalAccountComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CapitalAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
