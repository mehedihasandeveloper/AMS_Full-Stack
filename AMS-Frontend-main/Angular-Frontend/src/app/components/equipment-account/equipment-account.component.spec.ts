import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EquipmentAccountComponent } from './equipment-account.component';

describe('EquipmentAccountComponent', () => {
  let component: EquipmentAccountComponent;
  let fixture: ComponentFixture<EquipmentAccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EquipmentAccountComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EquipmentAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
