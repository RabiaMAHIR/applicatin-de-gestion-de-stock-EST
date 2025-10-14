package ma.zyn.app.bean.core.input;

import java.util.List;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.bean.core.agent.Fournisseur;
import ma.zyn.app.bean.core.store.Magasin;
import ma.zyn.app.bean.core.catalogue.Produit;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "entree_stock")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="entree_stock_seq",sequenceName="entree_stock_seq",allocationSize=1, initialValue = 1)
public class EntreeStock  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    private LocalDateTime dateEntree ;

    private String description;

    private Fournisseur fournisseur ;

    private List<EntreeStockDetail> entreeStockDetails ;

    public EntreeStock(){
        super();
    }

    public EntreeStock(Long id){
        this.id = id;
    }

    public EntreeStock(Long id,String code){
        this.id = id;
        this.code = code ;
    }
    public EntreeStock(String code){
        this.code = code ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="entree_stock_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public LocalDateTime getDateEntree(){
        return this.dateEntree;
    }
    public void setDateEntree(LocalDateTime dateEntree){
        this.dateEntree = dateEntree;
    }
      @Column(columnDefinition="TEXT")
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fournisseur")
    public Fournisseur getFournisseur(){
        return this.fournisseur;
    }
    public void setFournisseur(Fournisseur fournisseur){
        this.fournisseur = fournisseur;
    }
    @OneToMany(mappedBy = "entreeStock")
    public List<EntreeStockDetail> getEntreeStockDetails(){
        return this.entreeStockDetails;
    }

    public void setEntreeStockDetails(List<EntreeStockDetail> entreeStockDetails){
        this.entreeStockDetails = entreeStockDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntreeStock entreeStock = (EntreeStock) o;
        return id != null && id.equals(entreeStock.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

