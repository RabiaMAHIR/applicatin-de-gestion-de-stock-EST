package ma.zyn.app.bean.core.catalogue;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "produit")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="produit_seq",sequenceName="produit_seq",allocationSize=1, initialValue = 1)
public class Produit  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String label;

    private BigDecimal prixUnitaire = BigDecimal.ZERO;

    private String description;

    private CategorieProduit categorieProduit ;


    public Produit(){
        super();
    }

    public Produit(Long id){
        this.id = id;
    }

    public Produit(Long id,String label){
        this.id = id;
        this.label = label ;
    }
    public Produit(String label){
        this.label = label ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="produit_seq")
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
    public String getLabel(){
        return this.label;
    }
    public void setLabel(String label){
        this.label = label;
    }
    public BigDecimal getPrixUnitaire(){
        return this.prixUnitaire;
    }
    public void setPrixUnitaire(BigDecimal prixUnitaire){
        this.prixUnitaire = prixUnitaire;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categorie_produit")
    public CategorieProduit getCategorieProduit(){
        return this.categorieProduit;
    }
    public void setCategorieProduit(CategorieProduit categorieProduit){
        this.categorieProduit = categorieProduit;
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
        Produit produit = (Produit) o;
        return id != null && id.equals(produit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

