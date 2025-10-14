import {SortieStockDetailDto} from './SortieStockDetail.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class SortieStockDto extends BaseDto{

   public dateSortie: Date;

    public description: string;

    public code: string;

     public sortieStockDetails: Array<SortieStockDetailDto>;


    constructor() {
        super();

        this.dateSortie = null;
        this.description = '';
        this.code = '';
        this.sortieStockDetails = new Array<SortieStockDetailDto>();

        }

}
