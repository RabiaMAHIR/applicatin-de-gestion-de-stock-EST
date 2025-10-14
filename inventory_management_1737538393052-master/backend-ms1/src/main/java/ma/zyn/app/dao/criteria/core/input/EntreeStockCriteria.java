package  ma.zyn.app.dao.criteria.core.input;


import ma.zyn.app.dao.criteria.core.agent.FournisseurCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class EntreeStockCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private LocalDateTime dateEntree;
    private LocalDateTime dateEntreeFrom;
    private LocalDateTime dateEntreeTo;
    private String description;
    private String descriptionLike;

    private FournisseurCriteria fournisseur ;
    private List<FournisseurCriteria> fournisseurs ;


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

    public LocalDateTime getDateEntree(){
        return this.dateEntree;
    }
    public void setDateEntree(LocalDateTime dateEntree){
        this.dateEntree = dateEntree;
    }
    public LocalDateTime getDateEntreeFrom(){
        return this.dateEntreeFrom;
    }
    public void setDateEntreeFrom(LocalDateTime dateEntreeFrom){
        this.dateEntreeFrom = dateEntreeFrom;
    }
    public LocalDateTime getDateEntreeTo(){
        return this.dateEntreeTo;
    }
    public void setDateEntreeTo(LocalDateTime dateEntreeTo){
        this.dateEntreeTo = dateEntreeTo;
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


    public FournisseurCriteria getFournisseur(){
        return this.fournisseur;
    }

    public void setFournisseur(FournisseurCriteria fournisseur){
        this.fournisseur = fournisseur;
    }
    public List<FournisseurCriteria> getFournisseurs(){
        return this.fournisseurs;
    }

    public void setFournisseurs(List<FournisseurCriteria> fournisseurs){
        this.fournisseurs = fournisseurs;
    }
}
