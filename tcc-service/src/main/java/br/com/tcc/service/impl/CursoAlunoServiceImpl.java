/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.service.impl;

import br.com.tcc.common.entity.Curso;
import br.com.tcc.common.entity.CursoAluno;
import br.com.tcc.common.entity.Etapa;
import br.com.tcc.common.entity.EtapaAluno;
import br.com.tcc.common.entity.PerguntaEtapaAluno;
import br.com.tcc.common.entity.RelatorioEtapa;
import br.com.tcc.common.entity.Usuario;
import br.com.tcc.common.enums.SituacaoCursoAluno;
import br.com.tcc.common.util.ConstantesI18N;
import br.com.tcc.common.vo.TabuleiroCurso;
import br.com.tcc.common.vo.TabuleiroEtapa;
import br.com.tcc.common.vo.TdHtmlEtapa;
import br.com.tcc.service.persistence.GenericDao;
import br.com.tcc.service.query.BuscarCursoAluno;
import br.com.tcc.service.query.BuscarEtapaAluno;
import br.com.tcc.service.validator.CursoAlunoValidator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADM
 */
@Component("CursoAlunoServiceImpl")
public class CursoAlunoServiceImpl {

    @Autowired
    private GenericDao dao;

    @Autowired
    private CursoAlunoValidator validador;
    
    @Autowired
    private CursoServiceImpl cursoService;
    
    @Transactional(readOnly = false)
    public CursoAluno entrarCurso(Long idCurso, Long idAluno, String codAcesso) {
        CursoAluno cursoAlunoJaSalvo = (CursoAluno) dao.uniqueResult(new BuscarCursoAluno.Entities()
                .fetchAluno(ConstantesI18N.FETCH).fetchCurso(ConstantesI18N.FETCH)
                .whereIdAluno(idAluno).whereIdCurso(idCurso));
        Curso curso = dao.get(Curso.class, idCurso);
        validador.validarAlunoEntrarCurso(curso, codAcesso, cursoAlunoJaSalvo);
        CursoAluno cursoAluno = new CursoAluno();
        cursoAluno.setAluno(new Usuario(idAluno));
        cursoAluno.setCurso(new Curso(idCurso));
        cursoAluno.setSituacao(SituacaoCursoAluno.EM_ANDAMENTO);
        cursoAluno.setPontuacao(0);
        cursoAluno.setPosicaoAtual(1);
        dao.saveOrUpdate(cursoAluno);
        return cursoAluno;
    }
    
    @Transactional(readOnly = true)
    public List<CursoAluno> buscarCursoAlunoPorAlunoSituacao(Long idAluno, SituacaoCursoAluno situacao) {
        List<CursoAluno> cursosAlunos = dao.list(new BuscarCursoAluno.Entities()
                .fetchAluno(ConstantesI18N.FETCH).fetchCurso(ConstantesI18N.FETCH)
                .fetchEtapas(ConstantesI18N.FETCH)
                .whereIdAluno(idAluno).whereSituacaoCursoAluno(situacao));
        Set<CursoAluno> list = new HashSet<>(cursosAlunos);
        return new ArrayList<>(list);
    }
    
    @Transactional(readOnly = true)
    public TabuleiroCurso buscarCursoAlunoPorIdCursoAluno(Long idCursoAluno) {
        CursoAluno cursoAluno = (CursoAluno) dao.uniqueResult(new BuscarCursoAluno.Entities()
                .fetchAluno(ConstantesI18N.FETCH).fetchCurso(ConstantesI18N.FETCH)
                .fetchEtapas(ConstantesI18N.FETCH)
                .whereIdCursoAluno(idCursoAluno));
        TabuleiroCurso tabuleiroCurso = new TabuleiroCurso();
        tabuleiroCurso.setNome(cursoAluno.getCurso().getNome());
        tabuleiroCurso.setAssuntoGeral(cursoAluno.getCurso().getAssuntoGeral());
        tabuleiroCurso.setAnexo(cursoAluno.getCurso().getAnexo());
        tabuleiroCurso.setIdCurso(cursoAluno.getCurso().getId());
        tabuleiroCurso.setPontuacao(cursoAluno.getPontuacao());
        tabuleiroCurso.setAluno(cursoAluno.getAluno());
        tabuleiroCurso.setEtapaAtual(cursoAluno.getPosicaoAtual());
        
        List<TabuleiroEtapa> etapas = etapasPorCursoAluno(cursoAluno);
        List<TdHtmlEtapa> todasEtapas = tdEtapasPorTabuleiroEtapa(etapas);
        tabuleiroCurso.setTabuleiros(mapearTabuleiros(todasEtapas));
        
        return tabuleiroCurso;
    }
    
    private List<TabuleiroEtapa> etapasPorCursoAluno(CursoAluno cursoAluno) {
        List<TabuleiroEtapa> etapas = new ArrayList<>();
        Integer auxCont = 0;
        Integer cont = 0;
        for (Etapa etapa : cursoAluno.getCurso().getEtapasLista()) {
            cont++;
            if (auxCont > 0 && auxCont < 3) {
                cont = 2;
                auxCont++;
            }
            if (cont == 15 && cursoAluno.getCurso().getEtapasLista().size() > 15) {
                cont = 2;
                auxCont = 1;
            }
            TabuleiroEtapa e = new TabuleiroEtapa();
            e.setIdEtapa(etapa.getId());
            e.setImagemDesabilitado(ConstantesI18N.TABULEIRO_IMG_DESABILITADO.replace("*", cont.toString()));
            e.setImagemOff(ConstantesI18N.TABULEIRO_IMG_OFF.replace("*", cont.toString()));
            e.setImagemOn(ConstantesI18N.TABULEIRO_IMG_ON.replace("*", cont.toString()));
            e.setDesbloquada(etapa.getNivel() <= cursoAluno.getPosicaoAtual());
            etapas.add(e);
        }
        String ultimaImagem = "15";
        if (cont == 1) {
            ultimaImagem = "19";
        } else if (cont >= 7 && cont <= 10) {
            ultimaImagem = "16";
        } else if (cont == 6) {
            ultimaImagem = "17";
        } else if (cont == 11) {
            ultimaImagem = "18";
        } else if ((cont >= 2 && cont <= 5) || (cont >= 12 && cont <= 14)) {
            ultimaImagem = "15";
        }
        etapas.get(etapas.size()-1).setImagemDesabilitado(ConstantesI18N.TABULEIRO_IMG_DESABILITADO.replace("*", ultimaImagem));
        etapas.get(etapas.size()-1).setImagemOff(ConstantesI18N.TABULEIRO_IMG_OFF.replace("*", ultimaImagem));
        etapas.get(etapas.size()-1).setImagemOn(ConstantesI18N.TABULEIRO_IMG_ON.replace("*", ultimaImagem));
        return etapas;
    }
    
    private List<TdHtmlEtapa> tdEtapasPorTabuleiroEtapa(List<TabuleiroEtapa> etapas) {
        int qntTd = etapas.size()/5;
        qntTd = (qntTd * 5 >= etapas.size()) && qntTd > 0 ? qntTd : qntTd + 1;
        int linhaTabuleiro = 0;
        int posicao = 0;
        boolean direto = true;
        List<TdHtmlEtapa> tdHtmlEtapas = new ArrayList<>();
        for (int i = 0; i < qntTd; i++) {
            TdHtmlEtapa tdHtmlEtapa = new TdHtmlEtapa();
            if (direto) {
                tdHtmlEtapa.setEtapa1(etapas, posicao++);
                tdHtmlEtapa.setEtapa2(etapas, posicao++);
                tdHtmlEtapa.setEtapa3(etapas, posicao++);
                tdHtmlEtapa.setEtapa4(etapas, posicao++);
                tdHtmlEtapa.setEtapa5(etapas, posicao++);
            } else {
                tdHtmlEtapa.setEtapa5(etapas, posicao++);
                tdHtmlEtapa.setEtapa4(etapas, posicao++);
                tdHtmlEtapa.setEtapa3(etapas, posicao++);
                tdHtmlEtapa.setEtapa2(etapas, posicao++);
                tdHtmlEtapa.setEtapa1(etapas, posicao++);
            }
            direto = !direto;
            linhaTabuleiro++;
            if (linhaTabuleiro == 3) {
                direto = true;
                linhaTabuleiro = 0;
            }
            tdHtmlEtapas.add(tdHtmlEtapa);
        }
        return tdHtmlEtapas;
    }
    
    private Map<Integer,List<TdHtmlEtapa>> mapearTabuleiros(List<TdHtmlEtapa> todasEtapas) {
        Map<Integer,List<TdHtmlEtapa>> tabuleiros = new HashMap<>();
        int numTabuleiro = 1;
        int qntItens = 0;
        int quantidade = 0;
        List<TdHtmlEtapa> tabuleiro = new ArrayList<>();
        for (TdHtmlEtapa linhaColuna : todasEtapas) {
            tabuleiro.add(linhaColuna);
            qntItens++;
            quantidade++;
            if (qntItens == 3 || quantidade == todasEtapas.size()) {
                tabuleiros.put(numTabuleiro, tabuleiro);
                tabuleiro = new ArrayList<>();
                numTabuleiro++;
                qntItens = 0;
            }
        }
        return tabuleiros;
    }
    
    @Transactional(readOnly = true)
    public EtapaAluno buscarEtapaAlunoPorCursoAlunoEEtapa(Long idCursoAluno, Long idEtapa) {
        EtapaAluno etapaAluno = (EtapaAluno) dao.uniqueResult(new BuscarEtapaAluno.Entities()
                .fetchCursoAluno(ConstantesI18N.FETCH).fetchEtapa(ConstantesI18N.FETCH)
                .whereIdCursoAluno(idCursoAluno).whereIdEtapa(idEtapa));
        if (etapaAluno == null) {
            etapaAluno = new EtapaAluno();
            etapaAluno.setCursoAluno(dao.get(CursoAluno.class, idCursoAluno));
            etapaAluno.setEtapa(cursoService.buscarEtapaPorId(idEtapa, false));
            etapaAluno.setPontuacao(0);
        }
        return etapaAluno;
    }
    
    @Transactional(readOnly = false)
    public EtapaAluno salvarEtapaAluno(Long idCursoAluno, Long idEtapa) {
        EtapaAluno etapaAluno = buscarEtapaAlunoPorCursoAlunoEEtapa(idCursoAluno, idEtapa);
        return dao.saveOrUpdate(etapaAluno);
    }
    
    @Transactional(readOnly = false)
    public RelatorioEtapa salvarRelatorioEtapa(RelatorioEtapa relatorioEtapa) {
        validador.validarRelatorioEtapa(relatorioEtapa);
        dao.saveOrUpdate(relatorioEtapa);
        for (PerguntaEtapaAluno perguntaEtapaAluno : relatorioEtapa.getPerguntasEtapasAlunos()) {
            perguntaEtapaAluno.setRelatorioEtapa(relatorioEtapa);
            perguntaEtapaAluno.setPulo(perguntaEtapaAluno.getPulo()!=null?perguntaEtapaAluno.getPulo():false);
            perguntaEtapaAluno.setDica(perguntaEtapaAluno.getDica()!=null?perguntaEtapaAluno.getDica():false);
            perguntaEtapaAluno.setTempoAcabou(perguntaEtapaAluno.getTempoAcabou()!=null?perguntaEtapaAluno.getTempoAcabou():false);
            validador.validarPerguntaEtapaAluno(perguntaEtapaAluno);
            dao.saveOrUpdate(perguntaEtapaAluno);
        }
        return relatorioEtapa;
    }
}
