/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.service.impl;

import br.com.tcc.common.entity.Categoria;
import br.com.tcc.common.util.ConstantesI18N;
import br.com.tcc.service.persistence.GenericDao;
import br.com.tcc.service.query.BuscarCategoria;
import br.com.tcc.service.validator.CategoriaValidator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADM
 */
@Component("CategoriaServiceImpl")
public class CategoriaServiceImpl {

    @Autowired
    private GenericDao dao;

    @Autowired
    private CategoriaValidator validador;
    
    @Transactional(readOnly = false)
    public Categoria salvarCategoria(Categoria categoria) {
        List<Categoria> categorias = dao.list(new BuscarCategoria.Entities().whereNome(categoria.getNome()).whereIdNot(categoria.getId()));
        validador.validarSalvarCategoria(categoria, categorias.isEmpty());
        dao.saveOrUpdate(categoria);
        return categoria;
    }

    @Transactional(readOnly = false)
    public void excluirCategoria(Long idCategoria) {
        Categoria categoria = buscarCategoriaPorId(idCategoria);
        dao.remove(categoria);
    }

    @Transactional(readOnly = true)
    public Categoria buscarCategoriaPorId(Long idCategoria) {
        Categoria categoria = (Categoria) dao.uniqueResult(new BuscarCategoria.Entities()
                .whereId(idCategoria)
                .orderByNome());
        return categoria;
    }

    @Transactional(readOnly = true)
    public List<Categoria> buscarCategoriaPorFiltro(String parteNome) {
        return dao.list(new BuscarCategoria.Entities().whereNomeLike(parteNome));
    }
}
