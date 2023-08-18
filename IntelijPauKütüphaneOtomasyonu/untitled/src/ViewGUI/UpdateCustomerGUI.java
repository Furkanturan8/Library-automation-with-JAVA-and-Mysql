package ViewGUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import Helper.DBConnection;
import Helper.Helper;
import Model.Customer;
import Model.User;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class UpdateCustomerGUI extends JFrame {

    private JPanel contentPane;
    private DBConnection conn = new DBConnection();
    static private Customer customer;
    private JTextField txtF_name;
    private JTextField txtF_kAdi;
    private JTextField txtF_tc;
    private JTextField txtF_telNo;
    private JPasswordField passwordF;
    private JPasswordField passwordFAgain;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UpdateCustomerGUI frame = new UpdateCustomerGUI(customer);
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
    public UpdateCustomerGUI(Customer customer) {
        setTitle("Kullanıcı Güncelleme Ekranı");
        setResizable(false);
        setBounds(100, 100, 621, 533);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel panelGuncelle = new JPanel();
        panelGuncelle.setBackground(new Color(255, 255, 255));
        panelGuncelle.setBounds(269, 70, 328, 318);
        contentPane.add(panelGuncelle);
        panelGuncelle.setLayout(null);

        JButton btnGuncelle = new JButton("Güncelle");
        btnGuncelle.setEnabled(false);
        txtF_name = new JTextField();
        txtF_name.setEnabled(false);
        txtF_name.setBounds(124, 63, 192, 32);
        panelGuncelle.add(txtF_name);
        txtF_name.setColumns(10);

        txtF_kAdi = new JTextField();
        txtF_kAdi.setEnabled(false);
        txtF_kAdi.setColumns(10);
        txtF_kAdi.setBounds(124, 106, 192, 32);
        panelGuncelle.add(txtF_kAdi);

        txtF_tc = new JTextField();
        txtF_tc.setEnabled(false);
        txtF_tc.setColumns(10);
        txtF_tc.setBounds(124, 149, 192, 32);
        panelGuncelle.add(txtF_tc);

        txtF_telNo = new JTextField();
        txtF_telNo.setEnabled(false);
        txtF_telNo.setColumns(10);
        txtF_telNo.setBounds(124, 191, 192, 32);
        panelGuncelle.add(txtF_telNo);

        passwordF = new JPasswordField();
        passwordF.setEnabled(false);
        passwordF.setBounds(124, 233, 192, 31);
        panelGuncelle.add(passwordF);

        passwordFAgain = new JPasswordField();
        passwordFAgain.setEnabled(false);
        passwordFAgain.setBounds(124, 274, 192, 31);
        panelGuncelle.add(passwordFAgain);

        setContentPane(contentPane);
        Image image1 = new ImageIcon(this.getClass().getResource("users.png")).getImage();
        contentPane.setLayout(null);

        JLabel lblKullaniciGncellemeEkran = new JLabel("Kullanıcı Güncelleme Ekranı");
        lblKullaniciGncellemeEkran.setBackground(new Color(255, 255, 255));
        lblKullaniciGncellemeEkran.setBounds(13, 10, 584, 50);
        lblKullaniciGncellemeEkran.setHorizontalAlignment(SwingConstants.CENTER);
        lblKullaniciGncellemeEkran.setForeground(new Color(255, 255, 255));
        lblKullaniciGncellemeEkran.setFont(new Font("Segoe UI Semilight", Font.BOLD | Font.ITALIC, 20));
        contentPane.add(lblKullaniciGncellemeEkran);

        JLabel icon = new JLabel("");
        icon.setHorizontalAlignment(SwingConstants.CENTER);
        icon.setIcon(new ImageIcon(image1));
        icon.setBounds(13, 70, 246, 136);
        contentPane.add(icon);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(13, 210, 246, 178);
        contentPane.add(panel);
        panel.setLayout(null);

        passwordField = new JPasswordField();
        passwordField.setBounds(10, 84, 226, 28);
        panel.add(passwordField);

        JButton btnGoster = new JButton("Göster");
        btnGoster.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                User obj =customer.getUser(customer.getTc());
                String pass = obj.getPassword();
                if(pass.equals(passwordField.getText())) {
                    btnGuncelle.setEnabled(true);
                    txtF_name.setEnabled(true);
                    txtF_kAdi.setEnabled(true);
                    txtF_tc.setEnabled(true);
                    txtF_telNo.setEnabled(true);
                    passwordF.setEnabled(true);
                    passwordFAgain.setEnabled(true);
                    txtF_name.setText(customer.getName());
                    txtF_kAdi.setText(customer.getKullaniciAdi());
                    txtF_tc.setText(customer.getTc());
                    txtF_telNo.setText(customer.getTelefonNo());

                }
                else
                    Helper.showMsg("Yanlış Şifre Girişi");

            }
        });
        btnGoster.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        btnGoster.setBounds(10, 122, 226, 37);
        panel.add(btnGoster);

        JLabel lblAdSoyad_1 = new JLabel("Adı Soyadı:");
        lblAdSoyad_1.setBackground(new Color(255, 255, 255));
        lblAdSoyad_1.setForeground(new Color(0, 0, 0));
        lblAdSoyad_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lblAdSoyad_1.setBounds(19, 55, 95, 50);
        panelGuncelle.add(lblAdSoyad_1);

        JLabel lblKullanciAd_1 = new JLabel("Kullanıcı Adı:");
        lblKullanciAd_1.setBackground(new Color(0, 0, 0));
        lblKullanciAd_1.setForeground(new Color(0, 0, 0));
        lblKullanciAd_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lblKullanciAd_1.setBounds(19, 98, 95, 50);
        panelGuncelle.add(lblKullanciAd_1);

        JLabel lbl_tc_1 = new JLabel("TC:");
        lbl_tc_1.setBackground(new Color(255, 255, 255));
        lbl_tc_1.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_tc_1.setForeground(new Color(0, 0, 0));
        lbl_tc_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lbl_tc_1.setBounds(19, 153, 95, 37);
        panelGuncelle.add(lbl_tc_1);

        JLabel lbl_telNo_1 = new JLabel("Telefon No:");
        lbl_telNo_1.setBackground(new Color(255, 255, 255));
        lbl_telNo_1.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_telNo_1.setForeground(new Color(0, 0, 0));
        lbl_telNo_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lbl_telNo_1.setBounds(19, 200, 95, 27);
        panelGuncelle.add(lbl_telNo_1);

        JLabel lblKullanciAd_1_1 = new JLabel("Şifre:");
        lblKullanciAd_1_1.setForeground(Color.BLACK);
        lblKullanciAd_1_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lblKullanciAd_1_1.setBackground(Color.BLACK);
        lblKullanciAd_1_1.setBounds(19, 237, 95, 27);
        panelGuncelle.add(lblKullanciAd_1_1);

        JLabel lblKullanciAd_1_1_1 = new JLabel("Şifre Tekrarı:");
        lblKullanciAd_1_1_1.setForeground(Color.BLACK);
        lblKullanciAd_1_1_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lblKullanciAd_1_1_1.setBackground(Color.BLACK);
        lblKullanciAd_1_1_1.setBounds(19, 269, 95, 37);
        panelGuncelle.add(lblKullanciAd_1_1_1);

        JLabel lblBaslik = new JLabel("Güncellemek İstediğiniz Verileri Değiştirin");
        lblBaslik.setHorizontalAlignment(SwingConstants.CENTER);
        lblBaslik.setForeground(new Color(0, 0, 128));
        lblBaslik.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
        lblBaslik.setBackground(Color.RED);
        lblBaslik.setBounds(10, 10, 306, 50);
        panelGuncelle.add(lblBaslik);


        JLabel lblMevcutVerileriniz = new JLabel("Güncellemeyi Aktif Ediniz");
        lblMevcutVerileriniz.setHorizontalAlignment(SwingConstants.CENTER);
        lblMevcutVerileriniz.setForeground(new Color(0, 0, 128));
        lblMevcutVerileriniz.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
        lblMevcutVerileriniz.setBackground(Color.RED);
        lblMevcutVerileriniz.setBounds(0, 10, 236, 27);
        panel.add(lblMevcutVerileriniz);

        JLabel lbl_ANAHTAR = new JLabel("Mevcut Şifrenizi Giriniz:");
        lbl_ANAHTAR.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_ANAHTAR.setForeground(Color.BLACK);
        lbl_ANAHTAR.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lbl_ANAHTAR.setBackground(Color.WHITE);
        lbl_ANAHTAR.setBounds(10, 47, 226, 27);
        panel.add(lbl_ANAHTAR);

        JButton btnCancel = new JButton("İptal Et");
        btnCancel.setBounds(13, 425, 246, 37);
        contentPane.add(btnCancel);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancel.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));

        btnGuncelle.setBounds(269, 425, 328, 36);
        contentPane.add(btnGuncelle);
        btnGuncelle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int passwordControl = 1;
                if(txtF_name.getText().length() == 0 || txtF_kAdi.getText().length() == 0 || txtF_tc.getText().length() == 0 || txtF_telNo.getText().length() == 0 || passwordF.getText().length()==0 || passwordFAgain.getText().length()==0) {
                    Helper.showMsg("fill"); // tum alanlari doldurunuz
                }

                else if(!passwordF.getText().equals(passwordFAgain.getText())) {
                    Helper.showMsg("Şifre Tekrarını Doğru Giriniz!");
                    passwordF.setText(null);
                    passwordFAgain.setText(null);
                    passwordControl = 0;
                }

                else {
                    try {
                        boolean control;
                        if(passwordControl == 1) {
                            control = customer.updateCustomer(customer.getId(),txtF_kAdi.getText(),passwordF.getText(),txtF_name.getText(),txtF_tc.getText(), txtF_telNo.getText());
                            if(control == true) {
                                Helper.showMsg("success");
                                txtF_kAdi.setText(null); // dolu olan kutuları bosalttik
                                txtF_name.setText(null);
                                passwordF.setText(null);
                                passwordFAgain.setText(null);
                                txtF_tc.setText(null);
                                txtF_telNo.setText(null);
                                // simdi eklendi ama tablo guncellenmedi. onu da ayarlayalim --> ertelendi
                                dispose();
                            }
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        btnGuncelle.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));


    }

}

