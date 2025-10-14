package  ma.zyn.app.ws.dto.catalogue;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategorieProduitDto  extends AuditBaseDto {

    private String code  ;
    private String label  ;
    private String description  ;




    public CategorieProduitDto(){
        super();
    }




    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }


    public String getLabel(){
        return this.label;
    }
    public void setLabel(String label){
        this.label = label;
    }


    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }








}
