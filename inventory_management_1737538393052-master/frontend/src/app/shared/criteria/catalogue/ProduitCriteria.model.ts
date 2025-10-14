import {CategorieProduitCriteria} from './CategorieProduitCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class ProduitCriteria extends BaseCriteria {

    public id: number;
    public code: string;
    public codeLike: string;
    public label: string;
    public labelLike: string;
     public prixUnitaire: number;
     public prixUnitaireMin: number;
     public prixUnitaireMax: number;
    public description: string;
    public descriptionLike: string;

}
