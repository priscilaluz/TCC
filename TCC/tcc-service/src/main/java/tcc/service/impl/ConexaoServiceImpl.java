/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.service.impl;

import tcc.service.persistence.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tcc.common.business.ConexaoService;
import tcc.common.entity.Conexao;
import tcc.service.query.BuscarConexao;

/**
 *
 * @author ADM
 */
@Component("ConexaoServiceImpl")
public class ConexaoServiceImpl implements ConexaoService {

    @Autowired
    private GenericDao dao;
    
    @Override
    @Transactional(readOnly = true)
    public Conexao buscar() {
        try {
            Conexao conexao = (Conexao) dao.uniqueResult(new BuscarConexao.Entities().whereId(1L));
            dao.flush();
            return conexao;
        } catch (Exception e) {
            Conexao c = new Conexao();
            c.setNome(e.getMessage());
            return c;
        }
    }

}
