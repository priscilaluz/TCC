package br.com.tcc.common.embeddable;


import br.com.tcc.common.exception.BusinessException;
import br.com.tcc.common.util.ConstantesI18N;
/*import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
*/

public class MinutoTest {

    /*@Test
    public void oMinutoDefaultDeveSerZero() {
        Minuto minuto = new Minuto();
        assertEquals(new Integer(0), minuto.getMinutos());
    }
    
    @Test
    public void naoDeveExistirMinutoMenorQueZero() {
        try {
            Minuto minuto = new Minuto(-1);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.MINUTO_INCREMENTO_INVALIDO, ex.getMessage());
        }

        try {
            Minuto minuto = new Minuto();
            minuto.setMinutos(-1);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.MINUTO_INCREMENTO_INVALIDO, ex.getMessage());
        }
        
    }

    @Test
    public void naoDeveExistirMinutoMaiorQue1440() {
        try {
            Minuto minuto = new Minuto(1500);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.MINUTO_INCREMENTO_INVALIDO, ex.getMessage());
        }

        try {
            Minuto minuto = new Minuto();
            minuto.setMinutos(1500);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.MINUTO_INCREMENTO_INVALIDO, ex.getMessage());
        }
        
    }

    @Test
    public void deveSetarParaZeroSeOMinutoFor1440() {
        Minuto minuto = new Minuto(1440);
        assertEquals(new Integer(0), minuto.getMinutos());
        
        Minuto minuto2 = new Minuto();
        minuto2.setMinutos(1440);
        assertEquals(new Integer(0), minuto2.getMinutos());
        
    }
    
    @Test
    public void deveSomarMinuto() {
        Minuto minuto = new Minuto(500);
        Minuto minuto2 = minuto.somar(new Minuto(12));
        assertEquals(new Minuto(512), minuto2);
    }
    
    @Test
    public void deveSubtrair1440DoTotalDaSomaSeUltrapassar1440() {
        Minuto minuto = new Minuto(700);
        Minuto minuto2 = minuto.somar(new Minuto(900));
        assertEquals(new Minuto(160), minuto2);
    }
    
    @Test
    public void deveRetornarOValorMinutoEquivalenteAStringHoraMinutoPassada() {
        Minuto minuto = new Minuto();
        minuto.setHoraMinuto("08:30");
        assertEquals(minuto, new Minuto(510));
    }
    
    @Test
    public void deveRetornarOValorMinutoEquivalenteAStringHoraPassada() {
        Minuto minuto = new Minuto();
        minuto.setHoraMinuto("08");
        assertEquals(minuto, new Minuto(480));
    }
    
    @Test
    public void naoDeveGerarMinutosEscalaQuandoAStringNaoForValida() {
        try {
            Minuto minuto = new Minuto();
            minuto.setHoraMinuto("08:x1");
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.MINUTO_STRING_INVALIDO, ex.getMessage());
        }
    }
    
    @Test
    public void naoDeveGerarMinutosEscalaComMinutosMaiorQue59() {
        try {
            Minuto minuto = new Minuto();
            minuto.setHoraMinuto("08:60");
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.MINUTO_STRING_INVALIDO, ex.getMessage());
        }
    }*/
    
}
