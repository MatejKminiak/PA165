/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165;

import cz.muni.fi.pa165.currency.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 *
 * @author kmini
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("cz.muni.fi.pa165.logger")
public class JavaConfig {
    
    
    @Bean(name = "ExchangeRateTableJConfig")
    public ExchangeRateTable ExchangeRateTable() {
        return new ExchangeRateTableImpl();
    }
    
    @Bean(name = "CurrencyConvertorJConfig")
    public CurrencyConvertor CurrencyConvertor(ExchangeRateTable exchangeRateTable) {
        return new CurrencyConvertorImpl(exchangeRateTable);
    }
}
