package software.ulpgc.moneycalculator.swing;

import software.ulpgc.moneycalculator.Money;
import software.ulpgc.moneycalculator.MoneyDisplay;

import javax.swing.*;

public class SwingMoneyDisplay extends JPanel implements MoneyDisplay {
    private final JLabel label;

    public SwingMoneyDisplay() {
        add(label = createLabel());
    }

    private static JLabel createLabel() {
        return new JLabel();
    }

    @Override
    public void show(Money money) {
        label.setText(money.toString());
    }
}
