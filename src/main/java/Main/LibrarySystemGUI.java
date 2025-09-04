package Main;//package Main;

import model.Book;
import model.Member;
import repository.BookRepository;
import repository.LoanRepository;
import repository.MemberRepository;
import service.BookService;
import service.LoanService;
import service.MemberService;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class LibrarySystemGUI extends JFrame {


    private BookService bookService;
    private MemberService memberService;
    private LoanService loanService;

    private JTable bookTable;
    private JTable memberTable;
    private JTable loanTable;

    public LibrarySystemGUI() {
        // Repositories
        BookRepository bookRepository = new BookRepository();
        MemberRepository memberRepository = new MemberRepository();
        LoanRepository loanRepository = new LoanRepository();

        // Services
        bookService = new BookService(bookRepository);
        memberService = new MemberService(memberRepository);
        loanService = new LoanService(loanRepository);
        bookService.setLoanService(loanService);



        memberService.addMember(new Member("Bram", 64, "bram.labarque@gmail.com", 1234L));
        memberService.addMember(new Member("Lucie",22,"lucie.monarchie@gmail.com",2468L));
        memberService.addMember(new Member("Lola",18,"lola.cawlo@gmail.com",4689L));
        memberService.addMember(new Member("Charlie",45,"charlie.chapline@gmail.com",7649L));

        // GUI setup
        setTitle("📚 Bibliotheek Systeem");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(new Color(230, 230, 250)); // licht lavendel
        tabbedPane.setForeground(Color.DARK_GRAY);
        tabbedPane.setFont(new Font("SansSerif", Font.BOLD, 14));

        tabbedPane.add("Boeken", createBooksPanel());
        tabbedPane.add("Leden", createMembersPanel());
        tabbedPane.add("Leningen", createLoansPanel());

        add(tabbedPane);
        setLocationRelativeTo(null); // center op scherm
    }

    private JPanel createBooksPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Table
        String[] columns = {"Boek", "Lid", "Uitleendatum", "Vervaldatum", "Status"};
        bookTable = new JTable(new DefaultTableModel(columns, 0));
        refreshBooks();

        panel.add(new JScrollPane(bookTable), BorderLayout.CENTER);

        // Buttons
        JButton addButton = new JButton("➕ Boek toevoegen");
        addButton.setBackground(new Color(76, 175, 80)); // groen
        addButton.setForeground(Color.WHITE);            // witte tekst
        addButton.setFocusPainted(false);
        addButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        addButton.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(this, "Titel:");
            if (title != null && !title.isEmpty()) {
                bookService.addBook(new Book(title));
                refreshBooks();
            }
        });

        panel.add(addButton, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createMembersPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columns = {"Naam", "Leeftijd", "Email"};
        memberTable = new JTable(new DefaultTableModel(columns, 0));
        refreshMembers();

        panel.add(new JScrollPane(memberTable), BorderLayout.CENTER);

        JButton addButton = new JButton("➕ Lid toevoegen");
        addButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Naam:");
            if (name != null && !name.isEmpty()) {
                memberService.addMember(new Member(name, 0, name + "@mail.com", 1234L));
                refreshMembers();
            }
        });

        panel.add(addButton, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createLoansPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columns = {"Boek", "Lid", "Startdatum", "Einddatum"};
        loanTable = new JTable(new DefaultTableModel(columns, 0));
        loanTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int col) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

                // Controleer of de kolom 'Status' bestaat
                if (table.getColumnCount() > 4) {
                    String status = table.getValueAt(row, 4).toString(); // kolom "Status"
                    if (status.equals("ACTIVE")) {
                        c.setBackground(new Color(255, 250, 205)); // lichtgeel
                    } else if (status.equals("RETURNED")) {
                        c.setBackground(new Color(200, 255, 200)); // lichtgroen
                    } else {
                        c.setBackground(Color.WHITE);
                    }
                }

                return c;
            }
        });

        panel.add(new JScrollPane(loanTable), BorderLayout.CENTER);

        JButton loanButton = new JButton("📖 Uitlenen");
        loanButton.addActionListener(e -> {
            List<Book> books = bookService.getBooks();
            List<Member> members = memberService.listMembers();

            if (books.isEmpty() || members.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Geen boeken of leden beschikbaar!");
                return;
            }

            Book book = books.get(0); // voor demo: pak eerste boek
            Member member = members.get(0); // eerste lid
            loanService.createLoan(book, member, 14);
            refreshLoans();
        });

        panel.add(loanButton, BorderLayout.SOUTH);
        return panel;
    }

    private void refreshBooks() {
        DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
        model.setRowCount(0);
        for (Book b : bookService.getBooks()) {
            model.addRow(new Object[]{b.getIsbn(), b.getTitle()});

        }
    }

    private void refreshMembers() {
        DefaultTableModel model = (DefaultTableModel) memberTable.getModel();
        model.setRowCount(0);
        for (Member m : memberService.listMembers()) {
            model.addRow(new Object[]{m.getName(), m.getAge(), m.getEmail()});
        }
    }


    private void refreshLoans() {
        DefaultTableModel model = (DefaultTableModel) loanTable.getModel();
        model.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        loanService.findAll().forEach(l -> {
            model.addRow(new Object[]{
                    l.getBook().getTitle(),
                    l.getMember().getName(),
                    sdf.format(l.getLoanDate()), // datum formatteren
                    sdf.format(l.getDueDate()),  // datum formatteren
                    l.getStatus()
            });
        });


    }



    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new LibrarySystemGUI().setVisible(true));
    }
}

