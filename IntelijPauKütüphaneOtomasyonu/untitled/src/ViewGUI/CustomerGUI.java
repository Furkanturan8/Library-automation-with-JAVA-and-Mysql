package ViewGUI;

import java.awt.EventQueue;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Model.*;
import Panel.PAUBooksPanel;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class CustomerGUI extends JFrame {

    static Customer customer = new Customer();
    private JPanel contentPane;
    private JTable table;
    static  BooksSQL books = new BooksSQL();
    private DefaultTableModel booksModel = null;
    private Object[] booksData = null;
    private PAUBooksPanel booksPanel;
    JPanel menuPANEL = new JPanel();
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CustomerGUI frame = new CustomerGUI(customer);
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
    public CustomerGUI(Customer customer) {
        getContentPane().setBackground(new Color(0, 128, 128));
        setResizable(false);
        setTitle("Kullanıcı Paneli");
        setBounds(100, 100, 652, 478);
        getContentPane().setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 638, 22);
        getContentPane().add(menuBar);

        menuPANEL.setBounds(10, 32, 618, 399);
        getContentPane().add(menuPANEL);
        menuPANEL.setLayout(null);

        booksPanel = new PAUBooksPanel();
        booksPanel.setBounds(0, 0, 618, 399);
        menuPANEL.add(booksPanel);

        JMenu kisiselBilgiler = new JMenu("Kişisel Bilgiler");
        kisiselBilgiler.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                CustomerInfoGUI customerInfoGUI = new CustomerInfoGUI(customer);
                customerInfoGUI.setVisible(true);
            }
        });

        menuBar.add(kisiselBilgiler);

        JMenu islemYap = new JMenu("İşlem Yap");
        menuBar.add(islemYap);

        JMenuItem kitapAl = new JMenuItem("Kitap Al");
        kitapAl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BuyBooksGUI buyBook = new BuyBooksGUI(customer);
                buyBook.setVisible(true);

            }
        });
        islemYap.add(kitapAl);

        JMenuItem kitapSuresiniUzat = new JMenuItem("Kitap Süresini Uzat");
        kitapSuresiniUzat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DelayTheBookGUI delayTheBook = new DelayTheBookGUI(customer);
                delayTheBook.setVisible(true);

            }
        });
        islemYap.add(kitapSuresiniUzat);

        JMenu ayarlar = new JMenu("Ayarlar");
        menuBar.add(ayarlar);

        JMenuItem bilgilerimiDuzenle = new JMenuItem("Bilgilerimi Düzenle");
        bilgilerimiDuzenle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UpdateCustomerGUI updateCustomerGUI = new UpdateCustomerGUI(customer);
                updateCustomerGUI.setVisible(true);

            }
        });
        ayarlar.add(bilgilerimiDuzenle);

        JMenu menuExit = new JMenu("Çıkış Yap");
        menuExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(Helper.Helper.confirm("sure")) {
                    LoginGUI loginGUI = new LoginGUI();
                    loginGUI.setVisible(true);
                    dispose();
                }
                else {
                    Helper.Helper.showMsg("Çıkış İptal Edildi!");
                }
            }
        });
        menuBar.add(menuExit);

    }
}
