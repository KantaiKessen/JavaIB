import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel01 is a Panel that calculates the square root of a number.
 * <p>
 * It displays a text box, a display box, and a button.
 * A user will input a number into the text box.
 * The program will calculate the square root of the number when the button is pressed.
 * The program will then display it to the thousandths place.
 *
 * @author Kevin Liu
 * @version 1.0.0
 */
public class Panel01 extends JPanel {
    private JLabel label;
    private JTextField box;

    /**
     * This is the constructor of for the JFrame of the Panel.
     * This allows the program to do everything in the description.
     */
    public Panel01() {
        setLayout(new FlowLayout());
        box = new JTextField("0.0", 10);
        box.setHorizontalAlignment(SwingConstants.RIGHT);
        add(box);
        JButton button = new JButton("SQRT");
        button.addActionListener(new Listener());
        add(button);
        label = new JLabel("0.0");
        label.setFont(new Font("Serif", Font.BOLD, 20));
        label.setForeground(Color.BLUE);
        add(label);
    }

    /**
     * This is the Listener of the program and allows it to function properly.
     * It takes in the Argument for input number and outputs the square root.
     */
    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (Double.parseDouble(box.getText()) > 0) {
                label.setText("" + ((int) (Math.sqrt
                        (Double.parseDouble(box.getText())) * 100)) / 100.0);
            } else {
                label.setText(((int) (Math.sqrt
                        (-Double.parseDouble(box.getText())) * 100)) / 100.0
                        + "i");
            }
        }
    }
}