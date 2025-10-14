package  ma.zyn.app.ws.dto.input;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;


import ma.zyn.app.ws.dto.store.MagasinDto;
import ma.zyn.app.ws.dto.catalogue.ProduitDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class EntreeStockDetailDto  extends AuditBaseDto {

    private BigDecimal prix  ;
    private BigDecimal quantite  ;
    private String description  ;

    private EntreeStockDto entreeStock ;
    private ProduitDto produit ;
    private MagasinDto magazin ;



    public EntreeStockDetailDto(){
        super();
    }




    public BigDecimal getPrix(){
        return this.prix;
    }
    public void setPrix(BigDecimal prix){
        this.prix = prix;
    }


    public BigDecimal getQuantite(){
        return this.quantite;
    }
    public void setQuantite(BigDecimal quantite){
        this.quantite = quantite;
    }


    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    public EntreeStockDto getEntreeStock(){
        return this.entreeStock;
    }

    public void setEntreeStock(EntreeStockDto entreeStock){
        this.entreeStock = entreeStock;
    }
    public ProduitDto getProduit(){
        return this.produit;
    }

    public void setProduit(ProduitDto produit){
        this.produit = produit;
    }
    public MagasinDto getMagazin(){
        return this.magazin;
    }

    public void setMagazin(MagasinDto magazin){
        this.magazin = magazin;
    }






}
