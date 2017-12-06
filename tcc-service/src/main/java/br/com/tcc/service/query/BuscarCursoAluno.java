/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.service.query;

import br.com.tcc.common.entity.CursoAluno;
import br.com.tcc.common.enums.SituacaoCursoAluno;
import br.com.tcc.service.persistence.BusinessFluentQuery;
import java.io.Serializable;

public abstract class BuscarCursoAluno<T extends Serializable> extends BusinessFluentQuery<T, BuscarCursoAluno> {

    public static class Entities extends BuscarCursoAluno<CursoAluno> {
        public Entities() {
            appendText("select ca from CursoAluno ca ");
        }
    }
    
    public static class Count extends BuscarCursoAluno<Long> {
        public Count() {
            appendText("select count(ca) from CursoAluno ca ");
        }
    }
    
    public BuscarCursoAluno fetchCurso(String fetch) {
        appendText(" left join "+fetch+" ca.curso c ");
        return this;
    }
    
    public BuscarCursoAluno fetchCursoAnexo(String fetch) {
        appendText(" left join "+fetch+" c.anexo canexo ");
        return this;
    }
    
    public BuscarCursoAluno fetchProfessor(String fetch) {
        appendText(" left join "+fetch+" c.usuario u ");
        return this;
    }
    
    public BuscarCursoAluno fetchEtapas(String fetch) {
        appendText(" left join "+fetch+" c.etapas e ");
        return this;
    }
    
    public BuscarCursoAluno fetchAluno(String fetch) {
        appendText(" left join "+fetch+" ca.aluno a ");
        return this;
    }

    public BuscarCursoAluno whereIdCursoAluno(Long id) {
        if (id != null) {
            appendText(getPreffixFilter());
            appendText(" ca.id = :idCursoAluno ");
            addParameter("idCursoAluno", id);
        }    
        return this;
    }

    public BuscarCursoAluno whereIdCurso(Long id) {
        if (id != null) {
            appendText(getPreffixFilter());
            appendText(" c.id = :idCurso ");
            addParameter("idCurso", id);
        }    
        return this;
    }

    public BuscarCursoAluno whereIdAluno(Long id) {
        if (id != null) {
            appendText(getPreffixFilter());
            appendText(" a.id = :idAluno ");
            addParameter("idAluno", id);
        }    
        return this;
    }

    public BuscarCursoAluno whereIdProfessor(Long idProfessor) {
        if (idProfessor != null) {
            appendText(getPreffixFilter());
            appendText(" u.id = :idProfessor ");
            addParameter("idProfessor", idProfessor);
        }    
        return this;
    }

    public BuscarCursoAluno whereSituacaoCursoAluno(SituacaoCursoAluno situacao) {
        if (situacao != null) {
            appendText(getPreffixFilter());
            appendText(" ca.situacao = :situacao ");
            addParameter("situacao", situacao);
        }    
        return this;
    }
}
