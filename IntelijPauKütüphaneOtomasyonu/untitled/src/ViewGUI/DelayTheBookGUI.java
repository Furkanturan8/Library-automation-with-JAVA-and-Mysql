package ViewGUI;

import java.awt.EventQueue;
import Model.BooksAndUser;
import Model.Customer;
import Model.BooksSQL;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class DelayTheBookGUI extends JFrame {
    static private Customer customer;
    static private BooksSQL booksSQL = new BooksSQL();
    private JPanel contentPane;
    private JTextField txtF_ID;
    private JTextField txtF_firstTime;
    private JTextField txtF_secondTime;
    private JTextField txtF_ertelenmisTarih;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DelayTheBookGUI frame = new DelayTheBookGUI(customer);
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
    public DelayTheBookGUI(Customer customer) {
        setTitle("Teslim Tarihini Ertele");
        setResizable(false);
        setBounds(100, 100, 313, 448);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lbl_information = new JLabel("PAU Kitap Erteleme Sayfası");
        lbl_information.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_information.setForeground(SystemColor.text);
        lbl_information.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 16));
        lbl_information.setBounds(10, 10, 278, 37);
        contentPane.add(lbl_information);

        txtF_ID = new JTextField();
        txtF_ID.setColumns(10);
        txtF_ID.setBounds(159, 61, 129, 30);
        contentPane.add(txtF_ID);

        JLabel lbl_ID = new JLabel("Kitap ID'si:");
        lbl_ID.setForeground(SystemColor.text);
        lbl_ID.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        lbl_ID.setBounds(10, 50, 74, 37);
        contentPane.add(lbl_ID);


        JLabel lbl_dateFirst = new JLabel("Kitabın Alım Tarihi:");
        lbl_dateFirst.setForeground(SystemColor.text);
        lbl_dateFirst.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        lbl_dateFirst.setBounds(10, 99, 139, 37);
        contentPane.add(lbl_dateFirst);

        JLabel lbl_dateSecond = new JLabel("Kitabın Son Tarihi:");
        lbl_dateSecond.setForeground(SystemColor.text);
        lbl_dateSecond.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        lbl_dateSecond.setBounds(10, 141, 139, 37);
        contentPane.add(lbl_dateSecond);

        txtF_firstTime = new JTextField();
        txtF_firstTime.setEditable(false);
        txtF_firstTime.setColumns(10);
        txtF_firstTime.setBounds(159, 104, 128, 30);
        contentPane.add(txtF_firstTime);

        txtF_secondTime = new JTextField();
        txtF_secondTime.setEditable(false);
        txtF_secondTime.setColumns(10);
        txtF_secondTime.setBounds(159, 146, 128, 30);
        contentPane.add(txtF_secondTime);

        JButton btn_goster = new JButton("Göster");
        btn_goster.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selBookID = Integer.parseInt(txtF_ID.getText());
                BooksAndUser obj = booksSQL.delayTheBook(customer.getId(),selBookID);
                txtF_firstTime.setText(obj.getFirstDate());
                txtF_secondTime.setText(obj.getDeadline());
            }
        });
        btn_goster.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
        btn_goster.setBounds(10, 188, 278, 29);
        contentPane.add(btn_goster);

        JLabel lbl_dateSecond_1 = new JLabel("Ertelenmiş Kitabın Son Tarihi:");
        lbl_dateSecond_1.setForeground(SystemColor.text);
        lbl_dateSecond_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        lbl_dateSecond_1.setBounds(10, 282, 183, 37);
        contentPane.add(lbl_dateSecond_1);

        txtF_ertelenmisTarih = new JTextField();
        txtF_ertelenmisTarih.setEditable(false);
        txtF_ertelenmisTarih.setColumns(10);
        txtF_ertelenmisTarih.setBounds(10, 329, 278, 30);
        contentPane.add(txtF_ertelenmisTarih);

        JLabel lbl_dateSecond_1_1 = new JLabel("NOT : Erteleme İşlemi Yalnızca 1 Aydır ");
        lbl_dateSecond_1_1.setForeground(SystemColor.text);
        lbl_dateSecond_1_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        lbl_dateSecond_1_1.setBounds(10, 240, 278, 37);
        contentPane.add(lbl_dateSecond_1_1);

        JButton btn_ertele = new JButton("Ertele");
        btn_ertele.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String secondTime=txtF_secondTime.getText();
                String [] tarihleriAyir = secondTime.split("\\.");
                String [] tarihleriAyir2 = secondTime.split(" ");
                String saatVeDk=tarihleriAyir2[1];

                String [] tarihleriAyir3 = saatVeDk.split(":");
                String saat = tarihleriAyir3[0];
                String dk=tarihleriAyir3[1];
                //System.out.println(dk);
                //System.out.println(saat);

                String [] year = tarihleriAyir[2].split(" ");
                //System.out.println(year[0]);

                int month = Integer.parseInt(tarihleriAyir[1]);
                if(month==12) {
                    month=1;
                }else {
                    month++;
                }

                txtF_ertelenmisTarih.setText(tarihleriAyir[0] + "." +month+"."+year[0]+" "+saat+":"+dk);

                boolean durum = booksSQL.updateDeadline(Integer.parseInt(txtF_ID.getText()),customer.getId(),txtF_ertelenmisTarih.getText());
                if(durum == true)
                    Helper.Helper.showMsg("Almış Olduğunuz Kitabın Teslim Süresi 1 Ay Ertelenmiştir!");
                else
                    Helper.Helper.showMsg("Erteleme Yapılamadı! Girdiğiniz Verilerinizin Doğru Olduğundan Emin Olunuz!");
            }
        });
        btn_ertele.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
        btn_ertele.setBounds(10, 369, 278, 29);
        contentPane.add(btn_ertele);

    }


}
