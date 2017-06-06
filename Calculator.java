/* Name: Calculator.java
   Author: Robin Goyal
   Last-Modified: June 6, 2017
   Purpose: Create a basic calculator in Java using
            Djikstra's Two Stack algorithm
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Calculator extends JFrame implements ActionListener{

    private JTextField display = new JTextField(17);
    private JTextField resultDisplay = new JTextField(17);
    private double result = 0;

    public Calculator() {
        initGUI();
    }

    private void initGUI() {

        // GUI parameters
        setTitle("Calculator");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
        //JTextField display = new JTextField(17);
        display.setEditable(false);
        display.setText("");
        display.setHorizontalAlignment(JTextField.RIGHT);
        Font displayFont = display.getFont().deriveFont(Font.PLAIN, 25f);
        display.setFont(displayFont);

        resultDisplay.setEditable(false);
        resultDisplay.setText("0");
        resultDisplay.setHorizontalAlignment(JTextField.RIGHT);
        resultDisplay.setFont(displayFont);

        topPanel.add(display);
        topPanel.add(resultDisplay);

        // Create grid for buttons
        JPanel grid = new JPanel();
        grid.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        grid.setLayout(new GridLayout(6, 4, 5, 5));

        // Store array of strings for buttons
        String[] buttons = {
            "Clear", "(", ")", "Close", "7", "8", "9", "/", "4", 
            "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+", "ANS"
        };

        JButton[] arr = new JButton[buttons.length];

        // Add calculator buttons to grid
        for (int i = 0; i < buttons.length; i++) {
            arr[i] = new JButton(buttons[i]);
            arr[i].addActionListener(this);
            grid.add(arr[i]);
        }

        // Add text field and grid to Frame
        add(topPanel, BorderLayout.NORTH);
        add(grid, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            String text = e.getActionCommand();
            //display.setText(text);

            switch(text) {
                case "Clear":
                    display.setText("");
                    resultDisplay.setText("");
                    result = 0;
                    break;
                case "Close":
                    System.exit(0);
                case "ANS":
                    display.setText(display.getText() + result);
                    break;
                case "+":
                case "-":
                case "/":
                case "*":
                case "(":
                case ")":
                    display.setText(display.getText() + " " + text + " ");
                    break;
                case "=":
                    Stack<Double> values = new Stack<Double>();
                    Stack<String> ops = new Stack<String>();

                    String[] expression = display.getText().trim().split("\\s+");
                    Queue<String> q = new LinkedList<>();
                    q.addAll(Arrays.asList(expression));

                    System.out.println(expression);
                    System.out.println(q);

                    while(!q.isEmpty()) {
                        String token = q.poll();
                        if (token.equals("(")) continue;
                        else if (token.equals("+")) ops.push(token);
                        else if (token.equals("*")) ops.push(token);
                        else if (token.equals("-")) ops.push(token);
                        else if (token.equals("/")) ops.push(token);
                        else if (token.equals(")")) {
                            values.push(calculate(ops, values));
                        }
                        else {
                            values.push(Double.parseDouble(token));
                        }
                    }
                    if (values.size() >= 2) {
                        result = calculate(ops, values);
                    }
                    else {
                        result = values.pop();
                    }

                    display.setText("");
                    resultDisplay.setText(Double.toString(result));
                    break;
                default:
                    display.setText(display.getText() + text);
        // END CASE
            }
        }
    }

    private double calculate(Stack<String> ops, Stack<Double> values) {
        String op = ops.pop();
        Double val = values.pop();

        if (op.equals("+")) val = values.pop() + val;
        else if (op.equals("-")) val = values.pop() - val;
        else if (op.equals("*")) val = values.pop() * val;
        else if (op.equals("/")) {
            double val2 = values.pop();
            if (val2 == 0.0) {
                System.out.println("DIVISION BY ZERO");
                System.exit(0);
            }
            else val = val2 / val;
        }
        return val;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Calculator test = new Calculator();
            test.setVisible(true);
        });
    }
}