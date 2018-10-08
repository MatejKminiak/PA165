package cz.muni.fi.pa165.currency;

import java.math.BigDecimal;
import java.util.Currency;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;


public class CurrencyConvertorImplTest {
    public static final Currency EUR = Currency.getInstance("EUR");
    public static final Currency CZK = Currency.getInstance("CZK");
    
    @Test
    public void testConvert() throws ExternalServiceFailureException {
        // Don't forget to test border values and proper rounding.
        ExchangeRateTable exchangeRateTable = mock(ExchangeRateTable.class);
        
        CurrencyConvertorImpl currencyConvertor = new CurrencyConvertorImpl(exchangeRateTable);
        when(exchangeRateTable.getExchangeRate(EUR, CZK)).thenReturn(new BigDecimal("123.456"));
        
        
        assertThat(currencyConvertor.convert(EUR, CZK, new BigDecimal("654.321"))).isEqualTo(new BigDecimal("80779.85"));
       
    
    }

    @Test
    public void testConvertWithNullSourceCurrency() throws ExternalServiceFailureException {
        ExchangeRateTable exchangeRateTable = mock(ExchangeRateTable.class);
        CurrencyConvertorImpl currencyConvertor = new CurrencyConvertorImpl(exchangeRateTable);
    
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
        BigDecimal converted = currencyConvertor.convert(null, CZK, new BigDecimal("654.321"));
        });

    }

    @Test
    public void testConvertWithNullTargetCurrency() throws ExternalServiceFailureException {
        ExchangeRateTable exchangeRateTable = mock(ExchangeRateTable.class);
        CurrencyConvertorImpl currencyConvertor = new CurrencyConvertorImpl(exchangeRateTable);
        
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
        BigDecimal converted = currencyConvertor.convert(EUR, null, new BigDecimal("654.321"));
        });
   
    }

    @Test
    public void testConvertWithNullSourceAmount() throws ExternalServiceFailureException {
        ExchangeRateTable exchangeRateTable = mock(ExchangeRateTable.class);
        CurrencyConvertorImpl currencyConvertor = new CurrencyConvertorImpl(exchangeRateTable);
    

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
        BigDecimal converted = currencyConvertor.convert(EUR, CZK, null);
        });
       
    }

    @Test
    public void testConvertWithUnknownCurrency() throws ExternalServiceFailureException {
        ExchangeRateTable exchangeRateTable = mock(ExchangeRateTable.class);
        
        when(exchangeRateTable.getExchangeRate(EUR, CZK)).thenReturn(null);
        CurrencyConvertorImpl currencyConvertor = new CurrencyConvertorImpl(exchangeRateTable);
    
        assertThatExceptionOfType(UnknownExchangeRateException.class).isThrownBy(() -> {
        BigDecimal converted = currencyConvertor.convert(EUR, CZK, new BigDecimal("654.321"));
        });
    
    }
 
    @Test
   
    public void testConvertWithExternalServiceFailure() throws ExternalServiceFailureException {
        
        ExchangeRateTable exchangeRateTable = mock(ExchangeRateTable.class);
        
        
        when(exchangeRateTable.getExchangeRate(EUR, CZK)).thenThrow(new ExternalServiceFailureException("test exception"));
        CurrencyConvertorImpl currencyConvertor = new CurrencyConvertorImpl(exchangeRateTable);
        
        assertThatExceptionOfType(UnknownExchangeRateException.class).isThrownBy(() -> {
        BigDecimal converted = currencyConvertor.convert(EUR, CZK, new BigDecimal("654.321"));    
        });
        
    }

}
