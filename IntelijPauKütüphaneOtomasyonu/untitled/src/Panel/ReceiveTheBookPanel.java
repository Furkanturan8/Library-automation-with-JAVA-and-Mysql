package Panel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

import Helper.Helper;
import Model.BooksSQL;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReceiveTheBookPanel extends JPanel {
    private JTextField txtF_userID;
    private JTextField txtF_bookID;
    static private BooksSQL booksSQL = new BooksSQL();

    /**
     * Create the panel.
     */
    public ReceiveTheBookPanel() {
        setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Öğrenci İşlem Yapma Ekranı ");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1.setBounds(10, 17, 210, 30);
        add(lblNewLabel_1);

        JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);
        panel.setBounds(10, 51, 468, 4);
        add(panel);

        JLabel lbl_ID_1 = new JLabel("Öğrenci ID'si:");
        lbl_ID_1.setForeground(Color.BLUE);
        lbl_ID_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        lbl_ID_1.setBounds(324, 10, 90, 37);
        add(lbl_ID_1);

        txtF_userID = new JTextField();
        txtF_userID.setColumns(10);
        txtF_userID.setBounds(414, 16, 64, 30);
        add(txtF_userID);

        JLabel lblNewLabel_1_1 = new JLabel("Kitap Ver");
        lblNewLabel_1_1.setForeground(Color.RED);
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1_1.setBounds(10, 57, 210, 20);
        add(lblNewLabel_1_1);

        JLabel lbl_ID = new JLabel("Kitap ID'si:");
        lbl_ID.setForeground(Color.BLACK);
        lbl_ID.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        lbl_ID.setBounds(10, 76, 74, 37);
        add(lbl_ID);

        txtF_bookID = new JTextField();
        txtF_bookID.setColumns(10);
        txtF_bookID.setBounds(90, 82, 74, 30);
        add(txtF_bookID);

        JButton btn_BuyBook = new JButton("Kitabı Öğrenciden Al");
        btn_BuyBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(txtF_bookID.getText().length()==0 || txtF_userID.getText().length()==0){
                    Helper.showMsg("fill");
                }else {
                    boolean durum = booksSQL.receiveTheBook(Integer.parseInt(txtF_userID.getText()), Integer.parseInt(txtF_bookID.getText()));
                    if(durum)
                        Helper.showMsg("Kitap Teslim Alındı!");
                    else
                        Helper.showMsg("Kitap Teslim Alınamadı! Girdiğiniz Verileri Kontrol Ediniz!");
                }


            }
        });
        btn_BuyBook.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
        btn_BuyBook.setBounds(10, 123, 468, 37);
        add(btn_BuyBook);

    }
}
