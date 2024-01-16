package software.ulpgc.moneycalculator.control;

import software.ulpgc.moneycalculator.*;

import java.util.List;

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
        ExchangeRate rate = targetCodeIsNotUSD(target) ?
                exchangeRates.stream()
                        .filter(exchangeRate -> exchangeRate.getTo().getCode().equalsIgnoreCase(target.getCode()))
                        .findFirst()
                        .orElse(null) :
                exchangeRates.stream()
                        .filter(exchangeRate -> exchangeRate.getTo().getCode().equalsIgnoreCase(money.getCurrency().getCode()))
                        .findFirst()
                        .orElse(null);
        Money result = new Money(targetCodeIsNotUSD(target) ?
                money.getAmount() * rate.getRate() :
                money.getAmount() * 1/rate.getRate(), target);
        moneyDisplay.show(result);
    }

    private static boolean targetCodeIsNotUSD(Currency target) {
        return !target.getCode().equalsIgnoreCase("USD");
    }
}
