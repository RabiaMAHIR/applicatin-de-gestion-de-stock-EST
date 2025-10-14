import {Component, OnInit} from '@angular/core';
import {EntreeStockAdminService} from 'src/app/shared/service/admin/input/EntreeStockAdmin.service';
import {EntreeStockDto} from 'src/app/shared/model/input/EntreeStock.model';
import {EntreeStockCriteria} from 'src/app/shared/criteria/input/EntreeStockCriteria.model';


import {ConfirmationService, MessageService,MenuItem} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';
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

import {AuthService} from 'src/app/zynerator/security/shared/service/Auth.service';
import {ExportService} from 'src/app/zynerator/util/Export.service';


import {FournisseurDto} from 'src/app/shared/model/agent/Fournisseur.model';
import {FournisseurAdminService} from 'src/app/shared/service/admin/agent/FournisseurAdmin.service';
import {MagasinDto} from 'src/app/shared/model/store/Magasin.model';
import {MagasinAdminService} from 'src/app/shared/service/admin/store/MagasinAdmin.service';
import {EntreeStockDetailDto} from 'src/app/shared/model/input/EntreeStockDetail.model';
import {EntreeStockDetailAdminService} from 'src/app/shared/service/admin/input/EntreeStockDetailAdmin.service';
import {ProduitDto} from 'src/app/shared/model/catalogue/Produit.model';
import {ProduitAdminService} from 'src/app/shared/service/admin/catalogue/ProduitAdmin.service';
import {SortieStockDetailDto} from "../../../../../../shared/model/output/SortieStockDetail.model";
import {SortieStockAdminService} from "../../../../../../shared/service/admin/output/SortieStockAdmin.service";
import {
    SortieStockDetailAdminService
} from "../../../../../../shared/service/admin/output/SortieStockDetailAdmin.service";
import { DecimalPipe } from '@angular/common';





/*export class ValorisationStockAdminComponent implements OnInit {
    entreeStockItems: EntreeStockDetailDto[] = [];
    sortieStockItems: SortieStockDetailDto[] = [];
    filteredEntreeStockItems: EntreeStockDetailDto[] = [];
    filteredSortieStockItems: SortieStockDetailDto[] = [];
    magasins: MagasinDto[] = [];
    selectedMagasin: MagasinDto | null = null;
    listActionIsValid: boolean = true;
    magasinSearch: string = '';

    constructor(
        private entreeStockDetailService: EntreeStockDetailAdminService,
        private sortieStockDetailService: SortieStockDetailAdminService,
        private magasinAdminService: MagasinAdminService
    ) {}

    ngOnInit(): void {
        this.loadEntreeStockDetails();
        this.loadSortieStockDetails();
        this.loadMagasins();
    }

    loadEntreeStockDetails() {
        this.entreeStockDetailService.findAll().subscribe(
            (data) => {
                this.entreeStockItems = data;
                this.filteredEntreeStockItems = data;
            },
            (error) => {
                console.error('Error loading entree stock details', error);
            }
        );
    }

    loadSortieStockDetails() {
        this.sortieStockDetailService.findAll().subscribe(
            (data) => {
                this.sortieStockItems = data;
                this.filteredSortieStockItems = data;
            },
            (error) => {
                console.error('Error loading sortie stock details', error);
            }
        );
    }

    loadMagasins() {
        this.magasinAdminService.findAll().subscribe(
            (data) => {
                this.magasins = data;
            },
            (error) => {
                console.error('Error loading magasins', error);
            }
        );
    }

    filterByMagasin() {
        const searchTerm = this.magasinSearch.toLowerCase();
        this.filteredEntreeStockItems = this.entreeStockItems.filter(item =>
            item.magazin?.label.toLowerCase().includes(searchTerm)
        );
        this.filteredSortieStockItems = this.sortieStockItems.filter(item =>
            item.magazin?.label.toLowerCase().includes(searchTerm)
        );
    }

    openCreate() {

    }

    isSelectionDisabled(): boolean {
        return false;
    }
}
*/



/*
@Component({
  selector: 'app-valorisation-stock-admin',
  templateUrl: './valorisation-admin.component.html'

})
export class ValorisationStockAdminComponent implements OnInit {

    protected fileName = 'EntreeStock';

    protected findByCriteriaShow = false;
    protected cols: any[] = [];
    protected excelPdfButons: MenuItem[];
    protected exportData: any[] = [];
    protected criteriaData: any[] = [];
    protected _totalRecords = 0;
    private _pdfName: string;


    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    protected authService: AuthService;
    protected exportService: ExportService;
    protected excelFile: File | undefined;
    protected enableSecurity = false;

    sortieStockDetails: Array<SortieStockDetailDto>;
    entreeStockDetails: Array<EntreeStockDetailDto>;
    fournisseurs: Array<FournisseurDto>;



    constructor( private service: EntreeStockAdminService  , private fournisseurService: FournisseurAdminService, private magasinService: MagasinAdminService, private entreeStockDetailService: EntreeStockDetailAdminService, private produitService: ProduitAdminService, @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.authService = ServiceLocator.injector.get(AuthService);
        this.exportService = ServiceLocator.injector.get(ExportService);
    }

    ngOnInit(): void {
        this.findPaginatedByCriteria();
        this.initExport();
        this.initCol();
        this.loadFournisseur();
        this.findall();
        this.loadsortieStockDetails();
       // this.loadentreeStockDetails();

    }

    public findall():void{
   this.service.findAll().subscribe(response => {this.items = response});
    }



    public onExcelFileSelected(event: any): void {
        const input = event.target as HTMLInputElement;
        if (input.files && input.files.length > 0) {
            this.excelFile = input.files[0];
        }
    }

    public importExcel(): void {
        if (this.excelFile) {
            this.service.importExcel(this.excelFile).subscribe(
                response => {
                    this.items = response;
                    this.messageService.add({
                        severity: 'success',
                        summary: 'Success',
                        detail: 'File uploaded successfully!',
                        life: 3000
                    });
                },
                error => {
                    this.messageService.add({
                        severity: 'error',
                        summary: 'Error',
                        detail: 'File uploaded with Error!',
                        life: 3000
                    });
                }
            );
        }
    }

    public findPaginatedByCriteria() {
        this.service.findPaginatedByCriteria(this.criteria).subscribe(paginatedItems => {
            this.items = paginatedItems.list;
            this.totalRecords = paginatedItems.dataSize;
            this.selections = new Array<EntreeStockDto>();
        }, error => console.log(error));
    }

    public onPage(event: any) {
        this.criteria.page = event.page;
        this.criteria.maxResults = event.rows;
        this.findPaginatedByCriteria();
    }

    public async edit(dto: EntreeStockDto) {
        this.service.findByIdWithAssociatedList(dto).subscribe(res => {
            this.item = res;
            console.log(res);
            this.editDialog = true;
        });

    }

    public async view(dto: EntreeStockDto) {
        this.service.findByIdWithAssociatedList(dto).subscribe(res => {
            this.item = res;
            this.viewDialog = true;
        });
    }

    public async openCreate() {
        this.item = new EntreeStockDto();
        this.createDialog = true;
    }

    public async deleteMultiple() {
        this.confirmationService.confirm({
            message: 'Voulez-vous supprimer ces éléments ?',
            header: 'Confirmation',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                this.service.deleteMultiple().subscribe(() => {
                    for (let selection of this.selections) {
                        let index = this.items.findIndex(element => element.id === selection.id);
                        this.items.splice(index,1);
                    }
                    this.selections = new Array<EntreeStockDto>();
                    this.messageService.add({
                        severity: 'success',
                        summary: 'Succès',
                        detail: 'Les éléments sélectionnés ont été supprimés',
                        life: 3000
                    });

                }, error => console.log(error));
            }
        });
    }


    public isSelectionDisabled(): boolean {
        return this.selections == null || this.selections.length == 0;
    }


    public async delete(dto: EntreeStockDto) {

        this.confirmationService.confirm({
            message: 'Voulez-vous supprimer cet élément ?',
            header: 'Confirmation',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                this.service.delete(dto).subscribe(status => {
                    if (status > 0) {
                        const position = this.items.indexOf(dto);
                        position > -1 ? this.items.splice(position, 1) : false;
                        this.messageService.add({
                            severity: 'success',
                            summary: 'Succès',
                            detail: 'Element Supprimé',
                            life: 3000
                        });
                    }

                }, error => console.log(error));
            }
        });

    }

    public async duplicate(dto: EntreeStockDto) {
        this.service.findByIdWithAssociatedList(dto).subscribe(
            res => {
                this.initDuplicate(res);
                this.item = res;
                this.item.id = null;
                this.createDialog = true;
            });
    }

    // TODO : check if correct
    public initExport(): void {
        this.excelPdfButons = [
            {
                label: 'CSV', icon: 'pi pi-file', command: () => {
                    this.prepareColumnExport();
                    this.exportService.exporterCSV(this.criteriaData, this.exportData, this.fileName);
                }
            },
            {
                label: 'XLS', icon: 'pi pi-file-excel', command: () => {
                    this.prepareColumnExport();
                    this.exportService.exporterExcel(this.criteriaData, this.exportData, this.fileName);
                }
            },
            {
                label: 'PDF', icon: 'pi pi-file-pdf', command: () => {
                    this.prepareColumnExport();
                    this.exportService.exporterPdf(this.criteriaData, this.exportData, this.fileName);
                }
            }
        ];
    }

    public exportPdf(dto: EntreeStockDto): void {
        this.service.exportPdf(dto).subscribe((data: ArrayBuffer) => {
            const blob = new Blob([data], {type: 'application/pdf'});
            const url = window.URL.createObjectURL(blob);
            const link = document.createElement('a');
            link.href = url;
            link.download = this.pdfName;
            link.setAttribute('target', '_blank'); // open link in new tab
            link.click();
            window.URL.revokeObjectURL(url);
        }, (error) => {
            console.error(error); // handle any errors that occur
        });
    }

    public showSearch(): void {
        this.findByCriteriaShow = !this.findByCriteriaShow;
    }


    update() {
        this.service.edit().subscribe(data => {
            const myIndex = this.items.findIndex(e => e.id === this.item.id);
            this.items[myIndex] = data;
            this.editDialog = false;
            this.item = new EntreeStockDto();
        } , error => {
            console.log(error);
        });
    }

    public save() {
        this.service.save().subscribe(item => {
            if (item != null) {
                this.items.push({...item});
                this.createDialog = false;


                this.item = new EntreeStockDto();
            } else {
                this.messageService.add({severity: 'error', summary: 'Erreurs', detail: 'Element existant'});
            }
        }, error => {
            console.log(error);
        });
    }

// add


    public initCol() {
        this.cols = [
            {field: 'code', header: 'Code'},
            {field: 'dateEntree', header: 'Date entree'},
            {field: 'fournisseur?.label', header: 'Fournisseur'},
        ];
    }


    public async loadFournisseur(){
        this.fournisseurService.findAllOptimized().subscribe(fournisseurs => this.fournisseurs = fournisseurs, error => console.log(error))
    }
    public async loadsortieStockDetails(){
        this.fournisseurService.findAllOptimized().subscribe(fournisseurs => this.fournisseurs = fournisseurs, error => console.log(error))
    }


	public initDuplicate(res: EntreeStockDto) {
        if (res.entreeStockDetails != null) {
             res.entreeStockDetails.forEach(d => { d.entreeStock = null; d.id = null; });
        }
	}


    public prepareColumnExport(): void {
        this.service.findByCriteria(this.criteria).subscribe(
            (allItems) =>{
                this.exportData = allItems.map(e => {
					return {
						'Code': e.code ,
						'Date entree': this.datePipe.transform(e.dateEntree , 'dd/MM/yyyy hh:mm'),
						'Description': e.description ,
						'Fournisseur': e.fournisseur?.label ,
					}
				});

            this.criteriaData = [{
                'Code': this.criteria.code ? this.criteria.code : environment.emptyForExport ,
                'Date entree Min': this.criteria.dateEntreeFrom ? this.datePipe.transform(this.criteria.dateEntreeFrom , this.dateFormat) : environment.emptyForExport ,
                'Date entree Max': this.criteria.dateEntreeTo ? this.datePipe.transform(this.criteria.dateEntreeTo , this.dateFormat) : environment.emptyForExport ,
                'Description': this.criteria.description ? this.criteria.description : environment.emptyForExport ,
            //'Fournisseur': this.criteria.fournisseur?.label ? this.criteria.fournisseur?.label : environment.emptyForExport ,
            }];
			}

        )
    }


    get items(): Array<EntreeStockDto> {
        return this.service.items;
    }

    set items(value: Array<EntreeStockDto>) {
        this.service.items = value;
    }

    get selections(): Array<EntreeStockDto> {
        return this.service.selections;
    }

    set selections(value: Array<EntreeStockDto>) {
        this.service.selections = value;
    }

    get item(): EntreeStockDto {
        return this.service.item;
    }

    set item(value: EntreeStockDto) {
        this.service.item = value;
    }

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
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

    get dateFormat() {
        return environment.dateFormatList;
    }


    get totalRecords(): number {
        return this._totalRecords;
    }

    set totalRecords(value: number) {
        this._totalRecords = value;
    }

    get pdfName(): string {
        return this._pdfName;
    }

    set pdfName(value: string) {
        this._pdfName = value;
    }

    get createActionIsValid(): boolean {
        return this.service.createActionIsValid;
    }

    set createActionIsValid(value: boolean) {
        this.service.createActionIsValid = value;
    }


    get editActionIsValid(): boolean {
        return this.service.editActionIsValid;
    }

    set editActionIsValid(value: boolean) {
        this.service.editActionIsValid = value;
    }

    get listActionIsValid(): boolean {
        return this.service.listActionIsValid;
    }

    set listActionIsValid(value: boolean) {
        this.service.listActionIsValid = value;
    }

    get deleteActionIsValid(): boolean {
        return this.service.deleteActionIsValid;
    }

    set deleteActionIsValid(value: boolean) {
        this.service.deleteActionIsValid = value;
    }


    get viewActionIsValid(): boolean {
        return this.service.viewActionIsValid;
    }

    set viewActionIsValid(value: boolean) {
        this.service.viewActionIsValid = value;
    }

    get duplicateActionIsValid(): boolean {
        return this.service.duplicateActionIsValid;
    }

    set duplicateActionIsValid(value: boolean) {
        this.service.duplicateActionIsValid = value;
    }

    get createAction(): string {
        return this.service.createAction;
    }

    set createAction(value: string) {
        this.service.createAction = value;
    }

    get listAction(): string {
        return this.service.listAction;
    }

    set listAction(value: string) {
        this.service.listAction = value;
    }

    get editAction(): string {
        return this.service.editAction;
    }

    set editAction(value: string) {
        this.service.editAction = value;
    }

    get deleteAction(): string {
        return this.service.deleteAction;
    }

    set deleteAction(value: string) {
        this.service.deleteAction = value;
    }

    get viewAction(): string {
        return this.service.viewAction;
    }

    set viewAction(value: string) {
        this.service.viewAction = value;
    }

    get duplicateAction(): string {
        return this.service.duplicateAction;
    }

    set duplicateAction(value: string) {
        this.service.duplicateAction = value;
    }

    get entityName(): string {
        return this.service.entityName;
    }

    set entityName(value: string) {
        this.service.entityName = value;
    }

    deleteSortie(item: any) {

    }

}
*/
/*
@Component({
    selector: 'app-valorisation-stock-admin',
    templateUrl: './valorisation-admin.component.html'
})

export class ValorisationStockAdminComponent implements OnInit {
    entreeStockItems: EntreeStockDetailDto[] = [];
    sortieStockItems: SortieStockDetailDto[] = [];
    filteredEntreeStockItems: EntreeStockDetailDto[] = [];
    filteredSortieStockItems: SortieStockDetailDto[] = [];
    magasins: MagasinDto[] = [];
    selectedMagasin: MagasinDto | null = null;
    selectedProduit: ProduitDto | null = null;
    calculationMethods: { label: string, value: string }[] = [
        { label: 'FIFO', value: 'FIFO' },
        { label: 'LIFO', value: 'LIFO' },
        { label: 'CUMP', value: 'CUMP' }
    ];
    selectedCalculationMethod: string | null = null;
    listActionIsValid: boolean = true;
    magasinSearch: string = '';

    constructor(
        private entreeStockDetailService: EntreeStockDetailAdminService,
        private sortieStockDetailService: SortieStockDetailAdminService,
        private magasinAdminService: MagasinAdminService
    ) {}

    ngOnInit(): void {
        this.loadEntreeStockDetails();
        this.loadSortieStockDetails();
        this.loadMagasins();
    }

    loadEntreeStockDetails() {
        this.entreeStockDetailService.findAll().subscribe(
            (data) => {
                this.entreeStockItems = data;
                this.filteredEntreeStockItems = data;
            },
            (error) => {
                console.error('Error loading entree stock details', error);
            }
        );
    }

    loadSortieStockDetails() {
        this.sortieStockDetailService.findAll().subscribe(
            (data) => {
                this.sortieStockItems = data;
                this.filteredSortieStockItems = data;
            },
            (error) => {
                console.error('Error loading sortie stock details', error);
            }
        );
    }

    loadMagasins() {
        this.magasinAdminService.findAll().subscribe(
            (data) => {
                this.magasins = data;            },
            (error) => {
                console.error('Error loading magasins', error);
            }
        );
    }

    filterByMagasin() {
        if (this.selectedMagasin) {

            this.filteredEntreeStockItems = this.entreeStockItems.filter(item =>
                item.magazin?.id === this.selectedMagasin.id
            );
            this.filteredSortieStockItems = this.sortieStockItems.filter(item =>
                item.magazin?.id === this.selectedMagasin.id
            );
        } else {
            this.filteredEntreeStockItems = this.entreeStockItems;
            this.filteredSortieStockItems = this.sortieStockItems;
        }
    }
    filterByProduit() {
        if (this.selectedProduit) {

            this.filteredEntreeStockItems = this.entreeStockItems.filter(item =>
                item.magazin?.id === this.selectedProduit.id
            );
            this.filteredSortieStockItems = this.sortieStockItems.filter(item =>
                item.magazin?.id === this.selectedProduit.id
            );
        } else {
            this.filteredEntreeStockItems = this.entreeStockItems;
            this.filteredSortieStockItems = this.sortieStockItems;
        }
    }

    confirmAction() {

        console.log('Selected Magasin:', this.selectedMagasin);
        console.log('Selected Calculation Method:', this.selectedCalculationMethod);
    }

    openCreate() {

    }

    isSelectionDisabled(): boolean {
        return false;
    }
}
*/
@Component({
    selector: 'app-valorisation-stock-admin',
    templateUrl: './valorisation-admin.component.html',
    providers: [DecimalPipe]
})


export class ValorisationStockAdminComponent implements OnInit {
    entreeStockItems: EntreeStockDetailDto[] = [];
    sortieStockItems: SortieStockDetailDto[] = [];
    filteredEntreeStockItems: EntreeStockDetailDto[] = [];
    filteredSortieStockItems: SortieStockDetailDto[] = [];
    magasins: MagasinDto[] = [];
    produits: ProduitDto[] = [];
    selectedMagasin: MagasinDto | null = null;
    selectedProduit: ProduitDto | null = null;
    calculationMethods: { label: string, value: string }[] = [
        { label: 'FIFO', value: 'FIFO' },
        { label: 'LIFO', value: 'LIFO' },
        { label: 'CUMP', value: 'CUMP' }
    ];
    selectedCalculationMethod: string | null = null;
    listActionIsValid: boolean = true;
    magasinSearch: string = '';
    entreeStock: {
        dateEntree: Date;
    };
    totalCost: number;

    constructor(
        private entreeStockDetailService: EntreeStockDetailAdminService,
        private sortieStockDetailService: SortieStockDetailAdminService,
        private magasinAdminService: MagasinAdminService,
        private produitAdminService: ProduitAdminService,
    private decimalPipe: DecimalPipe
) {}

    ngOnInit(): void {
        this.loadMagasins();
        this.loadProduits();
        this.calculateCUMP();
        this.calculateFIFO();
        this.calculateLIFO();
    }

    loadMagasins() {
        this.magasinAdminService.findAll().subscribe(
            (data) => {
                this.magasins = data;
            },
            (error) => {
                console.error('Error loading magasins', error);
            }
        );
    }

    loadProduits() {
        this.produitAdminService.findAll().subscribe(
            (data) => {
                this.produits = data;
            },
            (error) => {
                console.error('Error loading produits', error);
            }
        );
    }

    onSelectionChange() {
        this.loadEntreeStockDetails();
        this.loadSortieStockDetails();
    }

    loadEntreeStockDetails() {
        if (this.selectedMagasin && this.selectedProduit) {
            this.entreeStockDetailService.findByMagazinIdAndProduitId(this.selectedMagasin.id, this.selectedProduit.id).subscribe(
                (data) => {
                    console.log('Data received:', data);
                    this.filteredEntreeStockItems = data;

                },
                (error) => {
                    console.error('Error loading entree stock details', error);
                }
            );
        } else {
            this.filteredEntreeStockItems = [];
        }
    }

    loadSortieStockDetails() {
        if (this.selectedMagasin && this.selectedProduit) {
            this.sortieStockDetailService.findByMagazinIdAndProduitId(this.selectedMagasin.id, this.selectedProduit.id).subscribe(
                (data) => {
                    this.filteredSortieStockItems = data;
                },
                (error) => {
                    console.error('Error loading sortie stock details', error);
                }
            );
        } else {
            this.filteredSortieStockItems = [];
        }
    }

    confirmAction() {
        console.log('Selected Magasin:', this.selectedMagasin);
        console.log('Selected Calculation Method:', this.selectedCalculationMethod);

        if (this.selectedCalculationMethod === 'FIFO') {
            this.updateSortieStockPricesFIFO();
        } else if (this.selectedCalculationMethod === 'CUMP') {
            this.updateSortieStockPrices();
        } else if (this.selectedCalculationMethod === 'LIFO') {
            this.updateSortieStockPricesLIFO();
        } else {
            this.loadEntreeStockDetails();
            this.loadSortieStockDetails();
        }
    }
    calculateCUMP(): number {
        if (this.filteredEntreeStockItems.length === 0) {
            return 0;
        }

        let totalCost = 0;
        let totalQuantity = 0;

        this.filteredEntreeStockItems.forEach(item => {
            totalCost += item.prix * item.quantite;
            totalQuantity += item.quantite;
        });
        return totalQuantity > 0 ? totalCost / totalQuantity : 0;
    }


    updateSortieStockPrices() {
        const cump = this.calculateCUMP();
        console.log('Calculated CUMP:', cump);

        this.filteredSortieStockItems.forEach(item => {
            item.prix =  parseFloat(this.decimalPipe.transform(cump, '1.3-3'));
        });
    }

    updateSortieStockPricesFIFO() {
        this.calculateFIFO();
    }


    calculateFIFO(): void {
        if (this.filteredEntreeStockItems.length === 0 || this.filteredSortieStockItems.length === 0) {
            return;
        }

        // Sort entree stock items by date of entry
        const sortedItems = this.filteredEntreeStockItems.sort((a, b) => {
            return new Date(a.entreeStock.dateEntree).getTime() - new Date(b.entreeStock.dateEntree).getTime();
        });

        this.filteredSortieStockItems.forEach(item => {
            let remainingQuantity = item.quantite; // Quantity to fulfill for this sortie stock item
            let totalCost = 0; // Total cost for this sortie stock item

            for (const stockItem of sortedItems) {
                if (remainingQuantity <= 0) break; // If no more quantity is needed, exit the loop

                const quantityToUse = Math.min(remainingQuantity, stockItem.quantite); // Determine how much to use from this stock item
                totalCost += stockItem.prix * quantityToUse; // Calculate the cost for the quantity used
                remainingQuantity -= quantityToUse; // Reduce the remaining quantity needed
                stockItem.quantite -= quantityToUse; // Reduce the quantity of the stock item
            }

            // Calculate the average price for the sortie stock item
            item.prix = totalCost / item.quantite; // Average price based on total cost and quantity
            item.totalCost = totalCost; // Store the total cost for this sortie stock item
        });
    }
    calculateLIFO(): void {
        if (this.filteredEntreeStockItems.length === 0 || this.filteredSortieStockItems.length === 0) {
            return;
        }

        const sortedItems = this.filteredEntreeStockItems.slice().reverse();

        this.filteredSortieStockItems.forEach(item => {
            let remainingQuantity = item.quantite;
            let totalCost = 0;

            for (const stockItem of sortedItems) {
                if (remainingQuantity <= 0) break;

                const quantityToUse = Math.min(remainingQuantity, stockItem.quantite);
                totalCost += stockItem.prix * quantityToUse;
                remainingQuantity -= quantityToUse;
            }

            item.prix = totalCost / item.quantite;
            item.prix = parseFloat(this.decimalPipe.transform(item.prix, '1.3-3'));
        });
    }
    updateSortieStockPricesLIFO() {
        this.calculateLIFO();
    }

    openCreate() {

    }

    isSelectionDisabled(): boolean {
        return false;
    }

}
