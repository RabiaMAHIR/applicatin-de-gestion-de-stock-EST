package  ma.zyn.app.ws.dto.catalogue;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProduitDto  extends AuditBaseDto {

    private String code  ;
    private String label  ;
    private BigDecimal prixUnitaire  ;
    private String description  ;

    private CategorieProduitDto categorieProduit ;



    public ProduitDto(){
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


    public BigDecimal getPrixUnitaire(){
        return this.prixUnitaire;
    }
    public void setPrixUnitaire(BigDecimal prixUnitaire){
        this.prixUnitaire = prixUnitaire;
    }


    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    public CategorieProduitDto getCategorieProduit(){
        return this.categorieProduit;
    }

    public void setCategorieProduit(CategorieProduitDto categorieProduit){
        this.categorieProduit = categorieProduit;
    }






}
