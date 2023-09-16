import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Purchases {
    private JButton updateButton;
    private JTextField txtS;
    private JTextField txtDate;
    private JTextField txtDis;
    private JTextField txtp;
    private JTable table1;
    private JTextField txtid;
    private JPanel Purch;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton searchButton;

    LoginPage a = new LoginPage();
    String id,p,dis,date,s;


    public Purchases() {
        table_load();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s=txtS.getText();
                date=txtDate.getText();
                dis=txtDis.getText();
                p=txtp.getText();
                try {
                    a.pst = a.con.prepareStatement("insert into purchase(Date,Discount,Paid_amount,Supplier_id)values(?,?,?,?)");
                    a.pst.setString(1, date);
                    a.pst.setString(2, dis);
                    a.pst.setString(3, p);
                    a.pst.setString(4, s);
                    a.pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
                    table_load();
                    txtS.setText("");
                    txtDate.setText("");
                    txtDis.setText("");
                    txtp.setText("");
                    txtS.requestFocus();
                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    id = txtid.getText();

                    a.pst = a.con.prepareStatement("select Date,Discount,Paid_amount,Supplier_id from purchase where Purchase_id = ?");
                    a.pst.setString(1, id);
                    ResultSet rs = a.pst.executeQuery();

                    if (rs.next() == true) {
                        date = rs.getString(1);
                        dis = rs.getString(2);
                        p = rs.getString(3);
                        s = rs.getString(4);


                        txtDate.setText(date);
                        txtp.setText(p);
                        txtS.setText(s);
                        txtDis.setText(dis);


                    } else {
                        txtDate.setText("");
                        txtp.setText("");
                        txtS.setText("");
                        txtDis.setText("");
                        JOptionPane.showMessageDialog(null, "Invalid purchase id");

                    }
                }

                catch(SQLException ex)

                    {
                        ex.printStackTrace();
                    }
                }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id=txtid.getText();
                s=txtS.getText();
                date=txtDate.getText();
                dis=txtDis.getText();
                p=txtp.getText();

                try {
                    a.pst = a.con.prepareStatement("update purchase set Date= ?,Discount= ?,Paid_amount=?,Supplier_id=? where Purchase_id = ?");
                    a.pst.setString(1, date);
                    a.pst.setString(2, dis);
                    a.pst.setString(3, p);
                    a.pst.setString(4, s);
                    a.pst.setString(5, id);


                    a.pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Updateee!!!!!");
                    table_load();
                    txtDate.setText("");
                    txtp.setText("");
                    txtS.setText("");
                    txtDis.setText("");
                    txtid.setText("");
                    txtDate.requestFocus();
                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id=txtid.getText();

                try {
                    a.pst = a.con.prepareStatement("delete from purchase  where Purchase_id = ?");

                    a.pst.setString(1,id);

                    a.pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Deleteeeeee!!!!!");
                    table_load();
                    txtDate.setText("");
                    txtp.setText("");
                    txtS.setText("");
                    txtDis.setText("");
                    txtid.setText("");
                    txtDate.requestFocus();
                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }
            }
        });
    }

    public static void pur(){
        JFrame frame = new JFrame("Purch");
        frame.setContentPane(new Purchases().Purch);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    void table_load()
    {
        try
        {
            a.pst = a.con.prepareStatement("select * from purchase");
            ResultSet rs = a.pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
