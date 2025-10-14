import {SortieStockDetailCriteria} from './SortieStockDetailCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class SortieStockCriteria extends BaseCriteria {

    public id: number;
    public dateSortie: Date;
    public dateSortieFrom: Date;
    public dateSortieTo: Date;
    public description: string;
    public descriptionLike: string;
    public code: string;
    public codeLike: string;
      public sortieStockDetails: Array<SortieStockDetailCriteria>;

}
