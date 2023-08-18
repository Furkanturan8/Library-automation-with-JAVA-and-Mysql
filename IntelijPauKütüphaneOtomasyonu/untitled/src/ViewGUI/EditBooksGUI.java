package ViewGUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PasswordView;

import Helper.Helper;
import Model.BooksSQL;
import Model.User;
import Panel.PAUBooksPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class EditBooksGUI extends JFrame {
    static private BooksSQL booksSQL = new BooksSQL();
    private JPanel contentPane;
    static private PAUBooksPanel booksPanel;
    private JTextField txtF_kitapAdi;
    private JTextField txtF_yazarAdi;
    private JTextField txtF_kitapAdi2;
    private JTextField txtF_yazarAdi2;
    private JTextField txtF_ID;
    private JTextField txtF_ID2;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EditBooksGUI frame = new EditBooksGUI(booksPanel);
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
    public EditBooksGUI(PAUBooksPanel booksPanel) {
        setResizable(false);
        setTitle("PAU Kitap Düzenleme Paneli");
        setBounds(100, 100, 487, 419);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("PAU Kitap Düzenleme ");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel.setBounds(10, 10, 219, 30);
        contentPane.add(lblNewLabel);

        JLabel lblKitapEkle = new JLabel("Kitap Ekle");
        lblKitapEkle.setForeground(new Color(255, 255, 255));
        lblKitapEkle.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
        lblKitapEkle.setBounds(10, 50, 87, 30);
        contentPane.add(lblKitapEkle);

        JLabel lbl_kitapAdı = new JLabel("Kitap Adı:");
        lbl_kitapAdı.setForeground(new Color(255, 222, 173));
        lbl_kitapAdı.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lbl_kitapAdı.setBounds(10, 90, 74, 30);
        contentPane.add(lbl_kitapAdı);

        JLabel lbl_yazarAdi = new JLabel("Yazar Adı:");
        lbl_yazarAdi.setForeground(new Color(255, 222, 173));
        lbl_yazarAdi.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lbl_yazarAdi.setBounds(10, 146, 74, 30);
        contentPane.add(lbl_yazarAdi);

        JLabel lbl_turu = new JLabel("Türü:");
        lbl_turu.setForeground(new Color(255, 222, 173));
        lbl_turu.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lbl_turu.setBounds(10, 200, 74, 30);
        contentPane.add(lbl_turu);

        txtF_kitapAdi = new JTextField();
        txtF_kitapAdi.setBounds(90, 90, 122, 37);
        contentPane.add(txtF_kitapAdi);
        txtF_kitapAdi.setColumns(10);

        txtF_yazarAdi = new JTextField();
        txtF_yazarAdi.setColumns(10);
        txtF_yazarAdi.setBounds(90, 145, 122, 37);
        contentPane.add(txtF_yazarAdi);

        String[] itemsComboBox = {"Eğitim", "Hikaye" , "Roman" ,"Otobiyografi" ,"Biyografi"};
        JComboBox cBox_turu = new JComboBox(itemsComboBox);
        cBox_turu.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
        cBox_turu.setBounds(90, 194, 122, 44);
        contentPane.add(cBox_turu);

        JButton btnNewButton = new JButton("Ekle");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int passwordControl = 1;
                if(txtF_kitapAdi.getText().length()==0 || txtF_yazarAdi.getText().length()==0 || cBox_turu.getItemListeners().length==0) {
                    Helper.showMsg("fill"); // tum alanlari doldurunuz
                }

                else {
                    try {
                        boolean control;
                        if(passwordControl == 1) {
                            control = booksSQL.addBooks(txtF_kitapAdi.getText(), txtF_yazarAdi.getText() ,cBox_turu.getSelectedItem());
                            if(control == true) {
                                Helper.showMsg("success");
                                txtF_kitapAdi.setText(null); // dolu olan kutuları bosalttik
                                txtF_yazarAdi.setText(null);
                            }
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }



            }
        });
        btnNewButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        btnNewButton.setBounds(10, 249, 202, 30);
        contentPane.add(btnNewButton);

        JLabel lblKitapGuncelle = new JLabel("Kitap Güncelle");
        lblKitapGuncelle.setForeground(new Color(255, 255, 255));
        lblKitapGuncelle.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
        lblKitapGuncelle.setBounds(245, 50, 134, 30);
        contentPane.add(lblKitapGuncelle);

        JLabel lbl_kitapAdı2 = new JLabel("Kitap Adı:");
        lbl_kitapAdı2.setForeground(new Color(255, 222, 173));
        lbl_kitapAdı2.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lbl_kitapAdı2.setBounds(245, 130, 74, 30);
        contentPane.add(lbl_kitapAdı2);

        JLabel lbl_yazarAdi2 = new JLabel("Yazar Adı:");
        lbl_yazarAdi2.setForeground(new Color(255, 222, 173));
        lbl_yazarAdi2.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lbl_yazarAdi2.setBounds(245, 170, 74, 30);
        contentPane.add(lbl_yazarAdi2);

        JLabel lbl_turu2 = new JLabel("Türü:");
        lbl_turu2.setForeground(new Color(255, 222, 173));
        lbl_turu2.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lbl_turu2.setBounds(245, 210, 74, 30);
        contentPane.add(lbl_turu2);

        txtF_kitapAdi2 = new JTextField();
        txtF_kitapAdi2.setColumns(10);
        txtF_kitapAdi2.setBounds(325, 130, 122, 27);
        contentPane.add(txtF_kitapAdi2);

        txtF_yazarAdi2 = new JTextField();
        txtF_yazarAdi2.setColumns(10);
        txtF_yazarAdi2.setBounds(325, 170, 122, 27);
        contentPane.add(txtF_yazarAdi2);

        JLabel lbl_ID = new JLabel("ID: ");
        lbl_ID.setForeground(new Color(255, 222, 173));
        lbl_ID.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lbl_ID.setBounds(245, 90, 74, 30);
        contentPane.add(lbl_ID);

        txtF_ID = new JTextField();
        txtF_ID.setColumns(10);
        txtF_ID.setBounds(325, 90, 122, 27);
        contentPane.add(txtF_ID);


        JComboBox cBox_turu2 = new JComboBox(itemsComboBox);
        cBox_turu.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
        cBox_turu2.setBounds(325, 210, 122, 28);
        contentPane.add(cBox_turu2);

        JButton btnGncelle = new JButton("Güncelle");
        btnGncelle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int passwordControl = 1;
                if(txtF_ID.getText().length()==0 || txtF_kitapAdi2.getText().length() == 0 || txtF_yazarAdi2.getText().length() == 0 || cBox_turu2.getItemListeners().length==0) {
                    Helper.showMsg("fill"); // tum alanlari doldurunuz
                }

                else {
                    try {
                        boolean control;
                        if(passwordControl == 1) {
                            int selID =Integer.parseInt(txtF_ID.getText());
                            control = booksSQL.updateBooks(selID,txtF_kitapAdi2.getText(),txtF_yazarAdi2.getText(),cBox_turu2.getSelectedItem());
                            if(control == true) {
                                Helper.showMsg("success");
                                txtF_ID.setText(null); // dolu olan kutuları bosalttik
                                txtF_kitapAdi2.setText(null);
                                txtF_yazarAdi2.setText(null);
                                // simdi eklendi ama tablo guncellenmedi. onu da ayarlayalim --> ertelendi
                            }
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }


            }
        });
        btnGncelle.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        btnGncelle.setBounds(245, 249, 202, 30);
        contentPane.add(btnGncelle);

        JLabel lblKitapSil = new JLabel("Kitap Sil");
        lblKitapSil.setForeground(new Color(255, 255, 255));
        lblKitapSil.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
        lblKitapSil.setBounds(10, 298, 134, 30);
        contentPane.add(lblKitapSil);

        JLabel lbl_ID2 = new JLabel("ID: ");
        lbl_ID2.setForeground(new Color(255, 222, 173));
        lbl_ID2.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lbl_ID2.setBounds(10, 329, 61, 30);
        contentPane.add(lbl_ID2);

        txtF_ID2 = new JTextField();
        txtF_ID2.setColumns(10);
        txtF_ID2.setBounds(81, 329, 131, 30);
        contentPane.add(txtF_ID2);

        JButton btnSil = new JButton("Sil");
        btnSil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if( txtF_ID2.getText().length()==0) {
                    Helper.showMsg("fill");
                }
                else {
                    int selID = Integer.parseInt(txtF_ID2.getText());
                    int passwordControl = 1;
                    if(Helper.confirm("sure")) {
                        try {
                            boolean control=booksSQL.deleteBook(selID);;
                            if(passwordControl==1) {
                                if(control==true) {
                                    Helper.showMsg("success");
                                    txtF_ID2.setText(null);
                                }
                            }
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                }

            }
        });
        btnSil.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        btnSil.setBounds(245, 329, 202, 30);
        contentPane.add(btnSil);

        JButton btnIptalEt = new JButton("İptal Et");
        btnIptalEt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnIptalEt.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        btnIptalEt.setBounds(376, 10, 87, 30);
        contentPane.add(btnIptalEt);







    }
}
