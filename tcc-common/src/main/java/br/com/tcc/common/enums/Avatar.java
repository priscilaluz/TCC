package br.com.tcc.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Enumeração Tipo Target
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Avatar {

    PESSOA  ("PE", "img/avatar/pessoa.png"),
    REI ("RE", "img/avatar/rei.png"),
    RAINHA ("RA", "img/avatar/rainha.png"),
    INVESTIGADOR ("IO", "img/avatar/investigador.png"),
    INVESTIGADORA  ("IA", "img/avatar/investigadora.png");

    /**
     * Id.
     */
    private final String id;
    private final String url;

    /**
     * Construtor padrao.
     *
     * @param newId O identificador desta Enumeração
     */
    private Avatar(final String newId, final String url) {
        this.id = newId;
        this.url = url;
    }

    /**
     * Retorna o identificador desta Enumeração.
     * @return id
     */
    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
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
    public static Avatar from(final String valor) {
        if (valor == null) {
            throw new NullPointerException();
        }

        for (Avatar e : Avatar.values()) {
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
