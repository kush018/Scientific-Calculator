import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Calculator {
    private JPanel basicButtonsPanel;
    private JPanel scientificButtonsPanel;
    private JPanel constantsPanel;

    private JPanel panelPanel;

    private JFrame frame;

    private ArrayList<JButton> basicButtonsList;
    private ArrayList<JButton> scientificButtonsList;
    private ArrayList<JButton> constantButtonsList;

    private JLabel display;

    private int status;

    private static final int BEGIN = -1;
    private static final int ENTER_OPERATION = 0;
    private static final int ENTER_OPERAND2 = 1;

    private boolean resetDisplay;

    private double answer, memory;

    private String operation;

    private double op1;

    public Calculator() {
        resetDisplay = false;
        status = BEGIN;
        answer = 0d;
        memory = 0d;
        operation = "";
        op1 = 0d;

        frame = new JFrame("Scientific Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(0, 20));

        ImageIcon icon = new ImageIcon("icon.png");
        frame.setIconImage(icon.getImage());

        panelPanel = new JPanel();
        panelPanel.setLayout(new GridLayout(1, 2, 20, 10));

        basicButtonsPanel = new JPanel();
        basicButtonsPanel.setLayout(new GridLayout(6, 5, 10,10));

        basicButtonsList = new ArrayList<>();
        basicButtonsList.add(new JButton("M+"));
        basicButtonsList.add(new JButton("M-"));
        basicButtonsList.add(new JButton("MR"));
        basicButtonsList.add(new JButton("DEL"));
        basicButtonsList.add(new JButton("%"));
        basicButtonsList.add(new JButton("AC"));
        basicButtonsList.add(new JButton("7"));
        basicButtonsList.add(new JButton("8"));
        basicButtonsList.add(new JButton("9"));
        basicButtonsList.add(new JButton("/"));
        basicButtonsList.add(new JButton("C"));
        basicButtonsList.add(new JButton("4"));
        basicButtonsList.add(new JButton("5"));
        basicButtonsList.add(new JButton("6"));
        basicButtonsList.add(new JButton("*"));
        basicButtonsList.add(new JButton("+/-"));
        basicButtonsList.add(new JButton("1"));
        basicButtonsList.add(new JButton("2"));
        basicButtonsList.add(new JButton("3"));
        basicButtonsList.add(new JButton("-"));
        basicButtonsList.add(new JButton("EXP"));
        basicButtonsList.add(new JButton("ANS"));
        basicButtonsList.add(new JButton("0"));
        basicButtonsList.add(new JButton("."));
        basicButtonsList.add(new JButton("+"));
        basicButtonsList.add(new JButton("="));

        ActionListener basicButtonsListener = (e) -> {
            buttonPressed( ((JButton) e.getSource()).getText() );
        };

        for (JButton button : basicButtonsList) {
            button.setFocusable(false);
            button.addActionListener(basicButtonsListener);
            basicButtonsPanel.add(button);
        }

        scientificButtonsPanel = new JPanel();
        scientificButtonsPanel.setLayout(new GridLayout(5, 4, 10, 10));

        scientificButtonsList = new ArrayList<>();
        scientificButtonsList.add(new JButton("log"));
        scientificButtonsList.add(new JButton("ln"));
        scientificButtonsList.add(new JButton("!"));
        scientificButtonsList.add(new JButton("nPk"));
        scientificButtonsList.add(new JButton("10^x"));
        scientificButtonsList.add(new JButton("e^x"));
        scientificButtonsList.add(new JButton("nCk"));
        scientificButtonsList.add(new JButton("1/x"));
        scientificButtonsList.add(new JButton("x^2"));
        scientificButtonsList.add(new JButton("x^3"));
        scientificButtonsList.add(new JButton("sin"));
        scientificButtonsList.add(new JButton("arcsin"));
        scientificButtonsList.add(new JButton("sqrt"));
        scientificButtonsList.add(new JButton("cbrt"));
        scientificButtonsList.add(new JButton("cos"));
        scientificButtonsList.add(new JButton("arccos"));
        scientificButtonsList.add(new JButton("x^y"));
        scientificButtonsList.add(new JButton("x^(1/y)"));
        scientificButtonsList.add(new JButton("tan"));
        scientificButtonsList.add(new JButton("arctan"));

        for (JButton button : scientificButtonsList) {
            button.setFocusable(false);
            scientificButtonsPanel.add(button);
        }

        panelPanel.add(scientificButtonsPanel);
        panelPanel.add(basicButtonsPanel);

        frame.add(panelPanel, BorderLayout.CENTER);

        constantsPanel = new JPanel();
        constantsPanel.setLayout(new GridLayout(2, 9, 10, 10));

        constantButtonsList = new ArrayList<>();
        constantButtonsList.add(new JButton("pi"));
        constantButtonsList.add(new JButton("e"));
        constantButtonsList.add(new JButton("c"));
        constantButtonsList.add(new JButton("h"));
        constantButtonsList.add(new JButton("R"));
        constantButtonsList.add(new JButton("ke"));
        constantButtonsList.add(new JButton("G"));
        constantButtonsList.add(new JButton("Na"));
        constantButtonsList.add(new JButton("qe"));
        constantButtonsList.add(new JButton("me"));
        constantButtonsList.add(new JButton("mp"));
        constantButtonsList.add(new JButton("mn"));

        for (JButton button : constantButtonsList) {
            button.setFocusable(false);
            constantsPanel.add(button);
        }

        frame.add(constantsPanel, BorderLayout.SOUTH);

        display = new JLabel("0");
        display.setHorizontalAlignment(JLabel.RIGHT);

        Font digitalFont = null;
        try {
            digitalFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/digital-7.ttf"));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        assert digitalFont != null;

        display.setFont(digitalFont.deriveFont(60f));
        display.setPreferredSize(new Dimension(0, 60));

        frame.add(display, BorderLayout.NORTH);

        frame.pack();

        frame.setVisible(true);
    }

    private void buttonPressed(String text) {
        if (text.equals("0") || text.equals("1") || text.equals("2") || text.equals("3") || text.equals("4") || text.equals("5") || text.equals("6") || text.equals("7") || text.equals("8") || text.equals("9") || text.equals(".")) {
            if (resetDisplay) {
                display.setText(text);
                resetDisplay = false;
            } else {
                display.setText(display.getText() + text);
            }
            if (status == ENTER_OPERATION) {
                status = ENTER_OPERAND2;
            }
        } else if (text.equals("EXP")) {
            if (resetDisplay) {
                display.setText("E");
                resetDisplay = false;
            } else {
                display.setText(display.getText() + "E");
            }
            if (status == ENTER_OPERATION) {
                status = ENTER_OPERAND2;
            }
        } else if (text.equals("ANS")) {
            display.setText(Double.toString(answer));
            resetDisplay = true;
            if (status == ENTER_OPERATION) {
                status = ENTER_OPERAND2;
            }
        } else if (text.equals("+/-")) {
            String txt = display.getText();
            if (txt.charAt(0) == '-') {
                txt = txt.substring(1);
            } else {
                txt = "-" + txt;
            }
            display.setText(txt);
        } else if (text.equals("C")) {
            display.setText("0");
            resetDisplay = false;
        } else if (text.equals("AC")) {
            status = BEGIN;
            display.setText("0");
            resetDisplay = false;
        } else if (text.equals("M+")) {
            memory += Double.parseDouble(display.getText());
        } else if (text.equals("M-")) {
            memory -= Double.parseDouble(display.getText());
        } else if (text.equals("MR")) {
            display.setText(Double.toString(memory));
            resetDisplay = true;
            if (status == ENTER_OPERATION) {
                status = ENTER_OPERAND2;
            }
        } else if (text.equals("DEL")) {
            if (resetDisplay) {
                display.setText("0");
                resetDisplay = false;
            } else {
                if (!(Double.parseDouble(display.getText()) == 0d)) {
                    display.setText(display.getText().substring(0, display.getText().length() - 1));
                }
            }
        } else if (text.equals("+") || text.equals("-") || text.equals("*") || text.equals("/") || text.equals("%")) {
            if (status == BEGIN) {
                resetDisplay = true;
                status = ENTER_OPERATION;
                operation = text;
                op1 = Double.parseDouble(display.getText());
            } else if (status == ENTER_OPERATION) {
                operation = text;
            } else if (status == ENTER_OPERAND2) {
                double ans = evaluate(op1, Double.parseDouble(display.getText()), operation);
                display.setText(Double.toString(ans));
                op1 = ans;
                resetDisplay = true;
                status = ENTER_OPERATION;
                operation = text;
            }
        } else if (text.equals("=")) {
            if (status == ENTER_OPERAND2) {
                answer = evaluate(op1, Double.parseDouble(display.getText()), operation);
                display.setText(Double.toString(answer));
                op1 = answer;
                resetDisplay = true;
                status = BEGIN;
            } else if (status == ENTER_OPERATION || status == BEGIN) {
                answer = Double.parseDouble(display.getText());
                status = BEGIN;
                resetDisplay = true;
            }
        }
    }

    public double evaluate(double op1, double op2, String operation) {
        return switch (operation) {
            case "+" -> op1 + op2;
            case "-" -> op1 - op2;
            case "*" -> op1 * op2;
            case "/" -> op1 / op2;
            case "%" -> op1 % op2;
            default -> 0d;
        };
    }

}
