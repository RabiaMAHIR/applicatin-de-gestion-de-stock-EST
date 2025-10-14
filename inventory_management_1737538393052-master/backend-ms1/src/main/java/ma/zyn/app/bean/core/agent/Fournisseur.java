package ma.zyn.app.bean.core.agent;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fournisseur")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="fournisseur_seq",sequenceName="fournisseur_seq",allocationSize=1, initialValue = 1)
public class Fournisseur  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String label;

    @Column(length = 500)
    private String adresse;

    @Column(length = 500)
    private String email;

    private String description;



    public Fournisseur(){
        super();
    }

    public Fournisseur(Long id){
        this.id = id;
    }

    public Fournisseur(Long id,String label){
        this.id = id;
        this.label = label ;
    }
    public Fournisseur(String label){
        this.label = label ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="fournisseur_seq")
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
    public String getAdresse(){
        return this.adresse;
    }
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
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
        Fournisseur fournisseur = (Fournisseur) o;
        return id != null && id.equals(fournisseur.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

