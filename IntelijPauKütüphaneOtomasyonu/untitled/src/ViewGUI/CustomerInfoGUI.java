package ViewGUI;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.BooksSQL;
import Model.Customer;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;

public class CustomerInfoGUI extends JFrame {
    static Customer customer = new Customer();
    static  BooksSQL books = new BooksSQL();
    private DefaultTableModel userBuyBooksModel = null;
    private Object[] booksData = null;
    private JPanel contentPane;
    private JLabel lbl_baslik_1;
    private JTable alınanKitaplar;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CustomerInfoGUI frame = new CustomerInfoGUI(customer);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public CustomerInfoGUI(Customer customer) {
        setTitle("Kişisel Bilgiler");
        setResizable(false);
        setBounds(100, 100, 608, 573);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);


        userBuyBooksModel = new DefaultTableModel();
        Object[] colBooksName = new Object[4];
        colBooksName[0] = "Kitap ID'si";
        colBooksName[1] = "Kullanıcı ID'si";
        colBooksName[2] = "Kitap Alış Tarihi";
        colBooksName[3] = "Kitabın Teslim Tarihi";

        userBuyBooksModel.setColumnIdentifiers(colBooksName); //basliklari atadik
        booksData = new Object[4]; // basliklarin altinda gosterilmesi gereken veriler

        try {
            for(int i = 0; i<books.getUserBooksList(customer.getId()).size(); i++) {
                booksData[0] = books.getUserBooksList(customer.getId()).get(i).getBookID();
                booksData[1] = books.getUserBooksList(customer.getId()).get(i).getUserID();
                booksData[2] = books.getUserBooksList(customer.getId()).get(i).getFirstDate();
                booksData[3] = books.getUserBooksList(customer.getId()).get(i).getDeadline();
                userBuyBooksModel.addRow(booksData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        Image image1 = new ImageIcon(this.getClass().getResource("users.png")).getImage();


        JLabel lbl_baslik = new JLabel("PAU Kullanıcı Bilgileri");
        lbl_baslik.setHorizontalTextPosition(SwingConstants.CENTER);
        lbl_baslik.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_baslik.setForeground(Color.WHITE);
        lbl_baslik.setFont(new Font("Tahoma", Font.BOLD, 16));
        lbl_baslik.setBounds(10, 0, 197, 49);
        contentPane.add(lbl_baslik);

        JLabel lbl_name = new JLabel("Ad ve Soyadınız");
        lbl_name.setForeground(Color.WHITE);
        lbl_name.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbl_name.setBounds(34, 273, 191, 27);
        contentPane.add(lbl_name);

        JLabel lbl_dynamicName = new JLabel(customer.getName());
        lbl_dynamicName.setForeground(Color.WHITE);
        lbl_dynamicName.setFont(new Font("Tahoma", Font.ITALIC, 14));
        lbl_dynamicName.setBounds(30, 310, 191, 27);
        contentPane.add(lbl_dynamicName);

        JLabel lbl_telNo = new JLabel("Telefonunuz");
        lbl_telNo.setForeground(Color.WHITE);
        lbl_telNo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbl_telNo.setBounds(30, 347, 191, 27);
        contentPane.add(lbl_telNo);

        JLabel lbl_dynamicTelNo = new JLabel(customer.getTelefonNo());
        lbl_dynamicTelNo.setForeground(Color.WHITE);
        lbl_dynamicTelNo.setFont(new Font("Tahoma", Font.ITALIC, 14));
        lbl_dynamicTelNo.setBounds(30, 384, 191, 27);
        contentPane.add(lbl_dynamicTelNo);

        JLabel lbl_tcNo = new JLabel("TC'niz");
        lbl_tcNo.setForeground(Color.WHITE);
        lbl_tcNo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbl_tcNo.setBounds(30, 421, 191, 27);
        contentPane.add(lbl_tcNo);

        JLabel lbl_dynamicTcNo = new JLabel(customer.getTc());
        lbl_dynamicTcNo.setForeground(Color.WHITE);
        lbl_dynamicTcNo.setFont(new Font("Tahoma", Font.ITALIC, 14));
        lbl_dynamicTcNo.setBounds(34, 458, 191, 26);
        contentPane.add(lbl_dynamicTcNo);

        JLabel icon = new JLabel("");
        icon.setHorizontalAlignment(SwingConstants.CENTER);
        icon.setIcon(new ImageIcon(image1));
        icon.setBounds(352, 273, 180, 131);
        contentPane.add(icon);

        JPanel panel = new JPanel();
        panel.setBounds(43, 52, 512, 211);
        contentPane.add(panel);
        panel.setLayout(null);

        lbl_baslik_1 = new JLabel("Aldığınız Kitaplar");
        lbl_baslik_1.setBounds(10, 14, 184, 17);
        lbl_baslik_1.setHorizontalTextPosition(SwingConstants.CENTER);
        lbl_baslik_1.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_baslik_1.setForeground(Color.DARK_GRAY);
        lbl_baslik_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lbl_baslik_1);

        JScrollPane scrollPane1 = new JScrollPane(alınanKitaplar,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane1.setBounds(10, 41, 492, 149);
        panel.add(scrollPane1);

        alınanKitaplar = new JTable(userBuyBooksModel);
        scrollPane1.setViewportView(alınanKitaplar);

        JLabel lblNewLabel = new JLabel("Bilgilendirme : Aldığınız Kitapları Zamanında Rektörlükteki Personele İade Ediniz !");
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Yu Gothic", Font.BOLD, 14));
        lblNewLabel.setBounds(10, 503, 574, 33);
        contentPane.add(lblNewLabel);
    }
}
