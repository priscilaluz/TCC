/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.service.impl;

import tcc.common.entity.Anexo;
import tcc.service.persistence.GenericDao;
import tcc.service.query.BuscarAnexo;
import tcc.service.validator.AnexoValidator;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tcc.common.business.AnexoService;

/**
 *
 * @author ADM
 */
@Component("AnexoServiceImpl")
public class AnexoServiceImpl implements AnexoService {

    @Autowired
    private GenericDao dao;

    @Autowired
    private AnexoValidator validador;

    @Override
    @Transactional(readOnly = false)
    public Anexo salvarAnexo(Anexo anexo) {
        validador.validarSalvarAnexo(anexo);
        dao.saveOrUpdate(anexo);
        return anexo;
    }

    @Override
    @Transactional(readOnly = false)
    public void excluirAnexo(Long idAnexo) {
        Anexo anexo = dao.get(Anexo.class, idAnexo);
        dao.remove(anexo);
    }

    @Override
    @Transactional(readOnly = true)
    public Anexo buscarAnexoPorId(Long idAnexo) {
        return (Anexo) dao.uniqueResult(new BuscarAnexo.Entities().whereId(idAnexo));
    }

    @Override
    @Transactional(readOnly = true)
    public InputStream obterBytesAnexo(Long idAnexo) {
        Anexo anexo = (Anexo) dao.uniqueResult(new BuscarAnexo.Entities().whereId(idAnexo));
        return new ByteArrayInputStream(anexo.getBytes());
    }
    
    @Override
    public InputStream downloadImgMonaLisa(String imagem){
        return getClass().getResourceAsStream("/icons/".concat(imagem));
    }
}
