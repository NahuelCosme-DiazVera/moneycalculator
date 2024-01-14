package software.ulpgc.moneycalculator.cli;

import software.ulpgc.moneycalculator.Currency;
import software.ulpgc.moneycalculator.CurrencyDialog;

import java.util.List;
import java.util.Scanner;

public class CliCurrencyDialog implements CurrencyDialog {
    private List<Currency> currencies;

    @Override
    public CurrencyDialog define(List<Currency> currencies) {
        this.currencies = currencies;
        return this;
    }

    @Override
    public Currency get() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Introduzca una divisa");
            String code = scanner.next();
            if (existsCurrency(code)) return findCurrency(code);
            System.out.println(code + "no encontrado");
        }
    }

    private boolean existsCurrency(String code) {
        return findCurrency(code) != null;
    }

    private Currency findCurrency(String code) {
        return currencies.stream()
                .filter(currency -> currency.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);
    }
}
