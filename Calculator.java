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

    // Instance variables
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
        
        // Initialize display fields
        display = new JTextField(17);
        resultDisplay = new JTextField(17);

        // Text field Font
        Font displayFont = display.getFont().deriveFont(Font.PLAIN, 25f);

        // Text Field parameters
        display.setEditable(false);
        display.setText("");
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(displayFont);

        resultDisplay.setEditable(false);
        resultDisplay.setText("0");
        resultDisplay.setHorizontalAlignment(JTextField.RIGHT);
        resultDisplay.setFont(displayFont);

        // Add text fields to panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
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

            // Clicked button text
            String text = e.getActionCommand();

            switch(text) {

                // Clear text fields and total
                case "Clear":
                    display.setText("");
                    resultDisplay.setText("");
                    result = 0;
                    break;

                // Close application
                case "Close":
                    System.exit(0);

                // Include previous result in current expression
                case "ANS":
                    display.setText(display.getText() + result);
                    break;

                // Space text around operations for easy split
                case "+":
                case "-":
                case "/":
                case "*":
                case "(":
                case ")":
                    display.setText(display.getText() + " " + text + " ");
                    break;

                // Evaluate current expression
                case "=":

                    // Split string and trim whitespace to store in Queue
                    String[] expression = display.getText().trim().split("\\s+");
                    Queue<String> q = new LinkedList<>();
                    q.addAll(Arrays.asList(expression));

                    // Hold values and operations
                    Stack<Double> values = new Stack<Double>();
                    Stack<String> ops = new Stack<String>();

                    // Iterate through queue till empty
                    while(!q.isEmpty()) {

                        // Get queue token
                        String token = q.poll();

                        // Ignore leading parenthesis
                        if (token.equals("(")) continue;

                        // Push operand
                        else if (token.equals("+")) ops.push(token);
                        else if (token.equals("*")) ops.push(token);
                        else if (token.equals("-")) ops.push(token);
                        else if (token.equals("/")) ops.push(token);

                        // Evaluate innermost expression 
                        else if (token.equals(")")) {
                            values.push(calculate(ops, values));
                        }

                        // Push value
                        else {
                            values.push(Double.parseDouble(token));
                        }
                    }

                    // Evaluate current values on stack if user didnt 
                    // include one set of parenthesis
                    if (values.size() >= 2) {
                        result = calculate(ops, values);
                    }
                    else {
                        result = values.pop();
                    }
    
                    // Update expression display text field
                    display.setText("");
                    // Display result in result text field
                    resultDisplay.setText(Double.toString(result));
                    break;

                default:
                    display.setText(display.getText() + text);
            }
        }
    }

    // Perform arithmetic operation on two values
    private double calculate(Stack<String> ops, Stack<Double> values) {

        // Pop first value and operation
        String op = ops.pop();
        Double val = values.pop();

        // Pop another value and perform operation
        if (op.equals("+")) val = values.pop() + val;
        else if (op.equals("-")) val = values.pop() - val;
        else if (op.equals("*")) val = values.pop() * val;
        else if (op.equals("/")) {

            // Check for division by zero
            if (val == 0.0) {

                // Show alert indicating division by zero
                JOptionPane.showMessageDialog(null, "Division By Zero");
                System.exit(0);
            }
            else val = values.pop() / val;
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