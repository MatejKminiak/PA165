package cz.muni.fi.pa165.currency;

import java.math.BigDecimal;
import org.junit.Test;
//import static org.junit.Assert.*;
import  java.util.Currency;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.*;




public class CurrencyConvertorImplTest {
    
    @Test
    public void testConvert() {
        // Don't forget to test border values and proper rounding.
        
    CurrencyConvertorImpl cctest = new CurrencyConvertorImpl(mock(ExchangeRateTable.class));
    Currency sourceCurrency = Currency.getInstance("EUR");
    Currency finalCurrency = Currency.getInstance("CZK");
    assertThat(cctest.convert(sourceCurrency, finalCurrency, new BigDecimal("15.29")).equals(new BigDecimal("382.25")));
    
    }

    @Test
    public void testConvertWithNullSourceCurrency() {
    CurrencyConvertorImpl cctest = new CurrencyConvertorImpl(mock(ExchangeRateTable.class));
    Currency finalCurrency = Currency.getInstance("CZK");
    
    
    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
    BigDecimal converted = cctest.convert(null, finalCurrency, new BigDecimal("15.29"));
    });
   
    }

    @Test
    public void testConvertWithNullTargetCurrency() {
    CurrencyConvertorImpl cctest = new CurrencyConvertorImpl(mock(ExchangeRateTable.class));
    Currency sourceCurrency = Currency.getInstance("CZK");
    
    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
    BigDecimal converted = cctest.convert(sourceCurrency, null, new BigDecimal("15.29"));
    });
   
    }

    @Test
    public void testConvertWithNullSourceAmount() {
    CurrencyConvertorImpl cctest = new CurrencyConvertorImpl(mock(ExchangeRateTable.class));
    Currency sourceCurrency = Currency.getInstance("EUR");
    Currency targetCurrency = Currency.getInstance("CZK");
    
    
    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
    BigDecimal converted = cctest.convert(sourceCurrency, targetCurrency, null);
    });
       
    }

    @Test
    public void testConvertWithUnknownCurrency() {
    CurrencyConvertorImpl cctest = new CurrencyConvertorImpl(mock(ExchangeRateTable.class));
    Currency sourceCurrency = Currency.getInstance("MOZNO");
    Currency targetCurrency = Currency.getInstance("CZK");
    
    assertThatExceptionOfType(UnknownExchangeRateException.class).isThrownBy(() -> {
    BigDecimal converted = cctest.convert(sourceCurrency, targetCurrency, null);
    });
    
    }

    @Test
    public void testConvertWithExternalServiceFailure() {
        fail("Test is not implemented yet.");
    }

}
