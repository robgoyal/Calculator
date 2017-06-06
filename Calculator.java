/* Name: Calculator.java
   Author: Robin Goyal
   Last-Modified: June 6, 2017
   Purpose: Create a basic calculator in Java using
            Djikstra's Two Stack algorithm
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame{

    public Calculator() {
        initGUI();
    }

    private void initGUI() {
        setTitle("Calculator");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JTextField display = new JTextField(17);
        Font displayFont = display.getFont().deriveFont(Font.PLAIN, 25f);
        display.setFont(displayFont);
        topPanel.add(display);

        JPanel grid = new JPanel();
        grid.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        grid.setLayout(new GridLayout(5, 4, 5, 5));

        String[] buttons = {
            "Clear", "Del", "", "Close", "7", "8", "9", "/", "4", 
            "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+"
        };

        for (int i = 0; i < buttons.length; i++) {
            if (i == 2) {
                grid.add(new JLabel(buttons[i]));
            }
            else {
                grid.add(new JButton(buttons[i]));
            }
        }

        add(topPanel, BorderLayout.NORTH);
        add(grid, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Calculator test = new Calculator();
            test.setVisible(true);
        });
    }
}