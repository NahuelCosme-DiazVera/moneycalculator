package software.ulpgc.moneycalculator.swing;

import software.ulpgc.moneycalculator.Currency;
import software.ulpgc.moneycalculator.CurrencyDialog;
import software.ulpgc.moneycalculator.Money;
import software.ulpgc.moneycalculator.MoneyDialog;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static java.lang.Long.parseLong;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {
    private final JTextField amount;
    private CurrencyDialog currencyDialog;

    public SwingMoneyDialog() {
        this.setLayout(new FlowLayout());
        add(amount = createAmountField());
    }

    private JTextField createAmountField() {
        JTextField result = new JTextField();
        result.setColumns(8);
        return result;
    }

    @Override
    public MoneyDialog define(List<Currency> currencies) {
        add(createCurrencyDialog(currencies));
        return this;
    }

    private Component createCurrencyDialog(List<Currency> currencies) {
        SwingCurrencyDialog dialog = new SwingCurrencyDialog();
        dialog.define(currencies);
        this.currencyDialog = dialog;
        return dialog;
    }

    @Override
    public Money get() {
        return new Money(parseLong(amount.getText()), currencyDialog.get());
    }
}
