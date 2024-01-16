package software.ulpgc.moneycalculator.swing;

import software.ulpgc.moneycalculator.CurrencyDialog;
import software.ulpgc.moneycalculator.control.Command;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private SwingMoneyDialog moneyDialog;
    private SwingCurrencyDialog currencyDialog;
    private SwingMoneyDisplay moneyDisplay;
    private Map<String, Command> commands = new HashMap<>();

    public MainFrame() throws HeadlessException {
        this.setTitle("Money Calculator");
        this.setSize(1200, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(createMoneyDialog());
        this.add(createCurrencyDialog());
        this.add(createMoneyDisplay());
        this.add(createCalculateButton());
    }

    private Component createCalculateButton() {
        JButton button = new JButton("Exchange");
        button.addActionListener(e -> commands.get("exchange").execute());
        return button;
    }

    private Component createMoneyDisplay() {
        SwingMoneyDisplay display = new SwingMoneyDisplay();
        this.moneyDisplay = display;
        return display;
    }

    private Component createCurrencyDialog() {
        SwingCurrencyDialog dialog = new SwingCurrencyDialog();
        this.currencyDialog = dialog;
        return dialog;
    }

    private Component createMoneyDialog() {
        SwingMoneyDialog dialog = new SwingMoneyDialog();
        this.moneyDialog = dialog;
        return dialog;
    }

    public SwingMoneyDialog getMoneyDialog() {
        return moneyDialog;
    }

    public SwingCurrencyDialog getCurrencyDialog() {
        return currencyDialog;
    }

    public SwingMoneyDisplay getMoneyDisplay() {
        return moneyDisplay;
    }

    public void add(String name, Command command) {
        commands.put(name, command);
    }

}
