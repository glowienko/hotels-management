import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {ReservationComponent} from "./reservation/reservation.component";
import {HotelsComponent} from "./reservation/hotels/hotels.component";
import {PersonalDataComponent} from "./reservation/personal-data/personal-data.component";
import {SummaryComponent} from "./reservation/summary/summary.component";
import {ManagementComponent} from "./management/management.component";
import {ContactComponent} from "./contact/contact.component";

const appRoutes: Routes = [
  {path: '', redirectTo:'/book', pathMatch: 'full'},
  {path: 'book', component: ReservationComponent, children:[
      {path: '', component: HotelsComponent},
      {path: 'summary', component: SummaryComponent},
      {path: 'hotels', component: HotelsComponent},
      {path: 'data', component: PersonalDataComponent},

    ]},
  {path: 'manage', component: ManagementComponent},
  {path: 'contact', component: ContactComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule{

}
