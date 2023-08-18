package Panel;

import java.awt.Font;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.BooksSQL;

public class PAUBooksPanel extends JPanel {
    static  BooksSQL books = new BooksSQL();
    private DefaultTableModel booksModel = null;
    private Object[] booksData = null;
    private JTable table;
    /**
     * Create the panel.
     */
    public PAUBooksPanel() {
        setBounds(0,0,602,403);
        setLayout(null);
        setVisible(true);

        booksModel = new DefaultTableModel();
        Object[] colBooksName = new Object[5];
        colBooksName[0] = "ID";
        colBooksName[1] = "Kitabın Adı";
        colBooksName[2] = "Kitabın Yazarı";
        colBooksName[3] = "Kitabın Türü";
        colBooksName[4] = "Kitabın Durumu";
        booksModel.setColumnIdentifiers(colBooksName);; //basliklari atadik
        booksData = new Object[5]; // basliklarin altinda gosterilmesi gereken veriler

        try {
            for(int i = 0; i<books.getBooksList().size(); i++) {
                booksData[0] = books.getBooksList().get(i).getId();
                booksData[1] = books.getBooksList().get(i).getBookName();
                booksData[2] = books.getBooksList().get(i).getBookAuthor();
                booksData[3] = books.getBooksList().get(i).getBookType();
                booksData[4] = books.getBooksList().get(i).getBookStatus();
                booksModel.addRow(booksData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JLabel lblNewLabel = new JLabel("PAU Kitap Listesi");
        lblNewLabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 18));
        lblNewLabel.setBounds(21, 21, 177, 32);
        add(lblNewLabel);


        JScrollPane sp = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setBounds(0, 63, 601, 339);
        add(sp);


        table = new JTable(booksModel);
        sp.setViewportView(table);

    }

}
