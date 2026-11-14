import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LibrarySearchScreen extends JFrame {
    private final JTextField searchField = new JTextField(22);
    private final JComboBox<String> category =
        new JComboBox<>(new String[]{"All", "Books", "Journals", "Theses"});
    private final DefaultListModel<String> resultsModel = new DefaultListModel<>();
    private final JList<String> results = new JList<>(resultsModel);
    private final JLabel status = new JLabel("Enter a title and click Search.");

    public LibrarySearchScreen() {
        super("Campus Library Search");
        setLayout(new BorderLayout(8, 8));

        JLabel title = new JLabel("Campus Library", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(category);
        JButton search = new JButton("Search");
        searchPanel.add(search);
        JPanel top = new JPanel(new BorderLayout());
        top.add(searchPanel, BorderLayout.CENTER);
        add(top, BorderLayout.PAGE_START);

        resultsModel.addElement("Java Programming – Books");
        resultsModel.addElement("Event-Driven Systems – Journals");
        add(new JScrollPane(results), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());
        JPanel buttons = new JPanel(new FlowLayout());
        JButton prev = new JButton("Prev"), next = new JButton("Next"), reserve = new JButton("Reserve");
        buttons.add(prev); buttons.add(next); buttons.add(reserve);
        bottom.add(buttons, BorderLayout.NORTH);
        bottom.add(status, BorderLayout.SOUTH);
        add(bottom, BorderLayout.SOUTH);

        search.addActionListener(e -> {
            String q = searchField.getText().trim();
            if (q.isEmpty()) status.setText("Please enter a search term.");
            else {
                resultsModel.clear();
                resultsModel.addElement("Sample result for: " + q + " (" + category.getSelectedItem() + ")");
                status.setText("1 result found.");
            }
        });
        prev.addActionListener(e -> status.setText("Previous page selected."));
        next.addActionListener(e -> status.setText("Next page selected."));
        reserve.addActionListener(e -> status.setText("Select a result to reserve."));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 420);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibrarySearchScreen().setVisible(true));
    }
}
