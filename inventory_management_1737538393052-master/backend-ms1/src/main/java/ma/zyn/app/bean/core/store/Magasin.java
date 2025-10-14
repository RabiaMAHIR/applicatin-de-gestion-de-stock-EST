package ma.zyn.app.bean.core.store;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "magasin")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="magasin_seq",sequenceName="magasin_seq",allocationSize=1, initialValue = 1)
public class Magasin  extends BaseEntity     {




    private String adresse;

    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String label;

    private String description;



    public Magasin(){
        super();
    }

    public Magasin(Long id){
        this.id = id;
    }

    public Magasin(Long id,String label){
        this.id = id;
        this.label = label ;
    }
    public Magasin(String label){
        this.label = label ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="magasin_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
      @Column(columnDefinition="TEXT")
    public String getAdresse(){
        return this.adresse;
    }
    public void setAdresse(String adresse){
        this.adresse = adresse;
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
        Magasin magasin = (Magasin) o;
        return id != null && id.equals(magasin.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

