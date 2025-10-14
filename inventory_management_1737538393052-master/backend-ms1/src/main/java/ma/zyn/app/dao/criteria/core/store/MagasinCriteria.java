package  ma.zyn.app.dao.criteria.core.store;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class MagasinCriteria extends  BaseCriteria  {

    private String adresse;
    private String adresseLike;
    private String code;
    private String codeLike;
    private String label;
    private String labelLike;
    private String description;
    private String descriptionLike;



    public String getAdresse(){
        return this.adresse;
    }
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }
    public String getAdresseLike(){
        return this.adresseLike;
    }
    public void setAdresseLike(String adresseLike){
        this.adresseLike = adresseLike;
    }

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


}
