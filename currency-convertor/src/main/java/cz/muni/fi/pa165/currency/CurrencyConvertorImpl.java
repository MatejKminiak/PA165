package cz.muni.fi.pa165.currency;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

/**
 * This is base implementation of {@link CurrencyConvertor}.
 *
 * @author petr.adamek@embedit.cz
 */
public class CurrencyConvertorImpl implements CurrencyConvertor {

    private final ExchangeRateTable exchangeRateTable;
    private final Logger logger = LoggerFactory.getLogger(CurrencyConvertorImpl.class);

    public CurrencyConvertorImpl(ExchangeRateTable exchangeRateTable) {
        this.exchangeRateTable = exchangeRateTable;
    }

    @Override
    public BigDecimal convert(Currency sourceCurrency, Currency targetCurrency, BigDecimal sourceAmount) {
        logger.trace("Convert method called");
        if (sourceCurrency == null || targetCurrency == null || sourceAmount == null){
            throw new IllegalArgumentException();
        }
        BigDecimal currencyRate;
        try {
            currencyRate = exchangeRateTable.getExchangeRate(sourceCurrency, targetCurrency);
            if (currencyRate == null) {
                String msg = "Not available currency rate lookup for given currencies";
                UnknownExchangeRateException ex = new UnknownExchangeRateException(msg);
                logger.warn(msg, ex);
                throw ex;
            }
        } catch(ExternalServiceFailureException ex) {
            String msg = "Not available currency rate lookup due to external service failure exception";
            logger.error(msg, ex);
            throw new UnknownExchangeRateException(msg, ex);        
        }
       
        return sourceAmount.multiply(currencyRate).setScale(2, RoundingMode.HALF_EVEN);
        
    }

    

}
