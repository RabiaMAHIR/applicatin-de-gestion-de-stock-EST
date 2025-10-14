import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ToastModule} from 'primeng/toast';
import {ToolbarModule} from 'primeng/toolbar';
import {TableModule} from 'primeng/table';
import {DropdownModule} from 'primeng/dropdown';
import {InputSwitchModule} from 'primeng/inputswitch';
import {InputTextareaModule} from 'primeng/inputtextarea';
import {EditorModule} from "primeng/editor";

import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {DialogModule} from 'primeng/dialog';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {CalendarModule} from 'primeng/calendar';
import {PanelModule} from 'primeng/panel';
import {InputNumberModule} from 'primeng/inputnumber';
import {BadgeModule} from 'primeng/badge';
import {MultiSelectModule} from 'primeng/multiselect';
import {TranslateModule} from '@ngx-translate/core';
import {FileUploadModule} from 'primeng/fileupload';
import {FullCalendarModule} from '@fullcalendar/angular';
import {CardModule} from "primeng/card";
import {TagModule} from "primeng/tag";

import {EntreeStockCreateAdminComponent} from './entree-stock/create/entree-stock-create-admin.component';
import {EntreeStockEditAdminComponent} from './entree-stock/edit/entree-stock-edit-admin.component';
import {EntreeStockViewAdminComponent} from './entree-stock/view/entree-stock-view-admin.component';
import {EntreeStockListAdminComponent} from './entree-stock/list/entree-stock-list-admin.component';

import {PasswordModule} from 'primeng/password';
import {InputTextModule} from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {TabViewModule} from 'primeng/tabview';
import {SplitButtonModule} from 'primeng/splitbutton';
import {MessageModule} from 'primeng/message';
import {MessagesModule} from 'primeng/messages';
import {PaginatorModule} from 'primeng/paginator';

import {
    ValorisationStockAdminComponent
} from "./entree-stock/valorisation/valorisation-admin.component";

import {
    DashboardAdminComponent
} from "./entree-stock/dashboard/dashboard-admin.component";
import {ChartModule} from 'primeng/chart';


@NgModule({
    declarations: [

        EntreeStockCreateAdminComponent,
        EntreeStockListAdminComponent,
        EntreeStockViewAdminComponent,
        EntreeStockEditAdminComponent,
        ValorisationStockAdminComponent,
        DashboardAdminComponent,
    ],
    imports: [
        ChartModule,

        CommonModule,
        ToastModule,
        ToolbarModule,
        TableModule,
        ConfirmDialogModule,
        DialogModule,
        PasswordModule,
        InputTextModule,
        ButtonModule,
        FormsModule,
        ReactiveFormsModule,
        RouterModule,
        SplitButtonModule,
        BrowserAnimationsModule,
        DropdownModule,
        TabViewModule,
        InputSwitchModule,
        InputTextareaModule,
        CalendarModule,
        PanelModule,
        MessageModule,
        MessagesModule,
        InputNumberModule,
        BadgeModule,
        MultiSelectModule,
        PaginatorModule,
        TranslateModule,
        FileUploadModule,
        FullCalendarModule,
        CardModule,
        EditorModule,
        TagModule,

        ChartModule,

    ],
    exports: [
        EntreeStockCreateAdminComponent,
        EntreeStockListAdminComponent,
        EntreeStockViewAdminComponent,
        EntreeStockEditAdminComponent,
        ValorisationStockAdminComponent,
        DashboardAdminComponent,
    ],
})
export class InputAdminModule {
}
