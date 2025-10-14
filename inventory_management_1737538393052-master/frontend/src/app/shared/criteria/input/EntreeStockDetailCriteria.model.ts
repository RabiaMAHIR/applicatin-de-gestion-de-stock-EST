import {MagasinCriteria} from '../store/MagasinCriteria.model';
import {ProduitCriteria} from '../catalogue/ProduitCriteria.model';
import {EntreeStockCriteria} from './EntreeStockCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class EntreeStockDetailCriteria extends BaseCriteria {

    public id: number;
     public prix: number;
     public prixMin: number;
     public prixMax: number;
     public quantite: number;
     public quantiteMin: number;
     public quantiteMax: number;
    public description: string;
    public descriptionLike: string;
  public produit: ProduitCriteria ;
  public produits: Array<ProduitCriteria> ;

}
