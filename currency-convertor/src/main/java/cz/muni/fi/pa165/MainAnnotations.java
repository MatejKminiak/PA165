/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165;

import cz.muni.fi.pa165.currency.CurrencyConvertor;
import cz.muni.fi.pa165.currency.CurrencyConvertorImpl;
import cz.muni.fi.pa165.currency.ExchangeRateTableImpl;
import java.math.BigDecimal;
import java.util.Currency;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author kmini
 */
public class MainAnnotations {
    
    public static void main(String args[]){
        
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CurrencyConvertorImpl.class,ExchangeRateTableImpl.class);
        CurrencyConvertor currencyConvertor = applicationContext.getBean(CurrencyConvertorImpl.class);
        System.err.println(currencyConvertor.convert(Currency.getInstance("EUR"), Currency.getInstance("CZK"), new BigDecimal("10")));

    }
}
