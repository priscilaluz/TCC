/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.common.vo;

/**
 *
 * @author ADM
 */
public class DadoProfessor {
    private Long qntAluno;
    private Long qntPerguntas;
    private Long qntCursoRascunho;
    private Long qntCursoConcluidos;

    public DadoProfessor() {}

    public Long getQntAluno() {
        return qntAluno!=null?qntAluno:0L;
    }

    public void setQntAluno(Long qntAluno) {
        this.qntAluno = qntAluno;
    }

    public Long getQntPerguntas() {
        return qntPerguntas!=null?qntPerguntas:0L;
    }

    public void setQntPerguntas(Long qntPerguntas) {
        this.qntPerguntas = qntPerguntas;
    }

    public Long getQntCursoRascunho() {
        return qntCursoRascunho!=null?qntCursoRascunho:0L;
    }

    public void setQntCursoRascunho(Long qntCursoRascunho) {
        this.qntCursoRascunho = qntCursoRascunho;
    }

    public Long getQntCursoConcluidos() {
        return qntCursoConcluidos!=null?qntCursoConcluidos:0L;
    }

    public void setQntCursoConcluidos(Long qntCursoConcluidos) {
        this.qntCursoConcluidos = qntCursoConcluidos;
    }
}
