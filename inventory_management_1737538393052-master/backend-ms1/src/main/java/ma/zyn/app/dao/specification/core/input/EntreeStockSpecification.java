package  ma.zyn.app.dao.specification.core.input;

import ma.zyn.app.dao.criteria.core.input.EntreeStockCriteria;
import ma.zyn.app.bean.core.input.EntreeStock;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class EntreeStockSpecification extends  AbstractSpecification<EntreeStockCriteria, EntreeStock>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("dateEntree", criteria.getDateEntree(), criteria.getDateEntreeFrom(), criteria.getDateEntreeTo());
        addPredicateFk("fournisseur","id", criteria.getFournisseur()==null?null:criteria.getFournisseur().getId());
        addPredicateFk("fournisseur","id", criteria.getFournisseurs());
        addPredicateFk("fournisseur","code", criteria.getFournisseur()==null?null:criteria.getFournisseur().getCode());
    }

    public EntreeStockSpecification(EntreeStockCriteria criteria) {
        super(criteria);
    }

    public EntreeStockSpecification(EntreeStockCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
