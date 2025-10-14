
import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class FournisseurCriteria extends BaseCriteria {

    public id: number;
    public code: string;
    public codeLike: string;
    public label: string;
    public labelLike: string;
    public adresse: string;
    public adresseLike: string;
    public email: string;
    public emailLike: string;
    public description: string;
    public descriptionLike: string;

}
