import {MagasinDto} from './Magasin.model';
import {ProduitDto} from '../catalogue/Produit.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class StockDto extends BaseDto{

    public quantite: null | number;

    public quantiteDeffecteuse: null | number;

    public produit: ProduitDto ;
    public magazin: MagasinDto ;


    constructor() {
        super();

        this.quantite = null;
        this.quantiteDeffecteuse = null;
        this.produit = new ProduitDto() ;

        }

}
