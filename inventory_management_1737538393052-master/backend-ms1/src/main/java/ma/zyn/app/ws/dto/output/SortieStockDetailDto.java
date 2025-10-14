package  ma.zyn.app.ws.dto.output;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;


import ma.zyn.app.ws.dto.store.MagasinDto;
import ma.zyn.app.ws.dto.catalogue.ProduitDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class SortieStockDetailDto  extends AuditBaseDto {

    private BigDecimal prix  ;
    private BigDecimal quantite  ;
    private String description  ;

    private SortieStockDto sortieStock ;
    private ProduitDto produit ;
    private MagasinDto magazin ;



    public SortieStockDetailDto(){
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


    public SortieStockDto getSortieStock(){
        return this.sortieStock;
    }

    public void setSortieStock(SortieStockDto sortieStock){
        this.sortieStock = sortieStock;
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
