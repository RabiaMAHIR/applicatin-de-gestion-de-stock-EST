package  ma.zyn.app.dao.specification.core.store;

import ma.zyn.app.dao.criteria.core.store.StockCriteria;
import ma.zyn.app.bean.core.store.Stock;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class StockSpecification extends  AbstractSpecification<StockCriteria, Stock>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateBigDecimal("quantite", criteria.getQuantite(), criteria.getQuantiteMin(), criteria.getQuantiteMax());
        addPredicateBigDecimal("quantiteDeffecteuse", criteria.getQuantiteDeffecteuse(), criteria.getQuantiteDeffecteuseMin(), criteria.getQuantiteDeffecteuseMax());
        addPredicateFk("produit","id", criteria.getProduit()==null?null:criteria.getProduit().getId());
        addPredicateFk("produit","id", criteria.getProduits());
        addPredicateFk("produit","code", criteria.getProduit()==null?null:criteria.getProduit().getCode());
        addPredicateFk("magazin","id", criteria.getMagazin()==null?null:criteria.getMagazin().getId());
        addPredicateFk("magazin","id", criteria.getMagazins());
        addPredicateFk("magazin","code", criteria.getMagazin()==null?null:criteria.getMagazin().getCode());
    }

    public StockSpecification(StockCriteria criteria) {
        super(criteria);
    }

    public StockSpecification(StockCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
