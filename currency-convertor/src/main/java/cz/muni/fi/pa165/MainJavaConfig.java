/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165;


import cz.muni.fi.pa165.currency.*;
import java.math.BigDecimal;
import java.util.Currency;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author kmini
 */
//@Configuration
//@ComponentScan("cz.muni.fi.pa165.*")
//@EnableAspectJAutoProxy
public class MainJavaConfig {
    
  
    public static void main(String args[]){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
        CurrencyConvertor currencyConvertor = applicationContext.getBean("CurrencyConvertorJConfig", CurrencyConvertor.class);
        System.err.println(currencyConvertor.convert(Currency.getInstance("EUR"), Currency.getInstance("CZK"), new BigDecimal("4.5")));
    }
}
