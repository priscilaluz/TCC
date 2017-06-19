/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.service.persistence;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author UP21
 *
 */
public abstract class HibernateSupport {
    
    @Resource
    private SessionFactory factory;

    /**
     *
     */
    public HibernateSupport() {}

    /**
     * <p>Obtém a sessão do Hibernate.</p>
     * <p>Este método deve ser usado sempre que for necessária uma sessão do Hibernate. Ele obtém
     * a sessão corrente (se houver), possibilitando o uso correto dentro de transações.</p>
     *
     * @return A sessão corrente do Hibernate
     */
    protected Session getCurrentSession() {
        return factory.getCurrentSession();
    }

    /**
     * Setter
     * @param factory the factory to set
     */
    public void setSessionFactory(SessionFactory factory) {
        this.factory = factory;
    }

}
