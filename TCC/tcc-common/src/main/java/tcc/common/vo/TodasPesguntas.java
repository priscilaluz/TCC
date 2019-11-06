/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.common.vo;

import tcc.common.entity.Pergunta;
import java.util.List;

/**
 *
 * @author ADM
 */
public class TodasPesguntas {
    private static final String EXEMPLO_1 = "No mundo artístico: Beatriz, Bruna e Patrícia são três garotas talentosas que estão tentando entrar no mundo artístico, cada qual numa atividade diferente (uma delas quer ser dançarina). Entretanto, por enquanto elas ainda não podem deixar seus empregos (uma delas é faxineira) para se dedicar à atividade artística a que almejam, Com base nas dicas e informações que fornecemos, tente descobrir o nome de cada garota, a profissão atual de cada uma e o que elas desejam ser no mundo artístico. Dicas: \n" +"a) Patrícia quer ser atriz \n" +"b) A bancária quer ser cantora \n" +"c) Bruna não é bancária nem enfermeira";
    private static final String EXEMPLO_2 = "Pausa para o lanche: Bruno, Carlos, Marcos e Ricardo trabalham em 4 escritórios diferentes, no quinto andar de um prédio. Todos os dias, na hora do intervalo para o café, um carrinho vai do escritório 501 ao 504 distribuindo os lanches costumeiros de cada empregado. Usando as dicas abaixo, tente descobrir o nome dos empregados, a função deles, o número de seu escritório onde trabalham e o que sempre lancham. \n" +"a) Marcos é digitador, mas não lancha misto-quente;\n" +"b) Carlos não lancha biscoito nem pão doce; \n" +"c) A pessoa que lancha café trabalha na sala 502, mas não é o recepcionista; \n" +"d) Os quatro empregados são: o vendedor, o que trabalha na sala 501, o que lancha misto quente e o que se chama Ricardo; \n" +"e) Bruno não é a pessoa trabalha na sala 504 (que sempre lancha pão doce); \n" +"f) Ricardo não é o arquivista; \n" +"g) Carlos trabalha na sala 503;";
    private static final String EXEMPLO_3 = "Sob proteção: Cinco mulheres famosas não saem de casa sem proteção de um guarda costas. A partir das informações fornecidas abaixo, descubra a idade, a profissão e o nome do guarda-costas de cada uma dessas cinco mulheres. \n" +"a) Arnaldo protege Elisa Eller, a modelo, que não tem 22 anos. \n" +"b) Camila Castro, a tenista, não é protegida nem por Fabrício nem por José. \n" +"c) A mulher de 24 anos, que é protegida por Fabrício, não é Laura Lopes. \n" +"d) Solange Souza tem 20 anos, mas não é apresentadora de TV nem é protegida por Marcelo. \n" +"e) A cantora tem 21 anos, mas não é protegida por José nem por Roberto.\n" +"f) Uma das famosas se chama Adélia Alves. \n" +"g) As famosas possuem as seguintes profissões: tenista, cantora, atriz, modelo e apresentadora de TV. Uma delas tem 23 anos.";
    private List<Pergunta> perguntas;
    private IntroducaoUnida introducaoUnida;

    public TodasPesguntas() {}

    public TodasPesguntas(List<Pergunta> perguntas) {
        this.perguntas = perguntas;
        this.introducaoUnida = new IntroducaoUnida(null, this.perguntas);
        criarIntroducaoUnida();
    }
    
    private void criarIntroducaoUnida() {
        boolean ehExemplo1 = true, ehExemplo2 = true, ehExemplo3 = true;
        for (Pergunta p : perguntas) {
            ehExemplo1 = !ehExemplo1?false:textoIgual(EXEMPLO_1, p.getDescricao());
            ehExemplo2 = !ehExemplo2?false:textoIgual(EXEMPLO_2, p.getDescricao());
            ehExemplo3 = !ehExemplo3?false:textoIgual(EXEMPLO_3, p.getDescricao());
        }
        if (ehExemplo1) {
            this.introducaoUnida = new IntroducaoUnida(EXEMPLO_1, this.perguntas);
            cadastraPartePergunta(EXEMPLO_1);
        } else if (ehExemplo2) {
            this.introducaoUnida = new IntroducaoUnida(EXEMPLO_2, this.perguntas);
            cadastraPartePergunta(EXEMPLO_2);
        } else if (ehExemplo3) {
            this.introducaoUnida = new IntroducaoUnida(EXEMPLO_3, this.perguntas);
            cadastraPartePergunta(EXEMPLO_3);
        } else {
            cadastraPartePergunta(null);
        }
    }

    public boolean textoIgual(String texto1, String texto2) {
        for (int i = 0; i < texto1.split("\n").length; i++) {
            String[] descricao = texto2.split("\n");
            String[] ex1 = texto1.split("\n");
            if (descricao[i].indexOf(ex1[i])!=0) {
                return false;
            }
        }
        return true;
    }
    public void cadastraPartePergunta(String removerParte) {
        for (Pergunta pergunta : perguntas) {
            pergunta.setPartePergunta(parteTexto(pergunta.getDescricao(), removerParte));
        }
    }
    public String parteTexto(String inteiro, String introducao) {
        if (introducao != null) {
            int tamanhoInteiro = inteiro.split("\n").length;
            int tamanhoIntroducao = introducao.split("\n").length;
            int tamanhoParte = tamanhoInteiro - tamanhoIntroducao;
            if (tamanhoParte > 0) {
                StringBuilder s = new StringBuilder();
                for (int i = tamanhoIntroducao; i < tamanhoInteiro; i++) {
                    s.append(inteiro.split("\n")[i]).append("\n");
                }
                return s.toString();
            }
        }
        return inteiro;
    }
    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }

    public IntroducaoUnida getIntroducaoUnida() {
        return introducaoUnida;
    }

    public void setIntroducaoUnida(IntroducaoUnida introducaoUnida) {
        this.introducaoUnida = introducaoUnida;
    }

    
}
