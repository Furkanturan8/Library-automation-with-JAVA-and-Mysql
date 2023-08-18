package Panel;
import Model.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import java.awt.Font;
import java.awt.Scrollbar;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.metal.MetalScrollBarUI;
import javax.swing.table.DefaultTableModel;



public class OgrenciDataPanel extends JPanel {
    private JTable table;
    static  Customer customers = new Customer();
    private DefaultTableModel customerModel = null;
    private Object[] customersData = null;

    /**
     * Create the panel.
     */
    public OgrenciDataPanel() {
        setBounds(0,0,617,476);
        setLayout(null);

        customerModel = new DefaultTableModel();
        Object[] colCustomersName = new Object[6];
        colCustomersName[0] = "ID";
        colCustomersName[1] = "Kullanıcı Adı";
        colCustomersName[2] = "İsim Soyisim";
        colCustomersName[3] = "Şifresi";
        colCustomersName[4] = "TC";
        colCustomersName[5] = "Telefon No";

        customerModel.setColumnIdentifiers(colCustomersName);; //basliklari atadik
        customersData = new Object[6]; // basliklarin altinda gosterilmesi gereken veriler

        try {
            for(int i = 0; i<customers.getCustomerList().size(); i++) {
                customersData[0] = customers.getCustomerList().get(i).getId();
                customersData[1] = customers.getCustomerList().get(i).getKullaniciAdi();
                customersData[2] = customers.getCustomerList().get(i).getName();
                customersData[3] = customers.getCustomerList().get(i).getPassword();
                customersData[4] = customers.getCustomerList().get(i).getTc();
                customersData[5] = customers.getCustomerList().get(i).getTelefonNo();

                customerModel.addRow(customersData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



        JLabel lblPaurenciVeriler = new JLabel("PAU Öğrenci Verileri");
        lblPaurenciVeriler.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
        lblPaurenciVeriler.setBounds(10, 10, 210, 32);
        add(lblPaurenciVeriler);

        JScrollPane sp = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setBounds(0, 55, 601, 339);
        add(sp);

        table = new JTable(customerModel);
        sp.setViewportView(table);
        setVisible(true);


    }
}
