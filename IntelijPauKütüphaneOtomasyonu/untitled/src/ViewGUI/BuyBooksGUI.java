package ViewGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.BooksSQL;
import Model.Customer;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
public class BuyBooksGUI extends JFrame {

    static private Customer customer;
    private JPanel contentPane;
    private JTextField txtF_buyBook;
    private JTextField txtF_DateFirst;
    private JTextField txtF_DateSecond;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BuyBooksGUI frame = new BuyBooksGUI(customer);
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
    public BuyBooksGUI(Customer customer) {
        setTitle("Kitap Al");
        setResizable(false);
        setBounds(100, 100, 502, 200);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lbl_information = new JLabel("PAU Kitap Alım Sayfası");
        lbl_information.setForeground(SystemColor.text);
        lbl_information.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_information.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 16));
        lbl_information.setBounds(10, 10, 416, 37);
        contentPane.add(lbl_information);

        JLabel lbl_ID = new JLabel("Kitap ID'si:");
        lbl_ID.setForeground(SystemColor.text);
        lbl_ID.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        lbl_ID.setBounds(10, 51, 74, 37);
        contentPane.add(lbl_ID);

        txtF_buyBook = new JTextField();
        txtF_buyBook.setBounds(90, 57, 74, 30);
        contentPane.add(txtF_buyBook);
        txtF_buyBook.setColumns(10);

        JLabel lbl_dateFirst = new JLabel("Kitabın Alım Tarihi:");
        lbl_dateFirst.setForeground(SystemColor.text);
        lbl_dateFirst.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        lbl_dateFirst.setBounds(201, 51, 139, 37);
        contentPane.add(lbl_dateFirst);

        txtF_DateFirst = new JTextField();
        txtF_DateFirst.setEditable(false);
        txtF_DateFirst.setColumns(10);
        txtF_DateFirst.setBounds(350, 56, 128, 30);
        contentPane.add(txtF_DateFirst);


        JButton btn_BuyBook = new JButton("Kitabı Al");
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
                        booksSQL.userBuyBook(selBookID, customer.getId(), dateFirst(), dateSecond());
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        btn_BuyBook.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
        btn_BuyBook.setBounds(10, 98, 154, 37);
        contentPane.add(btn_BuyBook);

        JLabel lbl_dateSecond = new JLabel("Kitabın Son Tarihi:");
        lbl_dateSecond.setForeground(SystemColor.text);
        lbl_dateSecond.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        lbl_dateSecond.setBounds(201, 96, 139, 37);
        contentPane.add(lbl_dateSecond);

        txtF_DateSecond = new JTextField();
        txtF_DateSecond.setEditable(false);
        txtF_DateSecond.setColumns(10);
        txtF_DateSecond.setBounds(350, 101, 128, 30);
        contentPane.add(txtF_DateSecond);

        JLabel lbl_dateSecond_1 = new JLabel("");
        lbl_dateSecond_1.setForeground(SystemColor.text);
        lbl_dateSecond_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        lbl_dateSecond_1.setBounds(339, 143, 139, 37);
        contentPane.add(lbl_dateSecond_1);
    }


    private String dateFirst() {

		/*	FARKLI BİR YONTEM
		 * String aylar[]={"Ocak","Şubat","Mart","Nisan","Mayıs","Haziran","Temmuz","Ağustos","Eylül","Ekim","Kasım","Aralık"};
		Calendar dateFirst=Calendar.getInstance();
		System.out.println(aylar[dateFirst.get(Calendar.MONTH)]); // Ağustos
		System.out.println(dateFirst.get(Calendar.DATE));         // 24
		System.out.println(dateFirst.get(Calendar.YEAR));         // 2014
		System.out.println(dateFirst.get(Calendar.HOUR));         // 2
		System.out.println(dateFirst.get(Calendar.MINUTE));       // 12
		System.out.println(dateFirst.get(Calendar.SECOND));       // 51
		*/

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
