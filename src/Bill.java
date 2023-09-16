import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bill {
    private JTextField textName;
    private JTextField textQuantity;
    private JTextArea textArea1;
    private JButton addButton;
    private JButton printButton;
    private JTextField textPrice;
    private JPanel bill;
    double sum=0;

    public Bill() {
        billHeader();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText(
                        textArea1.getText()+textName.getText()+"-"+textQuantity.getText()+"\t\t"+textPrice.getText()+"\n"
                );
                sum=sum+(Double.parseDouble(textQuantity.getText())*Double.parseDouble(textPrice.getText()));
                textName.setText("");
                textQuantity.setText("");
                textPrice.setText("");
            }
        });
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    textArea1.setText(textArea1.getText()+"============================"+"\n"+"Total Amount"+"\t\t"+String.valueOf(sum));
                    sum=0;
                    textArea1.print();
                    textArea1.setText("");
                    billHeader();
                } catch (Exception a) {
                }

            }
        });
    }

    public void billHeader()
    {

        textArea1.setText("========================"+"\n"
                +"ABC Pharmacy"+"\n"
                +"Contact No-xxxxxxxxx"+"\n"
                +"Address- Pharmacy Address"+"\n"
                +"========================"+"\n");

    }
    public  void  bill(){
        JFrame frame = new JFrame("Bill");
        frame.setVisible(true);
        frame.setContentPane(new Bill().bill);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
    }
}
