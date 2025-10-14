import { Component, OnInit } from '@angular/core';
import { MagasinDto } from 'src/app/shared/model/store/Magasin.model';
import { StockDto } from 'src/app/shared/model/store/Stock.model';
import { FournisseurAdminService } from 'src/app/shared/service/admin/agent/FournisseurAdmin.service';
import { EntreeStockAdminService } from 'src/app/shared/service/admin/input/EntreeStockAdmin.service';
import { SortieStockAdminService } from 'src/app/shared/service/admin/output/SortieStockAdmin.service';
import { MagasinAdminService } from 'src/app/shared/service/admin/store/MagasinAdmin.service';
import { StockAdminService } from 'src/app/shared/service/admin/store/StockAdmin.service';

@Component({
    selector: 'app-dashboard-admin',
    templateUrl: './dashboard-admin.component.html',
    styleUrls: ['./dashboard-admin.css']

})
export class DashboardAdminComponent implements OnInit {
    data: any;
    options: any;

    data1: any;
    options1: any;

    data2: any;
    options2: any;

    totalEntree: number = 0;
    totalSortie: number = 0;

    totalFournisseurs: number = 0;
    totalMagasin: number = 0;

    months: number[] = Array.from({ length: 12 }, (_, i) => i + 1);
    year: number = new Date().getFullYear();
    date: Date | null = null;


    entreeMonthlyData: number[] = [];
    sortieMonthlyData: number[] = [];
    entreeQuantityMonthlyData: number[] = [];
    sortieQuantityMonthlyData: number[] = [];

    magasins: MagasinDto[] = [];
    selectedMagasin: MagasinDto | null = null;
    monthlyData: number[] = [];
    displayChange: number | null = null;
    sortiedisplayChange: number | null = null;



    constructor(private fournisseurAdminService: FournisseurAdminService , private entreeStockService: EntreeStockAdminService,private magasinAdminService: MagasinAdminService, private sortieStockService: SortieStockAdminService , private QuintityAndProduitBymagazin: StockAdminService) {}


    ngOnInit(): void {
        this.loadMonthlyData();
        this.loadTotalQuantitiesForAllProduits();
        this.loadMagasins();
        this.updateChartData1();
        this.loadTotalFournisseurs();
        this.loadTotalMagasin();
        this.loadChangeEntreeTotalForYear(this.year);
        this.loadChangeSortieTotalForYear(this.year);


        const sortieRequests = this.months.map(month =>
            this.sortieStockService.getTotalSortForYearAndMonth(this.year, month).toPromise()
        );
        const entreeRequests = this.months.map(month =>
            this.entreeStockService.getTotalForYearAndMonth(this.year, month).toPromise()
        );
        const entreeQuintityRequests = this.months.map(month =>
            this.entreeStockService.calculateQuantityByYearAndMonth(this.year, month).toPromise()
        );
        const sortieQuintityRequests = this.months.map(month =>
            this.sortieStockService.calculateSortQuantityByYearAndMonth(this.year, month).toPromise()
        );





        Promise.all(entreeRequests).then(entreeResults => {
            this.entreeMonthlyData = entreeResults;
            this.totalEntree = this.entreeMonthlyData.reduce((acc, val) => acc + (val || 0), 0);
            this.updateChartData();
            this.loadChangeEntreeTotalForYear(this.year);
            this.loadChangeSortieTotalForYear(this.year);
            return Promise.all(sortieRequests);
        }).then(sortieResults => {
            this.sortieMonthlyData = sortieResults;
            this.totalSortie = this.sortieMonthlyData.reduce((acc, val) => acc + (val || 0), 0);
            this.loadChangeEntreeTotalForYear(this.year);
            this.loadChangeSortieTotalForYear(this.year);

            this.updateChartData();
            return Promise.all(entreeQuintityRequests);
        }).then(EntreeQuintityResults=>{
            this.entreeQuantityMonthlyData = EntreeQuintityResults;
            this.updateChartData();
            this.updateChartData1();
            return Promise.all(sortieQuintityRequests);
        }).then(SortieQuintityResults=>{
            this.sortieQuantityMonthlyData = SortieQuintityResults;
            this.updateChartData();
            this.updateChartData1();
        }).catch(error => {
            console.error('Error loading monthly data:', error);
        });


    }
    loadChangeEntreeTotalForYear(year: number): void {
        this.entreeStockService.getChangeEntreeTotalForYear(year).subscribe(
            (change: number) => {
                console.log('Change in total Entree Stock:', change);
                this.displayChange = change;
            },
            (error) => {
                console.error('Error fetching change in total Entree Stock:', error);
            }
        );
    }
    loadChangeSortieTotalForYear(year: number): void {
        this.sortieStockService.getChangeSortieTotalForYear(year).subscribe(
            (change: number) => {
                console.log('Change in total Sortie Stock:', change);
                this.sortiedisplayChange = change;
            },
            (error) => {
                console.error('Error fetching change in total Entree Stock:', error);
            }
        );
    }
    loadTotalFournisseurs(): void {
        this.fournisseurAdminService.countFournisseurs().subscribe(
            (count: number) => {
                this.totalFournisseurs = count;
            },
            (error) => {
                console.error('Error loading total fournisseurs:', error);
            }
        );
    }
    loadTotalMagasin(): void {
        this.magasinAdminService.countMagasins().subscribe(
            (count: number) => {
                this.totalMagasin = count;
            },
            (error) => {
                console.error('Error loading total Magasin:', error);
            }
        );
    }


    updateChartData(): void {
        const documentStyle = getComputedStyle(document.documentElement);
        const textColor = documentStyle.getPropertyValue('--text-color');
        const textColorSecondary = documentStyle.getPropertyValue('--text-color-secondary');
        const surfaceBorder = documentStyle.getPropertyValue('--surface-border');

        const validEntreeData = this.entreeMonthlyData.map(value => value !== null && value !== undefined ? value : 0);
        const validSortieData = this.sortieMonthlyData.map(value => value !== null && value !== undefined ? value : 0);


        this.data = {
            labels: ['Janvier',  'Février','Mars','Avril','Mai','Juin','Juillet' , 'Août' , 'Septembre', 'Octobre', 'Novembre' , 'Décembre'],
            datasets: [

                {
                    type: 'bar',
                    label: 'Entree Stock',
                    backgroundColor: '#00c0f4',
                    data:  validEntreeData,
                    borderColor: '#090909',
                    borderWidth: 0.5,
                    barPercentage: 1.07,
                    borderRadius: {
                        topLeft: 14,
                        topRight: 14
                    }
                },
                {
                    type: 'bar',
                    label: 'Sortie Stock',
                    backgroundColor: '#b4d039',
                    data: validSortieData,
                    barPercentage: 1.07,
                    borderColor: '#090909',
                    borderWidth: 0.5,
                    borderRadius: {
                        topLeft: 14,
                        topRight: 14
                    }


                }
            ]
        };

        this.options = {
            maintainAspectRatio: false,
            aspectRatio: 0.86,
            plugins: {
                legend: {
                    position: 'bottom',
                    align: 'start',
                    labels: {
                        color: textColor,
                        boxWidth: 15,
                        boxHeight: 15,
                        padding: 10
                    }
                }
            },

            scales: {
                x: {
                    grid: {
                        color: surfaceBorder,

                    },

                    ticks: {
                        color: textColorSecondary,

                    },


                },


                y: {
                    ticks: {
                        color: textColorSecondary
                    },
                    grid: {
                        color: surfaceBorder
                    }
                }
            }
        };
    }
    updateChartPolarData(data: Map<string, number>[]):void{
        const documentStyle = getComputedStyle(document.documentElement);
        const textColor = documentStyle.getPropertyValue('--text-color');
        const surfaceBorder = documentStyle.getPropertyValue('--surface-border');
        const labels = data.map(item => Object.keys(item)[0]);
        const quantities = data.map(item => Object.values(item)[0]);

        const predefinedColors = [
            '#FF6384',
            '#36A2EB',
            '#FFCE56',
            '#4BC0C0',
            '#9966FF',
        ];


        const additionalColors = [];
        for (let i = 0; i < labels.length - predefinedColors.length; i++) {
            additionalColors.push(this.getRandomColor());
        }

        const colors = [...predefinedColors, ...additionalColors];


        this.data1 = {
            datasets: [
                {
                    data: quantities,
                    backgroundColor: colors,
                    label: 'My dataset'
                }
            ],
            labels: labels
        };

        this.options1 = {
            maintainAspectRatio: false,
            aspectRatio: 0.84,
            plugins: {
                legend: {
                    position: 'left',
                    align: 'center',
                    labels: {
                        color: textColor
                    }
                }
            },
            scales: {
                r: {
                    grid: {
                        color: surfaceBorder
                    }
                }
            }
        };


    }
    updateChartPolarData1(data: { produit: string; quantite: number }[]): void {
        const documentStyle = getComputedStyle(document.documentElement);
        const textColor = documentStyle.getPropertyValue('--text-color');
        const surfaceBorder = documentStyle.getPropertyValue('--surface-border');

        const labels = data.map(item => item.produit);
        const quantities = data.map(item => item.quantite);

        this.data1 = {
            datasets:[
                {
                    data: quantities,
                    backgroundColor: this.getRandomColors(labels.length),
                    label: 'My dataset'
                }
            ],
            labels: labels
        };

        this.options1 = {
            maintainAspectRatio: false,
            aspectRatio: 0.8,
            plugins: {
                legend: {
                    position: 'right',
                    align: 'center',
                    labels: {
                        color: textColor
                    }
                }
            },
            scales: {
                r: {
                    grid: {
                        color: surfaceBorder
                    }
                }
            }
        };
    }
    updateChartData1(): void {
        const documentStyle = getComputedStyle(document.documentElement);
        const textColor = documentStyle.getPropertyValue('--text-color');
        const textColorSecondary = documentStyle.getPropertyValue('--text-color-secondary');
        const surfaceBorder = documentStyle.getPropertyValue('--surface-border');
        const validEntreeQuintityData = this.entreeQuantityMonthlyData.map(value => value !== null && value !== undefined ? value : 0);
        const validSortieQuintityData = this.sortieQuantityMonthlyData.map(value => value !== null && value !== undefined ? value : 0);


        this.data2 = {
            labels: ['Janvier',  'Février','Mars','Avril','Mai','Juin','Juillet' , 'Août' , 'Septembre', 'Octobre', 'Novembre' , 'Décembre'],
            datasets: [
                {
                    type: 'line',
                    label: 'Quintity Entree',
                    borderColor: '#aa3838',
                    borderWidth: 2,
                    fill: true,
                    tension: 0.4,
                    data:validEntreeQuintityData,
                    backgroundColor: 'rgba(230,158,158,0.41)'

                },
                {

                    label: 'Quintity Sortie',
                    borderColor: '#2b6fbd',
                    borderWidth: 2,
                    fill: true,
                    tension: 0.4,
                    data:validSortieQuintityData,
                    backgroundColor: 'rgba(110,175,193,0.37)'

                },

            ]
        };

        this.options2 = {
            maintainAspectRatio: false,
            aspectRatio: 0.8,
            plugins: {
                legend: {
                    position: 'bottom',
                    align: 'start',
                    labels: {
                        color: textColor,

                    }
                }
            },

            scales: {
                x: {
                    grid: {
                        color: surfaceBorder,

                    },

                    ticks: {
                        color: textColorSecondary,

                    },


                },


                y: {
                    ticks: {
                        color: textColorSecondary
                    },
                    grid: {
                        color: surfaceBorder
                    }
                }
            }
        };
    }


    loadMonthlyData(): void {

        const requests = this.months.map(month =>
            this.entreeStockService.getTotalForYearAndMonth(this.year, month).toPromise()
        );

        Promise.all(requests).then(results => {
            this.monthlyData = results;
            this.updateChartData();
        }).catch(error => {
            console.error('Error loading monthly data:', error);
        });
    }
    loadTotalQuantitiesForAllProduits(): void {
        this.QuintityAndProduitBymagazin.getTotalQuantitiesForAllProduits().subscribe(
            (data: Map<string, number>[]) => {
                this.updateChartPolarData(data);
            },
            (error) => {
                console.error('Error loading total quantities for all products:', error);
            }
        );}
    loadMonthlyDataForYear(year: number): void {
        const sortieRequests = this.months.map(month =>
            this.sortieStockService.getTotalSortForYearAndMonth(year, month).toPromise()
        );
        const entreeRequests = this.months.map(month =>
            this.entreeStockService.getTotalForYearAndMonth(year, month).toPromise()
        );
        const entreeQuintityRequests = this.months.map(month =>
            this.entreeStockService.calculateQuantityByYearAndMonth(year, month).toPromise()
        );
        const sortieQuintityRequests = this.months.map(month =>
            this.sortieStockService.calculateSortQuantityByYearAndMonth(year, month).toPromise()
        );

        Promise.all(entreeRequests).then(entreeResults => {
            this.entreeMonthlyData = entreeResults;
            this.totalEntree = this.entreeMonthlyData.reduce((acc, val) => acc + (val || 0), 0);
            this.updateChartData();
            return Promise.all(sortieRequests);
        }).then(sortieResults => {
            this.sortieMonthlyData = sortieResults;
            this.totalSortie = this.sortieMonthlyData.reduce((acc, val) => acc + (val || 0), 0);
            this.updateChartData();
            return Promise.all(entreeQuintityRequests);
        }).then(QuintityResults => {
            this.entreeQuantityMonthlyData = QuintityResults;
            this. updateChartData1();
            return Promise.all(sortieQuintityRequests);
        }).then(SortieQuintityResults => {
            this.sortieQuantityMonthlyData = SortieQuintityResults;
            this. updateChartData1();
        }).catch(error => {
            console.error('Error loading monthly data for selected year:', error);
        });
    }


    loadProductsForSelectedMagasin(magasin: MagasinDto): void {
        this.QuintityAndProduitBymagazin.getStocksByMagazinId(magasin.label).subscribe(
            (data: StockDto[]) => {
                const productQuantities = data.map(item => ({
                    produit: item.produit.label,
                    quantite: item.quantite
                }));

                this.updateChartPolarData1(productQuantities);
            },
            (error) => {
                console.error('Error loading products for selected magasin:', error);
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

    onSelectionChange() {
        if (this.selectedMagasin) {
            this.loadProductsForSelectedMagasin(this.selectedMagasin);
        } else {
            this.loadTotalQuantitiesForAllProduits();
        }
    }
    onDateChange(): void {
        if (this.date) {
            const selectedYear = this.date.getFullYear();
            this.loadMonthlyDataForYear(selectedYear);
            this.loadChangeEntreeTotalForYear(selectedYear);
            this.loadChangeSortieTotalForYear(selectedYear);

        }
    }



    getRandomColor(): string {
        const letters = '0123456789ABCDEF';
        let color = '#';
        for (let i = 0; i < 6; i++) {
            color += letters[Math.floor(Math.random() * 16)];
        }
        return color;
    }
    getRandomColors(count: number): string[] {
        const colors = [];
        for (let i = 0; i < count; i++) {
            colors.push(this.getRandomColor());
        }
        return colors;
    }




}

