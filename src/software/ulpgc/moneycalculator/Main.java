package software.ulpgc.moneycalculator;

import software.ulpgc.moneycalculator.control.ExchangeCommand;
import software.ulpgc.moneycalculator.mock.MockCurrencyDialog;
import software.ulpgc.moneycalculator.mock.MockMoneyDialog;
import software.ulpgc.moneycalculator.mock.MockMoneyDisplay;
import software.ulpgc.moneycalculator.tsvfileloader.*;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CurrencyLoader currencyLoader = new TsvFileCurrencyLoader(new File("currencies.tsv"));
        List<Currency> currencies = currencyLoader.load();
        MoneyDialog moneyDialog = new MockMoneyDialog().define(currencies);
        CurrencyDialog currencyDialog = new MockCurrencyDialog().define(currencies);
        MoneyDisplay moneyDisplay = new MockMoneyDisplay();
        TsvFileExchangeRateLoader exchangeRatesLoader = TsvFileExchangeRateLoader.with(new File("exchangerates.tsv"));
        ExchangeCommand exchangeCommand = new ExchangeCommand(moneyDialog, currencyDialog, exchangeRatesLoader, moneyDisplay);
        exchangeCommand.execute();
    }
}
