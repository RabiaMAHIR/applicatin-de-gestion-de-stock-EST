package ma.zyn.app.bean.core.store;






import ma.zyn.app.bean.core.catalogue.Produit;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "stock")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="stock_seq",sequenceName="stock_seq",allocationSize=1, initialValue = 1)
public class Stock  extends BaseEntity     {




    private BigDecimal quantite = BigDecimal.ZERO;

    private BigDecimal quantiteDeffecteuse = BigDecimal.ZERO;

    private Produit produit ;
    private Magasin magazin ;


    public Stock(){
        super();
    }

    public Stock(Long id){
        this.id = id;
    }

    public Stock(Magasin magazin, Produit produit, BigDecimal quantite){
        this.magazin= magazin;
        this.produit= produit;
        this.quantite=quantite;
    }



    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="stock_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produit")
    public Produit getProduit(){
        return this.produit;
    }
    public void setProduit(Produit produit){
        this.produit = produit;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "magazin")
    public Magasin getMagazin(){
        return this.magazin;
    }
    public void setMagazin(Magasin magazin){
        this.magazin = magazin;
    }
    public BigDecimal getQuantite(){
        return this.quantite;
    }
    public void setQuantite(BigDecimal quantite){
        this.quantite = quantite;
    }
    public BigDecimal getQuantiteDeffecteuse(){
        return this.quantiteDeffecteuse;
    }
    public void setQuantiteDeffecteuse(BigDecimal quantiteDeffecteuse){
        this.quantiteDeffecteuse = quantiteDeffecteuse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return id != null && id.equals(stock.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

