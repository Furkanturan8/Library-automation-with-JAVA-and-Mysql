package ViewGUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Helper.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import Model.*;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class LoginGUI extends JFrame {
    private DBConnection conn = new DBConnection();
    private JPanel contentPane;
    private JTextField txt_kullaniciAd;
    private JPasswordField txt_pasword;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginGUI frame = new LoginGUI();
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
    public LoginGUI() {
        setResizable(false);
        setTitle("Pamukkale Üniversitesi Kütüphane Sistemi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 549, 458);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblLogin = new JLabel("Kütüphane Giriş Ekranı");
        lblLogin.setForeground(new Color(255, 255, 255));
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogin.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 20));
        lblLogin.setBounds(180, 78, 255, 50);
        contentPane.add(lblLogin);

        JLabel lblKullanciAd = new JLabel("Kullanıcı Adı:");
        lblKullanciAd.setForeground(new Color(255, 255, 255));
        lblKullanciAd.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lblKullanciAd.setBounds(75, 135, 95, 50);
        contentPane.add(lblKullanciAd);

        JLabel lblSifre = new JLabel("Şifre:");
        lblSifre.setForeground(new Color(255, 255, 255));
        lblSifre.setHorizontalAlignment(SwingConstants.LEFT);
        lblSifre.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lblSifre.setBounds(75, 204, 95, 27);
        contentPane.add(lblSifre);

        txt_kullaniciAd = new JTextField();
        txt_kullaniciAd.setBounds(180, 144, 255, 37);
        contentPane.add(txt_kullaniciAd);
        txt_kullaniciAd.setColumns(10);

        txt_pasword = new JPasswordField();
        txt_pasword.setBounds(180, 201, 255, 37);
        contentPane.add(txt_pasword);

        JButton btnGirisYap = new JButton("Giriş Yap");
        btnGirisYap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(txt_kullaniciAd.getText().length() == 0 || txt_pasword.getPassword().length== 0) {
                    Helper.showMsg("fill");
                }
                else {
                    try {
                        Connection con = conn.connDb();
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery("SELECT * FROM user");
                        int control = 0 ; // giris yapti mi yapmadi mu kontrol
                        while(rs.next()) { // tum aradigim userlari geziyorum
                            if(txt_kullaniciAd.getText().equals(rs.getString("kullaniciAdi")) && txt_pasword.getText().equals(rs.getString("password"))) {
                                control = 1; // giris yapti
                                String adminMi="admin";
                                if(adminMi.equals(rs.getString("type"))) {
                                    Admin admin = new Admin();
                                    admin.setId(rs.getInt("id"));
                                    admin.setPassword(rs.getString("password"));
                                    admin.setKullaniciAdi(rs.getString("kullaniciAdi"));
                                    admin.setName(rs.getString("name"));
                                    admin.setType(rs.getString("type"));
                                    admin.setTc(rs.getString("tc"));
                                    admin.setTelefonNo(rs.getString("telefonNo"));
                                    JOptionPane.showMessageDialog(null,"Sayın "+ admin.getName()+" Başarılı Şekilde Giriş Yaptınız!","Mesaj",JOptionPane.INFORMATION_MESSAGE);
                                    dispose();
                                    AdminGUI adminGUI = new AdminGUI(admin);
                                    adminGUI.setVisible(true);
                                }
                                else {
                                    Customer customer = new Customer();
                                    customer.setId(rs.getInt("id"));
                                    customer.setPassword("password");
                                    customer.setKullaniciAdi(rs.getString("kullaniciAdi"));
                                    customer.setName(rs.getString("name"));
                                    customer.setType(rs.getString("type"));
                                    customer.setTelefonNo(rs.getString("telefonNo"));
                                    customer.setTc(rs.getString("tc"));

                                    CustomerGUI customerGUI = new CustomerGUI(customer);
                                    JOptionPane.showMessageDialog(null,"Sayın "+ customer.getName()+" Başarılı Şekilde Giriş Yaptınız!","Mesaj",JOptionPane.INFORMATION_MESSAGE);
                                    dispose();
                                    customerGUI.setVisible(true);

                                }
                            }
                        }
                        if(control == 0) {
                            Helper.showMsg("Hatalı Deneme! Lütfen Tekrar Deneyiniz.");
                            txt_kullaniciAd.setText("");
                            txt_pasword.setText("");
                        }
                    }

                    catch(SQLException e1) {
                        e1.printStackTrace();
                    }
                }



            }
        });
        btnGirisYap.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        btnGirisYap.setBounds(285, 260, 150, 37);
        contentPane.add(btnGirisYap);

        JButton btnKaydol = new JButton("Kaydol");
        btnKaydol.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegisterCustomerGUI kaydolGUI = new RegisterCustomerGUI();
                dispose();
                kaydolGUI.setVisible(true);

            }
        });
        btnKaydol.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        btnKaydol.setBounds(180, 260, 95, 37);
        contentPane.add(btnKaydol);



    }
}
