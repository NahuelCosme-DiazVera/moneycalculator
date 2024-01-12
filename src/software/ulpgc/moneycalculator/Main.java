package software.ulpgc.moneycalculator;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CurrencyLoader loader = new TsvFileCurrencyLoader(new File("currencies.tsv"));
        List<Currency> currencies = loader.load();
        for (Currency currency : currencies) {
            System.out.println(currency);
        }
        List<ExchangeRate> exchangeRates = TsvFileExchangeRateLoader.with(new File("exchangerates.tsv")).load();
        for (ExchangeRate exchangeRate : exchangeRates) {
            System.out.println(exchangeRate);
        }
    }
}
