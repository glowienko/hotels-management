import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { ReservationComponent } from './reservation/reservation.component';
import { ManagementComponent } from './management/management.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { HeaderComponent } from './header/header.component';
import { HotelsComponent } from './reservation/hotels/hotels.component';
import { PersonalDataComponent } from './reservation/personal-data/personal-data.component';
import { SummaryComponent } from './reservation/summary/summary.component';
import {AppRoutingModule} from "./app-routing.module";
import {FormsModule} from "@angular/forms";
import { ContactComponent } from './contact/contact.component';
import {DataTablesModule} from 'angular-datatables'
@NgModule({
  declarations: [
    AppComponent,
    ReservationComponent,
    ManagementComponent,
    WelcomeComponent,
    HeaderComponent,
    HotelsComponent,
    PersonalDataComponent,
    SummaryComponent,
    ContactComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    DataTablesModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
