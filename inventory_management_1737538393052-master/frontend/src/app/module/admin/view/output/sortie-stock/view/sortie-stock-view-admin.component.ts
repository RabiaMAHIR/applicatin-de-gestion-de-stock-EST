import {Component, OnInit} from '@angular/core';


import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {AbstractService} from 'src/app/zynerator/service/AbstractService';
import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';
import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';
import {ConfirmationService, MessageService,MenuItem} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';


import {SortieStockAdminService} from 'src/app/shared/service/admin/output/SortieStockAdmin.service';
import {SortieStockDto} from 'src/app/shared/model/output/SortieStock.model';
import {SortieStockCriteria} from 'src/app/shared/criteria/output/SortieStockCriteria.model';

import {MagasinDto} from 'src/app/shared/model/store/Magasin.model';
import {MagasinAdminService} from 'src/app/shared/service/admin/store/MagasinAdmin.service';
import {SortieStockDetailDto} from 'src/app/shared/model/output/SortieStockDetail.model';
import {SortieStockDetailAdminService} from 'src/app/shared/service/admin/output/SortieStockDetailAdmin.service';
import {ProduitDto} from 'src/app/shared/model/catalogue/Produit.model';
import {ProduitAdminService} from 'src/app/shared/service/admin/catalogue/ProduitAdmin.service';
@Component({
  selector: 'app-sortie-stock-view-admin',
  templateUrl: './sortie-stock-view-admin.component.html'
})
export class SortieStockViewAdminComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;


    sortieStockDetails = new SortieStockDetailDto();
    sortieStockDetailss: Array<SortieStockDetailDto> = [];

    constructor(private service: SortieStockAdminService, private magasinService: MagasinAdminService, private sortieStockDetailService: SortieStockDetailAdminService, private produitService: ProduitAdminService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
    }


    get produit(): ProduitDto {
        return this.produitService.item;
    }
    set produit(value: ProduitDto) {
        this.produitService.item = value;
    }
    get produits(): Array<ProduitDto> {
        return this.produitService.items;
    }
    set produits(value: Array<ProduitDto>) {
        this.produitService.items = value;
    }
    get magazin(): MagasinDto {
        return this.magasinService.item;
    }
    set magazin(value: MagasinDto) {
        this.magasinService.item = value;
    }
    get magazins(): Array<MagasinDto> {
        return this.magasinService.items;
    }
    set magazins(value: Array<MagasinDto>) {
        this.magasinService.items = value;
    }

    public hideViewDialog() {
        this.viewDialog = false;
    }

    get items(): Array<SortieStockDto> {
        return this.service.items;
    }

    set items(value: Array<SortieStockDto>) {
        this.service.items = value;
    }

    get item(): SortieStockDto {
        return this.service.item;
    }

    set item(value: SortieStockDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): SortieStockCriteria {
        return this.service.criteria;
    }

    set criteria(value: SortieStockCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
