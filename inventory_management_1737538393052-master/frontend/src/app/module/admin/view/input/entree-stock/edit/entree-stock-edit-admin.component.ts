import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';
import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';

import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




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
  selector: 'app-entree-stock-edit-admin',
  templateUrl: './entree-stock-edit-admin.component.html'
})
export class EntreeStockEditAdminComponent implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();


    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;
    private _file: any;
    private _files: any;

    protected entreeStockDetailsIndex = -1;

    private _entreeStockDetailsElement = new EntreeStockDetailDto();

    private _validEntreeStockCode = true;




    constructor(private service: EntreeStockAdminService , private fournisseurService: FournisseurAdminService, private magasinService: MagasinAdminService, private entreeStockDetailService: EntreeStockDetailAdminService, private produitService: ProduitAdminService, @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.entreeStockDetailsElement.produit = new ProduitDto();
        this.produitService.findAll().subscribe((data) => this.produits = data);
        this.entreeStockDetailsElement.magazin = new MagasinDto();
        this.magasinService.findAll().subscribe((data) => this.magazins = data);

        this.fournisseurService.findAll().subscribe((data) => this.fournisseurs = data);
    }

    public prepareEdit() {
        this.item.dateEntree = this.service.format(this.item.dateEntree);
    }



 public edit(): void {
        this.submitted = true;
        this.prepareEdit();
        this.validateForm();
        if (this.errorMessages.length === 0) {
            this.editWithShowOption(false);
        } else {
            this.messageService.add({
                severity: 'error',
                summary: 'Erreurs',
                detail: 'Merci de corrigé les erreurs sur le formulaire'
            });
        }
    }

    public editWithShowOption(showList: boolean) {
        this.service.edit().subscribe(religion=>{
            const myIndex = this.items.findIndex(e => e.id === this.item.id);
            this.items[myIndex] = religion;
            this.editDialog = false;
            this.submitted = false;
            this.item = new EntreeStockDto();
        } , error =>{
            console.log(error);
        });
    }

    public hideEditDialog() {
        this.editDialog = false;
        this.setValidation(true);
    }





    public validateEntreeStockDetails(){
        this.errorMessages = new Array();
    }

    public setValidation(value: boolean){
        this.validEntreeStockCode = value;
    }

    public addEntreeStockDetails() {
        if( this.item.entreeStockDetails == null )
            this.item.entreeStockDetails = new Array<EntreeStockDetailDto>();

       this.validateEntreeStockDetails();

       if (this.errorMessages.length === 0) {
            if (this.entreeStockDetailsIndex == -1){
                this.item.entreeStockDetails.push({... this.entreeStockDetailsElement});
            }else {
                this.item.entreeStockDetails[this.entreeStockDetailsIndex] =this.entreeStockDetailsElement;
            }
              this.entreeStockDetailsElement = new EntreeStockDetailDto();
              this.entreeStockDetailsIndex = -1;
       }else{
           this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
       }
    }

    public deleteEntreeStockDetails(p: EntreeStockDetailDto, index: number) {
        this.item.entreeStockDetails.splice(index, 1);
    }

    public editEntreeStockDetails(p: EntreeStockDetailDto, index: number) {
        this.entreeStockDetailsElement = {... p};
        this.entreeStockDetailsIndex = index;
        this.activeTab = 0;
    }

    public validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateEntreeStockCode();
    }

    public validateEntreeStockCode(){
        if (this.stringUtilService.isEmpty(this.item.code)) {
            this.errorMessages.push('Code non valide');
            this.validEntreeStockCode = false;
        } else {
            this.validEntreeStockCode = true;
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
    get createFournisseurDialog(): boolean {
        return this.fournisseurService.createDialog;
    }
    set createFournisseurDialog(value: boolean) {
        this.fournisseurService.createDialog= value;
    }

    get entreeStockDetailsElement(): EntreeStockDetailDto {
        if( this._entreeStockDetailsElement == null )
            this._entreeStockDetailsElement = new EntreeStockDetailDto();
         return this._entreeStockDetailsElement;
    }

    set entreeStockDetailsElement(value: EntreeStockDetailDto) {
        this._entreeStockDetailsElement = value;
    }

    get validEntreeStockCode(): boolean {
        return this._validEntreeStockCode;
    }
    set validEntreeStockCode(value: boolean) {
        this._validEntreeStockCode = value;
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

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
    }

    get criteria(): EntreeStockCriteria {
        return this.service.criteria;
    }

    set criteria(value: EntreeStockCriteria) {
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
