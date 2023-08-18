package ViewGUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Helper.Helper;
import Model.Admin;
import Panel.PersonelDataPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterAdminGUI extends JFrame {

    static Admin admin = new Admin();
    private JPanel contentPane;
    private JTextField txt_adSoyad;
    private JTextField txt_kullaniciAdi;
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
                    RegisterAdminGUI frame = new RegisterAdminGUI();
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
    public RegisterAdminGUI() {
        setTitle("Personel Kayıt Ekranı");
        setResizable(false);
        setBounds(100, 100, 499, 502);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblKullancKaytEkran = new JLabel("Personel Kayıt Ekranı");
        lblKullancKaytEkran.setHorizontalAlignment(SwingConstants.CENTER);
        lblKullancKaytEkran.setForeground(Color.WHITE);
        lblKullancKaytEkran.setFont(new Font("Segoe UI Semilight", Font.BOLD | Font.ITALIC, 20));
        lblKullancKaytEkran.setBounds(73, 23, 360, 50);
        contentPane.add(lblKullancKaytEkran);

        JLabel lblAdSoyad = new JLabel("Ad Soyad:");
        lblAdSoyad.setForeground(Color.WHITE);
        lblAdSoyad.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lblAdSoyad.setBounds(73, 89, 95, 50);
        contentPane.add(lblAdSoyad);

        txt_adSoyad = new JTextField();
        txt_adSoyad.setColumns(10);
        txt_adSoyad.setBounds(178, 94, 255, 37);
        contentPane.add(txt_adSoyad);

        JLabel lblKullanciAd = new JLabel("Kullanıcı Adı:");
        lblKullanciAd.setForeground(Color.WHITE);
        lblKullanciAd.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lblKullanciAd.setBounds(73, 132, 95, 50);
        contentPane.add(lblKullanciAd);

        txt_kullaniciAdi = new JTextField();
        txt_kullaniciAdi.setColumns(10);
        txt_kullaniciAdi.setBounds(178, 141, 255, 37);
        contentPane.add(txt_kullaniciAdi);

        JLabel lblSifre = new JLabel("Şifre:");
        lblSifre.setHorizontalAlignment(SwingConstants.LEFT);
        lblSifre.setForeground(Color.WHITE);
        lblSifre.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lblSifre.setBounds(73, 191, 95, 27);
        contentPane.add(lblSifre);

        txt_password = new JTextField();
        txt_password.setColumns(10);
        txt_password.setBounds(178, 188, 255, 37);
        contentPane.add(txt_password);

        JLabel lblifreTekrar = new JLabel("Şifre Tekrarı:");
        lblifreTekrar.setHorizontalAlignment(SwingConstants.LEFT);
        lblifreTekrar.setForeground(Color.WHITE);
        lblifreTekrar.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lblifreTekrar.setBounds(73, 245, 95, 27);
        contentPane.add(lblifreTekrar);

        txt_passwordAgain = new JTextField();
        txt_passwordAgain.setColumns(10);
        txt_passwordAgain.setBounds(178, 235, 255, 37);
        contentPane.add(txt_passwordAgain);

        JLabel lbl_tc = new JLabel("TC:");
        lbl_tc.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_tc.setForeground(Color.WHITE);
        lbl_tc.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lbl_tc.setBounds(73, 285, 95, 27);
        contentPane.add(lbl_tc);

        txt_tc = new JTextField();
        txt_tc.setColumns(10);
        txt_tc.setBounds(178, 282, 255, 37);
        contentPane.add(txt_tc);

        JLabel lbl_telNo = new JLabel("Telefon No:");
        lbl_telNo.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_telNo.setForeground(Color.WHITE);
        lbl_telNo.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lbl_telNo.setBounds(73, 332, 95, 27);
        contentPane.add(lbl_telNo);

        txt_telNo = new JTextField();
        txt_telNo.setColumns(10);
        txt_telNo.setBounds(178, 329, 255, 37);
        contentPane.add(txt_telNo);

        JButton btnCancel = new JButton("İptal Et");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // bu sayfayi kapatir
            }
        });
        btnCancel.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        btnCancel.setBounds(73, 384, 95, 37);
        contentPane.add(btnCancel);

        JButton btnKaydol = new JButton("Kaydet");
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
                            control = admin.addAdmin(txt_kullaniciAdi.getText(), txt_adSoyad.getText() ,txt_password.getText(),txt_tc.getText(), txt_telNo.getText());
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
                            }
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        });
        btnKaydol.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        btnKaydol.setBounds(177, 384, 256, 37);
        contentPane.add(btnKaydol);
    }
}
