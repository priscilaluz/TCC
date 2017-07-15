/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.service.impl;

import br.com.tcc.common.entity.Anexo;
import br.com.tcc.service.persistence.GenericDao;
import br.com.tcc.service.query.BuscarAnexo;
import br.com.tcc.service.validator.AnexoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADM
 */
@Component("AnexoServiceImpl")
public class AnexoServiceImpl {

    @Autowired
    private GenericDao dao;

    @Autowired
    private AnexoValidator validador;

    @Transactional(readOnly = false)
    public Anexo salvarAnexo(Anexo anexo) {
        validador.validarSalvarAnexo(anexo);
        dao.saveOrUpdate(anexo);
        return anexo;
    }

    @Transactional(readOnly = false)
    public void excluirAnexo(Long idAnexo) {
        Anexo anexo = dao.get(Anexo.class, idAnexo);
        dao.remove(anexo);
    }

    @Transactional(readOnly = true)
    public Anexo buscarAnexoPorId(Long idAnexo) {
        Anexo anexo = (Anexo) dao.uniqueResult(new BuscarAnexo.Entities().whereId(idAnexo));
        return anexo;
    }
}
