package tcc.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Enumeração Tipo Target
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TempoPergunta {

    TRINTA_SEGUNDOS ("T", "30 segundos"),
    SESSENTA_SEGUNDOS ("S", "60 segundos"),
    NOVENTA_SEGUNDOS ("N", "90 segundos");
    
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
    private TempoPergunta(final String newId, final String descricao) {
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
    public static TempoPergunta from(final String valor) {
        if (valor == null) {
            throw new NullPointerException();
        }

        for (TempoPergunta e : TempoPergunta.values()) {
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