package  ma.zyn.app.dao.specification.core.agent;

import ma.zyn.app.dao.criteria.core.agent.FournisseurCriteria;
import ma.zyn.app.bean.core.agent.Fournisseur;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class FournisseurSpecification extends  AbstractSpecification<FournisseurCriteria, Fournisseur>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("label", criteria.getLabel(),criteria.getLabelLike());
        addPredicate("adresse", criteria.getAdresse(),criteria.getAdresseLike());
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
    }

    public FournisseurSpecification(FournisseurCriteria criteria) {
        super(criteria);
    }

    public FournisseurSpecification(FournisseurCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
