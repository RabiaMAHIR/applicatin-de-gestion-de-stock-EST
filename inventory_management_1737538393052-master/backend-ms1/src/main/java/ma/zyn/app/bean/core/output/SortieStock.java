package ma.zyn.app.bean.core.output;

import java.util.List;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.bean.core.store.Magasin;
import ma.zyn.app.bean.core.catalogue.Produit;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sortie_stock")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="sortie_stock_seq",sequenceName="sortie_stock_seq",allocationSize=1, initialValue = 1)
public class SortieStock  extends BaseEntity     {




    private LocalDateTime dateSortie ;

    private String description;

    @Column(length = 500)
    private String code;


    private List<SortieStockDetail> sortieStockDetails ;

    public SortieStock(){
        super();
    }

    public SortieStock(Long id){
        this.id = id;
    }

    public SortieStock(Long id,String code){
        this.id = id;
        this.code = code ;
    }
    public SortieStock(String code){
        this.code = code ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="sortie_stock_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public LocalDateTime getDateSortie(){
        return this.dateSortie;
    }
    public void setDateSortie(LocalDateTime dateSortie){
        this.dateSortie = dateSortie;
    }
      @Column(columnDefinition="TEXT")
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
    @OneToMany(mappedBy = "sortieStock")
    public List<SortieStockDetail> getSortieStockDetails(){
        return this.sortieStockDetails;
    }

    public void setSortieStockDetails(List<SortieStockDetail> sortieStockDetails){
        this.sortieStockDetails = sortieStockDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SortieStock sortieStock = (SortieStock) o;
        return id != null && id.equals(sortieStock.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

