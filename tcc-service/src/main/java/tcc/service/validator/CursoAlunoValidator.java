package tcc.service.validator;

import tcc.common.entity.Curso;
import tcc.common.entity.CursoAluno;
import tcc.common.entity.PerguntaEtapaAluno;
import tcc.common.entity.RelatorioEtapa;
import tcc.common.exception.PendencyManager;
import tcc.common.util.ConstantesI18N;
import org.springframework.stereotype.Component;

@Component
public class CursoAlunoValidator {

    public void validarAlunoEntrarCurso(Curso curso, String codAcesso, CursoAluno cursoAlunoJaSalvo){
        PendencyManager pm = new PendencyManager();
        pm.assertThat(cursoAlunoJaSalvo==null).orRegister(ConstantesI18N.CURSO_ALUNO_JA_SALVO);
        pm.assertNotNull(codAcesso).orRegister(ConstantesI18N.CODIGO_ACESSO_OBRIGATORIO);
        pm.assertThat(codAcesso==null||curso.getCodAcesso().equals(codAcesso)).orRegister(ConstantesI18N.CODIGO_ACESSO_INVALIDO);
        pm.verifyAll();
    }
    
    public void validarRelatorioEtapa(RelatorioEtapa relatorioEtapa){
        PendencyManager pm = new PendencyManager();
        pm.assertNotNull(relatorioEtapa.getEtapaAluno()).orRegister(ConstantesI18N.RELATORIO_ETAPA_ETAPA_ALUNO_OBRIGATORIO);
        pm.assertNotNull(relatorioEtapa.getPontuacao()).orRegister(ConstantesI18N.RELATORIO_ETAPA_PONTUACAO_OBRIGATORIO);
        pm.verifyAll();
    }
    
    public void validarPerguntaEtapaAluno(PerguntaEtapaAluno perguntaEtapaAluno){
        PendencyManager pm = new PendencyManager();
        pm.assertNotNull(perguntaEtapaAluno.getPulo()).orRegister(ConstantesI18N.PERGUNTA_ETAPA_ALUNO_PULO_OBRIGATORIO);
        pm.assertNotNull(perguntaEtapaAluno.getDica()).orRegister(ConstantesI18N.PERGUNTA_ETAPA_ALUNO_DICA_OBRIGATORIO);
        pm.assertNotNull(perguntaEtapaAluno.getTempoAcabou()).orRegister(ConstantesI18N.PERGUNTA_ETAPA_ALUNO_TEMPOACABOU_OBRIGATORIO);
        pm.assertNotNull(perguntaEtapaAluno.getPontuacao()).orRegister(ConstantesI18N.PERGUNTA_ETAPA_ALUNO_PONTUACAO_OBRIGATORIO);
        pm.assertNotNull(perguntaEtapaAluno.getPergunta()).orRegister(ConstantesI18N.PERGUNTA_ETAPA_ALUNO_PERGUNTA_OBRIGATORIO);
        pm.assertNotNull(perguntaEtapaAluno.getRelatorioEtapa()).orRegister(ConstantesI18N.PERGUNTA_ETAPA_ALUNO_RELATORIOETAPA_OBRIGATORIO);
        pm.verifyAll();
    }
}
