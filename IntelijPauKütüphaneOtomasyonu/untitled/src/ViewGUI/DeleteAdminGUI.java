package ViewGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Admin;
import Model.User;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class DeleteAdminGUI extends JFrame {

    private JPanel contentPane;
    private JTextField txt_kullaniciAdi;
    static private Admin admin;
    private JTextField txt_id;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DeleteAdminGUI frame = new DeleteAdminGUI(admin);
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
    public DeleteAdminGUI(Admin admin) {
        setTitle("Personel Silme Ekranı");
        setResizable(false);
        setBounds(100, 100, 450, 269);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblKullanciAd = new JLabel("Kullanıcı Adı:");
        lblKullanciAd.setForeground(Color.WHITE);
        lblKullanciAd.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lblKullanciAd.setBounds(42, 53, 95, 50);
        contentPane.add(lblKullanciAd);

        JLabel lblKullancSilmeEkran = new JLabel("Personel Silme Ekranı");
        lblKullancSilmeEkran.setHorizontalAlignment(SwingConstants.CENTER);
        lblKullancSilmeEkran.setForeground(Color.WHITE);
        lblKullancSilmeEkran.setFont(new Font("Segoe UI Semilight", Font.BOLD | Font.ITALIC, 20));
        lblKullancSilmeEkran.setBounds(42, 10, 360, 50);
        contentPane.add(lblKullancSilmeEkran);

        txt_kullaniciAdi = new JTextField();
        txt_kullaniciAdi.setColumns(10);
        txt_kullaniciAdi.setBounds(147, 62, 255, 37);
        contentPane.add(txt_kullaniciAdi);

        JButton btnCancel = new JButton("İptal Et");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancel.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        btnCancel.setBounds(283, 109, 119, 37);
        contentPane.add(btnCancel);

        JButton btnDelete = new JButton("Sil");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(txt_kullaniciAdi.getText().length() == 0 || txt_id.getText().length()==0) {
                    Helper.showMsg("fill"); // tum alanlari doldurunuz
                }
                else {
                    int selID = Integer.parseInt(txt_id.getText());

                    if(Helper.confirm("sure")) {
                        try {
                            User adminSelected= admin.getUser(selID);
                            if(adminSelected.getId()==selID && adminSelected.getKullaniciAdi().equals(txt_kullaniciAdi.getText()) && admin.deleteAdmin(selID,txt_kullaniciAdi.getText())) {
                                Helper.showMsg("success");
                            }
                            else {
                                Helper.showMsg("ID ve Kullanıcı Adı Eşleşmesi Hatalı! Tekrar Deneyin!");
                            }

                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                }


            }
        });
        btnDelete.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        btnDelete.setBounds(147, 159, 255, 37);
        contentPane.add(btnDelete);

        txt_id = new JTextField();
        txt_id.setColumns(10);
        txt_id.setBounds(147, 109, 126, 37);
        contentPane.add(txt_id);

        JLabel lblId = new JLabel("ID:");
        lblId.setForeground(Color.WHITE);
        lblId.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lblId.setBounds(42, 100, 95, 50);
        contentPane.add(lblId);
    }
}
