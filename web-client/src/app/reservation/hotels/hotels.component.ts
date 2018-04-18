import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-hotels',
  templateUrl: './hotels.component.html',
  styleUrls: ['./hotels.component.css']
})
export class HotelsComponent implements OnInit {
  roomMode: boolean = false;
  selected: boolean = false;

  constructor(private router: Router) { }

  selectRoom(){
    this.selected = false;
    this.router.navigate(['/book/data']);
  };
  ngOnInit() {
  }

}
