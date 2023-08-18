package Panel;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.sql.SQLException;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Model.*;

public class PersonelDataPanel extends JPanel {
    private JTable table;
    static  Admin admin = new Admin();
    private DefaultTableModel adminModel = null;
    private Object[] adminData = null;
    /**
     * Create the panel.
     */
    public PersonelDataPanel() {
        setBounds(0,0,617,476);
        setLayout(null);

        adminModel = new DefaultTableModel();
        Object[] colAdminName = new Object[6];
        colAdminName[0] = "ID";
        colAdminName[1] = "Kullanıcı Adı";
        colAdminName[2] = "İsim Soyisim";
        colAdminName[3] = "Şifresi";
        colAdminName[4] = "TC";
        colAdminName[5] = "Telefon No";

        adminModel.setColumnIdentifiers(colAdminName);; //basliklari atadik
        adminData = new Object[6]; // basliklarin altinda gosterilmesi gereken veriler

        try {
            int sayac=0;
            if(sayac==1) { // veri eklenirse
                DefaultTableModel clearModel = (DefaultTableModel) table.getModel();
                clearModel.setRowCount(0); // sildik tabloyu
            }

            for(int i = 0; i<admin.getAdminList().size(); i++) {
                adminData[0] = admin.getAdminList().get(i).getId();
                adminData[1] = admin.getAdminList().get(i).getKullaniciAdi();
                adminData[2] = admin.getAdminList().get(i).getName();
                adminData[3] = admin.getAdminList().get(i).getPassword();
                adminData[4] = admin.getAdminList().get(i).getTc();
                adminData[5] = admin.getAdminList().get(i).getTelefonNo();

                adminModel.addRow(adminData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }





        JLabel lblPaurenciVeriler = new JLabel("PAU Personel Verileri");
        lblPaurenciVeriler.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
        lblPaurenciVeriler.setBounds(10, 10, 210, 32);
        add(lblPaurenciVeriler);


        JScrollPane sp = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setBounds(0, 55, 601, 339);
        add(sp);
        table = new JTable(adminModel);
        sp.setViewportView(table);
        setVisible(true);
    }





}
