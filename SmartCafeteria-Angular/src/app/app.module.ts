import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { BoardUserComponent } from './board-user/board-user.component';
//import { BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import { ChartComponent } from './chart/chart.component';
import { Chart2Component } from './chart2/chart2.component';
import {MatTabsModule} from '@angular/material/tabs';
import {MatToolbarModule} from'@angular/material/toolbar';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatInputModule} from '@angular/material/input'
import {MatNativeDateModule} from '@angular/material/core';
import { HistoricalComponent } from './historical/historical.component'

import { Historical2Component } from './historical2/historical2.component';

import { authInterceptorProviders } from './_helpers/auth.interceptor';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatSidenavModule } from '@angular/material/sidenav';
import { DrawerRailModule } from 'angular-material-rail-drawer';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ChartComponent,
    Chart2Component,
    HistoricalComponent,
    ProfileComponent,
    BoardAdminComponent,
    
    BoardUserComponent,
    Historical2Component
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
      MatDatepickerModule, MatInputModule,MatNativeDateModule,
     MatCardModule,
     MatButtonModule,
     ReactiveFormsModule,
      MatTabsModule,
      MatToolbarModule,
      MatIconModule,
    MatListModule,
    MatSidenavModule,
    DrawerRailModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
