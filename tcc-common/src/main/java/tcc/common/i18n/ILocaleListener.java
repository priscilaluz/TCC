package tcc.common.i18n;

import java.util.Locale;

public interface ILocaleListener {

    /**
     * Altera o locale para o listener
     * @param locale locale da aplicação
     */
    public void changeLocale(Locale locale);
}