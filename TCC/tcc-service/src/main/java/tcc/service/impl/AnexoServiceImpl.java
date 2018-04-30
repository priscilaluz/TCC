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
import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tcc.common.business.AnexoService;
import tcc.common.util.ConstantesI18N;

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
    
    @Autowired
    private AnexoFileSystemManager anexoFileSystemManager;

    @Override
    @Transactional(readOnly = false)
    public Anexo salvarAnexo(Anexo anexo) {
        validador.validarSalvarAnexo(anexo);
        dao.saveOrUpdate(anexo);
        try {
            anexoFileSystemManager.setRootPath(ConstantesI18N.rootPath);
            InputStream myInputStream = new ByteArrayInputStream(anexo.getBytes()); 
            anexoFileSystemManager.sendAnexo(anexo.getId(), myInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return anexo;
    }

    @Override
    @Transactional(readOnly = false)
    public void excluirAnexo(Long idAnexo) {
        Anexo anexo = dao.get(Anexo.class, idAnexo);
        dao.remove(anexo);
        try {
            anexoFileSystemManager.setRootPath(ConstantesI18N.rootPath);
            anexoFileSystemManager.excluirAnexo(idAnexo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Anexo buscarAnexoPorId(Long idAnexo) {
        return (Anexo) dao.uniqueResult(new BuscarAnexo.Entities().whereId(idAnexo));
    }

    @Override
    @Transactional(readOnly = true)
    public InputStream obterBytesAnexo(Long idAnexo) {
        InputStream dados;
        try {
            anexoFileSystemManager.setRootPath(ConstantesI18N.rootPath);
            dados = anexoFileSystemManager.getAnexo(idAnexo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dados;
    }
    
    @Override
    public InputStream downloadImgMonaLisa(String imagem){
        return getClass().getResourceAsStream("/icons/".concat(imagem));
    }
}
