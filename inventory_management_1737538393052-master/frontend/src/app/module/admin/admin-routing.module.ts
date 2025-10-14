import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import {AuthGuard} from 'src/app/zynerator/security/guards/auth.guard';

import { ActivationAdminComponent } from './activation-admin/activation-admin.component';
import { LoginAdminComponent } from './login-admin/login-admin.component';
import { RegisterAdminComponent } from './register-admin/register-admin.component';
import { ForgetPasswordAdminComponent } from './forget-password-admin/forget-password-admin.component';
import { ChangePasswordAdminComponent } from './change-password-admin/change-password-admin.component';

@NgModule({
    imports: [
        RouterModule.forChild(
            [
                {
                    path: '',
                    children: [
                        {
                            path: 'login',
                            children: [
                                {
                                    path: '',
                                    component: LoginAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'register',
                            children: [
                                {
                                    path: '',
                                    component: RegisterAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },{
                            path: 'forget-password',
                            children: [
                                {
                                    path: '',
                                    component: ForgetPasswordAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },{
                            path: 'change-password',
                            children: [
                                {
                                    path: '',
                                    component: ChangePasswordAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'activation',
                            children: [
                                {
                                    path: '',
                                    component: ActivationAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'output',
                            loadChildren: () => import('./view/output/output-admin-routing.module').then(x => x.OutputAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'input',
                            loadChildren: () => import('./view/input/input-admin-routing.module').then(x => x.InputAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'agent',
                            loadChildren: () => import('./view/agent/agent-admin-routing.module').then(x => x.AgentAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'store',
                            loadChildren: () => import('./view/store/store-admin-routing.module').then(x => x.StoreAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'catalogue',
                            loadChildren: () => import('./view/catalogue/catalogue-admin-routing.module').then(x => x.CatalogueAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'security',
                            loadChildren: () => import('../security/security-routing.module').then(x => x.SecurityRoutingModule),
                            canActivate: [AuthGuard],
                        }
                    ]
                },
            ]
        ),
    ],
    exports: [RouterModule],
})
export class AdminRoutingModule { }
