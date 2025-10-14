package  ma.zyn.app.ws.dto.store;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;


import ma.zyn.app.ws.dto.catalogue.ProduitDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockDto  extends AuditBaseDto {

    private BigDecimal quantite  ;
    private BigDecimal quantiteDeffecteuse  ;

    private ProduitDto produit ;
    private MagasinDto magazin ;



    public StockDto(){
        super();
    }




    public BigDecimal getQuantite(){
        return this.quantite;
    }
    public void setQuantite(BigDecimal quantite){
        this.quantite = quantite;
    }


    public BigDecimal getQuantiteDeffecteuse(){
        return this.quantiteDeffecteuse;
    }
    public void setQuantiteDeffecteuse(BigDecimal quantiteDeffecteuse){
        this.quantiteDeffecteuse = quantiteDeffecteuse;
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
