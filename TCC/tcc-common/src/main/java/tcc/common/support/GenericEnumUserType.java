/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.common.support;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.TypeResolver;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;

/**
 * Referencia:
 * http://appfuse.org/display/APF/Java+5+Enums+Persistence+with+Hibernate Vide
 * Ticket #90.
 */
public class GenericEnumUserType implements UserType, ParameterizedType {
    private static final String ENUM_CLASS_PARAM_NAME = "enumClass";
    private static final String VALUE_OF_METHOD_PARAM_NAME = "valueOfMethod";
    private static final String IDENTIFIER_METHOD_PARAM_NAME = "identifierMethod";

    private static final String DEFAULT_IDENTIFIER_METHOD_NAME = "getId";
    private static final String DEFAULT_VALUE_OF_METHOD_NAME = "from";

    private static final String MSG_NO_METHOD_NAMED = "No method named";
    private static final String MSG_FOUND = " found";
    private static final String MSG_INVOKE_METHOD = (new StringBuilder())
            .append("Exception while invoking valueOf method '").toString();
    private static final String MSG_INVOKE_IDENT_METHOD = (new StringBuilder())
            .append("Exception while invoking identifierMethod '").toString();
    private static final String MSG_OF = "' of ";
    private static final String MSG_ENUM_CLASS = "enumeration class '";
    private static final String MSG_SINGLE_QUOTE = "'";
    private static final int FIRST_ITEM = 0;

    private Class<? extends Enum<?>> enumClass;
    private Class<?> identifierType;
    private Method identifierMethod;
    private Method valueOfMethod;
    private AbstractSingleColumnStandardBasicType type;
    private int[] sqlTypes;

    /**
     * {@inheritDoc}
     * @param parameters
     *            {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void setParameterValues(Properties parameters) {
        String enumClassName = parameters.getProperty(ENUM_CLASS_PARAM_NAME);
        try {
            enumClass = (Class<? extends Enum<?>>) Class.forName(enumClassName).asSubclass(Enum.class);
        } catch (ClassNotFoundException cfne) {
            throw new HibernateException((new StringBuilder()).append(
                    "Enum class named '").append(enumClass)
                    .append("'not found").toString(), cfne);
        }

        String identifierMethodName = parameters.getProperty(
                IDENTIFIER_METHOD_PARAM_NAME, DEFAULT_IDENTIFIER_METHOD_NAME);

        try {
            identifierMethod = enumClass.getMethod(identifierMethodName,
                    new Class[FIRST_ITEM]);
            identifierType = identifierMethod.getReturnType();
        } catch (NoSuchMethodException e) {
            throw new HibernateException((new StringBuilder()).append(
                    MSG_NO_METHOD_NAMED).append(identifierMethodName).append(
                    MSG_FOUND).toString(), e);
        } catch (SecurityException e) {
            throw new HibernateException((new StringBuilder()).append(
                    MSG_NO_METHOD_NAMED).append(identifierMethodName).append(
                    MSG_FOUND).toString(), e);
        }

        TypeResolver tr = new TypeResolver();
        type = (AbstractSingleColumnStandardBasicType)tr.basic( identifierType.getName() );

        if (type == null) {
            throw new HibernateException((new StringBuilder())
                                .append("Unsupported identifier type ")
                                .append(identifierType.getName())
                                .toString());
        }

        sqlTypes = new int[]{type.sqlType()};

        String valueOfMethodName = parameters.getProperty(
                VALUE_OF_METHOD_PARAM_NAME, DEFAULT_VALUE_OF_METHOD_NAME);

        try {
            valueOfMethod = enumClass.getMethod(valueOfMethodName,
                    new Class[]{identifierType});
        } catch (NoSuchMethodException e) {
            throw new HibernateException((new StringBuilder())
                    .append(MSG_NO_METHOD_NAMED)
                    .append(valueOfMethodName)
                    .append(MSG_FOUND)
                    .toString(), e);
        } catch (SecurityException e) {
            throw new HibernateException((new StringBuilder())
                    .append(MSG_NO_METHOD_NAMED)
                    .append(valueOfMethodName)
                    .append(MSG_FOUND)
                    .toString(), e);
        }
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Enum> returnedClass() {
        return enumClass;
    }

    /**
     * {@inheritDoc}
     * @param rs
     *            {@inheritDoc}
     * @param names
     *            {@inheritDoc}
     * @param owner
     *            {@inheritDoc}
     * @return {@inheritDoc}
     */
    
    
    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor si, Object owner)
            throws SQLException {
        Object identifier = type.get(rs, names[FIRST_ITEM], si);
        if (identifier == null) {
            return null;
        }

        try {
            return valueOfMethod.invoke(enumClass, new Object[]{identifier});
        } catch (IllegalAccessException e) {
            throw new HibernateException((new StringBuilder())
                    .append(MSG_INVOKE_METHOD)
                    .append(valueOfMethod.getName())
                    .append(MSG_OF)
                    .append(MSG_ENUM_CLASS)
                    .append(enumClass)
                    .append(MSG_SINGLE_QUOTE)
                    .toString(), e);
        } catch (InvocationTargetException e) {
            throw new HibernateException((new StringBuilder())
                    .append(MSG_INVOKE_METHOD)
                    .append(valueOfMethod.getName())
                    .append(MSG_OF)
                    .append(MSG_ENUM_CLASS)
                    .append(enumClass)
                    .append(MSG_SINGLE_QUOTE)
                    .toString(), e);
        } catch (IllegalArgumentException e) {
            throw new HibernateException((new StringBuilder())
                    .append(MSG_INVOKE_METHOD)
                    .append(valueOfMethod.getName())
                    .append(MSG_OF)
                    .append(MSG_ENUM_CLASS)
                    .append(enumClass)
                    .append(MSG_SINGLE_QUOTE)
                    .toString(), e);
        }
    }

    /**
     * {@inheritDoc}
     * @param st
     *            {@inheritDoc}
     * @param value
     *            {@inheritDoc}
     * @param index
     *            {@inheritDoc}
     * @throws SQLException
     *             {@inheritDoc}
     */
    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor si)
            throws SQLException {
        try {
            if (value == null) {
                st.setNull(index, type.sqlType());
            } else {
                Object identifier = identifierMethod.invoke(value,
                        new Object[FIRST_ITEM]);
                type.set(st, identifier, index, si);
            }
        } catch (IllegalAccessException e) {
            throw new HibernateException((new StringBuilder())
                    .append(MSG_INVOKE_IDENT_METHOD)
                    .append(identifierMethod.getName())
                    .append(MSG_OF)
                    .append(MSG_ENUM_CLASS)
                    .append(enumClass)
                    .append(MSG_SINGLE_QUOTE)
                    .toString(), e);
        } catch (InvocationTargetException e) {
            throw new HibernateException((new StringBuilder())
                    .append(MSG_INVOKE_IDENT_METHOD)
                    .append(identifierMethod.getName())
                    .append(MSG_OF)
                    .append(MSG_ENUM_CLASS)
                    .append(enumClass)
                    .append(MSG_SINGLE_QUOTE)
                    .toString(), e);
        } catch (IllegalArgumentException e) {
            throw new HibernateException((new StringBuilder())
                    .append(MSG_INVOKE_IDENT_METHOD)
                    .append(identifierMethod.getName())
                    .append(MSG_OF)
                    .append(MSG_ENUM_CLASS)
                    .append(enumClass)
                    .append(MSG_SINGLE_QUOTE)
                    .toString(), e);
        }

    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public int[] sqlTypes() {
        return Arrays.copyOf(sqlTypes, sqlTypes.length);
    }

    /**
     * {@inheritDoc}
     * @param cached
     *            {@inheritDoc}
     * @param owner
     *            {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public Object assemble(Serializable cached, Object owner) {
        return cached;
    }

    /**
     * {@inheritDoc}
     * @param value
     *            {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public Object deepCopy(Object value) {
        return value;
    }

    /**
     * {@inheritDoc}
     * @param value
     *            {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public Serializable disassemble(Object value) {
        return (Serializable) value;
    }

    /**
     * {@inheritDoc}
     * @param x
     *            {@inheritDoc}
     * @param y
     *            {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(Object x, Object y) {
        return x == y;
    }

    /**
     * {@inheritDoc}
     * @param x
     *            {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode(Object x) {
        return x.hashCode();
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean isMutable() {
        return false;
    }

    /**
     * {@inheritDoc}
     * @param original
     *            {@inheritDoc}
     * @param target
     *            {@inheritDoc}
     * @param owner
     *            {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public Object replace(Object original, Object target, Object owner) {
        return original;
    }

}
