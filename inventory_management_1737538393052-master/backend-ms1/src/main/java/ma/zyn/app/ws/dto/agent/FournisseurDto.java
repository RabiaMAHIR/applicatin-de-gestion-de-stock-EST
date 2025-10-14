package  ma.zyn.app.ws.dto.agent;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class FournisseurDto  extends AuditBaseDto {

    private String code  ;
    private String label  ;
    private String adresse  ;
    private String email  ;
    private String description  ;




    public FournisseurDto(){
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


    public String getAdresse(){
        return this.adresse;
    }
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }


    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }


    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }








}
