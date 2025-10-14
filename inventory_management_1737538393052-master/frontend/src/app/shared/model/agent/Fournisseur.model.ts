
import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class FournisseurDto extends BaseDto{

    public code: string;

    public label: string;

    public adresse: string;

    public email: string;

    public description: string;



    constructor() {
        super();

        this.code = '';
        this.label = '';
        this.adresse = '';
        this.email = '';
        this.description = '';

        }

}
