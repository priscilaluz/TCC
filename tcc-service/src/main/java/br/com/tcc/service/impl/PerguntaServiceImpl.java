/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.service.impl;

import br.com.tcc.common.entity.Anexo;
import br.com.tcc.common.entity.Pergunta;
import br.com.tcc.common.entity.Resposta;
import br.com.tcc.common.enums.NivelPergunta;
import br.com.tcc.common.enums.TipoPergunta;
import br.com.tcc.common.util.ConstantesI18N;
import br.com.tcc.service.persistence.GenericDao;
import br.com.tcc.service.query.BuscarEtapaPergunta;
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
        Long qntEtapasPerguntas = 0L;
        if (pergunta.getId() != null) {
            qntEtapasPerguntas = (Long) dao.uniqueResult(new BuscarEtapaPergunta.Count()
                .fetchPergunta("").wherePergunta(pergunta.getId()));
        }
        
        Anexo anexo = pergunta.getAnexo();
        if (anexo != null) {
            anexo.setId(pergunta.getIdAnexo());
            pergunta.setAnexo(anexoService.salvarAnexo(anexo));
        } else if (pergunta.getIdAnexo() != null) {
            Anexo anexoRemover = dao.get(Anexo.class, pergunta.getIdAnexo());
            dao.remove(anexoRemover);
        }
        validador.validarSalvarPergunta(pergunta, qntEtapasPerguntas);
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
        Pergunta pergunta = buscarPerguntaPorId(idPergunta);
        dao.remove(pergunta);
        if (pergunta.getAnexo() != null){
            dao.remove(pergunta.getAnexo());
        }
    }

    @Transactional(readOnly = true)
    public Pergunta buscarPerguntaPorId(Long idPergunta) {
        Pergunta pergunta = (Pergunta)dao.uniqueResult(new BuscarPergunta.Entities()
                .fetchUsuario(ConstantesI18N.FETCH)
                .fetchAnexo(ConstantesI18N.FETCH)
                .fetchResposta(ConstantesI18N.FETCH)
                .fetchCategoria(ConstantesI18N.FETCH).whereId(idPergunta));
        pergunta.setIdAnexo(pergunta.getAnexo()!=null?pergunta.getAnexo().getId():null);
        return pergunta;
    }

    @Transactional(readOnly = true)
    public List<Pergunta> buscarPerguntaPorFiltro(Long idUsuario, String parteNome, Long idCategoria, TipoPergunta tipo, NivelPergunta nivel) {
        return dao.list(new BuscarPergunta.Entities()
                .fetchUsuario(ConstantesI18N.FETCH)
                .fetchCategoria(ConstantesI18N.FETCH)
                .whereNivel(nivel)
                .whereTipo(tipo)
                .whereUsuario(idUsuario)
                .whereDescricaoLike(parteNome)
                .whereCategoria(idCategoria));
    }
    
    @Transactional(readOnly = true)
    public Long buscarCountPerguntaPorFiltro(Long idUsuario) {
        return (Long) dao.uniqueResult(new BuscarPergunta.Count().fetchUsuario("").whereUsuario(idUsuario));
    }
}
