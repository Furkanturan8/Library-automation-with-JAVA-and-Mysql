package ViewGUI;
import Model.Admin;
import Model.Customer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class RegisterCustomerGUI extends JFrame {

    static Customer customer = new Customer();
    private JPanel contentPane;
    private JTextField txt_kullaniciAdi;
    private JTextField txt_adSoyad;
    private JTextField txt_password;
    private JTextField txt_passwordAgain;
    private JTextField txt_tc;
    private JTextField txt_telNo;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RegisterCustomerGUI frame = new RegisterCustomerGUI();
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
    public RegisterCustomerGUI() {
        setResizable(false);
        setTitle("Kullanıcı Kayıt Ekranı");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 499, 502);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblKullancKaytEkran = new JLabel("Kullanıcı Kayıt Ekranı");
        lblKullancKaytEkran.setForeground(new Color(255, 255, 255));
        lblKullancKaytEkran.setHorizontalAlignment(SwingConstants.CENTER);
        lblKullancKaytEkran.setFont(new Font("Segoe UI Semilight", Font.BOLD | Font.ITALIC, 20));
        lblKullancKaytEkran.setBounds(54, 34, 360, 50);
        contentPane.add(lblKullancKaytEkran);

        JLabel lblKullanciAd = new JLabel("Kullanıcı Adı:");
        lblKullanciAd.setForeground(new Color(255, 255, 255));
        lblKullanciAd.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lblKullanciAd.setBounds(54, 143, 95, 50);
        contentPane.add(lblKullanciAd);

        txt_kullaniciAdi = new JTextField();
        txt_kullaniciAdi.setColumns(10);
        txt_kullaniciAdi.setBounds(159, 152, 255, 37);
        contentPane.add(txt_kullaniciAdi);

        JLabel lblSifre = new JLabel("Şifre:");
        lblSifre.setForeground(new Color(255, 255, 255));
        lblSifre.setHorizontalAlignment(SwingConstants.LEFT);
        lblSifre.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lblSifre.setBounds(54, 202, 95, 27);
        contentPane.add(lblSifre);

        JButton btnKaydol = new JButton("Kaydol");
        btnKaydol.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int passwordControl = 1;

                if(txt_adSoyad.getText().length() == 0 || txt_kullaniciAdi.getText().length() == 0 || txt_password.getText().length() == 0 || txt_passwordAgain.getText().length() == 0 || txt_tc.getText().length()==0 || txt_telNo.getText().length()==0) {
                    Helper.showMsg("fill"); // tum alanlari doldurunuz
                }

                else if(!txt_password.getText().equals(txt_passwordAgain.getText())) {
                    Helper.showMsg("Şifre Tekrarını Doğru Giriniz!");
                    txt_password.setText(null);
                    txt_passwordAgain.setText(null);
                    passwordControl = 0;
                }

                else {
                    try {
                        boolean control;
                        if(passwordControl == 1) {
                            control = customer.addCustomer(txt_kullaniciAdi.getText(), txt_adSoyad.getText() ,txt_password.getText(),txt_tc.getText(), txt_telNo.getText());
                            if(control == true) {
                                Helper.showMsg("success");
                                txt_kullaniciAdi.setText(null); // dolu olan kutuları bosalttik
                                txt_adSoyad.setText(null);
                                txt_password.setText(null);
                                txt_passwordAgain.setText(null);
                                txt_tc.setText(null);
                                txt_telNo.setText(null);
                                // simdi eklendi ama tablo guncellenmedi. onu da ayarlayalim
                                dispose();
                                LoginGUI loginGUI =new LoginGUI();
                                loginGUI.setVisible(true);
                            }
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });







        btnKaydol.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        btnKaydol.setBounds(158, 395, 256, 37);
        contentPane.add(btnKaydol);

        JLabel lblifreTekrar = new JLabel("Şifre Tekrarı:");
        lblifreTekrar.setForeground(new Color(255, 255, 255));
        lblifreTekrar.setHorizontalAlignment(SwingConstants.LEFT);
        lblifreTekrar.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lblifreTekrar.setBounds(54, 256, 95, 27);
        contentPane.add(lblifreTekrar);

        txt_adSoyad = new JTextField();
        txt_adSoyad.setColumns(10);
        txt_adSoyad.setBounds(159, 105, 255, 37);
        contentPane.add(txt_adSoyad);

        JLabel lblAdSoyad = new JLabel("Ad Soyad:");
        lblAdSoyad.setForeground(new Color(255, 255, 255));
        lblAdSoyad.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lblAdSoyad.setBounds(54, 100, 95, 50);
        contentPane.add(lblAdSoyad);

        JButton btnCancel = new JButton("İptal Et");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginGUI loginGUI = new LoginGUI();
                dispose(); // bu sayfayi kapatir
                loginGUI.setVisible(true);
            }
        });
        btnCancel.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        btnCancel.setBounds(54, 395, 95, 37);
        contentPane.add(btnCancel);

        txt_password = new JTextField();
        txt_password.setColumns(10);
        txt_password.setBounds(159, 199, 255, 37);
        contentPane.add(txt_password);

        txt_passwordAgain = new JTextField();
        txt_passwordAgain.setColumns(10);
        txt_passwordAgain.setBounds(159, 246, 255, 37);
        contentPane.add(txt_passwordAgain);

        txt_tc = new JTextField();
        txt_tc.setColumns(10);
        txt_tc.setBounds(159, 293, 255, 37);
        contentPane.add(txt_tc);

        JLabel lbl_tc = new JLabel("TC:");
        lbl_tc.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_tc.setForeground(Color.WHITE);
        lbl_tc.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lbl_tc.setBounds(54, 296, 95, 27);
        contentPane.add(lbl_tc);

        txt_telNo = new JTextField();
        txt_telNo.setColumns(10);
        txt_telNo.setBounds(159, 340, 255, 37);
        contentPane.add(txt_telNo);

        JLabel lbl_telNo = new JLabel("Telefon No:");
        lbl_telNo.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_telNo.setForeground(Color.WHITE);
        lbl_telNo.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lbl_telNo.setBounds(54, 343, 95, 27);
        contentPane.add(lbl_telNo);
    }
}
