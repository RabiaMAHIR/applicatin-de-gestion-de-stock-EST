package  ma.zyn.app.ws.dto.output;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.ws.dto.store.MagasinDto;
import ma.zyn.app.ws.dto.catalogue.ProduitDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class SortieStockDto  extends AuditBaseDto {

    private String dateSortie ;
    private String description  ;
    private String code  ;


    private List<SortieStockDetailDto> sortieStockDetails ;


    public SortieStockDto(){
        super();
    }




    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateSortie(){
        return this.dateSortie;
    }
    public void setDateSortie(String dateSortie){
        this.dateSortie = dateSortie;
    }


    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }





    public List<SortieStockDetailDto> getSortieStockDetails(){
        return this.sortieStockDetails;
    }

    public void setSortieStockDetails(List<SortieStockDetailDto> sortieStockDetails){
        this.sortieStockDetails = sortieStockDetails;
    }



}
