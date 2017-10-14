package br.com.tcc.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Enumeração Tipo Target
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SituacaoCursoAluno {

    EM_ANDAMENTO ("A", "Em Andamento"),
    CONCLUIDA  ("C", "Concluído");

    /**
     * Id.
     */
    private String id;
    private String descricao;

    /**
     * Construtor padrao.
     *
     * @param newId O identificador desta Enumeração
     */
    private SituacaoCursoAluno(final String newId, final String descricao) {
        this.id = newId;
        this.descricao = descricao;
    }

    /**
     * Retorna o identificador desta Enumeração.
     * @return id
     */
    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public String getName() {
        return name();
    }
    /**
     * Obtém uma instância desta classe a partir do valor de um outro objeto.
     *
     * @param valor
     *          O valor a partir do qual se obterá a instância desta classe.
     * @return  Uma instância desta classe correspondente ao valor passado
     *          como parâmetro ou exceção caso o parâmetro passado for
     *          <code>null</code> ou não estiver dentro dos valores
     *          possÃ­veis da enumeração.
     */
    public static SituacaoCursoAluno from(final String valor) {
        if (valor == null) {
            return null;
        }

        for (SituacaoCursoAluno e : SituacaoCursoAluno.values()) {
            if (valor.equals(e.id) || valor.equals(e.name())) {
                return e;
            }
        }

        final StringBuilder msg = new StringBuilder("");
        msg.append("Cannot parse into an element of Cds: '");
        msg.append(valor);
        msg.append("'");

        throw new IllegalArgumentException(msg.toString());
    }

}