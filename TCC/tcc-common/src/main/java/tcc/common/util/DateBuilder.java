/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.common.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Classe utilitária para construção de datas. Exemplo:
 *
 * <blockquote><pre>
 * // amanhã às 8 horas
 * Date data1 = DateBuilder.tomorrow().at(8, 0).getTime();
 *
 * // no dia 21/12/2012 ao meio-dia
 * Date data2 = DateBuilder.on(21, 12, 2012).atNoon().getTime();
 *
 * // no dia 21/12/2013 ao meio-dia
 * Date data3 = DateBuilder.on(data2).plus(1).years().getTime();
 * </pre></blockquote>
 *
 * 
 */
public final class DateBuilder {
    private static final int NOON = 12;
    
    private Calendar calendar;
    private int amountToSum;

    private DateBuilder() {
        this.calendar = Calendar.getInstance();
        this.amountToSum = 0;
    }

    // <editor-fold defaultstate="collapsed" desc="Métodos Estáticos">
    /**
     * Inicia o DateBuilder com a data e a hora atual.
     */
    public static DateBuilder now() {
        return new DateBuilder();
    }

    /**
     * Inicia o DateBuilder com a data atual e a hora zerada.
     */
    public static DateBuilder today() {
        return new DateBuilder().atMidnight();
    }

    /**
     * Inicia o DateBuilder com a data de ontem e a hora zerada.
     */
    public static DateBuilder yesterday() {
        DateBuilder builder = today();
        builder.calendar.add(Calendar.DATE, -1);
        return builder;
    }

    /**
     * Inicia o DateBuilder com a data de amanhã e a hora zerada.
     */
    public static DateBuilder tomorrow() {
        DateBuilder builder = today();
        builder.calendar.add(Calendar.DATE, 1);
        return builder;
    }

    /**
     * Inicia o DateBuilder com a data informada e a hora zerada. O dia deve ser
     * um valor de 1 a 31 e o mês deve ser um valor de 1 a 12.
     */
    public static DateBuilder on(int day, int month, int year) {
        DateBuilder builder = new DateBuilder();
        builder.calendar.clear();
        builder.calendar.set(year, month - 1, day);
        return builder;
    }

    /**
     * Inicia o DateBuilder com a data informada.
     */
    public static DateBuilder on(Date date) {
        DateBuilder builder = new DateBuilder();
        builder.calendar.setTime(date);
        return builder;
    }

    /**
     * Inicia o DateBuilder no dia 1 do mês corrente.
     */
    public static DateBuilder onFirstOfCurrentMonth() {
        DateBuilder builder = today();
        builder.calendar.set(Calendar.DAY_OF_MONTH, 1);
        return builder;
    }

    /**
     * Inicia o DateBuilder no dia 1 de Janeiro do ano corrente.
     */
    public static DateBuilder onFirstOfCurrentYear() {
        DateBuilder builder = onFirstOfCurrentMonth();
        builder.calendar.set(Calendar.MONTH, Calendar.JANUARY);
        return builder;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos Públicos">
    /**
     * Zera a hora, minuto, segundo e milissegundo de uma data.
     */
    public DateBuilder atMidnight() {
        this.calendar.set(Calendar.HOUR_OF_DAY, 0);
        this.calendar.set(Calendar.MINUTE, 0);
        this.calendar.set(Calendar.SECOND, 0);
        this.calendar.set(Calendar.MILLISECOND, 0);
        return this;
    }

    /**
     * Define a hora de uma data para meio-dia.
     */
    public DateBuilder atNoon() {
        this.calendar.set(Calendar.HOUR_OF_DAY, NOON);
        this.calendar.set(Calendar.MINUTE, 0);
        this.calendar.set(Calendar.SECOND, 0);
        this.calendar.set(Calendar.MILLISECOND, 0);
        return this;
    }

    /**
     * Define a hora, minuto de uma data. O segundo e milissegundo são zerados.
     */
    public DateBuilder at(int hour, int minute) {
        this.atMidnight();
        this.calendar.set(Calendar.HOUR_OF_DAY, hour);
        this.calendar.set(Calendar.MINUTE, minute);
        return this;
    }

    /**
     * Define a hora, minuto e segundo de uma data. O milissegundo é zerado.
     */
    public DateBuilder at(int hour, int minute, int second) {
        this.atMidnight();
        this.calendar.set(Calendar.HOUR_OF_DAY, hour);
        this.calendar.set(Calendar.MINUTE, minute);
        this.calendar.set(Calendar.SECOND, second);
        return this;
    }

    /**
     * Soma o valor informado à data registrada. Deve ser usado em conjunção com
     * um dos métodos: <code>years</code>, <code>months</code>, <code>days</code>,
     * <code>hours</code>, <code>minutes</code>, <code>seconds</code>.
     */
    public DateBuilder plus(int amount) {
        this.amountToSum = Math.abs(amount);
        return this;
    }

    /**
     * Subtrai o valor informado da data registrada. Deve ser usado em conjunção com
     * um dos métodos: <code>years</code>, <code>months</code>, <code>days</code>,
     * <code>hours</code>, <code>minutes</code>, <code>seconds</code>.
     */
    public DateBuilder minus(int amount) {
        this.amountToSum = Math.abs(amount) * -1;
        return this;
    }

    /**
     * Soma o valor informado com o método <code>plus</code> aos anos da data.
     */
    public DateBuilder years() {
        return this.sumTo(Calendar.YEAR);
    }

    /**
     * Soma o valor informado com o método <code>plus</code> aos meses da data.
     */
    public DateBuilder months() {
        return this.sumTo(Calendar.MONTH);
    }

    /**
     * Soma o valor informado com o método <code>plus</code> aos dias da data.
     */
    public DateBuilder days() {
        return this.sumTo(Calendar.DATE);
    }

    /**
     * Soma o valor informado com o método <code>plus</code> às horas da data.
     */
    public DateBuilder hours() {
        return this.sumTo(Calendar.HOUR_OF_DAY);
    }

    /**
     * Soma o valor informado com o método <code>plus</code> aos minutos da data.
     */
    public DateBuilder minutes() {
        return this.sumTo(Calendar.MINUTE);
    }

    /**
     * Soma o valor informado com o método <code>plus</code> aos segundos da data.
     */
    public DateBuilder seconds() {
        return this.sumTo(Calendar.SECOND);
    }

    public Date getTime() {
        return this.getTime(TimeZone.getDefault());
    }
    
    public Date getTime(TimeZone timeZone) {
        this.calendar.setTimeZone(timeZone);
        return this.calendar.getTime();
    }
    
    public long diffInSeconds(Date dEnd) {
        Calendar cEnd = Calendar.getInstance();
        cEnd.setTimeZone(this.calendar.getTimeZone());
        cEnd.setTime(dEnd);

        return (cEnd.getTimeInMillis() - this.calendar.getTimeInMillis()) / 1000;
    }
    
    public boolean equalsWithoutTime(Date other) {
        Calendar c1 = Calendar.getInstance();
        c1.setTimeZone(this.calendar.getTimeZone());
        c1.setTime(this.calendar.getTime());
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        c1.set(Calendar.MILLISECOND, 0);
        
        
        Calendar c2 = Calendar.getInstance();
        c2.setTimeZone(this.calendar.getTimeZone());
        c2.setTime(other);
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);
        c2.set(Calendar.MILLISECOND, 0);
        
        return c1.getTimeInMillis() == c2.getTimeInMillis();
        
    }
    
    public List<Date> listDatesBetween(Date dEnd, boolean withWeekEnd) {
        List<Date> dates = new ArrayList<Date>();
        if (this.equalsWithoutTime(dEnd)) {
            return dates;
        }
        if (this.getTime().after(dEnd)) {
            return dates;
        }
        
        DateBuilder builder = DateBuilder.on(this.getTime());
        while (!builder.equalsWithoutTime(dEnd)) {
            builder.calendar.add(Calendar.DATE, 1);
            boolean isEnd = builder.equalsWithoutTime(dEnd);
            boolean isWeekEnd = builder.calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || builder.calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
            if (!isEnd && (withWeekEnd || !isWeekEnd)) {
                dates.add(builder.getTime());
            }
        }
        
        return dates;
    }
    
    public boolean isWeekEnd() {
        return this.calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || this.calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos Privados">
    private DateBuilder sumTo(int type) {
        this.calendar.add(type, this.amountToSum);
        this.amountToSum = 0;
        return this;
    }// </editor-fold>
}
