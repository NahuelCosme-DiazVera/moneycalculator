package software.ulpgc.moneycalculator;

import software.ulpgc.moneycalculator.cli.*;
import software.ulpgc.moneycalculator.control.ExchangeCommand;
import software.ulpgc.moneycalculator.mock.*;
import software.ulpgc.moneycalculator.tsvfileloader.*;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CurrencyLoader currencyLoader = TsvFileCurrencyLoader.with(new File("currencies.tsv"));
        List<Currency> currencies = currencyLoader.load();
        MoneyDialog moneyDialog = new CliMoneyDialog().define(currencies);
        CurrencyDialog currencyDialog = new CliCurrencyDialog().define(currencies);
        MoneyDisplay moneyDisplay = new MockMoneyDisplay();
        TsvFileExchangeRateLoader exchangeRatesLoader = TsvFileExchangeRateLoader.with(new File("dollarexchangerates.tsv"));
        ExchangeCommand exchangeCommand = new ExchangeCommand(moneyDialog, currencyDialog, exchangeRatesLoader, moneyDisplay);
        exchangeCommand.execute();
    }
}
