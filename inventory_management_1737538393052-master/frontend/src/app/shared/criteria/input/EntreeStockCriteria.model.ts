import {FournisseurCriteria} from '../agent/FournisseurCriteria.model';
import {EntreeStockDetailCriteria} from './EntreeStockDetailCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class EntreeStockCriteria extends BaseCriteria {

    public id: number;
    public code: string;
    public codeLike: string;
    public dateEntree: Date;
    public dateEntreeFrom: Date;
    public dateEntreeTo: Date;
    public description: string;
    public descriptionLike: string;
      public entreeStockDetails: Array<EntreeStockDetailCriteria>;

}
