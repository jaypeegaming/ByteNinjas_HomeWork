import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Main class for the Milestone Calculator application
public class MilestoneCalculator extends JFrame implements ActionListener, KeyListener {
    // GUI components
    private JTextField milestone1Field, milestone2Field, terminalField;
    private JLabel milestone1Label, milestone2Label, terminalLabel, resultLabel, passingGradeLabel;
    private JButton calculateButton;

    // Constructor to initialize the GUI
    public MilestoneCalculator() {
        // Set window properties
        setTitle("MO-IT103 - Computer Programming 2 Milestone Grade Calculator\n");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new GridLayout(7, 2, 10, 10)); // Set layout
        getContentPane().setBackground(new Color(240, 240, 240)); // Set background color

        // Set application icon
        ImageIcon icon = new ImageIcon("ByteNinjas_HomeWork/src/MMDC_Logo.jpg"); // Replace "MMdC.png" with the path to your icon file
        Image image = icon.getImage();
        setIconImage(image);

        // Initialize GUI components
        milestone1Label = new JLabel("Milestone 1 (Max. 25 pts):");
        milestone1Label.setHorizontalAlignment(SwingConstants.RIGHT); // Align label to the right
        milestone1Label.setForeground(Color.BLUE); // Set label text color
        milestone2Label = new JLabel("Milestone 2 (Max. 35 pts):");
        milestone2Label.setHorizontalAlignment(SwingConstants.RIGHT); // Align label to the right
        milestone2Label.setForeground(Color.BLUE); // Set label text color
        terminalLabel = new JLabel("Terminal Assessment (Max. 40 pts):");
        terminalLabel.setHorizontalAlignment(SwingConstants.RIGHT); // Align label to the right
        terminalLabel.setForeground(Color.BLUE); // Set label text color

        milestone1Field = new JTextField();
        milestone1Field.addKeyListener(this); // Add key listener
        milestone2Field = new JTextField();
        milestone2Field.addKeyListener(this); // Add key listener
        terminalField = new JTextField();
        terminalField.addKeyListener(this); // Add key listener

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);
        calculateButton.setBackground(new Color(30, 130, 76)); // Set button background color
        calculateButton.setForeground(Color.WHITE); // Set button text color

        resultLabel = new JLabel("");
        resultLabel.setForeground(Color.RED); // Set result label text color
        passingGradeLabel = new JLabel("");
        passingGradeLabel.setForeground(Color.BLUE); // Set passing grade label text color

        // Add components to the layout
        add(milestone1Label);
        add(milestone1Field);
        add(milestone2Label);
        add(milestone2Field);
        add(terminalLabel);
        add(terminalField);
        add(new JLabel()); // Placeholder
        add(calculateButton);
        add(new JLabel()); // Placeholder
        add(resultLabel);
        add(passingGradeLabel);
    }

    // Action performed when the calculate button is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            calculate();
        }
    }

    // Key event for Enter key pressed
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            calculate();
        }
    }

    public void keyTyped(KeyEvent e) {} // Unused method

    public void keyReleased(KeyEvent e) {} // Unused method

    // Calculate total grade based on input values
    private void calculate() {
        try {
            // Parse input values
            double milestone1 = Double.parseDouble(milestone1Field.getText());
            double milestone2 = Double.parseDouble(milestone2Field.getText());
            double terminal = Double.parseDouble(terminalField.getText());

            // Validate input values
            if (milestone1 < 0 || milestone1 > 25 || milestone2 < 0 || milestone2 > 35 || terminal < 0 || terminal > 40) {
                throw new IllegalArgumentException("Invalid input. Values should be Numeric between 0 and maximum points per Milestone.");
            }

            // Calculate grades for each milestone
            double milestone1Grade = (milestone1 / 25) * 25;
            double milestone2Grade = (milestone2 / 35) * 35;
            double terminalGrade = (terminal / 40) * 40;

            // Calculate total grade
            double totalGrade = milestone1Grade + milestone2Grade + terminalGrade;

            // Display total grade
            resultLabel.setText("Total Grade: " + totalGrade);
            setPassingGradeIndicator(totalGrade);
            resultLabel.setForeground(Color.BLUE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Values should be Numeric between 0 and maximum points per Milestone.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    // Set passing grade indicator based on total grade
    private void setPassingGradeIndicator(double totalGrade) {
        if (totalGrade >= 95.0) {
            passingGradeLabel.setText("Passing Grade: Excellent!");
            passingGradeLabel.setForeground(Color.GREEN); // Set passing grade label text color to green
        } else if (totalGrade >= 90.0) {
            passingGradeLabel.setText("Passing Grade: Superior!");
            passingGradeLabel.setForeground(Color.GREEN); // Set passing grade label text color to green
        } else if (totalGrade >= 85.0) {
            passingGradeLabel.setText("Passing Grade: Very Good!");
            passingGradeLabel.setForeground(Color.GREEN); // Set passing grade label text color to green
        } else if (totalGrade >= 80.0) {
            passingGradeLabel.setText("Passing Grade: Good.");
            passingGradeLabel.setForeground(Color.GREEN); // Set passing grade label text color to green
        } else if (totalGrade >= 76.0) {
            passingGradeLabel.setText("Passing Grade: Very Satisfactory.");
            passingGradeLabel.setForeground(Color.GREEN); // Set passing grade label text color to green
        } else if (totalGrade >= 70.0) {
            passingGradeLabel.setText("Passing Grade: Satisfactory.");
            passingGradeLabel.setForeground(Color.magenta); // Set passing grade label text color to yellow
        } else if (totalGrade >= 64.0) {
            passingGradeLabel.setText("Passing Grade: Fairly Satisfactory.");
            passingGradeLabel.setForeground(Color.magenta); // Set passing grade label text color to yellow
        } else if (totalGrade >= 60.0) {
            passingGradeLabel.setText("Passing Grade: Passed! uy pasok sa banga! ");
            passingGradeLabel.setForeground(Color.magenta); // Set passing grade label text color to yellow
        } else {
            passingGradeLabel.setText(" Failed ka! Bawi nalang Next life :(");
            passingGradeLabel.setForeground(Color.RED); // Set passing grade label text color to red
        }
    }

    // Main method to start the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MilestoneCalculator calculator = new MilestoneCalculator();
            calculator.setVisible(true);
        });
    }
}
