package Panel;

import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Model.BooksSQL;
import Model.Customer;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OgrenciSorgulaPanel extends JPanel {
    private JTextField txtF_ID;
    private JTable table;
    static  BooksSQL books = new BooksSQL();

    private DefaultTableModel userBuyBooksModel = null;
    private Object[] booksData = null;

    /**
     * Create the panel.
     */
    public OgrenciSorgulaPanel() {
        setBounds(0,0,516,417);
        setLayout(null);

        txtF_ID = new JTextField();
        txtF_ID.setBounds(156, 62, 96, 35);
        add(txtF_ID);
        txtF_ID.setColumns(10);


        userBuyBooksModel = new DefaultTableModel();
        Object[] colBooksName = new Object[4];
        colBooksName[0] = "Kitap ID'si";
        colBooksName[1] = "Kullanıcı ID'si";
        colBooksName[2] = "Kitap Alış Tarihi";
        colBooksName[3] = "Kitabın Teslim Tarihi";

        userBuyBooksModel.setColumnIdentifiers(colBooksName); //basliklari atadik
        booksData = new Object[4]; // basliklarin altinda gosterilmesi gereken veriler




        JLabel lblNewLabel = new JLabel("Öğrenci Sorgulama Ekranı ");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setBounds(10, 10, 198, 30);
        add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Öğrenci ID:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(10, 62, 136, 35);
        add(lblNewLabel_1);


        JButton btn_verileriGetir = new JButton("Verileri Getir");
        btn_verileriGetir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                DefaultTableModel clearModel = (DefaultTableModel) userBuyBooksModel; // temizleme islemi yapar
                clearModel.setRowCount(0); // row lar silinir yani data kisimlari silinir.

                int selId=Integer.parseInt(txtF_ID.getText());

                try {

                    for(int i = 0; i<books.getUserBooksList(selId).size(); i++) {
                        booksData[0] = books.getUserBooksList(selId).get(i).getBookID();
                        booksData[1] = books.getUserBooksList(selId).get(i).getUserID();
                        booksData[2] = books.getUserBooksList(selId).get(i).getFirstDate();
                        booksData[3] = books.getUserBooksList(selId).get(i).getDeadline();
                        userBuyBooksModel.addRow(booksData);
                    }
                } catch (SQLException exp) {
                    exp.printStackTrace();
                }
            }
        });
        btn_verileriGetir.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btn_verileriGetir.setBounds(272, 62, 234, 35);
        add(btn_verileriGetir);



        JScrollPane scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(10, 107, 496, 300);
        add(scrollPane);

        table = new JTable(userBuyBooksModel);
        scrollPane.setViewportView(table);
        setVisible(true);




    }
}
