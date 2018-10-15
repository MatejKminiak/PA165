/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165;

import cz.muni.fi.pa165.currency.CurrencyConvertor;
import java.math.BigDecimal;
import java.util.Currency;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author kmini
 */
public class MainXml {
    
    
    public static void main(String args[]){
     
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        CurrencyConvertor currencyConvertor = applicationContext.getBean(CurrencyConvertor.class);
        System.err.println(currencyConvertor.convert(Currency.getInstance("EUR"), Currency.getInstance("CZK"), new BigDecimal("5")));
    }
    
}
