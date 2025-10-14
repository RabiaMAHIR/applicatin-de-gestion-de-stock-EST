package  ma.zyn.app.dao.criteria.core.catalogue;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class ProduitCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private String label;
    private String labelLike;
    private String prixUnitaire;
    private String prixUnitaireMin;
    private String prixUnitaireMax;
    private String description;
    private String descriptionLike;

    private CategorieProduitCriteria categorieProduit ;
    private List<CategorieProduitCriteria> categorieProduits ;


    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCodeLike(){
        return this.codeLike;
    }
    public void setCodeLike(String codeLike){
        this.codeLike = codeLike;
    }

    public String getLabel(){
        return this.label;
    }
    public void setLabel(String label){
        this.label = label;
    }
    public String getLabelLike(){
        return this.labelLike;
    }
    public void setLabelLike(String labelLike){
        this.labelLike = labelLike;
    }

    public String getPrixUnitaire(){
        return this.prixUnitaire;
    }
    public void setPrixUnitaire(String prixUnitaire){
        this.prixUnitaire = prixUnitaire;
    }   
    public String getPrixUnitaireMin(){
        return this.prixUnitaireMin;
    }
    public void setPrixUnitaireMin(String prixUnitaireMin){
        this.prixUnitaireMin = prixUnitaireMin;
    }
    public String getPrixUnitaireMax(){
        return this.prixUnitaireMax;
    }
    public void setPrixUnitaireMax(String prixUnitaireMax){
        this.prixUnitaireMax = prixUnitaireMax;
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


    public CategorieProduitCriteria getCategorieProduit(){
        return this.categorieProduit;
    }

    public void setCategorieProduit(CategorieProduitCriteria categorieProduit){
        this.categorieProduit = categorieProduit;
    }
    public List<CategorieProduitCriteria> getCategorieProduits(){
        return this.categorieProduits;
    }

    public void setCategorieProduits(List<CategorieProduitCriteria> categorieProduits){
        this.categorieProduits = categorieProduits;
    }
}
