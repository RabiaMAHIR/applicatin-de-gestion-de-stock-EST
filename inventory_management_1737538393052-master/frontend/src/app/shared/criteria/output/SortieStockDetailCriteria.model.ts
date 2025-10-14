import {SortieStockCriteria} from './SortieStockCriteria.model';
import {MagasinCriteria} from '../store/MagasinCriteria.model';
import {ProduitCriteria} from '../catalogue/ProduitCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class SortieStockDetailCriteria extends BaseCriteria {

    public id: number;
     public prix: number;
     public prixMin: number;
     public prixMax: number;
     public quantite: number;
     public quantiteMin: number;
     public quantiteMax: number;
    public description: string;
    public descriptionLike: string;
  public sortieStock: SortieStockCriteria ;
  public sortieStocks: Array<SortieStockCriteria> ;
  public produit: ProduitCriteria ;
  public produits: Array<ProduitCriteria> ;

}
