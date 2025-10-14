package  ma.zyn.app.ws.dto.input;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.ws.dto.agent.FournisseurDto;
import ma.zyn.app.ws.dto.store.MagasinDto;
import ma.zyn.app.ws.dto.catalogue.ProduitDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class EntreeStockDto  extends AuditBaseDto {

    private String code  ;
    private String dateEntree ;
    private String description  ;

    private FournisseurDto fournisseur ;

    private List<EntreeStockDetailDto> entreeStockDetails ;


    public EntreeStockDto(){
        super();
    }




    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateEntree(){
        return this.dateEntree;
    }
    public void setDateEntree(String dateEntree){
        this.dateEntree = dateEntree;
    }


    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    public FournisseurDto getFournisseur(){
        return this.fournisseur;
    }

    public void setFournisseur(FournisseurDto fournisseur){
        this.fournisseur = fournisseur;
    }



    public List<EntreeStockDetailDto> getEntreeStockDetails(){
        return this.entreeStockDetails;
    }

    public void setEntreeStockDetails(List<EntreeStockDetailDto> entreeStockDetails){
        this.entreeStockDetails = entreeStockDetails;
    }



}
