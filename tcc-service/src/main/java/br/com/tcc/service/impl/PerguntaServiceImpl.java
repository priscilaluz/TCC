/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.service.impl;

import br.com.tcc.common.entity.Anexo;
import br.com.tcc.common.entity.Pergunta;
import br.com.tcc.common.entity.Resposta;
import br.com.tcc.common.enums.Categoria;
import br.com.tcc.common.enums.NivelPergunta;
import br.com.tcc.common.enums.TipoPergunta;
import br.com.tcc.common.util.ConstantesI18N;
import br.com.tcc.service.persistence.GenericDao;
import br.com.tcc.service.query.BuscarPergunta;
import br.com.tcc.service.query.ExcluirRespostaPorPergunta;
import br.com.tcc.service.validator.PerguntaValidator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADM
 */
@Component("PerguntaServiceImpl")
public class PerguntaServiceImpl {

    @Autowired
    private GenericDao dao;
    
    @Autowired
    private AnexoServiceImpl anexoService;
    
    @Autowired
    private PerguntaValidator validador;

    @Transactional(readOnly = false)
    public Pergunta salvarPergunta(Pergunta pergunta) {
        Anexo anexo = pergunta.getAnexo();
        if (anexo != null) {
            pergunta.setAnexo(anexoService.salvarAnexo(anexo));
        }
        validador.validarSalvarPergunta(pergunta);
        if (pergunta.getId() != null){
            dao.executeDML(new ExcluirRespostaPorPergunta(pergunta.getId()));
        }
        dao.saveOrUpdate(pergunta);
        for (Resposta resposta : pergunta.getRespostas()) {
            resposta.setId(null);
            resposta.setPergunta(pergunta);
            dao.saveOrUpdate(resposta);
        }
        return pergunta;
    }

    @Transactional(readOnly = false)
    public void excluirPergunta(Long idPergunta) {
        dao.executeDML(new ExcluirRespostaPorPergunta(idPergunta));
        Pergunta pergunta = dao.get(Pergunta.class, idPergunta);
        dao.remove(pergunta);
    }

    @Transactional(readOnly = true)
    public Pergunta buscarPerguntaPorId(Long idPergunta) {
        return (Pergunta)dao.uniqueResult(new BuscarPergunta.Entities()
                .fetchResposta(ConstantesI18N.FETCH).whereId(idPergunta));
    }

    @Transactional(readOnly = true)
    public List<Pergunta> buscarPerguntaPorFiltro(Long idUsuario, String parteNome, Categoria categoria, TipoPergunta tipo, NivelPergunta nivel) {
        return dao.list(new BuscarPergunta.Entities()
                .whereNivel(nivel)
                .whereTipo(tipo)
                .whereUsuario(idUsuario)
                .whereDescricaoLike(parteNome)
                .whereCategoria(categoria));
    }
}
