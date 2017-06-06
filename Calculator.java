/* Name: Calculator.java
   Author: Robin Goyal
   Last-Modified: June 6, 2017
   Purpose: Create a basic calculator in Java using
            Djikstra's Two Stack algorithm
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener{

    private JTextField display = new JTextField(17);
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
        
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //JTextField display = new JTextField(17);
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        Font displayFont = display.getFont().deriveFont(Font.PLAIN, 25f);
        display.setFont(displayFont);
        topPanel.add(display);

        // Create grid for buttons
        JPanel grid = new JPanel();
        grid.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        grid.setLayout(new GridLayout(5, 4, 5, 5));

        // Store array of strings for buttons
        String[] buttons = {
            "Clear", "Del", "", "Close", "7", "8", "9", "/", "4", 
            "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+"
        };

        JButton[] arr = new JButton[buttons.length];

        // Add calculator buttons to grid
        for (int i = 0; i < buttons.length; i++) {
            if (i == 2) {
                grid.add(new JLabel(buttons[i]));
            }
            else {
                arr[i] = new JButton(buttons[i]);
                arr[i].addActionListener(this);
                grid.add(arr[i]);
            }
        }

        // Add text field and grid to Frame
        add(topPanel, BorderLayout.NORTH);
        add(grid, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            String text = e.getActionCommand();
            display.setText(text);

            if (text == "Clear") {
                display.setText("");
            }

            else if(text == "Close") {
                System.exit(0);
            }
        }
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Calculator test = new Calculator();
            test.setVisible(true);
        });
    }
}