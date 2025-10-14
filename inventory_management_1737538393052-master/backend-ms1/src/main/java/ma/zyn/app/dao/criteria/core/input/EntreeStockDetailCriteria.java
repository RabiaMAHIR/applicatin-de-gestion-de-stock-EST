package  ma.zyn.app.dao.criteria.core.input;


import ma.zyn.app.dao.criteria.core.store.MagasinCriteria;
import ma.zyn.app.dao.criteria.core.catalogue.ProduitCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class EntreeStockDetailCriteria extends  BaseCriteria  {

    private String prix;
    private String prixMin;
    private String prixMax;
    private String quantite;
    private String quantiteMin;
    private String quantiteMax;
    private String description;
    private String descriptionLike;

    private EntreeStockCriteria entreeStock ;
    private List<EntreeStockCriteria> entreeStocks ;
    private ProduitCriteria produit ;
    private List<ProduitCriteria> produits ;
    private MagasinCriteria magazin ;
    private List<MagasinCriteria> magazins ;


    public String getPrix(){
        return this.prix;
    }
    public void setPrix(String prix){
        this.prix = prix;
    }   
    public String getPrixMin(){
        return this.prixMin;
    }
    public void setPrixMin(String prixMin){
        this.prixMin = prixMin;
    }
    public String getPrixMax(){
        return this.prixMax;
    }
    public void setPrixMax(String prixMax){
        this.prixMax = prixMax;
    }
      
    public String getQuantite(){
        return this.quantite;
    }
    public void setQuantite(String quantite){
        this.quantite = quantite;
    }   
    public String getQuantiteMin(){
        return this.quantiteMin;
    }
    public void setQuantiteMin(String quantiteMin){
        this.quantiteMin = quantiteMin;
    }
    public String getQuantiteMax(){
        return this.quantiteMax;
    }
    public void setQuantiteMax(String quantiteMax){
        this.quantiteMax = quantiteMax;
    }
      
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescriptionLike(){
        return this.descriptionLike;
    }
    public void setDescriptionLike(String descriptionLike){
        this.descriptionLike = descriptionLike;
    }


    public EntreeStockCriteria getEntreeStock(){
        return this.entreeStock;
    }

    public void setEntreeStock(EntreeStockCriteria entreeStock){
        this.entreeStock = entreeStock;
    }
    public List<EntreeStockCriteria> getEntreeStocks(){
        return this.entreeStocks;
    }

    public void setEntreeStocks(List<EntreeStockCriteria> entreeStocks){
        this.entreeStocks = entreeStocks;
    }
    public ProduitCriteria getProduit(){
        return this.produit;
    }

    public void setProduit(ProduitCriteria produit){
        this.produit = produit;
    }
    public List<ProduitCriteria> getProduits(){
        return this.produits;
    }

    public void setProduits(List<ProduitCriteria> produits){
        this.produits = produits;
    }
    public MagasinCriteria getMagazin(){
        return this.magazin;
    }

    public void setMagazin(MagasinCriteria magazin){
        this.magazin = magazin;
    }
    public List<MagasinCriteria> getMagazins(){
        return this.magazins;
    }

    public void setMagazins(List<MagasinCriteria> magazins){
        this.magazins = magazins;
    }
}
