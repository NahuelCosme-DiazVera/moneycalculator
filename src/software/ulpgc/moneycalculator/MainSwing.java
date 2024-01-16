package software.ulpgc.moneycalculator;

import software.ulpgc.moneycalculator.control.ExchangeCommand;
import software.ulpgc.moneycalculator.swing.*;
import software.ulpgc.moneycalculator.tsvfileloader.*;

import java.io.File;
import java.util.List;

public class MainSwing {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        CurrencyLoader currencyLoader = TsvFileCurrencyLoader.with(new File("currencies.tsv"));
        List<Currency> currencies = currencyLoader.load();
        ExchangeRateLoader exchangeRateLoader = TsvFileExchangeRateLoader.with(new File("dollarexchangerates.tsv"));
        MoneyDialog moneyDialog = frame.getMoneyDialog().define(currencies);
        CurrencyDialog currencyDialog = frame.getCurrencyDialog().define(currencies);
        MoneyDisplay moneyDisplay = frame.getMoneyDisplay();
        frame.add("exchange", new ExchangeCommand(moneyDialog, currencyDialog, exchangeRateLoader, moneyDisplay));
        frame.setVisible(true);
    }
}
