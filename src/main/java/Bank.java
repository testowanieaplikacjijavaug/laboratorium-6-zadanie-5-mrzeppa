import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

class Bank extends JFrame {

    private int saldo = 100;
    private JTextField t = new JTextField(20);

    private ArrayList<JButton> buttons = new ArrayList<JButton>();

    Bank() {
        setTitle("Bank");
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());


        buttons.add(new JButton("Wyplac 50"));
        buttons.add(new JButton("Wplac 50"));
        cp.add(buttons.get(0));
        cp.add(buttons.get(1));
        buttons.get(0).addActionListener(new wyplac());
        buttons.get(1).addActionListener(new wplac());
        t.setText(String.valueOf(saldo));
        cp.add(t);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setVisible(true);

    }

    class wyplac implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof JButton) {
                if (saldo >= 50) {
                    saldo -= 50;
                    if(saldo == 0)
                        buttons.get(0).setEnabled(false);

                    t.setText(String.valueOf(saldo));
                }
            }
        }
    }

    class wplac implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof JButton) {
                saldo += 50;
                t.setText(String.valueOf(saldo));
                buttons.get(0).setEnabled(true);
            }
        }
    }

    public static void main(String[] arg) {
        JFrame f = new Bank();
    }
}




