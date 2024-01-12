package software.ulpgc.moneycalculator.control;

import software.ulpgc.moneycalculator.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ExchangeCommand implements Command{
    private final MoneyDialog moneyDialog;
    private final CurrencyDialog currencyDialog;
    private final ExchangeRateLoader exchangeRateLoader;
    private final MoneyDisplay moneyDisplay;

    public ExchangeCommand(MoneyDialog moneyDialog, CurrencyDialog currencyDialog, ExchangeRateLoader exchangeRateLoader, MoneyDisplay moneyDisplay) {
        this.moneyDialog = moneyDialog;
        this.currencyDialog = currencyDialog;
        this.exchangeRateLoader = exchangeRateLoader;
        this.moneyDisplay = moneyDisplay;
    }

    @Override
    public void execute() {
        Money money = moneyDialog.get();
        Currency target = currencyDialog.get();
        List<ExchangeRate> exchangeRates = exchangeRateLoader.load();
        Money result = new Money(money.getAmount() * exchangeRates.get(0).getRate(), target);
        moneyDisplay.show(result);
    }
}
