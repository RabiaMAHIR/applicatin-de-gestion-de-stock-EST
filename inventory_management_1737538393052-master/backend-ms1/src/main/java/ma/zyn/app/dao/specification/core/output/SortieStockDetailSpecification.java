package  ma.zyn.app.dao.specification.core.output;

import ma.zyn.app.dao.criteria.core.output.SortieStockDetailCriteria;
import ma.zyn.app.bean.core.output.SortieStockDetail;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class SortieStockDetailSpecification extends  AbstractSpecification<SortieStockDetailCriteria, SortieStockDetail>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateBigDecimal("prix", criteria.getPrix(), criteria.getPrixMin(), criteria.getPrixMax());
        addPredicateBigDecimal("quantite", criteria.getQuantite(), criteria.getQuantiteMin(), criteria.getQuantiteMax());
        addPredicateFk("sortieStock","id", criteria.getSortieStock()==null?null:criteria.getSortieStock().getId());
        addPredicateFk("sortieStock","id", criteria.getSortieStocks());
        addPredicateFk("sortieStock","code", criteria.getSortieStock()==null?null:criteria.getSortieStock().getCode());
        addPredicateFk("produit","id", criteria.getProduit()==null?null:criteria.getProduit().getId());
        addPredicateFk("produit","id", criteria.getProduits());
        addPredicateFk("produit","code", criteria.getProduit()==null?null:criteria.getProduit().getCode());
        addPredicateFk("magazin","id", criteria.getMagazin()==null?null:criteria.getMagazin().getId());
        addPredicateFk("magazin","id", criteria.getMagazins());
        addPredicateFk("magazin","code", criteria.getMagazin()==null?null:criteria.getMagazin().getCode());
    }

    public SortieStockDetailSpecification(SortieStockDetailCriteria criteria) {
        super(criteria);
    }

    public SortieStockDetailSpecification(SortieStockDetailCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
