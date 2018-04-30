package tcc.service.persistence;

import tcc.common.support.IEntity;
import java.io.Serializable;
import java.util.List;

/**
 *
 */
public class SimpleTestDao {
    
    private GenericDao dao;

    public SimpleTestDao(GenericDao dao) {
        this.dao = dao;
    }
    
    public <T extends Serializable> List<T> query(final String hql, final Object... params) {
        return (List<T>) dao.runInsideCustomTransaction(new TransactionCallback() {
            @Override public Object doInsideTransaction() {
                return dao.list(new InnerQuery<T>(hql, params));
            }
        });
    }
    
    public <T extends Serializable> List<T> executeBusinnesQuery(final BusinessQuery<T> businessQuery) {
         return (List<T>) dao.runInsideCustomTransaction(new TransactionCallback() {
            @Override public Object doInsideTransaction() {
                return dao.list(businessQuery);
            }
        });
    }
    
    public <T extends IEntity<? extends Serializable>> T getById(final Class<T> clazz, final Serializable id) {
        return (T) dao.runInsideCustomTransaction(new TransactionCallback() {
            @Override public Object doInsideTransaction() {
                return dao.get(clazz, id);
            }
        });
    }
    
    public <T extends IEntity<? extends Serializable>> void saveOrUpdate(final T entity) {
        dao.runInsideCustomTransaction(new TransactionCallback() {
            @Override public Object doInsideTransaction() {
                return dao.saveOrUpdate(entity);
            }
        });
    }
    
    private class InnerQuery<T extends Serializable> extends BusinessQuery<T> {
        public InnerQuery(String hql, Object... params) {
           appendText(hql);
           setParameters(params);
        }
    }
    
    public int executeDML(final BusinessQuery businessQuery) {
         return (Integer) dao.runInsideCustomTransaction(new TransactionCallback() {
            @Override public Object doInsideTransaction() {
                return dao.executeDML(businessQuery);
            }
        });
        
    }

}
