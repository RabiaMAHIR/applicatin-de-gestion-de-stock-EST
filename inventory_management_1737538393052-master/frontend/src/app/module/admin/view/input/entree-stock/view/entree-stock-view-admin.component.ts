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


import {EntreeStockAdminService} from 'src/app/shared/service/admin/input/EntreeStockAdmin.service';
import {EntreeStockDto} from 'src/app/shared/model/input/EntreeStock.model';
import {EntreeStockCriteria} from 'src/app/shared/criteria/input/EntreeStockCriteria.model';

import {FournisseurDto} from 'src/app/shared/model/agent/Fournisseur.model';
import {FournisseurAdminService} from 'src/app/shared/service/admin/agent/FournisseurAdmin.service';
import {MagasinDto} from 'src/app/shared/model/store/Magasin.model';
import {MagasinAdminService} from 'src/app/shared/service/admin/store/MagasinAdmin.service';
import {EntreeStockDetailDto} from 'src/app/shared/model/input/EntreeStockDetail.model';
import {EntreeStockDetailAdminService} from 'src/app/shared/service/admin/input/EntreeStockDetailAdmin.service';
import {ProduitDto} from 'src/app/shared/model/catalogue/Produit.model';
import {ProduitAdminService} from 'src/app/shared/service/admin/catalogue/ProduitAdmin.service';
@Component({
  selector: 'app-entree-stock-view-admin',
  templateUrl: './entree-stock-view-admin.component.html'
})
export class EntreeStockViewAdminComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;


    entreeStockDetails = new EntreeStockDetailDto();
    entreeStockDetailss: Array<EntreeStockDetailDto> = [];

    constructor(private service: EntreeStockAdminService, private fournisseurService: FournisseurAdminService, private magasinService: MagasinAdminService, private entreeStockDetailService: EntreeStockDetailAdminService, private produitService: ProduitAdminService){
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
    get fournisseur(): FournisseurDto {
        return this.fournisseurService.item;
    }
    set fournisseur(value: FournisseurDto) {
        this.fournisseurService.item = value;
    }
    get fournisseurs(): Array<FournisseurDto> {
        return this.fournisseurService.items;
    }
    set fournisseurs(value: Array<FournisseurDto>) {
        this.fournisseurService.items = value;
    }

    public hideViewDialog() {
        this.viewDialog = false;
    }

    get items(): Array<EntreeStockDto> {
        return this.service.items;
    }

    set items(value: Array<EntreeStockDto>) {
        this.service.items = value;
    }

    get item(): EntreeStockDto {
        return this.service.item;
    }

    set item(value: EntreeStockDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): EntreeStockCriteria {
        return this.service.criteria;
    }

    set criteria(value: EntreeStockCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
