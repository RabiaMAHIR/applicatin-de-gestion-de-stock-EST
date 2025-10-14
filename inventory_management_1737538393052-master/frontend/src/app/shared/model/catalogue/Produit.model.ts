import {CategorieProduitDto} from './CategorieProduit.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class ProduitDto extends BaseDto{

    public code: string;

    public label: string;

    public prixUnitaire: null | number;

    public description: string;

    public categorieProduit: CategorieProduitDto ;


    constructor() {
        super();

        this.code = '';
        this.label = '';
        this.prixUnitaire = null;
        this.description = '';

        }

}
