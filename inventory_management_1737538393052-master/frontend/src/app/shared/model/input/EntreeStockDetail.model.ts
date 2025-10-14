import {MagasinDto} from '../store/Magasin.model';
import {ProduitDto} from '../catalogue/Produit.model';
import {EntreeStockDto} from './EntreeStock.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class EntreeStockDetailDto extends BaseDto{

    public prix: null | number;

    public quantite: null | number;

    public description: string;

    public entreeStock: EntreeStockDto ;
    public produit: ProduitDto ;
    public magazin: MagasinDto ;


    constructor() {
        super();

        this.prix = null;
        this.quantite = null;
        this.description = '';
        this.produit = new ProduitDto() ;

        }

}
