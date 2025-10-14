
import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class MagasinDto extends BaseDto{

    public adresse: string;

    public code: string;

    public label: string;

    public description: string;

   public value: MagasinDto | null; 



    constructor() {
        super();

        this.adresse = '';
        this.code = '';
        this.label = '';
        this.description = '';
        

        }

}
