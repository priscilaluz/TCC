package tcc.service.persistence;

import java.io.Serializable;

/**
 *
 */
public class BusinessFluentQuery<T extends Serializable, Q extends BusinessFluentQuery> extends BusinessQuery<T> {
    private String initFilter = " where ";
    private String initOrder = " order by ";

    public Q maxResults(int maxResults) {
        setPagination(new Pagination(maxResults));
        return (Q) this;
    }

    public Q paging(Integer pagina, Integer tamanho, Integer totalItens) {
        if (pagina!=null && tamanho!=null) {
            setPagination(new Pagination(tamanho, pagina, totalItens));
        }
        return (Q) this;
    }
    
    protected String getPreffixFilter() {
        String preffixFilter = initFilter;
        initFilter = " and ";
        return preffixFilter;
    }
    
    protected String getPreffixOrder() {
        String preffixOrder = initOrder;
        initOrder = " , ";
        return preffixOrder;
    }
}
