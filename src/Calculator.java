import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Calculator {

    private final ArrayList<JButton> basicButtonsList;
    //private ArrayList<JButton> constantButtonsList;

    private JLabel display;

    private int status;

    private static final int BEGIN = -1;
    private static final int ENTER_OPERATION = 0;
    private static final int ENTER_OPERAND2 = 1;

    private boolean resetDisplay;

    private double answer, memory;

    private String operation;

    private double op1;

    private static final float BUTTON_FONT = 15f;
    private static final float DISPLAY_FONT = 55f;
    private static final float HISTORY_FONT = 15f;

    private boolean isDegrees;

    private final JLabel history;

    private final ArrayList<String> historyList;

    private boolean clearHistory;

    private JFrame qeFrame;

    public Calculator() {
        resetDisplay = false;
        status = BEGIN;
        answer = 0d;
        memory = 0d;
        operation = "";
        op1 = 0d;
        isDegrees = true;

        historyList = new ArrayList<>();

        clearHistory = false;

        qeFrame = null;

        Font robotoFont = null;
        try {
            robotoFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Roboto-Thin.ttf"));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        assert robotoFont != null;

        JFrame frame = new JFrame("Scientific Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(0, 10));

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        //probably should replace this with key bindings
        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case '0' -> findButtonByText("0", basicButtonsList).doClick();
                    case '1' -> findButtonByText("1", basicButtonsList).doClick();
                    case '2' -> findButtonByText("2", basicButtonsList).doClick();
                    case '3' -> findButtonByText("3", basicButtonsList).doClick();
                    case '4' -> findButtonByText("4", basicButtonsList).doClick();
                    case '5' -> findButtonByText("5", basicButtonsList).doClick();
                    case '6' -> findButtonByText("6", basicButtonsList).doClick();
                    case '7' -> findButtonByText("7", basicButtonsList).doClick();
                    case '8' -> findButtonByText("8", basicButtonsList).doClick();
                    case '9' -> findButtonByText("9", basicButtonsList).doClick();
                    case '.' -> findButtonByText(".", basicButtonsList).doClick();
                    case '+' -> findButtonByText("+", basicButtonsList).doClick();
                    case '-' -> findButtonByText("-", basicButtonsList).doClick();
                    case '*' -> findButtonByText("*", basicButtonsList).doClick();
                    case '/' -> findButtonByText("/", basicButtonsList).doClick();
                    case '%' -> findButtonByText("%", basicButtonsList).doClick();
                    case 'E', 'e' -> findButtonByText("EXP", basicButtonsList).doClick();
                    case 'A', 'a' -> findButtonByText("ANS", basicButtonsList).doClick();
                    case 'D', 'd' -> findButtonByText("DEG", basicButtonsList).doClick();
                    case 'R', 'r' -> findButtonByText("RAD", basicButtonsList).doClick();
                    case 'M', 'm' -> findButtonByText("+/-", basicButtonsList).doClick();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER -> findButtonByText("=", basicButtonsList).doClick();
                    case KeyEvent.VK_BACK_SPACE, KeyEvent.VK_DELETE -> findButtonByText("DEL", basicButtonsList).doClick();
                    case KeyEvent.VK_ESCAPE -> findButtonByText("AC", basicButtonsList).doClick();
                    case KeyEvent.VK_F1 -> findButtonByText("C", basicButtonsList).doClick();
                    case KeyEvent.VK_F12 -> findButtonByText("MC", basicButtonsList).doClick();
                    case KeyEvent.VK_F8 -> findButtonByText("MR", basicButtonsList).doClick();
                    case KeyEvent.VK_F5 -> findButtonByText("M+", basicButtonsList).doClick();
                    case KeyEvent.VK_F6 -> findButtonByText("M-", basicButtonsList).doClick();
                }
            }
        };

        frame.addKeyListener(keyListener);

        ImageIcon icon = new ImageIcon("icon.png");
        frame.setIconImage(icon.getImage());

        ActionListener buttonListener = (e) -> {
            try {
                buttonPressed(((JButton) e.getSource()).getText());
            } catch (NumberFormatException ex) {
                display.setText("0");
                resetDisplay = false;
            }
        };

        JPanel panelPanelPanel = new JPanel();
        panelPanelPanel.setLayout(new BorderLayout(0, 10));

        JRadioButton degButton = new JRadioButton("deg");
        JRadioButton radButton = new JRadioButton("rad");
        degButton.addActionListener((e) -> isDegrees = true);
        radButton.addActionListener((e) -> isDegrees = false);
        degButton.setFocusable(false);
        radButton.setFocusable(false);
        degButton.setSelected(true);
        radButton.setSelected(false);
        degButton.setFont(robotoFont.deriveFont(BUTTON_FONT));
        radButton.setFont(robotoFont.deriveFont(BUTTON_FONT));

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(degButton);
        buttonGroup.add(radButton);

        JPanel radioButtonPanel = new JPanel();
        radioButtonPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 0));

        radioButtonPanel.add(degButton);
        radioButtonPanel.add(radButton);

        panelPanelPanel.add(radioButtonPanel, BorderLayout.NORTH);

        JPanel panelPanel = new JPanel();
        panelPanel.setLayout(new GridLayout(1, 2, 20, 10));

        JPanel basicButtonsPanel = new JPanel();
        basicButtonsPanel.setLayout(new GridLayout(6, 5, 10, 10));

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
        basicButtonsList.add(new JButton("MC"));
        basicButtonsList.add(new JButton("DEG"));
        basicButtonsList.add(new JButton("RAD"));

        for (JButton button : basicButtonsList) {
            button.setFocusable(false);
            button.addActionListener(buttonListener);
            button.setFont(robotoFont.deriveFont(BUTTON_FONT));
            basicButtonsPanel.add(button);
        }

        JPanel scientificButtonsPanel = new JPanel();
        scientificButtonsPanel.setLayout(new GridLayout(5, 4, 10, 10));

        ArrayList<JButton> scientificButtonsList = new ArrayList<>();
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
            button.addActionListener(buttonListener);
            button.setFont(robotoFont.deriveFont(BUTTON_FONT));
            scientificButtonsPanel.add(button);
        }

        panelPanel.add(scientificButtonsPanel);
        panelPanel.add(basicButtonsPanel);

        panelPanelPanel.add(panelPanel, BorderLayout.CENTER);

        frame.add(panelPanelPanel, BorderLayout.CENTER);

        JPanel constantsPanel = new JPanel();
        constantsPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));

        /*

        constantButtonsList = new ArrayList<>();
        constantButtonsList.add(new JButton("pi"));
        constantButtonsList.add(new JButton("e"));
        constantButtonsList.add(new JButton("c"));
        constantButtonsList.add(new JButton("h"));
        constantButtonsList.add(new JButton("R"));
        constantButtonsList.add(new JButton("G"));
        constantButtonsList.add(new JButton("Na"));
        constantButtonsList.add(new JButton("qe"));
        constantButtonsList.add(new JButton("me"));
        constantButtonsList.add(new JButton("mp"));
        constantButtonsList.add(new JButton("mn"));

        for (JButton button : constantButtonsList) {
            button.setFocusable(false);
            button.addActionListener(buttonListener);
            button.setPreferredSize(new Dimension(50, 50));
            constantsPanel.add(button);
        }
         */

        String[] constants = {"pi", "e", "c", "h", "R", "G", "Na", "qe", "me", "mp", "mn"};
        JComboBox constantsComboBox = new JComboBox(constants);
        constantsComboBox.addActionListener((e) -> buttonPressed((String) constantsComboBox.getSelectedItem()));
        constantsComboBox.setPreferredSize(new Dimension(50, 32));
        constantsComboBox.setFocusable(false);
        constantsComboBox.setFont(robotoFont.deriveFont(BUTTON_FONT));

        constantsPanel.add(constantsComboBox);

        JButton qeButton = new JButton("QE Solver!");
        qeButton.setFont(robotoFont.deriveFont(BUTTON_FONT));
        qeButton.setFocusable(false);
        Font finalRobotoFont = robotoFont;
        qeButton.addActionListener((e) -> {
            if (qeFrame != null) {
                //if a qeFrame instance is already running
                qeFrame.toFront();
                return;
            }

            qeFrame = new JFrame("Quadratic Equation Solver");
            qeFrame.setIconImage(icon.getImage());
            qeFrame.setLocationRelativeTo(frame);
            qeFrame.setLayout(new GridLayout(4, 1));

            qeFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    qeFrame = null;
                }
            });

            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

            Dimension textFieldDimension = new Dimension(200, 30);

            JTextField aTextField = new JTextField();
            aTextField.setFont(finalRobotoFont.deriveFont(BUTTON_FONT));
            aTextField.setPreferredSize(textFieldDimension);
            JTextField bTextField = new JTextField();
            bTextField.setFont(finalRobotoFont.deriveFont(BUTTON_FONT));
            bTextField.setPreferredSize(textFieldDimension);
            JTextField cTextField = new JTextField();
            cTextField.setFont(finalRobotoFont.deriveFont(BUTTON_FONT));
            cTextField.setPreferredSize(textFieldDimension);

            JLabel aLabel = new JLabel("x^2 + ");
            aLabel.setFont(finalRobotoFont.deriveFont(BUTTON_FONT));
            JLabel bLabel = new JLabel("x + ");
            bLabel.setFont(finalRobotoFont.deriveFont(BUTTON_FONT));
            JLabel zeroLabel = new JLabel(" = 0");
            zeroLabel.setFont(finalRobotoFont.deriveFont(BUTTON_FONT));

            inputPanel.add(aTextField);
            inputPanel.add(aLabel);
            inputPanel.add(bTextField);
            inputPanel.add(bLabel);
            inputPanel.add(cTextField);
            inputPanel.add(zeroLabel);

            qeFrame.add(inputPanel);

            JLabel alphaSolutionLabel = new JLabel("0.0");
            JLabel betaSolutionLabel = new JLabel("0.0");

            JPanel solveButtonPanel = new JPanel();
            solveButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            JButton solveButton = new JButton("Solve!");
            solveButton.setFont(finalRobotoFont.deriveFont(BUTTON_FONT));
            solveButton.addActionListener((event) -> {
                try {
                    double a = Double.parseDouble(aTextField.getText());
                    double b = Double.parseDouble(bTextField.getText());
                    double c = Double.parseDouble(cTextField.getText());
                    double alpha = (-b + Math.sqrt(b * b - 4 * a * c)) / 2 * a;
                    double beta = (-b - Math.sqrt(b * b - 4 * a * c)) / 2 * a;
                    alphaSolutionLabel.setText(Double.toString(alpha));
                    betaSolutionLabel.setText(Double.toString(beta));
                } catch (NumberFormatException exception) {
                    alphaSolutionLabel.setText("Invalid numbers entered");
                    betaSolutionLabel.setText("Invalid numbers entered");
                }
            });
            solveButton.setFocusable(false);
            solveButtonPanel.add(solveButton);

            qeFrame.add(solveButtonPanel);

            JPanel alphaSolutionPanel = new JPanel();
            alphaSolutionPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            alphaSolutionLabel.setFont(finalRobotoFont.deriveFont(BUTTON_FONT));
            JLabel alphaLabel = new JLabel("Alpha: ");
            alphaLabel.setFont(finalRobotoFont.deriveFont(BUTTON_FONT));
            JButton alphaButton = new JButton("Use");
            alphaButton.setFont(finalRobotoFont.deriveFont(BUTTON_FONT));
            alphaButton.setFocusable(false);
            alphaButton.addActionListener((ea) -> {
                display.setText(alphaSolutionLabel.getText());
                resetDisplay = true;
            });
            alphaSolutionPanel.add(alphaLabel);
            alphaSolutionPanel.add(alphaSolutionLabel);
            alphaSolutionPanel.add(alphaButton);

            JPanel betaSolutionPanel = new JPanel();
            betaSolutionPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            betaSolutionLabel.setFont(finalRobotoFont.deriveFont(BUTTON_FONT));
            JLabel betaLabel = new JLabel("Beta: ");
            betaLabel.setFont(finalRobotoFont.deriveFont(BUTTON_FONT));
            JButton betaButton = new JButton("Use");
            betaButton.setFont(finalRobotoFont.deriveFont(BUTTON_FONT));
            betaButton.setFocusable(false);
            betaButton.addActionListener((eb) -> {
                display.setText(betaSolutionLabel.getText());
                resetDisplay = true;
            });
            betaSolutionPanel.add(betaLabel);
            betaSolutionPanel.add(betaSolutionLabel);
            betaSolutionPanel.add(betaButton);

            qeFrame.add(alphaSolutionPanel);
            qeFrame.add(betaSolutionPanel);

            qeFrame.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    switch (e.getKeyChar()) {
                        case 'a', 'A' -> alphaButton.doClick();
                        case 'b', 'B' -> betaButton.doClick();
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_ENTER -> solveButton.doClick();
                    }
                }
            });

            qeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            qeFrame.pack();
            qeFrame.setResizable(false);
            qeFrame.setVisible(true);
        });

        constantsPanel.add(qeButton);

        frame.add(constantsPanel, BorderLayout.SOUTH);

        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout(0, 0));

        history = new JLabel("");
        history.setHorizontalAlignment(JLabel.RIGHT);
        history.setFont(robotoFont.deriveFont(HISTORY_FONT));
        //history.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));

        display = new JLabel("0");
        display.setHorizontalAlignment(JLabel.RIGHT);
        display.setFont(robotoFont.deriveFont(DISPLAY_FONT));
        display.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        //display.setPreferredSize(new Dimension(0, 60));

        displayPanel.add(history, BorderLayout.NORTH);
        displayPanel.add(display, BorderLayout.CENTER);

        frame.add(displayPanel, BorderLayout.NORTH);

        frame.pack();

        frame.setVisible(true);

        frame.requestFocus();
    }

    private void buttonPressed(String text) {
        if (clearHistory) {
            clearHistory();
            clearHistory = false;
        }
        if (text.equals("0") || text.equals("1") || text.equals("2") || text.equals("3") || text.equals("4") || text.equals("5") || text.equals("6") || text.equals("7") || text.equals("8") || text.equals("9")) {
            if (resetDisplay) {
                display.setText(text);
                resetDisplay = false;
            } else {
                if (display.getText().length() == 1 && display.getText().charAt(0) == '0') {
                    display.setText(text);
                } else {
                    display.setText(display.getText() + text);
                }
            }
            if (status == ENTER_OPERATION) {
                status = ENTER_OPERAND2;
            }
        } else if (text.equals("EXP")) {
            if (resetDisplay) {
                display.setText("0E");
                resetDisplay = false;
            } else {
                display.setText(display.getText() + "E");
            }
            if (status == ENTER_OPERATION) {
                status = ENTER_OPERAND2;
            }
        } else if (text.equals(".")) {
            if (resetDisplay) {
                display.setText("0.");
                resetDisplay = false;
            } else {
                display.setText(display.getText() + ".");
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
            if (txt.contains("E")) {
                String[] arr = txt.split("E");
                if (arr.length == 1) {
                    String[] temp = new String[2];
                    temp[0] = arr[0];
                    temp[1] = "-";
                    arr = temp;
                } else {
                    if (arr[1].charAt(0) == '-') {
                        arr[1] = arr[1].substring(1);
                    } else {
                        arr[1] = "-" + arr[1];
                    }
                }
                txt = arr[0] + "E" + arr[1];
            } else {
                if (txt.charAt(0) == '-') {
                    txt = txt.substring(1);
                } else {
                    txt = "-" + txt;
                }
            }
            display.setText(txt);
        } else if (text.equals("C")) {
            display.setText("0");
            resetDisplay = false;
        } else if (text.equals("AC")) {
            status = BEGIN;
            display.setText("0");
            resetDisplay = false;
            operation = "";
            clearHistory();
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
                if (!(display.getText().length() == 1)) {
                    display.setText(display.getText().substring(0, display.getText().length() - 1));
                } else {
                    display.setText("0");
                }
            }
        } else if (text.equals("+") || text.equals("-") || text.equals("*") || text.equals("/") || text.equals("%") || text.equals("nPk") || text.equals("nCk") || text.equals("x^y") || text.equals("x^(1/y)")) {
            if (status == BEGIN) {
                op1 = Double.parseDouble(display.getText());
                resetDisplay = true;
                status = ENTER_OPERATION;
                operation = text;
                historyList.add(display.getText());
                historyList.add(operation);
                refreshHistory();
            } else if (status == ENTER_OPERATION) {
                boolean operationAlreadyEntered = !operation.equals("");
                operation = text;
                if (operationAlreadyEntered) {
                    historyList.set(historyList.size() - 1, operation);
                } else {
                    historyList.add(operation);
                }
                refreshHistory();
            } else if (status == ENTER_OPERAND2) {
                historyList.add(display.getText());
                double ans = evaluate(op1, Double.parseDouble(display.getText()), operation);
                display.setText(Double.toString(ans));
                op1 = ans;
                resetDisplay = true;
                status = ENTER_OPERATION;
                operation = text;
                historyList.add(operation);
                refreshHistory();
            }
        } else if (text.equals("=")) {
            if (status == ENTER_OPERAND2) {
                historyList.add(display.getText());
                answer = evaluate(op1, Double.parseDouble(display.getText()), operation);
                display.setText(Double.toString(answer));
                op1 = answer;
                resetDisplay = true;
                status = BEGIN;
            } else if (status == ENTER_OPERATION) {
                historyList.remove(historyList.size() - 1);
                answer = Double.parseDouble(display.getText());
                status = BEGIN;
                resetDisplay = true;
            } else if (status == BEGIN) {
                historyList.add(display.getText());
                answer = Double.parseDouble(display.getText());
                status = BEGIN;
                resetDisplay = true;
            }
            operation = "";
            historyList.add("=");
            clearHistory = true;
            refreshHistory();
        } else if (text.equals("pi") || text.equals("e") || text.equals("c") || text.equals("h") || text.equals("R") || text.equals("G") || text.equals("Na") || text.equals("qe") || text.equals("me") || text.equals("mp") || text.equals("mn")) {
            //for any constant button
            display.setText(Double.toString(evaluateConstants(text)));
            resetDisplay = true;
            if (status == ENTER_OPERATION) {
                status = ENTER_OPERAND2;
            }
        } else if (text.equals("log") || text.equals("ln") || text.equals("!") || text.equals("10^x") || text.equals("e^x") || text.equals("1/x") || text.equals("x^2") || text.equals("x^3") || text.equals("sin") || text.equals("arcsin") || text.equals("sqrt") || text.equals("cbrt") || text.equals("cos") || text.equals("arccos") || text.equals("tan") || text.equals("arctan") || text.equals("DEG") || text.equals("RAD")) {
            display.setText(Double.toString(evaluateUnary(Double.parseDouble(display.getText()), text)));
            resetDisplay = true;
            if (status == ENTER_OPERATION) {
                status = ENTER_OPERAND2;
            }
        } else if (text.equals("MC")) {
            memory = 0d;
        }
    }

    public double evaluate(double op1, double op2, String operation) {
        double ans = switch (operation) {
            case "+" -> op1 + op2;
            case "-" -> op1 - op2;
            case "*" -> op1 * op2;
            case "/" -> op1 / op2;
            case "%" -> op1 % op2;
            case "nPk" -> nPk(op1, op2);
            case "nCk" -> nCk(op1, op2);
            case "x^y" -> Math.pow(op1, op2);
            case "x^(1/y)" -> Math.pow(op1, 1 / op2);
            default -> 0d;
        };
        return roundDouble(ans);
    }

    public double evaluateUnary(double n, String operation) {
        double ans = switch (operation) {
            case "log" -> Math.log10(n);
            case "ln" -> Math.log(n);
            case "!" -> factorial(n);
            case "10^x" -> Math.pow(10, n);
            case "e^x" -> Math.pow(Math.E, n);
            case "1/x" -> (1 / n);
            case "x^2" -> n * n;
            case "x^3" -> n * n * n;
            case "sin" -> isDegrees ? Math.sin(Math.toRadians(n)) : Math.sin(n);
            case "arcsin" -> isDegrees ? Math.toDegrees(Math.asin(n)) : Math.asin(n);
            case "sqrt" -> Math.sqrt(n);
            case "cbrt" -> Math.cbrt(n);
            case "cos" -> isDegrees ? Math.cos(Math.toRadians(n)) : Math.cos(n);
            case "arccos" -> isDegrees ? Math.toDegrees(Math.acos(n)) : Math.acos(n);
            case "tan" -> isDegrees ? Math.tan(Math.toRadians(n)) : Math.tan(n);
            case "arctan" -> isDegrees ? Math.toDegrees(Math.atan(n)) : Math.atan(n);
            case "DEG" -> Math.toDegrees(n);
            case "RAD" -> Math.toRadians(n);
            default -> 0d;
        };
        return roundDouble(ans);
    }

    public double evaluateConstants(String constant) {
        double ans = switch (constant) {
            case "pi" -> Constants.PI;
            case "e" -> Constants.E;
            case "c" -> Constants.C;
            case "h" -> Constants.H;
            case "R" -> Constants.R;
            case "G" -> Constants.G;
            case "Na" -> Constants.NA;
            case "qe" -> Constants.QE;
            case "me" -> Constants.ME;
            case "mp" -> Constants.MP;
            case "mn" -> Constants.MN;
            default -> 0d;
        };
        return roundDouble(ans);
    }

    public double factorial(double n) {
        double answer = 1d;
        for (double i = 1; i <= n; i++) {
            answer *= i;
            if (answer == Double.parseDouble("Infinity")) {
                break;
            }
        }
        return answer;
    }

    public double nPk(double op1, double op2) {
        return factorial(op1) / factorial(op1 - op2);
    }

    public double nCk(double op1, double op2) {
        return factorial(op1) / (factorial(op2) * factorial(op1 - op2));
    }

    public JButton findButtonByText(String text, ArrayList<JButton> buttonArrayList) {
        for (JButton button : buttonArrayList) {
            if (text.equals(button.getText())) {
                return button;
            }
        }
        return null;
    }

    public double roundDouble(double n) {
        try {
            int scale = 10;

            String nAsString = Double.toString(n);
            String[] arr = nAsString.split("E");

            BigDecimal bd = new BigDecimal(arr[0]);
            bd = bd.setScale(scale, RoundingMode.HALF_UP);
            arr[0] = bd.toString();

            if (arr.length == 1) {
                return Double.parseDouble(arr[0]);
            } else if (arr.length == 2) {
                return Double.parseDouble(arr[0] + "E" + arr[1]);
            } else {
                throw new NumberFormatException(n + " not valid number");
            }
        } catch (NumberFormatException e) {
            return n;
        }
    }

    public void refreshHistory() {
        StringBuilder builder = new StringBuilder();
        for (String str : historyList) {
            builder.append(str).append(" ");
        }
        history.setText(builder.toString());
    }

    public void clearHistory() {
        historyList.clear();
        history.setText("");
    }

}
