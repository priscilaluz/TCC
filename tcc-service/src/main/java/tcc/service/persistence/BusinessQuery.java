/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.service.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.type.Type;

/**
 * Encapsula uma Query textual do Hibernate.
 * @author 
 */
public abstract class BusinessQuery<T extends Serializable>  {
    /**
     * Builder para armazenar o texto da query enquanto está sendo montado
     */
    private StringBuilder text;
    /**
     * Define se a query contem SQL nativo
     */
    private boolean nativeQuery;
    /**
     * Parâmetros por ordem
     */
    private List parameterList = new ArrayList();
    /**
     * Parâmetros por nome
     */
    private Map<String, Object> parameterMap = new HashMap();
    /**
     * projectionValueObjectClass
     */
    private Class<T> projectionValueObjectClass;
    /**
     * projectionTypeMappings
     */
    private Map<String, Type> projectionTypeMappings;
    /**
     * Dados de paginação
     */
    private Pagination pagination;

    /**
     * Construtor
     */
    public BusinessQuery() {
        this(false);
    }

    /**
     * Construtor
     * @param nativa define se a query é nativa
     */
    public BusinessQuery(boolean nativa) {
        this.nativeQuery = nativa;
    }

    /**
     * Retorna o texto da consulta.
     * @return
     */
    protected final String getText() {
        return text.toString();
    }

    /**
     * Seta o texto da consulta.
     * @param text
     */
    protected final void setText(String text) {
        appendText(text);
    }

    /**
     * @return lista de parâmetros por ordem da consulta
     */
    public List getParameterList() {
        return Collections.unmodifiableList(parameterList);
    }

    /**
     * @return Mapa de parâmetros por nome da consulta
     */
    public Map<String, Object> getParameterMap() {
        return Collections.unmodifiableMap(parameterMap);
    }

    /**
     * @return Mapeaento de tipos usado em consulta nativa com projeção em um
     * value object
     */
    public Map<String, Type> getProjectionTypeMappings() {
        return projectionTypeMappings;
    }

    /**
     * Classe para transformação da projeção da consulta em um
     * value object
     * @return
     */
    public Class<T> getProjectionValueObjectClass() {
        return projectionValueObjectClass;
    }

    /**
     * @return true se a query for nativa
     */
    public boolean isNativeQuery() {
        return nativeQuery;
    }

    /**
     * Adiciona as strings recebidas como parâmetro ao texto da consulta. Este método
     * foi criado para ser utilizado conforme o exemplo abaixo:
     * <blockquote><pre>
     *   appendText("from ", Pedido.class.getSimpleName(), " p ");
     *   appendText("join fetch p.", Pedido.PROP_CLIENTE, " c ");
     *   appendText("join fetch c.", Cliente.PROP_ENDERECO, " ");
     *   appendText("where p.", Pedido.PROP_SITUACAO, " = :pendentePagamento ");
     *   buildQuery();
     * </pre></blockquote>
     */
    protected final BusinessQuery appendText(String... parts) {
        if (text == null) {
            text = new StringBuilder();
        }
        for (String part : parts) {
            text.append(part);
        }
        return this;
    }

    /**
     * Define os parâmetros indexados.
     * @param params
     */
    public void setParameters(Object ... params) {
        if (params==null) {
            return;
        }
        parameterList.clear();
        parameterList.addAll(Arrays.asList(params));
    }

    /**
     * Define os parâmetros nomeados.
     * @param params
     */
    public void setParameters(Map<String, Object> params) {
        parameterMap.clear();
        parameterMap.putAll(params);
    }

    /**
     * Adiciona um parâmetro nomeado.
     * @param key
     * @param param
     */
    public void addParameter(String key, Object param) {
        parameterMap.put(key, param);
    }

    public void setProjectionValueObjectClass(Class<T> projectionValueObjectClass) {
        this.projectionValueObjectClass = projectionValueObjectClass;
    }

    public void setProjectionTypeMappings(Map<String, Type> projectionTypeMappings) {
        this.projectionTypeMappings = projectionTypeMappings;
    }

    public Pagination getPagination() {
        return pagination;
    }
    
    public void setPagination(Pagination pagination) {
        if (pagination.getOrdering().length > 0) {
            throw new IllegalArgumentException("O order by não pode ser definido na paginação");
        }
        this.pagination = pagination;
    }
}
