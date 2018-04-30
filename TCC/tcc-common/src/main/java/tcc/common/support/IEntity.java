/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.common.support;

import java.io.Serializable;

/**
 * Interface utilizada por componentes do framework para operar sobre uma
 * entidade da aplicação. Sugere-se que toda classe que represente uma
 * entidade implemente esta interface.
 * O uso desta interface pressupõe que a entidade possuirá uma chave primária
 * cujo tipo deve ser especificado como parâmetro desta interface. Tal chave
 * primária, preferencialmente, não deve ter valor de regras de negócio.
 * Trata-se de uma realização dos padrões de projeto "Identity Field" e
 * "Layer Supertype" descritos no livro "Patterns of Enterprise Application
 * Architecture".
 *
 * @param <ID> O tipo do identificador único desta classe.
 */
public interface IEntity<ID extends Serializable> extends Serializable {

    /**
     * Atribui um identificador à esta entidade.
     * @param id O identificador único a atribuir.
     */
    void setId(ID id);

    /**
     * Obtém o identificador desta entidade.
     * @return o identificador desta entidade.
     */
    ID getId();
}