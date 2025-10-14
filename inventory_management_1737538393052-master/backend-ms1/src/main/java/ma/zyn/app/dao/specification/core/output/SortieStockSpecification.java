package  ma.zyn.app.dao.specification.core.output;

import ma.zyn.app.dao.criteria.core.output.SortieStockCriteria;
import ma.zyn.app.bean.core.output.SortieStock;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class SortieStockSpecification extends  AbstractSpecification<SortieStockCriteria, SortieStock>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("dateSortie", criteria.getDateSortie(), criteria.getDateSortieFrom(), criteria.getDateSortieTo());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
    }

    public SortieStockSpecification(SortieStockCriteria criteria) {
        super(criteria);
    }

    public SortieStockSpecification(SortieStockCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
