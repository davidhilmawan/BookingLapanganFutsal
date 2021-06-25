package lapanganfutsal;
import javax.swing.JOptionPane;
import gui.Login;

public class LapanganFutsal {
    public static void main(String[] args) {
        try
        {
            Login log = new Login();
            log.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
    

