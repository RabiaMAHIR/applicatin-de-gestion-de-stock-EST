package  ma.zyn.app.dao.specification.core.catalogue;

import ma.zyn.app.dao.criteria.core.catalogue.ProduitCriteria;
import ma.zyn.app.bean.core.catalogue.Produit;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ProduitSpecification extends  AbstractSpecification<ProduitCriteria, Produit>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("label", criteria.getLabel(),criteria.getLabelLike());
        addPredicateBigDecimal("prixUnitaire", criteria.getPrixUnitaire(), criteria.getPrixUnitaireMin(), criteria.getPrixUnitaireMax());
        addPredicateFk("categorieProduit","id", criteria.getCategorieProduit()==null?null:criteria.getCategorieProduit().getId());
        addPredicateFk("categorieProduit","id", criteria.getCategorieProduits());
        addPredicateFk("categorieProduit","code", criteria.getCategorieProduit()==null?null:criteria.getCategorieProduit().getCode());
    }

    public ProduitSpecification(ProduitCriteria criteria) {
        super(criteria);
    }

    public ProduitSpecification(ProduitCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
