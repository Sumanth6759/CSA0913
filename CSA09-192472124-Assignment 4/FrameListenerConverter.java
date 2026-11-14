import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FrameListenerConverter extends JFrame implements ActionListener {
    JTextField amount = new JTextField(10);
    JComboBox<String> target = new JComboBox<>(new String[]{"USD", "EUR"});
    JLabel result = new JLabel(" ");
    public FrameListenerConverter() {
        super("Converter – Frame Listener");
        setLayout(new FlowLayout());
        JButton convert = new JButton("Convert");
        add(new JLabel("INR")); add(amount); add(target); add(convert); add(result);
        convert.addActionListener(this);
        amount.addActionListener(this);
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) { dispose(); }
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); pack();
    }
    @Override public void actionPerformed(ActionEvent e) { convert(); }
    private void convert() {
        try {
            double v = Double.parseDouble(amount.getText());
            double rate = target.getSelectedItem().equals("USD") ? .012 : .011;
            String s = String.format("%.2f %s", v * rate, target.getSelectedItem());
            result.setText(s); JOptionPane.showMessageDialog(this, s);
        } catch (NumberFormatException ex) { result.setText("Invalid amount"); }
    }
    public static void main(String[] a) { SwingUtilities.invokeLater(() -> new FrameListenerConverter().setVisible(true)); }
}
