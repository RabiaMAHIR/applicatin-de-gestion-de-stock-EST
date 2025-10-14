import {SortieStockDto} from './SortieStock.model';
import {MagasinDto} from '../store/Magasin.model';
import {ProduitDto} from '../catalogue/Produit.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class SortieStockDetailDto extends BaseDto{

    public prix: null | number;

    public quantite: null | number;

    public description: string;

    public sortieStock: SortieStockDto ;
    public produit: ProduitDto ;
    public magazin: MagasinDto ;
    totalCost: number;
   


    constructor() {
        super();

        this.prix = null;
        this.quantite = null;
        this.description = '';
        this.sortieStock = new SortieStockDto() ;
        this.produit = new ProduitDto() ;

        }

}
