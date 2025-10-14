
import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class CategorieProduitDto extends BaseDto{

    public code: string;

    public label: string;

    public description: string;



    constructor() {
        super();

        this.code = '';
        this.label = '';
        this.description = '';

        }

}
