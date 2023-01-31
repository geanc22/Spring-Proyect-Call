import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RadioGroupPrecioComponent } from './radio-group-precio.component';

describe('RadioGroupPrecioComponent', () => {
  let component: RadioGroupPrecioComponent;
  let fixture: ComponentFixture<RadioGroupPrecioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RadioGroupPrecioComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RadioGroupPrecioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
