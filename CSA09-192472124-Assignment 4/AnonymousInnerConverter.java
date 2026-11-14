import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AnonymousInnerConverter extends JFrame {
    private final JTextField amount = new JTextField(10);
    private final JComboBox<String> target = new JComboBox<>(new String[]{"USD", "EUR"});
    private final JLabel result = new JLabel(" ");
    public AnonymousInnerConverter() {
        super("Converter – Anonymous Inner Class");
        setLayout(new FlowLayout());
        JButton b = new JButton("Convert");
        add(new JLabel("INR")); add(amount); add(target); add(b); add(result);
        ActionListener handler = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double v = Double.parseDouble(amount.getText());
                    double rate = target.getSelectedItem().equals("USD") ? .012 : .011;
                    String s = String.format("%.2f %s", v * rate, target.getSelectedItem());
                    result.setText(s); JOptionPane.showMessageDialog(AnonymousInnerConverter.this, s);
                } catch (NumberFormatException ex) { result.setText("Invalid amount"); }
            }
        };
        b.addActionListener(handler); amount.addActionListener(handler);
        addWindowListener(new WindowAdapter() { @Override public void windowClosing(WindowEvent e) { dispose(); }});
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); pack();
    }
    public static void main(String[] a) { SwingUtilities.invokeLater(() -> new AnonymousInnerConverter().setVisible(true)); }
}
