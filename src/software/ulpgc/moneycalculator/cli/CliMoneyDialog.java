package software.ulpgc.moneycalculator.cli;

import software.ulpgc.moneycalculator.Currency;
import software.ulpgc.moneycalculator.CurrencyDialog;
import software.ulpgc.moneycalculator.Money;
import software.ulpgc.moneycalculator.MoneyDialog;

import java.util.List;
import java.util.Scanner;

public class CliMoneyDialog implements MoneyDialog {
    private CurrencyDialog currencyDialog;

    @Override
    public MoneyDialog define(List<Currency> currencies) {
        this.currencyDialog = new CliCurrencyDialog().define(currencies);
        return this;
    }

    @Override
    public Money get() {
        return new Money(getAmount(), getCurrency());
    }

    private Currency getCurrency() {
        return currencyDialog.get();
    }

    private static long getAmount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca una cantidad");
        long amount = scanner.nextLong();
        return amount;
    }
}
