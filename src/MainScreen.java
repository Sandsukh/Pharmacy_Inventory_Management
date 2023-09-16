import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class MainScreen {

    private JPanel panel1;
    private JLabel MSlabel1;
    private JLabel MSmed;
    private JLabel MSsales;
    private JLabel MSpurch;
    private JLabel MSemp;
    private JLabel MSsup;
    private JLabel MScust;
    private JLabel PIC;
    private JLabel MSbill;

    public MainScreen() {
        MSsup.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Supplier.sup();
            }
        });

        MSpurch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Purchases.pur();
            }
        });
        MSsales.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Sale.sal();

            }
        });
        MSemp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Employees.emp();
            }
        });
        MScust.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Customers.cus();
            }
        });
        MSmed.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Medicine.med();
            }
        });
        MSbill.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Bill b=new Bill();
                b.bill();
            }
        });
    }

    public static void MS() {
        JFrame frame = new JFrame("MainScreen");
        frame.setVisible(true);
        frame.setContentPane(new MainScreen().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }




}



