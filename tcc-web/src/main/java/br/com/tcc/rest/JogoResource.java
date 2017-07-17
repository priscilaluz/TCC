/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.rest;

import br.com.tcc.common.entity.Pergunta;
import br.com.tcc.common.entity.Resposta;
import br.com.tcc.common.entity.Anexo;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.stereotype.Component;

/**
 *
 * @author ADM
 */
@Path("/jogoPerguntas")
@Component
public class JogoResource {
    @GET
    @Path("/quizApresentacao")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pergunta> buscarPerguntaDaApresentacaoDoJogo() {
        List<Pergunta> perguntas = new ArrayList<>();
        perguntas.add(obterPerguntaModelo1());
        perguntas.add(obterPerguntaModelo2());
        perguntas.add(obterPerguntaModelo3());
        perguntas.add(obterPerguntaModelo4());
        perguntas.add(obterPerguntaModelo5());
        return perguntas;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Perguntas do Simuldao Modelo">
    private Pergunta obterPerguntaModelo1() {
        Pergunta pergunta = new Pergunta();
        pergunta.setPosicao(1);
        pergunta.setDescricao("Quanto é o valor de x na equação: x-5=10?");
        pergunta.setJustificativa("x=10+5/n x=15");
        pergunta.setDica("x=10+5");
        
        Set<Resposta> respostas = new HashSet<>();
        Resposta resposta1 = new Resposta();
        resposta1.setId(1L);
        resposta1.setCorreta(Boolean.FALSE);
        resposta1.setDescricao("5");
        respostas.add(resposta1);
        
        Resposta resposta2 = new Resposta();
        resposta2.setId(2L);
        resposta2.setCorreta(Boolean.TRUE);
        resposta2.setDescricao("15");
        respostas.add(resposta2);
        
        Resposta resposta3 = new Resposta();
        resposta3.setId(3L);
        resposta3.setCorreta(Boolean.FALSE);
        resposta3.setDescricao("10");
        respostas.add(resposta3);
        
        Resposta resposta4 = new Resposta();
        resposta4.setId(4L);
        resposta4.setCorreta(Boolean.FALSE);
        resposta4.setDescricao("20");
        respostas.add(resposta4);
        
        Resposta resposta5 = new Resposta();
        resposta5.setId(5L);
        resposta5.setCorreta(Boolean.FALSE);
        resposta5.setDescricao("50");
        respostas.add(resposta5);
        
        pergunta.setRespostas(respostas);
        
        return pergunta;
    }
    private Pergunta obterPerguntaModelo2() {
        Pergunta pergunta = new Pergunta();
        pergunta.setPosicao(2);
        pergunta.setDescricao("Como se escreve o substantivo que significa aquilo que se desvia ou exclui de regras e padrões?");
        pergunta.setDica("Existe um X na resposta.");
        
        Set<Resposta> respostas = new HashSet<>();
        Resposta resposta1 = new Resposta();
        resposta1.setId(6L);
        resposta1.setCorreta(Boolean.FALSE);
        resposta1.setDescricao("Esseção");
        respostas.add(resposta1);
        
        Resposta resposta2 = new Resposta();
        resposta2.setId(7L);
        resposta2.setCorreta(Boolean.FALSE);
        resposta2.setDescricao("Ecessão");
        respostas.add(resposta2);
        
        Resposta resposta3 = new Resposta();
        resposta3.setId(8L);
        resposta3.setCorreta(Boolean.FALSE);
        resposta3.setDescricao("Excessão");
        respostas.add(resposta3);
        
        Resposta resposta4 = new Resposta();
        resposta4.setId(9L);
        resposta4.setCorreta(Boolean.TRUE);
        resposta4.setDescricao("Exceção");
        respostas.add(resposta4);
        
        Resposta resposta5 = new Resposta();
        resposta5.setId(10L);
        resposta5.setCorreta(Boolean.FALSE);
        resposta5.setDescricao("Essessão");
        respostas.add(resposta5);
        
        pergunta.setRespostas(respostas);
        
        return pergunta;
    }
    private Pergunta obterPerguntaModelo3() {
        Pergunta pergunta = new Pergunta();
        pergunta.setPosicao(3);
        pergunta.setDescricao("\"Cinquenta anos em cinco\" foi o bordão de qual popular presidente?");
        pergunta.setJustificativa("O Governo Juscelino Kubitschek é o período de governo vivido entre 1956 e 1961[1]. Sua eleição foi marcada pelo plano de ação \"Cinquenta anos em cinco\", marca do desenvolvimentismo, já que o ideal era trazer ao Brasil o desenvolvimento econômico e social. Segundo JK, se com outros governantes este processo levaria cinquenta anos, com ele levaria apenas cinco[2]. Trouxe diversas empresas estrangeiras para o país, entre elas, as automobilísticas Chrysler e Ford através do Grupo Executivo da Indústria Automobilística, já que ele queria incentivar o comércio de carros, além de televisões e outros bens de consumo. Em resumo, procurou alinhar a economia brasileira à economia americana.");
        pergunta.setDica("Segundo JK, se com outros governantes este processo levaria cinquenta anos, com ele levaria apenas cinco");
        
        Set<Resposta> respostas = new HashSet<>();
        Resposta resposta1 = new Resposta();
        resposta1.setId(11L);
        resposta1.setCorreta(Boolean.FALSE);
        resposta1.setDescricao("Jânio Quadros");
        respostas.add(resposta1);
        
        Resposta resposta2 = new Resposta();
        resposta2.setId(12L);
        resposta2.setCorreta(Boolean.FALSE);
        resposta2.setDescricao("Fernando Collor");
        respostas.add(resposta2);
        
        Resposta resposta3 = new Resposta();
        resposta3.setId(13L);
        resposta3.setCorreta(Boolean.FALSE);
        resposta3.setDescricao("Ranieri Mazzilli");
        respostas.add(resposta3);
        
        Resposta resposta4 = new Resposta();
        resposta4.setId(14L);
        resposta4.setCorreta(Boolean.FALSE);
        resposta4.setDescricao("Ernesto Geisel");
        respostas.add(resposta4);
        
        Resposta resposta5 = new Resposta();
        resposta5.setId(15L);
        resposta5.setCorreta(Boolean.TRUE);
        resposta5.setDescricao("Juscelino Kubitschek");
        respostas.add(resposta5);
        
        pergunta.setRespostas(respostas);
        
        return pergunta;
    }
    private Pergunta obterPerguntaModelo4() {
        Pergunta pergunta = new Pergunta();
        pergunta.setPosicao(5);
        pergunta.setDescricao("O Brasil é de que continente?");
        pergunta.setDica("O Brasil fica na America.");
        pergunta.setJustificativa("O Brasil fica na América, mais precisamente no subcontinente América do Sul. A Argentina, Bolívia, Chile, Colômbia, Equador, Guiana, Paraguai, Peru, Suriname, Uruguai e Venezuela são outros países localizados na América do Sul.");
        
        Set<Resposta> respostas = new HashSet<>();
        Resposta resposta1 = new Resposta();
        resposta1.setId(16L);
        resposta1.setCorreta(Boolean.FALSE);
        resposta1.setDescricao("America do norte");
        respostas.add(resposta1);
        
        Resposta resposta2 = new Resposta();
        resposta2.setId(17L);
        resposta2.setCorreta(Boolean.FALSE);
        resposta2.setDescricao("America central");
        respostas.add(resposta2);
        
        Resposta resposta3 = new Resposta();
        resposta3.setId(18L);
        resposta3.setCorreta(Boolean.TRUE);
        resposta3.setDescricao("America do sul");
        respostas.add(resposta3);
        
        Resposta resposta4 = new Resposta();
        resposta4.setId(19L);
        resposta4.setCorreta(Boolean.FALSE);
        resposta4.setDescricao("Europa");
        respostas.add(resposta4);
        
        Resposta resposta5 = new Resposta();
        resposta5.setId(20L);
        resposta5.setCorreta(Boolean.FALSE);
        resposta5.setDescricao("Asia");
        respostas.add(resposta5);
        
        pergunta.setRespostas(respostas);
        
        return pergunta;
    }
    private Pergunta obterPerguntaModelo5() {
        Pergunta pergunta = new Pergunta();
        pergunta.setPosicao(5);
        pergunta.setDescricao("Quem é o pintor desse quadro e qual o nome dessa obra?");
        pergunta.setDica("O quadro também é conhecido como A Gioconda (em italiano: La Gioconda, \"a sorridente\"; em francês, La Joconde) ");
        pergunta.setJustificativa("O sorriso dela é muito famoso.");
//        Anexo anexo = new Anexo();
//        anexo.setBytes(ReadImageAsByteArray());
//        pergunta.setAnexo(anexo);
        
        Set<Resposta> respostas = new HashSet<>();
        Resposta resposta1 = new Resposta();
        resposta1.setId(21L);
        resposta1.setCorreta(Boolean.FALSE);
        resposta1.setDescricao("Autor: Michelangelo. Pintura: Mona Lisa");
        respostas.add(resposta1);
        
        Resposta resposta2 = new Resposta();
        resposta2.setId(22L);
        resposta2.setCorreta(Boolean.FALSE);
        resposta2.setDescricao("Autor: Antoine le Moiturier. Pintura: Vênus de Milo");
        respostas.add(resposta2);
        
        Resposta resposta3 = new Resposta();
        resposta3.setId(23L);
        resposta3.setCorreta(Boolean.FALSE);
        resposta3.setDescricao("Autor: Michelangelo. Pintura: Estudo para um retrato de Isabella d'Este");
        respostas.add(resposta3);
        
        Resposta resposta4 = new Resposta();
        resposta4.setId(24L);
        resposta4.setCorreta(Boolean.TRUE);
        resposta4.setDescricao("Autor: Leonardo da Vinci. Pintura: Mona Lisa");
        respostas.add(resposta4);
        
        Resposta resposta5 = new Resposta();
        resposta5.setId(25L);
        resposta5.setCorreta(Boolean.FALSE);
        resposta5.setDescricao("Autor: Leonardo da Vinci. Pintura: Estudo para um retrato de Isabella d'Este");
        respostas.add(resposta5);
        
        pergunta.setRespostas(respostas);
        
        return pergunta;
    }
    private byte[] ReadImageAsByteArray (){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            byte []buffer = new byte[1024];
            InputStream is = getClass().getResourceAsStream("MonaLisa.jpg");
            while (is.read( buffer ) != -1)  {
                out.write( buffer );
            }
        } catch (IOException e) {}
        return out.toByteArray();
    }
    //</editor-fold>
    
}