package  ma.zyn.app.dao.specification.core.input;

import ma.zyn.app.dao.criteria.core.input.EntreeStockDetailCriteria;
import ma.zyn.app.bean.core.input.EntreeStockDetail;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class EntreeStockDetailSpecification extends  AbstractSpecification<EntreeStockDetailCriteria, EntreeStockDetail>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateBigDecimal("prix", criteria.getPrix(), criteria.getPrixMin(), criteria.getPrixMax());
        addPredicateBigDecimal("quantite", criteria.getQuantite(), criteria.getQuantiteMin(), criteria.getQuantiteMax());
        addPredicateFk("entreeStock","id", criteria.getEntreeStock()==null?null:criteria.getEntreeStock().getId());
        addPredicateFk("entreeStock","id", criteria.getEntreeStocks());
        addPredicateFk("entreeStock","code", criteria.getEntreeStock()==null?null:criteria.getEntreeStock().getCode());
        addPredicateFk("produit","id", criteria.getProduit()==null?null:criteria.getProduit().getId());
        addPredicateFk("produit","id", criteria.getProduits());
        addPredicateFk("produit","code", criteria.getProduit()==null?null:criteria.getProduit().getCode());
        addPredicateFk("magazin","id", criteria.getMagazin()==null?null:criteria.getMagazin().getId());
        addPredicateFk("magazin","id", criteria.getMagazins());
        addPredicateFk("magazin","code", criteria.getMagazin()==null?null:criteria.getMagazin().getCode());
    }

    public EntreeStockDetailSpecification(EntreeStockDetailCriteria criteria) {
        super(criteria);
    }

    public EntreeStockDetailSpecification(EntreeStockDetailCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
