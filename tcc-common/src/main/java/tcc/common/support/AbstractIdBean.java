package tcc.common.support;

import java.io.Serializable;

public abstract class AbstractIdBean<T extends Serializable> implements IEntity<T>, Serializable {
    
     /**
     * Construtor padrão
     */
    public AbstractIdBean() {
    }
    
      /**
     * Implementação padrão de hashCode baseada no retorno de getId().
     * @return hashCode baseado no retorno getId()
     */
    @Override
    public int hashCode() {
        if (null == getId()) {
            return super.hashCode();
        } else {
            return getId().hashCode();
        }
    }

    /**
     * Implementação padrão de equals baseada no getId().
     * {@inheritDoc}
     * @return true se o objeto tiver o mesmo Id
     */
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (!this.getClass().isAssignableFrom(object.getClass())) {
            return false;
        }

        AbstractIdBean other = (AbstractIdBean) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && other.getId() == null)) {
            return false;
        } else if (this.getId() == null && other.getId() == null) {
            return super.equals(object);
        } else {
            return this.getId().equals(other.getId());
        }
    }
    
}
