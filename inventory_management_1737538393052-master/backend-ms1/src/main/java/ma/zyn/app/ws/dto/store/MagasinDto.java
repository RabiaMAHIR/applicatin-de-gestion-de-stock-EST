package  ma.zyn.app.ws.dto.store;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class MagasinDto  extends AuditBaseDto {

    private String adresse  ;
    private String code  ;
    private String label  ;
    private String description  ;




    public MagasinDto(){
        super();
    }




    public String getAdresse(){
        return this.adresse;
    }
    public void setAdresse(String adresse){
        this.adresse = adresse;
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
