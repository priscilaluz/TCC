/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.rest;

import tcc.common.entity.Anexo;
import tcc.common.entity.Etapa;
import tcc.common.entity.EtapaPergunta;
import tcc.common.entity.Pergunta;
import tcc.common.entity.Resposta;
import tcc.common.enums.OrdemCacaPalavra;
import tcc.common.enums.OrientacaoCacaPalavra;
import tcc.common.vo.CacaPalavra;
import tcc.common.vo.CacaPalavraLista;
import tcc.common.vo.Letra;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tcc.common.business.AnexoService;
import tcc.common.business.CursoService;
import tcc.common.enums.TempoPergunta;
import tcc.common.vo.TodasPesguntas;

/**
 *
 * @author ADM
 */
@Path("/jogoPerguntas")
@Component
public class JogoResource {
    private final Integer tamanhoMatriz = 12;
    @Autowired
    private AnexoService anexoService;
    @Autowired
    private CursoService cursoService;
    
    @GET
    @Path("/quizApresentacao")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pergunta> buscarPerguntaDaApresentacaoDoJogoQuiz() {
        List<Pergunta> perguntas = new ArrayList<>();
        perguntas.add(obterPerguntaQuizModelo1());
        perguntas.add(obterPerguntaQuizModelo2());
        perguntas.add(obterPerguntaQuizModelo3());
        perguntas.add(obterPerguntaQuizModelo4());
        perguntas.add(obterPerguntaQuizModelo5());
        return perguntas;
    }
    
    @GET
    @Path("/forcaApresentacao")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pergunta> buscarPerguntasDaApresentacaoDoJogoForca() {
        List<Pergunta> perguntas = new ArrayList<>();
        perguntas.add(obterPerguntaForcaModelo4());
        perguntas.add(obterPerguntaForcaModelo1());
        perguntas.add(obterPerguntaForcaModelo2());
        perguntas.add(obterPerguntaForcaModelo3());
        return perguntas;
    }
    
    @GET
    @Path("/apostaApresentacao")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pergunta> buscarPerguntaDaApresentacaoDoJogoAposta() {
        List<Pergunta> perguntas = new ArrayList<>();
        perguntas.add(obterPerguntaApostaModelo1());
        perguntas.add(obterPerguntaApostaModelo2());
        perguntas.add(obterPerguntaApostaModelo3());
        perguntas.add(obterPerguntaApostaModelo4());
        perguntas.add(obterPerguntaApostaModelo5());
        return perguntas;
    }
    
    @GET
    @Path("/cacaPalavraApresentacao")
    @Produces(MediaType.APPLICATION_JSON)
    public CacaPalavraLista buscarPerguntasDaApresentacaoDoJogoCacaPalavra() {
        
        List<Pergunta> perguntasTodas = new ArrayList<>();
        perguntasTodas.add(obterPerguntaCacaPalavraModelo1());
        perguntasTodas.add(obterPerguntaCacaPalavraModelo2());
        perguntasTodas.add(obterPerguntaCacaPalavraModelo3());
        perguntasTodas.add(obterPerguntaCacaPalavraModelo4());
        perguntasTodas.add(obterPerguntaCacaPalavraModelo5());
        perguntasTodas.add(obterPerguntaCacaPalavraModelo6());
        perguntasTodas.add(obterPerguntaCacaPalavraModelo7());
        perguntasTodas.add(obterPerguntaCacaPalavraModelo8());
        perguntasTodas.add(obterPerguntaCacaPalavraModelo1());
        perguntasTodas.add(obterPerguntaCacaPalavraModelo2());
        perguntasTodas.add(obterPerguntaCacaPalavraModelo3());
        perguntasTodas.add(obterPerguntaCacaPalavraModelo4());
        perguntasTodas.add(obterPerguntaCacaPalavraModelo9());
        perguntasTodas.add(obterPerguntaCacaPalavraModelo5());
        perguntasTodas.add(obterPerguntaCacaPalavraModelo6());
        perguntasTodas.add(obterPerguntaCacaPalavraModelo7());
        
        return gerarCacaPalavra(perguntasTodas);
    }
    
    @GET
    @Path("/jogoDaVelhaApresentacao")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pergunta> buscarPerguntasDaApresentacaoDoJogoDaVelha() {
        List<Pergunta> perguntas = new ArrayList<>();
        perguntas.add(obterPerguntaQuizModelo1());
        perguntas.add(obterPerguntaQuizModelo2());
        perguntas.add(obterPerguntaQuizModelo3());
        perguntas.add(obterPerguntaQuizModelo4());
        return perguntas;
    }
    
    @GET
    @Path("/jogoGerais")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pergunta> buscarPerguntaDosJogosQuizForcaAposta(@QueryParam("idEtapa") Long idEtapa) {
        Etapa etapa = cursoService.buscarEtapaPorId(idEtapa, true);
        Set<EtapaPergunta> etapasPerguntas = etapa.getEtapasPerguntas();
        List<Pergunta> perguntas = new ArrayList<>();
        for (EtapaPergunta etapaPergunta : etapasPerguntas) {
            Pergunta pergunta = etapaPergunta.getPergunta();
            if (pergunta.getAnexo() != null) {
                byte[] anexo = anexoService.obterOsBytesAnexo(pergunta.getAnexo().getId());
                pergunta.getAnexo().setBytes(anexo);
            }
            perguntas.add(pergunta);
        }
        return perguntas;
    }
    
    @GET
    @Path("/cacaPalavra")
    public CacaPalavraLista buscarPerguntasDoJogoCacaPalavra(@QueryParam("idEtapa") Long idEtapa) {
        List<Pergunta> perguntas = buscarPerguntaDosJogosQuizForcaAposta(idEtapa);
        return gerarCacaPalavra(perguntas);
    }
    
    private CacaPalavraLista gerarCacaPalavra(List<Pergunta> perguntasTodas) {
        TodasPesguntas todasPesguntas = new TodasPesguntas(perguntasTodas);
        Integer qntPerguntas = todasPesguntas.getPerguntas().size();
        List<CacaPalavra> lista = new ArrayList<>();
        
        int qntMatriz = (qntPerguntas/tamanhoMatriz) + 1;
        int qntPerguntaPorMatriz = (qntPerguntas/qntMatriz);
        int resto = qntPerguntas % qntMatriz;
        for (int i = 0; i < qntMatriz; i++) {
            int tamanhoSubList = qntPerguntaPorMatriz+(i*qntPerguntaPorMatriz);
            tamanhoSubList = (i == (qntMatriz-1))?tamanhoSubList+resto:tamanhoSubList;
            List<Pergunta> perguntas = todasPesguntas.getPerguntas().subList(i*qntPerguntaPorMatriz, tamanhoSubList);
            lista.add(new CacaPalavra(null, perguntas, tamanhoMatriz));
        }
        
        for (CacaPalavra cacaPalavra : lista) {
            List<String> respostas = new ArrayList<>();
            for (Pergunta pergunta : cacaPalavra.getPerguntas()) {
                for (Resposta resposta : pergunta.getRespostas()) {
                    if (resposta.getCorreta()){
                        respostas.add(resposta.getDescricao().toUpperCase());
                    }
                }
            }
            Letra[][] matriz = criarMatriz(respostas);

            for (int i = 0; i < tamanhoMatriz; i++) {
                for (int j = 0; j < tamanhoMatriz; j++) {
                    if (matriz[i][j] == null){
                        matriz[i][j] = new Letra(-1, getLetraAleatoria());
                    }
                }
            }
            cacaPalavra.setMatriz(matriz);
        }
        return new CacaPalavraLista(lista, qntPerguntas, todasPesguntas.getIntroducaoUnida().getIntroducao());
    }
    private Letra[][] criarMatriz(List<String> respostas) {
        Letra[][] matriz = new Letra[tamanhoMatriz][tamanhoMatriz];
        int qntOrientacao = (respostas.size() > 1)?2:3;
        OrientacaoCacaPalavra orientacao = OrientacaoCacaPalavra.from(getIntAleatoria(qntOrientacao).toString());
        List<Integer> linhasColunaPalavra = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11));
        for (String palavra : respostas) {
            int indexLinhaColuna = getIntAleatoria(linhasColunaPalavra.size());
            int linhaOuColunaPalavra = linhasColunaPalavra.get(indexLinhaColuna);
            int espacoSobrando = tamanhoMatriz - palavra.length();
            int espacoSobrandoCima = espacoSobrando>0?getIntAleatoria(espacoSobrando):0;
            
            inserirPalavra(matriz, palavra, orientacao, espacoSobrandoCima, linhaOuColunaPalavra);
            linhasColunaPalavra.remove(indexLinhaColuna);
        }
        return matriz;
    }
    private Letra[][] inserirPalavra(Letra[][] matriz, String palavra, OrientacaoCacaPalavra orientacao, int espacoSobrandoCima, int linhaOuColunaPalavra) {
        OrdemCacaPalavra ordem = OrdemCacaPalavra.from(getIntAleatoria(2).toString());
        if (OrdemCacaPalavra.INVERSA.equals(ordem)) {
            StringBuilder s = new StringBuilder(palavra);
            palavra = s.reverse().toString();
        }
        
        int x = (OrientacaoCacaPalavra.VERTICAL.equals(orientacao))?linhaOuColunaPalavra:espacoSobrandoCima;
        int y = (OrientacaoCacaPalavra.HORIZONTAL.equals(orientacao))?linhaOuColunaPalavra:espacoSobrandoCima;
        
        for (int i = 0; i < palavra.length(); i++) {
            int a = (OrientacaoCacaPalavra.VERTICAL.equals(orientacao))?x:x+i;
            int b = (OrientacaoCacaPalavra.HORIZONTAL.equals(orientacao))?y:y+i;
            matriz[a][b] = new Letra(-1, ""+palavra.charAt(i));
        }
        return matriz;
    }
    private Integer getIntAleatoria(int ateNum) {
        Random rnd = new Random();
        return (Integer) rnd.nextInt(ateNum);
    }
    private String getLetraAleatoria() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÃÕ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        int index = (int) (rnd.nextFloat() * SALTCHARS.length());
        salt.append(SALTCHARS.charAt(index));
        return salt.toString();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Perguntas do Quiz Simuldao Modelo">
    private Pergunta obterPerguntaQuizModelo1() {
        Pergunta pergunta = new Pergunta();
        pergunta.setPosicao(1);
        pergunta.setDescricao("Quanto é o valor de x na equação: x-5=10?");
        pergunta.setJustificativa("x=10+5/n x=15");
        pergunta.setDica("x=10+5");
        pergunta.setTempo(TempoPergunta.SESSENTA_SEGUNDOS);
//        Anexo anexo = new Anexo();
//        anexo.setNomeArquivo("paisagens.jpg");
//        anexo.setBytes(ReadImageAsByteArray("paisagens.jpg"));
//        pergunta.setAnexo(anexo);
        
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
    private Pergunta obterPerguntaQuizModelo2() {
        Pergunta pergunta = new Pergunta();
        pergunta.setPosicao(2);
        pergunta.setDescricao("Como se escreve o substantivo que significa aquilo que se desvia ou exclui de regras e padrões?");
        pergunta.setDica("Existe um X na resposta.");
        pergunta.setTempo(TempoPergunta.NOVENTA_SEGUNDOS);
        
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
    private Pergunta obterPerguntaQuizModelo3() {
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
    private Pergunta obterPerguntaQuizModelo4() {
        Pergunta pergunta = new Pergunta();
        pergunta.setPosicao(4);
        pergunta.setDescricao("O Brasil é de que continente?");
        pergunta.setDica("O Brasil fica na America.");
        pergunta.setJustificativa("O Brasil fica na América, mais precisamente no subcontinente América do Sul. A Argentina, Bolívia, Chile, Colômbia, Equador, Guiana, Paraguai, Peru, Suriname, Uruguai e Venezuela são outros países localizados na América do Sul.");
        pergunta.setTempo(TempoPergunta.TRINTA_SEGUNDOS);
        
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
    private Pergunta obterPerguntaQuizModelo5() {
        Pergunta pergunta = new Pergunta();
        pergunta.setPosicao(5);
        pergunta.setDescricao("Quem é o pintor desse quadro e qual o nome dessa obra?");
        pergunta.setDica("O sorriso dela é muito famoso.");
        pergunta.setJustificativa("O quadro também é conhecido como A Gioconda (em italiano: La Gioconda, \"a sorridente\"; em francês, La Joconde)");
        Anexo anexo = new Anexo();
        anexo.setNomeArquivo("MonaLisa.jpg");
        anexo.setBytes(ReadImageAsByteArray("MonaLisa.jpg"));
        pergunta.setAnexo(anexo);
        pergunta.setTempo(TempoPergunta.SESSENTA_SEGUNDOS);
        
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
    private byte[] ReadImageAsByteArray (String imagem){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            byte []buffer = new byte[1024];
            InputStream is = anexoService.downloadImgMonaLisa(imagem);
            while (is.read( buffer ) != -1)  {
                out.write( buffer );
            }
        } catch (IOException e) {}
        return out.toByteArray();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Perguntas do Forca Simuldao Modelo">
    private Pergunta obterPerguntaForcaModelo1() {
        Pergunta pergunta = new Pergunta();
        pergunta.setPosicao(1);
        pergunta.setDescricao("São consideradas a menor parte dos organismos vivos, sendo, portanto, elementos estruturais e funcionais.");
        pergunta.setJustificativa("O corpo humano é formado por uma quantidade enorme de células. As células são consideradas a menor parte dos organismos vivos, sendo, portanto, elementos estruturais e funcionais.");
        pergunta.setDica("O corpo humano é pluricelular");
        
        Set<Resposta> respostas = new HashSet<>();
        Resposta resposta = new Resposta();
        resposta.setId(1L);
        resposta.setCorreta(Boolean.TRUE);
        resposta.setDescricao("Célula");
        respostas.add(resposta);
        
        pergunta.setRespostas(respostas);
        
        return pergunta;
    }
    private Pergunta obterPerguntaForcaModelo2() {
        Pergunta pergunta = new Pergunta();
        pergunta.setPosicao(1);
        pergunta.setDescricao("Foi o 20º Presidente do Brasil. O destaque do governo de foi a chamada política desenvolvimentista, ou seja, fazer o Brasil crescer e se desenvolver “cinqüenta anos em cinco”.");
        pergunta.setJustificativa("Mineiro de Diamantina, Juscelino Kubitschek de Oliveira foi o 20º Presidente do Brasil. Mas seus feitos o consagraram como um dos principais dentre muitos que já passaram por este cargo.");
        pergunta.setDica("Era conhecido como JK.");
        
        Set<Resposta> respostas = new HashSet<>();
        Resposta resposta = new Resposta();
        resposta.setId(1L);
        resposta.setCorreta(Boolean.TRUE);
        resposta.setDescricao("Juscelino Kubitschek");
        respostas.add(resposta);
        
        pergunta.setRespostas(respostas);
        
        return pergunta;
    }
    private Pergunta obterPerguntaForcaModelo3() {
        Pergunta pergunta = new Pergunta();
        pergunta.setPosicao(3);
        pergunta.setDescricao("A palavra 'cachorro' é o que na frase: 'O cachorro era pequeno'.");
        pergunta.setJustificativa("Substantivos são palavras que dão nomes aos seres. O significado de “seres”, porém, não é o biológico. Seres, neste contexto, pode significar pessoas, lugares, grupos, indivíduos, animais, elementos mitológicos ou da natureza. O conceito de seres é muito mais abrangente do que aquele ao qual estamos habituados. Em termos gerais, os substantivos são nomes, não só de seres, mas também de emoções, sensações e diversos outros elementos que podem ser nomeados.");
        pergunta.setDica("Pode ser pronome, substantivo, adjetivo ou verbo.");
        
        Set<Resposta> respostas = new HashSet<>();
        Resposta resposta = new Resposta();
        resposta.setId(1L);
        resposta.setCorreta(Boolean.TRUE);
        resposta.setDescricao("Substantivo");
        respostas.add(resposta);
        
        pergunta.setRespostas(respostas);
        
        return pergunta;
    }
    private Pergunta obterPerguntaForcaModelo4() {
        
        
        Pergunta pergunta = new Pergunta();
        pergunta.setPosicao(4);
        pergunta.setDescricao("Qual é o nome dessa obra?");
        pergunta.setDica("O sorriso dela é muito famoso.");
        pergunta.setJustificativa("O quadro também é conhecido como A Gioconda (em italiano: La Gioconda, \"a sorridente\"; em francês, La Joconde)");
        Anexo anexo = new Anexo();
        anexo.setNomeArquivo("MonaLisa.jpg");
        anexo.setBytes(ReadImageAsByteArray("MonaLisa.jpg"));
        pergunta.setAnexo(anexo);
        
        Set<Resposta> respostas = new HashSet<>();
        Resposta resposta = new Resposta();
        resposta.setId(1L);
        resposta.setCorreta(Boolean.TRUE);
        resposta.setDescricao("MonaLisa");
        respostas.add(resposta);
        
        pergunta.setRespostas(respostas);
        
        return pergunta;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Perguntas do Aposta Simuldao Modelo">
    private Pergunta obterPerguntaApostaModelo1() {
        Pergunta pergunta = new Pergunta();
        pergunta.setPosicao(1);
        pergunta.setDescricao("Quanto é o valor de x na equação: x-5=10?");
        pergunta.setJustificativa("x=10+5/n x=15");
        pergunta.setDica("x=10+5");
        pergunta.setTempo(TempoPergunta.SESSENTA_SEGUNDOS);
        
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
        
        pergunta.setRespostas(respostas);
        
        return pergunta;
    }
    private Pergunta obterPerguntaApostaModelo2() {
        Pergunta pergunta = new Pergunta();
        pergunta.setPosicao(2);
        pergunta.setDescricao("Como se escreve o substantivo que significa aquilo que se desvia ou exclui de regras e padrões?");
        pergunta.setDica("Existe um X na resposta.");
        pergunta.setTempo(TempoPergunta.SESSENTA_SEGUNDOS);
        
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
        
        pergunta.setRespostas(respostas);
        
        return pergunta;
    }
    private Pergunta obterPerguntaApostaModelo3() {
        Pergunta pergunta = new Pergunta();
        pergunta.setPosicao(3);
        pergunta.setDescricao("\"Cinquenta anos em cinco\" foi o bordão de qual popular presidente?");
        pergunta.setJustificativa("O Governo Juscelino Kubitschek é o período de governo vivido entre 1956 e 1961[1]. Sua eleição foi marcada pelo plano de ação \"Cinquenta anos em cinco\", marca do desenvolvimentismo, já que o ideal era trazer ao Brasil o desenvolvimento econômico e social. Segundo JK, se com outros governantes este processo levaria cinquenta anos, com ele levaria apenas cinco[2]. Trouxe diversas empresas estrangeiras para o país, entre elas, as automobilísticas Chrysler e Ford através do Grupo Executivo da Indústria Automobilística, já que ele queria incentivar o comércio de carros, além de televisões e outros bens de consumo. Em resumo, procurou alinhar a economia brasileira à economia americana.");
        pergunta.setDica("Segundo JK, se com outros governantes este processo levaria cinquenta anos, com ele levaria apenas cinco");
        pergunta.setTempo(TempoPergunta.TRINTA_SEGUNDOS);
         
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
        resposta4.setId(15L);
        resposta4.setCorreta(Boolean.TRUE);
        resposta4.setDescricao("Juscelino Kubitschek");
        respostas.add(resposta4);
        
        pergunta.setRespostas(respostas);
        
        return pergunta;
    }
    private Pergunta obterPerguntaApostaModelo4() {
        Pergunta pergunta = new Pergunta();
        pergunta.setPosicao(5);
        pergunta.setDescricao("O Brasil é de que continente?");
        pergunta.setDica("O Brasil fica na America.");
        pergunta.setJustificativa("O Brasil fica na América, mais precisamente no subcontinente América do Sul. A Argentina, Bolívia, Chile, Colômbia, Equador, Guiana, Paraguai, Peru, Suriname, Uruguai e Venezuela são outros países localizados na América do Sul.");
        pergunta.setTempo(TempoPergunta.NOVENTA_SEGUNDOS);
        
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
        
        pergunta.setRespostas(respostas);
        
        return pergunta;
    }
    private Pergunta obterPerguntaApostaModelo5() {
        Pergunta pergunta = new Pergunta();
        pergunta.setPosicao(5);
        pergunta.setDescricao("Quem é o pintor desse quadro e qual o nome dessa obra?");
        pergunta.setDica("O quadro também é conhecido como A Gioconda (em italiano: La Gioconda, \"a sorridente\"; em francês, La Joconde) ");
        pergunta.setJustificativa("O sorriso dela é muito famoso.");
        Anexo anexo = new Anexo();
        anexo.setNomeArquivo("MonaLisa.jpg");
        anexo.setBytes(ReadImageAsByteArray("MonaLisa.jpg"));
        pergunta.setAnexo(anexo);
        pergunta.setTempo(TempoPergunta.SESSENTA_SEGUNDOS);
        
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
        resposta3.setDescricao("Autor: Leonardo da Vinci. Pintura: Estudo para um retrato de Isabella d'Este");
        respostas.add(resposta3);
        
        Resposta resposta4 = new Resposta();
        resposta4.setId(24L);
        resposta4.setCorreta(Boolean.TRUE);
        resposta4.setDescricao("Autor: Leonardo da Vinci. Pintura: Mona Lisa");
        respostas.add(resposta4);
        
        pergunta.setRespostas(respostas);
        
        return pergunta;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Perguntas do Caça Palavra Simuldao Modelo">
    private Pergunta obterPerguntaCacaPalavraModelo1() {
        Pergunta pergunta = new Pergunta();
        pergunta.setPosicao(1);
        pergunta.setDescricao("São consideradas a menor parte dos organismos vivos, sendo, portanto, elementos estruturais e funcionais.");
        pergunta.setJustificativa("O corpo humano é formado por uma quantidade enorme de células. As células são consideradas a menor parte dos organismos vivos, sendo, portanto, elementos estruturais e funcionais.");
        pergunta.setDica("O corpo humano é pluricelular");
        pergunta.setTempo(TempoPergunta.SESSENTA_SEGUNDOS);
        
        Set<Resposta> respostas = new HashSet<>();
        Resposta resposta = new Resposta();
        resposta.setId(1L);
        resposta.setCorreta(Boolean.TRUE);
        resposta.setDescricao("Célula");
        respostas.add(resposta);
        
        pergunta.setRespostas(respostas);
        
        return pergunta;
    }
    private Pergunta obterPerguntaCacaPalavraModelo2() {
        Pergunta pergunta = new Pergunta();
        pergunta.setPosicao(2);
        pergunta.setDescricao("Primeiro nome do 20º Presidente do Brasil. O destaque do governo de foi a chamada política desenvolvimentista, ou seja, fazer o Brasil crescer e se desenvolver “cinqüenta anos em cinco”.");
        pergunta.setJustificativa("Mineiro de Diamantina, Juscelino Kubitschek de Oliveira foi o 20º Presidente do Brasil. Mas seus feitos o consagraram como um dos principais dentre muitos que já passaram por este cargo.");
        pergunta.setDica("Kubitschek");
        pergunta.setTempo(TempoPergunta.SESSENTA_SEGUNDOS);
        
        Set<Resposta> respostas = new HashSet<>();
        Resposta resposta = new Resposta();
        resposta.setId(1L);
        resposta.setCorreta(Boolean.TRUE);
        resposta.setDescricao("Juscelino");
        respostas.add(resposta);
        
        pergunta.setRespostas(respostas);
        
        return pergunta;
    }
    private Pergunta obterPerguntaCacaPalavraModelo3() {
        Pergunta pergunta = new Pergunta();
        pergunta.setPosicao(3);
        pergunta.setDescricao("A palavra 'pequeno' é o que na frase: 'O cachorro era pequeno'.");
        pergunta.setJustificativa("Substantivos são palavras que dão nomes aos seres. O significado de “seres”, porém, não é o biológico. Seres, neste contexto, pode significar pessoas, lugares, grupos, indivíduos, animais, elementos mitológicos ou da natureza. O conceito de seres é muito mais abrangente do que aquele ao qual estamos habituados. Em termos gerais, os substantivos são nomes, não só de seres, mas também de emoções, sensações e diversos outros elementos que podem ser nomeados.");
        pergunta.setDica("Pode ser pronome, substantivo, adjetivo ou verbo.");
        pergunta.setTempo(TempoPergunta.SESSENTA_SEGUNDOS);
        
        Set<Resposta> respostas = new HashSet<>();
        Resposta resposta = new Resposta();
        resposta.setId(1L);
        resposta.setCorreta(Boolean.TRUE);
        resposta.setDescricao("Adjetivo");
        respostas.add(resposta);
        
        pergunta.setRespostas(respostas);
        
        return pergunta;
    }
    private Pergunta obterPerguntaCacaPalavraModelo4() {
        Pergunta pergunta = new Pergunta();
        pergunta.setPosicao(3);
        pergunta.setDescricao("Qual o sobrenome do presidente do Brasil?");
        pergunta.setJustificativa("Era vice de Dilma e virou presidente após o impeachment.");
        pergunta.setDica("Michel é o primeiro nome.");
        
        Set<Resposta> respostas = new HashSet<>();
        Resposta resposta = new Resposta();
        resposta.setId(1L);
        resposta.setCorreta(Boolean.TRUE);
        resposta.setDescricao("Temer");
        respostas.add(resposta);
        
        pergunta.setRespostas(respostas);
        
        return pergunta;
    }
    private Pergunta obterPerguntaCacaPalavraModelo5() {
        Pergunta pergunta = new Pergunta();
        pergunta.setPosicao(3);
        pergunta.setDescricao("É uma série sobre seis amigos em Nova York, seus caminhos pela vida e como eles aprendem a crescer enquanto eles se aproximam da terceira década da vida deles. Tudo com a ajuda de cada um deles para conseguirem passar pelos obstáculos que a vida naturalmente traz a nós. Os amigos são: Monica, Rachel, Phoebe, Ross, Joey e Chandler. ");
        pergunta.setJustificativa("justificativa.");
        pergunta.setDica("Amigos em Inglês.");
        
        Set<Resposta> respostas = new HashSet<>();
        Resposta resposta = new Resposta();
        resposta.setId(1L);
        resposta.setCorreta(Boolean.TRUE);
        resposta.setDescricao("Friends");
        respostas.add(resposta);
        
        pergunta.setRespostas(respostas);
        
        return pergunta;
    }
    private Pergunta obterPerguntaCacaPalavraModelo6() {
        Pergunta pergunta = new Pergunta();
        pergunta.setPosicao(3);
        pergunta.setDescricao("O Brasil é de que continente?");
        pergunta.setDica("O Brasil fica na America.");
        pergunta.setJustificativa("O Brasil fica na América, mais precisamente no subcontinente América do Sul. A Argentina, Bolívia, Chile, Colômbia, Equador, Guiana, Paraguai, Peru, Suriname, Uruguai e Venezuela são outros países localizados na América do Sul.");
        
        Set<Resposta> respostas = new HashSet<>();
        Resposta resposta = new Resposta();
        resposta.setId(1L);
        resposta.setCorreta(Boolean.TRUE);
        resposta.setDescricao("Americadosul");
        respostas.add(resposta);
        
        pergunta.setRespostas(respostas);
        
        return pergunta;
    }
    private Pergunta obterPerguntaCacaPalavraModelo7() {
        Pergunta pergunta = new Pergunta();
        pergunta.setPosicao(3);
        pergunta.setDescricao("Foi um pintor, escultor, poeta e arquiteto italiano, considerado um dos maiores criadores da história da arte do ocidente. Algumas de suas obras são: A escultura 'David', 'Teto da Capela Sistina' e 'A Criação de Adão'.");
        pergunta.setDica("Começa com M.");
        pergunta.setJustificativa("Michelangelo di Lodovico Buonarroti Simoni (Caprese, 6 de março de 1475 — Roma, 18 de fevereiro de 1564), mais conhecido simplesmente como Michelangelo ou Miguel Ângelo, foi um pintor, escultor, poeta e arquiteto italiano, considerado um dos maiores criadores da história da arte do ocidente..");
        
        Set<Resposta> respostas = new HashSet<>();
        Resposta resposta = new Resposta();
        resposta.setId(1L);
        resposta.setCorreta(Boolean.TRUE);
        resposta.setDescricao("Michelangelo");
        respostas.add(resposta);
        
        pergunta.setRespostas(respostas);
        
        return pergunta;
    }
    private Pergunta obterPerguntaCacaPalavraModelo8() {
        Pergunta pergunta = new Pergunta();
        pergunta.setPosicao(3);
        pergunta.setDescricao("Foi um cantor, ator, e produtor americano, amplamente considerado um dos mais populares e influentes artistas musicais do século 20. O seu primeiro nome é Frank e algumas de suas músicas são: 'New York, New York' e 'Fly Me to the Moon'.");
        pergunta.setDica("Começa com S.");
        pergunta.setJustificativa("Sinatra");
        
        Set<Resposta> respostas = new HashSet<>();
        Resposta resposta = new Resposta();
        resposta.setId(1L);
        resposta.setCorreta(Boolean.TRUE);
        resposta.setDescricao("Sinatra");
        respostas.add(resposta);
        
        pergunta.setRespostas(respostas);
        
        return pergunta;
    }
    private Pergunta obterPerguntaCacaPalavraModelo9() {
        Pergunta pergunta = new Pergunta();
        pergunta.setPosicao(9);
        pergunta.setDescricao("Qual é o nome dessa obra?");
        pergunta.setDica("O sorriso dela é muito famoso.");
        pergunta.setJustificativa("O quadro também é conhecido como A Gioconda (em italiano: La Gioconda, \"a sorridente\"; em francês, La Joconde)");
        Anexo anexo = new Anexo();
        anexo.setNomeArquivo("MonaLisa.jpg");
        anexo.setBytes(ReadImageAsByteArray("MonaLisa.jpg"));
        pergunta.setAnexo(anexo);
        
        Set<Resposta> respostas = new HashSet<>();
        Resposta resposta = new Resposta();
        resposta.setId(1L);
        resposta.setCorreta(Boolean.TRUE);
        resposta.setDescricao("MonaLisa");
        respostas.add(resposta);
        
        pergunta.setRespostas(respostas);
        
        return pergunta;
    }
    //</editor-fold>
    
}
