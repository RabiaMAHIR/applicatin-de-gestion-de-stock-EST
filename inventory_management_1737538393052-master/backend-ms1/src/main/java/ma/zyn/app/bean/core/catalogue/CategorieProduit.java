package ma.zyn.app.bean.core.catalogue;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categorie_produit")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="categorie_produit_seq",sequenceName="categorie_produit_seq",allocationSize=1, initialValue = 1)
public class CategorieProduit  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String label;

    private String description;



    public CategorieProduit(){
        super();
    }

    public CategorieProduit(Long id){
        this.id = id;
    }

    public CategorieProduit(Long id,String label){
        this.id = id;
        this.label = label ;
    }
    public CategorieProduit(String label){
        this.label = label ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="categorie_produit_seq")
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
        CategorieProduit categorieProduit = (CategorieProduit) o;
        return id != null && id.equals(categorieProduit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

