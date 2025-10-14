import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




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
  selector: 'app-sortie-stock-create-admin',
  templateUrl: './sortie-stock-create-admin.component.html'
})
export class SortieStockCreateAdminComponent  implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;
    protected sortieStockDetailsIndex = -1;

    private _sortieStockDetailsElement = new SortieStockDetailDto();


   private _validSortieStockCode = true;

	constructor(private service: SortieStockAdminService , private magasinService: MagasinAdminService, private sortieStockDetailService: SortieStockDetailAdminService, private produitService: ProduitAdminService, @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.sortieStockDetailsElement.produit = new ProduitDto();
        this.produitService.findAll().subscribe((data) => this.produits = data);
        this.sortieStockDetailsElement.magazin = new MagasinDto();
        this.magasinService.findAll().subscribe((data) => this.magazins = data);
    }



    public save(): void {
        this.submitted = true;
        this.validateForm();
        if (this.errorMessages.length === 0) {
            this.saveWithShowOption(false);
        } else {
            this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs sur le formulaire'});
        }
    }

    public saveWithShowOption(showList: boolean) {
        this.service.save().subscribe(item => {
            if (item != null) {
                this.items.push({...item});
                this.createDialog = false;
                this.submitted = false;
                this.item = new SortieStockDto();
            } else {
                this.messageService.add({severity: 'error', summary: 'Erreurs', detail: 'Element existant'});
            }

        }, error => {
            console.log(error);
        });
    }


    public hideCreateDialog() {
        this.createDialog = false;
        this.setValidation(true);
    }



    validateSortieStockDetails(){
        this.errorMessages = new Array();
    }


    public  setValidation(value: boolean){
        this.validSortieStockCode = value;
    }

    public addSortieStockDetails() {
        if( this.item.sortieStockDetails == null )
            this.item.sortieStockDetails = new Array<SortieStockDetailDto>();

       this.validateSortieStockDetails();

       if (this.errorMessages.length === 0) {
            if (this.sortieStockDetailsIndex == -1){
                this.item.sortieStockDetails.push({... this.sortieStockDetailsElement});
            }else {
                this.item.sortieStockDetails[this.sortieStockDetailsIndex] =this.sortieStockDetailsElement;
            }
              this.sortieStockDetailsElement = new SortieStockDetailDto();
              this.sortieStockDetailsIndex = -1;
       }else{
           this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
       }
    }

    public deleteSortieStockDetails(p: SortieStockDetailDto, index: number) {
        this.item.sortieStockDetails.splice(index, 1);
    }

    public editSortieStockDetails(p: SortieStockDetailDto, index: number) {
        this.sortieStockDetailsElement = {... p};
        this.sortieStockDetailsIndex = index;
        this.activeTab = 0;
    }


    public  validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateSortieStockCode();
    }

    public validateSortieStockCode(){
        if (this.stringUtilService.isEmpty(this.item.code)) {
        this.errorMessages.push('Code non valide');
        this.validSortieStockCode = false;
        } else {
            this.validSortieStockCode = true;
        }
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
    get createProduitDialog(): boolean {
        return this.produitService.createDialog;
    }
    set createProduitDialog(value: boolean) {
        this.produitService.createDialog= value;
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
    get createMagazinDialog(): boolean {
        return this.magasinService.createDialog;
    }
    set createMagazinDialog(value: boolean) {
        this.magasinService.createDialog= value;
    }



    get validSortieStockCode(): boolean {
        return this._validSortieStockCode;
    }

    set validSortieStockCode(value: boolean) {
         this._validSortieStockCode = value;
    }


    get sortieStockDetailsElement(): SortieStockDetailDto {
        if( this._sortieStockDetailsElement == null )
            this._sortieStockDetailsElement = new SortieStockDetailDto();
        return this._sortieStockDetailsElement;
    }

    set sortieStockDetailsElement(value: SortieStockDetailDto) {
        this._sortieStockDetailsElement = value;
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

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): SortieStockCriteria {
        return this.service.criteria;
    }

    set criteria(value: SortieStockCriteria) {
        this.service.criteria = value;
    }

    get dateFormat() {
        return environment.dateFormatCreate;
    }

    get dateFormatColumn() {
        return environment.dateFormatCreate;
    }

    get submitted(): boolean {
        return this._submitted;
    }

    set submitted(value: boolean) {
        this._submitted = value;
    }

    get errorMessages(): string[] {
        if (this._errorMessages == null) {
            this._errorMessages = new Array<string>();
        }
        return this._errorMessages;
    }

    set errorMessages(value: string[]) {
        this._errorMessages = value;
    }

    get validate(): boolean {
        return this.service.validate;
    }

    set validate(value: boolean) {
        this.service.validate = value;
    }


    get activeTab(): number {
        return this._activeTab;
    }

    set activeTab(value: number) {
        this._activeTab = value;
    }

}
