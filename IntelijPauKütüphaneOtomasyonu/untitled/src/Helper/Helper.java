package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {

    public static void optionPaneChangeButtonText() {
        UIManager.put("OptionPane.cancelButtonText","İptal");
        UIManager.put("OptionPane.yesButtonText","Evet");
        UIManager.put("OptionPane.noButtonText","Hayır");
        UIManager.put("OptionPane.okButtonText","Tamam");
    }

    public static void showMsg(String str) {

        String msg;

        optionPaneChangeButtonText() ;

        switch(str) {
            case "fill":  // TUM ALANLARI DOLDUR MANASINDA
                msg =  "Lütfen Tüm Alanları Doldurunuz!";
                break;
            case "success":
                msg = "İşlem Başarılı !";
                break;
            default:
                msg=str;
        }

        JOptionPane.showMessageDialog(null,msg,"Mesaj",JOptionPane.INFORMATION_MESSAGE);

    }

    public static boolean confirm(String str) { // onayliyor musun diye sorgu mesaji verir

        String msg;

        optionPaneChangeButtonText() ;

        switch(str) {
            case "sure":
                msg = "Bu işlemi gerçekleştirmek istiyor musunuz?";
                break;

            default :
                msg = str;
                break;
        }
        int result = JOptionPane.showConfirmDialog(null, msg, "Dikkat ! ", JOptionPane.YES_NO_OPTION);

        if(result == 0) // 0 ise ilki yani yes secilmis ise
            return true;
        else
            return false;
    }



}
