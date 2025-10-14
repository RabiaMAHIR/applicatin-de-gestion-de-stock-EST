import {FournisseurDto} from '../agent/Fournisseur.model';
import {EntreeStockDetailDto} from './EntreeStockDetail.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class EntreeStockDto extends BaseDto{

    public code: string;

   public dateEntree: Date;

    public description: string;

    public fournisseur: FournisseurDto ;
     public entreeStockDetails: Array<EntreeStockDetailDto>;


    constructor() {
        super();

        this.code = '';
        this.dateEntree = null;
        this.description = '';
        this.entreeStockDetails = new Array<EntreeStockDetailDto>();

        }

}
