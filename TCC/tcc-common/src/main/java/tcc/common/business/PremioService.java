package tcc.common.business;

import tcc.common.entity.Premio;
import tcc.common.entity.RelatorioEtapa;
import tcc.common.entity.Usuario;

/**
 *
 * @author ADM
 */
public interface PremioService {

    Premio cadastrarPremio(Usuario usuario);
    
    void atualizarPremio(RelatorioEtapa relatorioEtapa, Integer qntCursoConcluido, Integer qntPontosAcumulados);

//    void atualizarPremio(Long idPremio, Integer qndPergFacil, Integer qndPergMedio,
//            Integer qndPergDificil, Integer qntEtapaVencida, Integer qntCursoConcluido,
//            Integer qntEtapaSemPulo, Integer qntEtapaSemDica, Integer qntPontosAcumulados);

    Premio buscarPremioPorId(Long idPremio);

    Premio buscarPremioPorFiltro(Long idUsuario);
}
