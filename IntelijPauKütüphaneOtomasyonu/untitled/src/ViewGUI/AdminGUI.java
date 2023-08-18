package ViewGUI;

import javax.swing.ImageIcon;
import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Model.Admin;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Panel.*;

public class AdminGUI extends JFrame {
    static Admin admin = new Admin();
    private JPanel contentPane;
    private OgrenciDataPanel ogrDataPanel;
    private PAUBooksPanel booksPanel;
    private PersonelDataPanel personelDataPanel;
    private OgrenciSorgulaPanel ogrSorgulaPanel;
    private TakeActionPanel takeActionPanel;
    private ReceiveTheBookPanel receiveTheBookPanel;
    JPanel menuPANEL = new JPanel();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminGUI frame = new AdminGUI(admin);
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
    public AdminGUI(Admin admin) {
        setResizable(false);
        setTitle("PAU Kütüphane Yönetici Paneli");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 903, 478);
        contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("inactiveCaption"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        ogrDataPanel = new OgrenciDataPanel();
        ogrDataPanel.setBounds(0, 0, 617, 417);
        menuPANEL.add(ogrDataPanel);

        booksPanel = new PAUBooksPanel();
        booksPanel.setBounds(0, 0, 617, 417);
        menuPANEL.add(booksPanel);

        personelDataPanel = new PersonelDataPanel();
        personelDataPanel.setBounds(0, 0, 617, 417);
        menuPANEL.add(personelDataPanel);

        ogrSorgulaPanel = new OgrenciSorgulaPanel();
        ogrSorgulaPanel.setBounds(0, 0, 617, 417);
        menuPANEL.add(ogrSorgulaPanel);

        takeActionPanel = new TakeActionPanel();
        takeActionPanel.setBounds(0, 0, 617, 417);
        menuPANEL.add(takeActionPanel);

        receiveTheBookPanel = new ReceiveTheBookPanel();
        receiveTheBookPanel.setBounds(0,0,617,417);
        menuPANEL.add(receiveTheBookPanel);

        Image image1 = new ImageIcon(this.getClass().getResource("users.png")).getImage();

        JLabel menuPanelBilgilendirme = new JLabel("PAMUKKALE ÜNİVERSİTESİ KÜTÜPHANE OTOMASYONUNA HOŞGELDİNİZ!");
        menuPanelBilgilendirme.setBounds(100,0,600,400);
        menuPANEL.add(menuPanelBilgilendirme);

        contentPane.setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 879, 22);
        contentPane.add(menuBar);

        JMenu menuDosyalar = new JMenu("Dosyalar");
        menuBar.add(menuDosyalar);

        JMenuItem itemBooks = new JMenuItem("PAU Kitap Arşivi ");
        itemBooks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                personelDataPanel.setVisible(false);
                ogrSorgulaPanel.setVisible(false);
                receiveTheBookPanel.setVisible(false);
                takeActionPanel.setVisible(false);
                ogrDataPanel.setVisible(false);
                booksPanel.setVisible(true);

            }
        });
        menuDosyalar.add(itemBooks);

        JMenuItem itemPersonelData = new JMenuItem("Personel Verileri");
        itemPersonelData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                booksPanel.setVisible(false);
                ogrSorgulaPanel.setVisible(false);
                takeActionPanel.setVisible(false);
                receiveTheBookPanel.setVisible(false);
                ogrDataPanel.setVisible(false);
                personelDataPanel.setVisible(true);
            }
        });
        menuDosyalar.add(itemPersonelData);

        JMenuItem itemStudentData = new JMenuItem("Öğrenci Verileri");
        itemStudentData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                takeActionPanel.setVisible(false);
                booksPanel.setVisible(false);
                personelDataPanel.setVisible(false);
                receiveTheBookPanel.setVisible(false);
                ogrSorgulaPanel.setVisible(false);
                ogrDataPanel.setVisible(true);
            }
        });
        menuDosyalar.add(itemStudentData);

        JMenu menuOgrenciSistemi = new JMenu("Öğrenci Sistemi");
        menuBar.add(menuOgrenciSistemi);

        JMenuItem itemSorgula = new JMenuItem("Sorgula");
        itemSorgula.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                booksPanel.setVisible(false);
                personelDataPanel.setVisible(false);
                takeActionPanel.setVisible(false);
                receiveTheBookPanel.setVisible(false);
                ogrDataPanel.setVisible(false);
                ogrSorgulaPanel.setVisible(true);
            }
        });
        menuOgrenciSistemi.add(itemSorgula);

        JMenuItem itemTakeAction = new JMenuItem("İşlem Yap");
        itemTakeAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                booksPanel.setVisible(false);
                personelDataPanel.setVisible(false);
                ogrSorgulaPanel.setVisible(false);
                receiveTheBookPanel.setVisible(false);
                ogrDataPanel.setVisible(false);
                takeActionPanel.setVisible(true);
            }
        });
        menuOgrenciSistemi.add(itemTakeAction);

        JMenuItem itemReceiveTheBook = new JMenuItem("Kitabı Teslim Al");
        itemReceiveTheBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                booksPanel.setVisible(false);
                personelDataPanel.setVisible(false);
                ogrSorgulaPanel.setVisible(false);
                ogrDataPanel.setVisible(false);
                takeActionPanel.setVisible(false);
                receiveTheBookPanel.setVisible(true);
            }
        });
        menuOgrenciSistemi.add(itemReceiveTheBook);

        JMenu menuAyarlar = new JMenu("Ayarlar");
        menuBar.add(menuAyarlar);

        JMenuItem itemAddPersonel = new JMenuItem("Personel Ekle");
        itemAddPersonel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegisterAdminGUI registerAdmin = new RegisterAdminGUI();
                registerAdmin.setVisible(true);
            }
        });
        menuAyarlar.add(itemAddPersonel);

        JMenuItem itemDeletePersonel = new JMenuItem("Personel Sil");
        itemDeletePersonel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                DeleteAdminGUI deleteAdmin = new DeleteAdminGUI(admin);
                deleteAdmin.setVisible(true);
            }
        });
        menuAyarlar.add(itemDeletePersonel);

        JMenuItem itemUpdatePersonel = new JMenuItem("Kişisel Verileri Değiştir");
        itemUpdatePersonel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UpdateAdminGUI updateAdmin = new UpdateAdminGUI(admin);
                updateAdmin.setVisible(true);
            }
        });
        menuAyarlar.add(itemUpdatePersonel);

        JMenuItem mnıtmNewMenuItem_9 = new JMenuItem("Kitapları Düzenle");
        mnıtmNewMenuItem_9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditBooksGUI editBooksGUI = new EditBooksGUI(booksPanel);
                editBooksGUI.setVisible(true);

            }
        });
        menuAyarlar.add(mnıtmNewMenuItem_9);

        JMenu menuExit = new JMenu("Çıkış Yap");
        menuExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(Helper.Helper.confirm("sure")) {
                    LoginGUI loginGUI = new LoginGUI();
                    loginGUI.setVisible(true);
                    dispose();
                }
                else {
                    Helper.Helper.showMsg("Çıkış İptal Edildi!");
                }
            }
        });
        menuBar.add(menuExit);

        JPanel infoPANEL = new JPanel();
        infoPANEL.setBackground(new Color(0, 128, 128));
        infoPANEL.setBounds(0, 21, 252, 417);
        contentPane.add(infoPANEL);
        infoPANEL.setLayout(null);

        JLabel lbl_baslik = new JLabel("PAU Personel Bilgileri");
        lbl_baslik.setBounds(0, 0, 250, 49);
        lbl_baslik.setHorizontalTextPosition(SwingConstants.CENTER);
        lbl_baslik.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_baslik.setForeground(new Color(255, 255, 255));
        lbl_baslik.setFont(new Font("Tahoma", Font.BOLD, 14));
        infoPANEL.add(lbl_baslik);

        JLabel lbl_name = new JLabel("PAU Personel Adı-Soyadı");
        lbl_name.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbl_name.setForeground(new Color(255, 255, 255));
        lbl_name.setBounds(10, 52, 232, 27);
        infoPANEL.add(lbl_name);

        JLabel lbl_dynamicName = new JLabel(admin.getName());
        lbl_dynamicName.setForeground(new Color(255, 255, 255));
        lbl_dynamicName.setFont(new Font("Tahoma", Font.ITALIC, 14));
        lbl_dynamicName.setBounds(10, 87, 232, 27);
        infoPANEL.add(lbl_dynamicName);

        JLabel lbl_telNo = new JLabel("PAU Personel Telefonu");
        lbl_telNo.setForeground(Color.WHITE);
        lbl_telNo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbl_telNo.setBounds(10, 125, 232, 27);
        infoPANEL.add(lbl_telNo);

        JLabel lbl_dynamicTelNo = new JLabel(admin.getTelefonNo());
        lbl_dynamicTelNo.setForeground(Color.WHITE);
        lbl_dynamicTelNo.setFont(new Font("Tahoma", Font.ITALIC, 14));
        lbl_dynamicTelNo.setBounds(10, 160, 232, 27);
        infoPANEL.add(lbl_dynamicTelNo);

        JLabel lbl_tcNo = new JLabel("PAU Personel TC");
        lbl_tcNo.setForeground(Color.WHITE);
        lbl_tcNo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbl_tcNo.setBounds(10, 197, 232, 27);
        infoPANEL.add(lbl_tcNo);

        JLabel lbl_dynamicTcNo = new JLabel(admin.getTc());
        lbl_dynamicTcNo.setForeground(Color.WHITE);
        lbl_dynamicTcNo.setFont(new Font("Tahoma", Font.ITALIC, 14));
        lbl_dynamicTcNo.setBounds(10, 227, 232, 27);
        infoPANEL.add(lbl_dynamicTcNo);

        JLabel icon = new JLabel("");
        icon.setIcon(new ImageIcon(image1));
        icon.setBounds(10, 264, 180, 143);
        infoPANEL.add(icon);
        menuPANEL.setBounds(262, 21, 613, 417);
        contentPane.add(menuPANEL);

        menuPANEL.setBackground(new Color(173, 216, 230));
        menuPANEL.setLayout(null);



        menuClicked(ogrDataPanel);




    }

    public void menuClicked(JPanel panel) {
        booksPanel.setVisible(false);
        personelDataPanel.setVisible(false);
        ogrSorgulaPanel.setVisible(false);
        takeActionPanel.setVisible(false);
        ogrDataPanel.setVisible(false);
        receiveTheBookPanel.setVisible(false);
        menuPANEL.setVisible(true);
    }
}
