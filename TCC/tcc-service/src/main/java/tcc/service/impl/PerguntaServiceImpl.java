/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.service.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import tcc.common.entity.Anexo;
import tcc.common.entity.Pergunta;
import tcc.common.entity.Resposta;
import tcc.common.enums.NivelPergunta;
import tcc.common.enums.TipoPergunta;
import tcc.common.util.ConstantesI18N;
import tcc.service.persistence.GenericDao;
import tcc.service.query.BuscarEtapaPergunta;
import tcc.service.query.BuscarPergunta;
import tcc.service.query.ExcluirRespostaPorPergunta;
import tcc.service.validator.PerguntaValidator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tcc.common.business.AnexoService;
import tcc.common.business.PerguntaService;

/**
 *
 * @author ADM
 */
@Component("PerguntaServiceImpl")
public class PerguntaServiceImpl implements PerguntaService {

    @Autowired
    private GenericDao dao;
    
    @Autowired
    private AnexoService anexoService;
    
    @Autowired
    private PerguntaValidator validador;

    @Override
    @Transactional(readOnly = false)
    public Pergunta salvarPergunta(Pergunta pergunta) {
        Long qntEtapasPerguntas = 0L;
        if (pergunta.getId() != null) {
            qntEtapasPerguntas = (Long) dao.uniqueResult(new BuscarEtapaPergunta.Count()
                .fetchPergunta("").wherePergunta(pergunta.getId()));
        }
        
        validador.validarSalvarPergunta(pergunta, qntEtapasPerguntas);
        Anexo anexo = pergunta.getAnexo();
        if (anexo != null && anexo.getId() == null) {
            InputStream inputStream = new ByteArrayInputStream(anexo.getBytes());
            anexo.setArquivo(inputStream);
            pergunta.setAnexo(anexoService.salvarAnexo(anexo));
        }
        if (pergunta.getIdAnexoExcluido()!= null) {
            anexoService.excluirAnexo(pergunta.getIdAnexoExcluido());
        }
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

    @Override
    @Transactional(readOnly = false)
    public void excluirPergunta(Long idPergunta) {
        dao.executeDML(new ExcluirRespostaPorPergunta(idPergunta));
        Pergunta pergunta = buscarPerguntaPorId(idPergunta);
        dao.remove(pergunta);
        if (pergunta.getAnexo() != null){
            dao.remove(pergunta.getAnexo());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Pergunta buscarPerguntaPorId(Long idPergunta) {
        Pergunta pergunta = (Pergunta)dao.uniqueResult(new BuscarPergunta.Entities()
                .fetchUsuario(ConstantesI18N.FETCH)
                .fetchAnexo(ConstantesI18N.FETCH)
                .fetchResposta(ConstantesI18N.FETCH)
                .fetchCategoria(ConstantesI18N.FETCH).whereId(idPergunta));
        return pergunta;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pergunta> buscarPerguntaPorFiltro(Long idUsuario, String parteNome, Long idCategoria, 
            TipoPergunta tipo, NivelPergunta nivel, Long idCurso) {
        return dao.list(new BuscarPergunta.Entities()
                .fetchUsuario(ConstantesI18N.FETCH)
                .fetchCategoria(ConstantesI18N.FETCH)
                .whereNivel(nivel)
                .whereTipo(tipo)
                .whereUsuario(idUsuario)
                .whereDescricaoLike(parteNome)
                .whereCategoria(idCategoria)
                .whereCursoNaoTem(idCurso));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Long buscarCountPerguntaPorFiltro(Long idUsuario) {
        return (Long) dao.uniqueResult(new BuscarPergunta.Count().fetchUsuario("").whereUsuario(idUsuario));
    }
}
