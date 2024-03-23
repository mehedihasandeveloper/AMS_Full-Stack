import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { JournalFormComponent } from './components/journal-form/journal-form.component';
import { HomeComponent } from './components/home/home.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from  '@angular/common/http';
import { JournalListComponent } from './components/journal-list/journal-list.component';
import { JournalEditComponent } from './components/journal-edit/journal-edit.component';
import { ReportFormComponent } from './components/report-form/report-form.component';
import { CashAccountComponent } from './components/cash-account/cash-account.component';
import { ReceivableAccountComponent } from './components/receivable-account/receivable-account.component';
import { EquipmentAccountComponent } from './components/equipment-account/equipment-account.component';
import { SuppliesAccountComponent } from './components/supplies-account/supplies-account.component';
import { ExpenseAccountComponent } from './components/expense-account/expense-account.component';
import { CapitalAccountComponent } from './components/capital-account/capital-account.component';
import { DrawingsAccountComponent } from './components/drawings-account/drawings-account.component';
import { PayableAccountComponent } from './components/payable-account/payable-account.component';
import { RevenueAccountComponent } from './components/revenue-account/revenue-account.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { CreateRoleComponent } from './components/create-role/create-role.component';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { AllUsersComponent } from './components/all-users/all-users.component';
import { AllRolesComponent } from './components/all-roles/all-roles.component';
import { BarChartComponent } from './components/bar-chart/bar-chart.component';
import { LineChartComponent } from './components/line-chart/line-chart.component';
import { DoughnutComponent } from './components/doughnut/doughnut.component';
import { BanglaNumberPipe } from './bangla-number.pipe';
import { RoleEditComponent } from './components/role-edit/role-edit.component';


@NgModule({
  declarations: [
    AppComponent,
    JournalFormComponent,
    HomeComponent,
    JournalListComponent,
    JournalEditComponent,
    ReportFormComponent,
    CashAccountComponent,
    ReceivableAccountComponent,
    EquipmentAccountComponent,
    SuppliesAccountComponent,
    ExpenseAccountComponent,
    CapitalAccountComponent,
    DrawingsAccountComponent,
    PayableAccountComponent,
    RevenueAccountComponent,
    SignUpComponent,
    CreateRoleComponent,
    SignInComponent,
    AllUsersComponent,
    AllRolesComponent,
    BarChartComponent,
    LineChartComponent,
    DoughnutComponent,
    BanglaNumberPipe,
    RoleEditComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
