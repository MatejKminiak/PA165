package cz.muni.fi.pa165.currency;

import java.math.BigDecimal;
import java.util.Currency;


/**
 * This is base implementation of {@link CurrencyConvertor}.
 *
 * @author petr.adamek@embedit.cz
 */
public class CurrencyConvertorImpl implements CurrencyConvertor {

    private final ExchangeRateTable exchangeRateTable;
    //private final Logger logger = LoggerFactory.getLogger(CurrencyConvertorImpl.class);

    public CurrencyConvertorImpl(ExchangeRateTable exchangeRateTable) {
        this.exchangeRateTable = exchangeRateTable;
    }

    @Override
    public BigDecimal convert(Currency sourceCurrency, Currency targetCurrency, BigDecimal sourceAmount) {
    if (sourceCurrency == null || targetCurrency == null || sourceAmount == null){
        throw new IllegalArgumentException();
    }
    
    try {
        BigDecimal currencyRate = exchangeRateTable.getExchangeRate(sourceCurrency, targetCurrency);
    } catch(ExternalServiceFailureException ex) {
        throw new UnknownExchangeRateException('No lookup for exchange rate');
    }
    
    
    }

}
