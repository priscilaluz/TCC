/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.service.impl;

import tcc.common.entity.Categoria;
import tcc.service.persistence.GenericDao;
import tcc.service.query.BuscarCategoria;
import tcc.service.validator.CategoriaValidator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tcc.common.business.CategoriaService;

/**
 *
 * @author ADM
 */
@Component("CategoriaServiceImpl")
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private GenericDao dao;

    @Autowired
    private CategoriaValidator validador;
    
    @Override
    @Transactional(readOnly = false)
    public Categoria salvarCategoria(Categoria categoria) {
        List<Categoria> categorias = dao.list(new BuscarCategoria.Entities().whereNome(categoria.getNome()).whereIdNot(categoria.getId()));
        validador.validarSalvarCategoria(categoria, categorias.isEmpty());
        dao.saveOrUpdate(categoria);
        return categoria;
    }

    @Override
    @Transactional(readOnly = false)
    public void excluirCategoria(Long idCategoria) {
        Categoria categoria = buscarCategoriaPorId(idCategoria);
        dao.remove(categoria);
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria buscarCategoriaPorId(Long idCategoria) {
        Categoria categoria = (Categoria) dao.uniqueResult(new BuscarCategoria.Entities()
                .whereId(idCategoria)
                .orderByNome());
        return categoria;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> buscarCategoriaPorFiltro(String parteNome) {
        return dao.list(new BuscarCategoria.Entities().whereNomeLike(parteNome));
    }
}