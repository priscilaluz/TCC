package tcc.test.builder;

import tcc.common.entity.Anexo;
import tcc.common.entity.Categoria;
import tcc.common.entity.Curso;
import tcc.common.entity.Etapa;
import tcc.common.entity.Usuario;
import tcc.common.enums.SituacaoCurso;
import java.util.Set;

public class CursoBuilder {
    private Curso curso;
    
    private CursoBuilder(Curso curso){
        this.curso = curso;
    }
    
    public static CursoBuilder nova(){
        return new CursoBuilder(new Curso());
    }
    
    public Curso build(){
        return this.curso;
    }
    
    public CursoBuilder comCategoria(Categoria categoria){
        curso.setCategoria(categoria);
        return this;
    }
    
    public CursoBuilder comUsuario(Usuario usuario){
        curso.setUsuario(usuario);
        return this;
    }
    
    public CursoBuilder comNome(String nome){
        curso.setNome(nome);
        return this;
    }
    
    public CursoBuilder comAssuntoGeral(String assuntoGeral){
        curso.setAssuntoGeral(assuntoGeral);
        return this;
    }
    
    public CursoBuilder comCodAcesso(String codAcesso){
        curso.setCodAcesso(codAcesso);
        return this;
    }
    
    public CursoBuilder comAnexo(Anexo anexo){
        curso.setAnexo(anexo);
        return this;
    }
    
    public CursoBuilder comSituacao(SituacaoCurso situacao){
        curso.setSituacao(situacao);
        return this;
    }
    
    public CursoBuilder comEtapas(Set<Etapa> etapas){
        curso.setEtapas(etapas);
        return this;
    }
    
}
