import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GanttUiComponent } from './gantt-ui.component';

describe('GanttUiComponent', () => {
  let component: GanttUiComponent;
  let fixture: ComponentFixture<GanttUiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GanttUiComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GanttUiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
