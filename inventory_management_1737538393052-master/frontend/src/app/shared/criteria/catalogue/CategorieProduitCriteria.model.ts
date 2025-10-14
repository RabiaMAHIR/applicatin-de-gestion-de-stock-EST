
import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class CategorieProduitCriteria extends BaseCriteria {

    public id: number;
    public code: string;
    public codeLike: string;
    public label: string;
    public labelLike: string;
    public description: string;
    public descriptionLike: string;

}
