import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { JournalFormComponent } from './components/journal-form/journal-form.component';
import { HomeComponent } from './components/home/home.component';
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
import { RoleEditComponent } from './components/role-edit/role-edit.component';


const routes: Routes = [
  {path: "journalForm", component: JournalFormComponent},
  {path: "home", component: HomeComponent},
  {path: "journalList", component: JournalListComponent},
  {path:'edit/:id', component: JournalEditComponent},
  {path:'editRole/:roleName', component: RoleEditComponent},
  {path:'report', component: ReportFormComponent},
  {path:"cashAccount", component: CashAccountComponent},
  {path:"receivableAccount", component: ReceivableAccountComponent},
  {path:"equipmentAccount", component: EquipmentAccountComponent},
  {path:"suppliesAccount", component: SuppliesAccountComponent},
  {path:"expenseAccount", component: ExpenseAccountComponent},
  {path:"capitalAccount", component: CapitalAccountComponent},
  {path:"drawingsAccount", component: DrawingsAccountComponent},
  {path:"payableAccount", component: PayableAccountComponent},
  {path:'revenueAccount', component: RevenueAccountComponent},
  {path:'sign-up', component: SignUpComponent},
  {path:'create-role', component: CreateRoleComponent},
  {path:'sign-in', component: SignInComponent},
  {path:'userList', component: AllUsersComponent},
  {path:'roleList', component: AllRolesComponent},
  {path:'**', component: SignInComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
