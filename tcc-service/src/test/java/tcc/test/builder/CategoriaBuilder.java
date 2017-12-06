package tcc.test.builder;

import tcc.common.entity.Categoria;

public class CategoriaBuilder {
    private Categoria categoria;
    
    private CategoriaBuilder(Categoria categoria){
        this.categoria = categoria;
    }
    
    public static CategoriaBuilder nova(){
        return new CategoriaBuilder(new Categoria());
    }
    
    public Categoria build(){
        return this.categoria;
    }
    
    public CategoriaBuilder comNome(String nome){
        categoria.setNome(nome);
        return this;
    }
}
