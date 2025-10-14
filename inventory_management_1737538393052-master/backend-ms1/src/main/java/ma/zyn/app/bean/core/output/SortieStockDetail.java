package ma.zyn.app.bean.core.output;






import ma.zyn.app.bean.core.store.Magasin;
import ma.zyn.app.bean.core.catalogue.Produit;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "sortie_stock_detail")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="sortie_stock_detail_seq",sequenceName="sortie_stock_detail_seq",allocationSize=1, initialValue = 1)
public class SortieStockDetail  extends BaseEntity     {




    private BigDecimal prix = BigDecimal.ZERO;

    private BigDecimal quantite = BigDecimal.ZERO;

    private String description;

    private SortieStock sortieStock ;
    private Produit produit ;
    private Magasin magazin ;


    public SortieStockDetail(){
        super();
    }

    public SortieStockDetail(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="sortie_stock_detail_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sortie_stock")
    public SortieStock getSortieStock(){
        return this.sortieStock;
    }
    public void setSortieStock(SortieStock sortieStock){
        this.sortieStock = sortieStock;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produit")
    public Produit getProduit(){
        return this.produit;
    }
    public void setProduit(Produit produit){
        this.produit = produit;
    }
    public BigDecimal getPrix(){
        return this.prix;
    }
    public void setPrix(BigDecimal prix){
        this.prix = prix;
    }
    public BigDecimal getQuantite(){
        return this.quantite;
    }
    public void setQuantite(BigDecimal quantite){
        this.quantite = quantite;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "magazin")
    public Magasin getMagazin(){
        return this.magazin;
    }
    public void setMagazin(Magasin magazin){
        this.magazin = magazin;
    }
      @Column(columnDefinition="TEXT")
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SortieStockDetail sortieStockDetail = (SortieStockDetail) o;
        return id != null && id.equals(sortieStockDetail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

