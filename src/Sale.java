import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Sale {
    private JTextField txtc;
    private JButton saveButton;
    private JTable table1;
    private JTextField txtid;
    private JPanel sale;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JTextField txtDate;
    private JTextField txtDis;
    private JTextField txtp;

    LoginPage a = new LoginPage();
    String id,p,dis,date,c;


    public Sale() {
        table_load();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c=txtc.getText();
                date=txtDate.getText();
                dis=txtDis.getText();
                p=txtp.getText();
                try {
                    a.pst = a.con.prepareStatement("insert into sale(Date,Discount,Paid_amount,Customer_id)values(?,?,?,?)");
                    a.pst.setString(1, date);
                    a.pst.setString(2, dis);
                    a.pst.setString(3, p);
                    a.pst.setString(4, c);
                    a.pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
                    table_load();
                    txtc.setText("");
                    txtDate.setText("");
                    txtDis.setText("");
                    txtp.setText("");
                    txtc.requestFocus();
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

                    a.pst = a.con.prepareStatement("select Date,Discount,Paid_amount,Customer_id from sale where Sale_id = ?");
                    a.pst.setString(1, id);
                    ResultSet rs = a.pst.executeQuery();

                    if (rs.next() == true) {
                        date = rs.getString(1);
                        dis = rs.getString(2);
                        p = rs.getString(3);
                        c = rs.getString(4);


                        txtDate.setText(date);
                        txtp.setText(p);
                        txtc.setText(c);
                        txtDis.setText(dis);


                    } else {
                        txtDate.setText("");
                        txtp.setText("");
                        txtc.setText("");
                        txtDis.setText("");
                        JOptionPane.showMessageDialog(null, "Invalid sale id");

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
                c=txtc.getText();
                date=txtDate.getText();
                dis=txtDis.getText();
                p=txtp.getText();

                try {
                    a.pst = a.con.prepareStatement("update sale set Date= ?,Discount= ?,Paid_amount=?,Customer_id=? where Sale_id = ?");
                    a.pst.setString(1, date);
                    a.pst.setString(2, dis);
                    a.pst.setString(3, p);
                    a.pst.setString(4, c);
                    a.pst.setString(5, id);


                    a.pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Updateee!!!!!");
                    table_load();
                    txtDate.setText("");
                    txtp.setText("");
                    txtc.setText("");
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
                    a.pst = a.con.prepareStatement("delete from sale  where Sale_id = ?");

                    a.pst.setString(1,id);

                    a.pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Deleteeeeee!!!!!");
                    table_load();
                    txtDate.setText("");
                    txtp.setText("");
                    txtc.setText("");
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

    public static void sal(){
        JFrame frame = new JFrame("sale");
        frame.setContentPane(new Sale().sale);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    void table_load()
    {
        try
        {
            a.pst = a.con.prepareStatement("select * from sale");
            ResultSet rs = a.pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
