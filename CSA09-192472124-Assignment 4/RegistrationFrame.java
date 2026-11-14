import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RegistrationFrame extends JFrame {
    private final JTextField studentId = new JTextField(12);
    private final JComboBox<String> event = new JComboBox<>(new String[]{"Tech Fest", "Cultural Fest", "Sports Meet"});
    private final JComboBox<String> filter = new JComboBox<>(new String[]{"All", "Tech Fest", "Cultural Fest", "Sports Meet"});
    private final DefaultTableModel model = new DefaultTableModel(
        new String[]{"ID", "Student ID", "Event", "Registered At"}, 0);
    private final JTable table = new JTable(model);
    private final RegistrationService service = new RegistrationService();
    private int page = 0;
    private static final int PAGE_SIZE = 25;
    private final JLabel status = new JLabel("Ready");

    public RegistrationFrame() {
        super("Campus Event Registration");
        setLayout(new BorderLayout(8, 8));
        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem refresh = new JMenuItem("Refresh");
        JMenuItem exit = new JMenuItem("Exit");
        refresh.addActionListener(e -> loadPage(0));
        exit.addActionListener(e -> dispose());
        file.add(refresh); file.add(exit); bar.add(file); setJMenuBar(bar);

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        form.add(new JLabel("Student ID:")); form.add(studentId);
        form.add(new JLabel("Event:")); form.add(event);
        JButton register = new JButton("Register"); form.add(register);
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(new JLabel("Filter:")); filterPanel.add(filter);
        JButton apply = new JButton("Apply Filter"); filterPanel.add(apply);
        JPanel north = new JPanel(new GridLayout(2, 1));
        north.add(form); north.add(filterPanel); add(north, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        JPanel south = new JPanel(new BorderLayout());
        JPanel paging = new JPanel();
        JButton prev = new JButton("Previous"), next = new JButton("Next"), export = new JButton("Export CSV");
        paging.add(prev); paging.add(next); paging.add(export);
        south.add(paging, BorderLayout.NORTH); south.add(status, BorderLayout.SOUTH);
        add(south, BorderLayout.SOUTH);

        register.addActionListener(e -> registerAsync());
        apply.addActionListener(e -> loadPage(0));
        prev.addActionListener(e -> { if (page > 0) loadPage(page - 1); });
        next.addActionListener(e -> loadPage(page + 1));
        export.addActionListener(e -> exportCsv());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(850, 500); setLocationRelativeTo(null);
        loadPage(0);
    }

    private void registerAsync() {
        String id = studentId.getText().trim(), selectedEvent = (String) event.getSelectedItem();
        if (id.isEmpty()) { status.setText("Enter a student ID."); return; }
        setBusy(true);
        new SwingWorker<String, Void>() {
            protected String doInBackground() { return service.register(id, selectedEvent); }
            protected void done() {
                try { status.setText(get()); loadPage(0); }
                catch (Exception ex) { status.setText("Unable to register: " + ex.getMessage()); }
                setBusy(false);
            }
        }.execute();
    }

    private void loadPage(int requestedPage) {
        page = Math.max(0, requestedPage);
        String selectedFilter = (String) filter.getSelectedItem();
        setBusy(true);
        new SwingWorker<RegistrationService.ResultSetPage, Void>() {
            protected RegistrationService.ResultSetPage doInBackground() throws Exception {
                return service.loadPage(selectedFilter, PAGE_SIZE, page * PAGE_SIZE);
            }
            protected void done() {
                try {
                    model.setRowCount(0);
                    for (String[] row : get().rows) model.addRow(row);
                    status.setText("Page " + (page + 1) + " loaded. " + model.getRowCount() + " rows.");
                } catch (Exception ex) { status.setText("Load failed: " + ex.getMessage()); }
                setBusy(false);
            }
        }.execute();
    }

    private void setBusy(boolean busy) { setCursor(Cursor.getPredefinedCursor(busy ? Cursor.WAIT_CURSOR : Cursor.DEFAULT_CURSOR)); }

    private void exportCsv() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;
        try (java.io.PrintWriter out = new java.io.PrintWriter(chooser.getSelectedFile())) {
            for (int c = 0; c < model.getColumnCount(); c++) out.print((c == 0 ? "" : ",") + model.getColumnName(c));
            out.println();
            for (int r = 0; r < model.getRowCount(); r++) {
                for (int c = 0; c < model.getColumnCount(); c++) {
                    if (c > 0) out.print(",");
                    out.print("\"" + String.valueOf(model.getValueAt(r, c)).replace("\"", "\"\"") + "\"");
                }
                out.println();
            }
            status.setText("CSV exported.");
        } catch (Exception ex) { status.setText("Export failed: " + ex.getMessage()); }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrationFrame().setVisible(true));
    }
}
