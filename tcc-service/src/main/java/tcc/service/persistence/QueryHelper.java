/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.service.persistence;

import tcc.common.support.AssertUtils;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.Type;

/**
 *
 * @author 
 */
public final class QueryHelper {
    private Session session;
    private BusinessQuery businessTextQuery;
    private Query query;
        
    public static Query createHibernateQuery(Session session, BusinessQuery businessTextQuery) {
        return new QueryHelper(session, businessTextQuery)
                            .createQueryInstance()
                            .setParameters()
                            .setProjection()
                            .setPagination()
                            .build();
    }

    public static <T extends Serializable> List<T>
            list(BusinessQuery<T> businessTextQuery, Session session) {
        return createHibernateQuery(session, businessTextQuery).list();
    }

    public static <T extends Serializable> Iterator<T>
            iterate(BusinessQuery<T> businessTextQuery, Session session) {
        return createHibernateQuery(session, businessTextQuery).iterate();
    }

    public static <T extends Serializable> T
            uniqueResult(BusinessQuery<T> businessTextQuery, Session session) {
        return (T) createHibernateQuery(session, businessTextQuery).uniqueResult();
    }

    public static int executeDML(BusinessQuery businessTextQuery, Session session) {
        return createHibernateQuery(session, businessTextQuery).executeUpdate();
    }

    private QueryHelper(Session session, BusinessQuery businessTextQuery) {
        this.session = session;
        this.businessTextQuery = businessTextQuery;
    }
    
    private QueryHelper createQueryInstance() {
        if (businessTextQuery.isNativeQuery()) {
            query = session.createSQLQuery(businessTextQuery.getText());
        } else {
            query = session.createQuery(businessTextQuery.getText());
        }
        query.setComment(businessTextQuery.getClass().getSimpleName());
        return this;
    }
    
    private QueryHelper setParameters() {
        List parameterList = businessTextQuery.getParameterList();
        if (!parameterList.isEmpty()) {
            for (int i = 0; i < parameterList.size(); i++) {
                query.setParameter(i, parameterList.get(i));
            }
        }
        Map<String, Object> parameterMap = businessTextQuery.getParameterMap();
        if (!parameterMap.isEmpty()) {
            for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
                if (entry.getValue() instanceof Collection) {
                    query.setParameterList(entry.getKey(), (Collection) entry.getValue());
                } else {
                    query.setParameter(entry.getKey(), entry.getValue());
                }
            }
        }
        return this;
    }

    private QueryHelper setProjection() {
        Map<String, Type> typeMappings = businessTextQuery.getProjectionTypeMappings();
        if (typeMappings!=null) {
            AssertUtils.assertExpression(businessTextQuery.isNativeQuery(), "ProjectionTypeMappings só pode ser usado apenas em queries nativas");

            for (Map.Entry<String, Type> entry : typeMappings.entrySet()) {
                ((SQLQuery)query).addScalar(entry.getKey(), entry.getValue());
            }
        }

        if (businessTextQuery.getProjectionValueObjectClass() != null) {
            query.setResultTransformer( Transformers.aliasToBean(businessTextQuery.getProjectionValueObjectClass()) );
        }
        return this;
    }

    private QueryHelper setPagination() {
        if (businessTextQuery.getPagination() != null) {
            query.setMaxResults(businessTextQuery.getPagination().getPageSize());
            query.setFirstResult(businessTextQuery.getPagination().getFirstItemIndex());
        }
        return this;
    }
    
    private Query build() {
        return query;
    }
}
