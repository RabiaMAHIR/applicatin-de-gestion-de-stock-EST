package  ma.zyn.app.dao.criteria.core.store;


import ma.zyn.app.dao.criteria.core.catalogue.ProduitCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class StockCriteria extends  BaseCriteria  {

    private String quantite;
    private String quantiteMin;
    private String quantiteMax;
    private String quantiteDeffecteuse;
    private String quantiteDeffecteuseMin;
    private String quantiteDeffecteuseMax;

    private ProduitCriteria produit ;
    private List<ProduitCriteria> produits ;
    private MagasinCriteria magazin ;
    private List<MagasinCriteria> magazins ;


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
      
    public String getQuantiteDeffecteuse(){
        return this.quantiteDeffecteuse;
    }
    public void setQuantiteDeffecteuse(String quantiteDeffecteuse){
        this.quantiteDeffecteuse = quantiteDeffecteuse;
    }   
    public String getQuantiteDeffecteuseMin(){
        return this.quantiteDeffecteuseMin;
    }
    public void setQuantiteDeffecteuseMin(String quantiteDeffecteuseMin){
        this.quantiteDeffecteuseMin = quantiteDeffecteuseMin;
    }
    public String getQuantiteDeffecteuseMax(){
        return this.quantiteDeffecteuseMax;
    }
    public void setQuantiteDeffecteuseMax(String quantiteDeffecteuseMax){
        this.quantiteDeffecteuseMax = quantiteDeffecteuseMax;
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
