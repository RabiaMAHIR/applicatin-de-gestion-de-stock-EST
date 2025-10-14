import {MagasinCriteria} from './MagasinCriteria.model';
import {ProduitCriteria} from '../catalogue/ProduitCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class StockCriteria extends BaseCriteria {

    public id: number;
     public quantite: number;
     public quantiteMin: number;
     public quantiteMax: number;
     public quantiteDeffecteuse: number;
     public quantiteDeffecteuseMin: number;
     public quantiteDeffecteuseMax: number;
  public produit: ProduitCriteria ;
  public produits: Array<ProduitCriteria> ;

}
