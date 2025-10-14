package  ma.zyn.app.dao.criteria.core.output;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class SortieStockCriteria extends  BaseCriteria  {

    private LocalDateTime dateSortie;
    private LocalDateTime dateSortieFrom;
    private LocalDateTime dateSortieTo;
    private String description;
    private String descriptionLike;
    private String code;
    private String codeLike;



    public LocalDateTime getDateSortie(){
        return this.dateSortie;
    }
    public void setDateSortie(LocalDateTime dateSortie){
        this.dateSortie = dateSortie;
    }
    public LocalDateTime getDateSortieFrom(){
        return this.dateSortieFrom;
    }
    public void setDateSortieFrom(LocalDateTime dateSortieFrom){
        this.dateSortieFrom = dateSortieFrom;
    }
    public LocalDateTime getDateSortieTo(){
        return this.dateSortieTo;
    }
    public void setDateSortieTo(LocalDateTime dateSortieTo){
        this.dateSortieTo = dateSortieTo;
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


}
