/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.service.persistence;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * Define o fluxo padrão de uma transação de banco de dados utilizando por
 * base o transactionManager do Spring Framework.
 * 
 * @author x4rc
 */
public class SpringBasedTransactionHandler extends TransactionAspectSupport implements TransactionHandler {

    public SpringBasedTransactionHandler(PlatformTransactionManager transactionManager) {
        this.setTransactionManager(transactionManager);
    }

    @Override
    public void afterPropertiesSet() {
        // Não faz nada propositalmente ...
    }

    @Override
    @SuppressWarnings({"PMD.AvoidCatchingThrowable","PMD.AvoidInstanceofChecksInCatchClause"})
    public Object handle(TransactionCallback callback) {
        TransactionInfo txInfo = createTransactionIfNecessary(getTransactionManager(), new DefaultTransactionAttribute(), "GenericDao.runInsideCustomTransaction");
        Object retVal = null;
        try {
            retVal = callback.doInsideTransaction();
        } catch (Throwable ex) {
            completeTransactionAfterThrowing(txInfo, ex);

            if (ex instanceof RuntimeException) {
                throw (RuntimeException) ex;
            } else {
                throw new RuntimeException(ex);
            }
        } finally {
            cleanupTransactionInfo(txInfo);
        }
        commitTransactionAfterReturning(txInfo);
        return retVal;
    }
}
