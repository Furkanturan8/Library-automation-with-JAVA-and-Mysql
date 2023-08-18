package Panel;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JTextField;

import Model.BooksAndUser;
import Model.BooksSQL;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class TakeActionPanel extends JPanel {
    private JTextField txtF_buyBook;
    private JTextField txtF_DateSecond;
    private JTextField txtF_DateFirst;
    private JTextField txt_ogrID;
    private JTextField txtF_bookID;
    private JTextField txtF_firstTime;
    private JTextField txtF_secondTime;
    private JTextField txtF_ertelenmisTarih;
    static private BooksSQL booksSQL = new BooksSQL();

    /**
     * Create the panel.
     */
    public TakeActionPanel() {
        setBounds(0,0,516,410);
        setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Öğrenci İşlem Yapma Ekranı ");
        lblNewLabel_1.setBounds(10, 10, 210, 30);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(lblNewLabel_1);

        JLabel lbl_ID = new JLabel("Kitap ID'si:");
        lbl_ID.setForeground(Color.BLACK);
        lbl_ID.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        lbl_ID.setBounds(10, 69, 74, 37);
        add(lbl_ID);

        txtF_buyBook = new JTextField();
        txtF_buyBook.setColumns(10);
        txtF_buyBook.setBounds(90, 75, 74, 30);
        add(txtF_buyBook);

        JLabel lbl_dateFirst = new JLabel("Kitabın Alım Tarihi:");
        lbl_dateFirst.setForeground(Color.BLACK);
        lbl_dateFirst.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        lbl_dateFirst.setBounds(201, 69, 139, 37);
        add(lbl_dateFirst);

        JButton btn_BuyBook = new JButton("Kitabı Öğrenciye Ver");
        btn_BuyBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int selID = Integer.parseInt(txtF_buyBook.getText());
                BooksSQL buyBook = new BooksSQL();
                boolean durum = buyBook.bookStatus("0",selID); // 0 alındı demek yani pasif durumda
                if(durum == false) {
                    Helper.Helper.showMsg("Bu kitap şu an başkasında! Alım Gerçekleşmedi!");
                }
                else{
                    Helper.Helper.showMsg("Alım Gerçekleşti!");
                    txtF_DateFirst.setText(dateFirst());
                    txtF_DateSecond.setText(dateSecond());
                    BooksSQL booksSQL = new BooksSQL();
                    int selBookID = Integer.parseInt(txtF_buyBook.getText());
                    try {
                        booksSQL.userBuyBook(selBookID, Integer.parseInt(txt_ogrID.getText()) , dateFirst(), dateSecond());
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }


        });
        btn_BuyBook.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
        btn_BuyBook.setBounds(10, 169, 468, 37);
        add(btn_BuyBook);

        JLabel lbl_dateSecond = new JLabel("Kitabın Son Tarihi:");
        lbl_dateSecond.setForeground(Color.BLACK);
        lbl_dateSecond.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        lbl_dateSecond.setBounds(201, 114, 139, 37);
        add(lbl_dateSecond);

        txtF_DateSecond = new JTextField();
        txtF_DateSecond.setEditable(false);
        txtF_DateSecond.setColumns(10);
        txtF_DateSecond.setBounds(350, 119, 128, 30);
        add(txtF_DateSecond);

        txtF_DateFirst = new JTextField();
        txtF_DateFirst.setEditable(false);
        txtF_DateFirst.setColumns(10);
        txtF_DateFirst.setBounds(350, 74, 128, 30);
        add(txtF_DateFirst);

        JLabel lbl_ID_1 = new JLabel("Öğrenci ID'si:");
        lbl_ID_1.setForeground(Color.BLUE);
        lbl_ID_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        lbl_ID_1.setBounds(324, 3, 90, 37);
        add(lbl_ID_1);

        txt_ogrID = new JTextField();
        txt_ogrID.setColumns(10);
        txt_ogrID.setBounds(414, 9, 64, 30);
        add(txt_ogrID);

        JLabel lblNewLabel_1_1 = new JLabel("Kitap Ver");
        lblNewLabel_1_1.setForeground(Color.RED);
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1_1.setBounds(10, 50, 210, 20);
        add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_1_1 = new JLabel("Kitap Süresini Uzat");
        lblNewLabel_1_1_1.setForeground(Color.RED);
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1_1_1.setBounds(10, 219, 139, 30);
        add(lblNewLabel_1_1_1);

        JLabel lbl_ID_2 = new JLabel("Kitap ID'si:");
        lbl_ID_2.setForeground(Color.BLACK);
        lbl_ID_2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        lbl_ID_2.setBounds(10, 259, 74, 37);
        add(lbl_ID_2);

        txtF_bookID = new JTextField();
        txtF_bookID.setColumns(10);
        txtF_bookID.setBounds(128, 265, 38, 30);
        add(txtF_bookID);

        JLabel lbl_dateFirst_1 = new JLabel("Kitabın Alım Tarihi:");
        lbl_dateFirst_1.setForeground(Color.BLACK);
        lbl_dateFirst_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        lbl_dateFirst_1.setBounds(10, 306, 139, 37);
        add(lbl_dateFirst_1);

        txtF_firstTime = new JTextField();
        txtF_firstTime.setEditable(false);
        txtF_firstTime.setColumns(10);
        txtF_firstTime.setBounds(128, 312, 128, 30);
        add(txtF_firstTime);

        JLabel lbl_dateSecond_1 = new JLabel("Kitabın Son Tarihi:");
        lbl_dateSecond_1.setForeground(Color.BLACK);
        lbl_dateSecond_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        lbl_dateSecond_1.setBounds(10, 353, 139, 37);
        add(lbl_dateSecond_1);

        txtF_secondTime = new JTextField();
        txtF_secondTime.setEditable(false);
        txtF_secondTime.setColumns(10);
        txtF_secondTime.setBounds(128, 359, 128, 30);
        add(txtF_secondTime);

        JButton btn_goster = new JButton("Göster");
        btn_goster.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int selBookID = Integer.parseInt(txtF_bookID.getText());
                BooksAndUser obj = booksSQL.delayTheBook(Integer.parseInt(txt_ogrID.getText()),selBookID);
                txtF_firstTime.setText(obj.getFirstDate());
                txtF_secondTime.setText(obj.getDeadline());

            }
        });
        btn_goster.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
        btn_goster.setBounds(176, 265, 80, 30);
        add(btn_goster);

        JLabel lbl_dateSecond_1_1 = new JLabel("NOT : Erteleme İşlemi Yalnızca 1 Aydır ");
        lbl_dateSecond_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl_dateSecond_1_1.setForeground(Color.BLACK);
        lbl_dateSecond_1_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        lbl_dateSecond_1_1.setBounds(153, 216, 325, 37);
        add(lbl_dateSecond_1_1);

        JLabel lbl_dateSecond_1_2 = new JLabel("Ertelenmiş Kitabın Son Tarihi:");
        lbl_dateSecond_1_2.setForeground(Color.BLACK);
        lbl_dateSecond_1_2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        lbl_dateSecond_1_2.setBounds(278, 265, 183, 31);
        add(lbl_dateSecond_1_2);

        txtF_ertelenmisTarih = new JTextField();
        txtF_ertelenmisTarih.setEditable(false);
        txtF_ertelenmisTarih.setColumns(10);
        txtF_ertelenmisTarih.setBounds(278, 312, 183, 30);
        add(txtF_ertelenmisTarih);

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

                boolean durum = booksSQL.updateDeadline(Integer.parseInt(txtF_bookID.getText()),Integer.parseInt(txt_ogrID.getText()),txtF_ertelenmisTarih.getText());
                if(durum == true)
                    Helper.Helper.showMsg("Almış Olduğunuz Kitabın Teslim Süresi 1 Ay Ertelenmiştir!");
                else
                    Helper.Helper.showMsg("Erteleme Yapılamadı! Girdiğiniz Verilerinizin Doğru Olduğundan Emin Olunuz!");
            }
        });
        btn_ertele.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
        btn_ertele.setBounds(278, 357, 183, 29);
        add(btn_ertele);

        JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);
        panel.setBounds(10, 44, 468, 4);
        add(panel);
        setVisible(true);
    }

    private String dateFirst() {

        SimpleDateFormat sekil = new SimpleDateFormat();
        Date tarih = new Date();

        return sekil.format(tarih) ;

    }
    private String dateSecond() {
        SimpleDateFormat sekil = new SimpleDateFormat();
        Date tarih = new Date();
        int hour = tarih.getHours();
        int minute = tarih.getMinutes();
        int day = tarih.getDate();
        int month = tarih.getMonth()+1; // 0 ile 11 arası aylar old. dolayı +1 ekledik
        int year = tarih.getYear()+1900; // getYear fonx yerel yıldan 1900 çıkmış halini verdiginden tekrar 1900 ile topladik

        String tarih2;
        if(month >=10) {
            year ++;
            switch(month) {
                case 10: month=01;
                case 11: month=02;
                case 12: month=03;
            }
        }
        else if(month == 11 && day >= 29) { // şubat 28 çektiginden dolayı 3 ay ilerleyelim
            month =3;
            day = 2;
        }
        else {
            switch(month){
                case 1: month=4; break;
                case 2: month=5; break;
                case 3: month=6; break;
                case 4: month=7; break;
                case 5: month=8; break;
                case 6: month=9; break;
                case 7: month=10; break;
                case 8: month=11; break;
                case 9: month=12; break;

            }
        }

        return tarih2 = day+"."+month+"."+year+" "+hour+":"+minute;

    }
}
