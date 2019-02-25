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
import tcc.common.business.PremioService;
import tcc.common.entity.PerguntaEtapaAluno;
import tcc.common.entity.Premio;
import tcc.common.entity.RelatorioEtapa;
import tcc.common.entity.Usuario;
import tcc.common.enums.Jogo;
import tcc.common.enums.NivelPergunta;
import tcc.service.query.BuscarPremio;

/**
 *
 * @author ADM
 */
@Component("PremioServiceImpl")
public class PremioServiceImpl implements PremioService {

    @Autowired
    private GenericDao dao;

    @Override
    @Transactional(readOnly = false)
    public Premio cadastrarPremio(Usuario usuario) {
        Premio premio = new Premio();
        premio.setQntQuizVencidos(0);
        premio.setQntPergFacilCorreta(0);
        premio.setQntPergMediaCorreta(0);
        premio.setQntPergDificilCorreta(0);
        premio.setQntApostaVencida(0);
        premio.setQntApostaTudoEmUmaPergVencida(0);
        premio.setQntForcaVencida(0);
        premio.setQntCacaPalavrasVencida(0);
        premio.setQntJogoDaVelhaVencida(0);
        premio.setQntEtapaVencida(0);
        premio.setQntCursoConcluido(0);
        premio.setQntEtapaSemPulo(0);
        premio.setQntEtapaSemDica(0);
        premio.setQntPontosAcumulados(0);
        premio.setUsuario(usuario);
        dao.saveOrUpdate(premio);
        return premio;
    }

    @Override
    @Transactional(readOnly = false)
    public void atualizarPremio(RelatorioEtapa relatorioEtapa, Integer qntCursoConcluido, Integer qntPontosAcumulados) {
        Jogo jogo = relatorioEtapa.getEtapaAluno().getEtapa().getJogo();
        if (relatorioEtapa.getGanhou()) {
            Long idAluno = relatorioEtapa.getIdAluno();
            boolean pulou = false;
            boolean usouDica = false;
            int qntQuizVencidos = Jogo.QUIZ.equals(jogo)?1:0;
            int qntPergFacilCorreta = 0;
            int qntPergMediaCorreta = 0;
            int qntPergDificilCorreta = 0;
            int qntApostaVencida = Jogo.APOSTA.equals(jogo)?1:0;
            int qntApostaTudoEmUmaPergVencida = 0;
            int qntForcaVencida = Jogo.FORCA.equals(jogo)?1:0;
            int qntCacaPalavrasVencida = Jogo.CACA_PALAVRA.equals(jogo)?1:0;
            int qntJogoDaVelhaVencida = Jogo.JOGO_DA_VELHA.equals(jogo)?1:0;
            for (PerguntaEtapaAluno perguntasEtapasAluno : relatorioEtapa.getPerguntasEtapasAlunos()) {
                if (perguntasEtapasAluno.getPulo()) {
                    pulou = true;
                }
                if (perguntasEtapasAluno.getDica()) {
                    usouDica = true;
                }
                if (!Jogo.APOSTA.equals(jogo) && perguntasEtapasAluno.getGanhou()) {
                    if (NivelPergunta.FACIL.equals(perguntasEtapasAluno.getPergunta().getNivel())) {
                        qntPergFacilCorreta++;
                    }
                    if (NivelPergunta.MEDIO.equals(perguntasEtapasAluno.getPergunta().getNivel())) {
                        qntPergMediaCorreta++;
                    }
                    if (NivelPergunta.DIFICIL.equals(perguntasEtapasAluno.getPergunta().getNivel())) {
                        qntPergDificilCorreta++;
                    }
                }
                int qntOpcoes = 0;
                int maiorAposta = 0;
                
                for (String aposta : perguntasEtapasAluno.getApostasFase()) {
                    int apostaOpcao = Integer.valueOf(aposta);
                    maiorAposta = maiorAposta > apostaOpcao ? maiorAposta : apostaOpcao;
                    qntOpcoes = apostaOpcao > 0 ? qntOpcoes+1 : qntOpcoes;
                }
                
                if (Jogo.APOSTA.equals(jogo) && qntOpcoes==1 && (maiorAposta*2 == perguntasEtapasAluno.getPontuacao())) {
                    qntApostaTudoEmUmaPergVencida++;
                }
            }
            int qntEtapaSemPulo = pulou?0:1;
            int qntEtapaSemDica = usouDica?0:1;
            
            
            Premio p = buscarPremioPorFiltro(idAluno);
            p.setQntQuizVencidos(p.getQntQuizVencidos()+qntQuizVencidos);
            p.setQntPergFacilCorreta(p.getQntPergFacilCorreta()+qntPergFacilCorreta);
            p.setQntPergMediaCorreta(p.getQntPergMediaCorreta()+qntPergMediaCorreta);
            p.setQntPergDificilCorreta(p.getQntPergDificilCorreta()+qntPergDificilCorreta);
            p.setQntApostaVencida(p.getQntApostaVencida()+qntApostaVencida);
            p.setQntApostaTudoEmUmaPergVencida(p.getQntApostaTudoEmUmaPergVencida()+qntApostaTudoEmUmaPergVencida);
            p.setQntForcaVencida(p.getQntForcaVencida()+qntForcaVencida);
            p.setQntCacaPalavrasVencida(p.getQntCacaPalavrasVencida()+qntCacaPalavrasVencida);
            p.setQntJogoDaVelhaVencida(p.getQntJogoDaVelhaVencida()+qntJogoDaVelhaVencida);
            p.setQntEtapaVencida(p.getQntEtapaVencida()+1);
            p.setQntCursoConcluido(p.getQntCursoConcluido()+qntCursoConcluido);
            p.setQntEtapaSemPulo(p.getQntEtapaSemPulo()+qntEtapaSemPulo);
            p.setQntEtapaSemDica(p.getQntEtapaSemDica()+qntEtapaSemDica);
            p.setQntPontosAcumulados(p.getQntPontosAcumulados()+qntPontosAcumulados);
            dao.saveOrUpdate(p);
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Premio buscarPremioPorId(Long idPremio) {
        return (Premio) dao.uniqueResult(new BuscarPremio.Entities()
                .whereId(idPremio));
    }

    @Override
    @Transactional(readOnly = true)
    public Premio buscarPremioPorFiltro(Long idUsuario) {
        Premio premio = (Premio) dao.uniqueResult(new BuscarPremio.Entities()
                .whereIdUsuario(idUsuario));
        if (premio == null) {
            premio = new Premio();
            premio.setUsuario(new Usuario(idUsuario));
        }
        return premio;
    }
}
