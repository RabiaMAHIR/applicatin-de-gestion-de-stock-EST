
import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class MagasinCriteria extends BaseCriteria {

    public id: number;
    public adresse: string;
    public adresseLike: string;
    public code: string;
    public codeLike: string;
    public label: string;
    public labelLike: string;
    public description: string;
    public descriptionLike: string;

}
