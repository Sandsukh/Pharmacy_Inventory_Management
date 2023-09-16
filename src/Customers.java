import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customers {
    private JButton saveButton;
    private JTextField txtName;
    private JTable table1;
    private JTextField txtid;
    private JPanel customer;
    private JButton updateButton;
    private JButton DeleteButton;
    private JButton SearchButton;
    private JTextField txtMobile;
    private JTextField txtAd;

    LoginPage a = new LoginPage();
    String cid, name,mobile,ad;


    public Customers() {
        table_load();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name=txtName.getText();
                mobile=txtMobile.getText();
                ad=txtAd.getText();
                try {
                    a.pst = a.con.prepareStatement("insert into customer(Customer_name,Mobile,Address)values(?,?,?)");
                    a.pst.setString(1, name);
                    a.pst.setString(2, mobile);
                    a.pst.setString(3, ad);
                    a.pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
                    table_load();
                    txtName.setText("");
                    txtMobile.setText("");
                    txtAd.setText("");
                    txtName.requestFocus();
                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }

            }
        });
        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    cid = txtid.getText();

                    a.pst = a.con.prepareStatement("select Customer_name,Mobile,Address from customer where Customer_id = ?");
                    a.pst.setString(1, cid);
                    ResultSet rs = a.pst.executeQuery();

                    if(rs.next()==true)
                    {
                        name=rs.getString(1);
                        mobile=rs.getString(2);
                        ad=rs.getString(3);


                        txtName.setText(name);
                        txtMobile.setText(mobile);
                        txtAd.setText(ad);

                    }
                    else
                    {
                        txtName.setText("");
                        txtMobile.setText("");
                        txtAd.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid customer id");

                    }
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cid=txtid.getText();
                name=txtName.getText();
                mobile=txtMobile.getText();
                ad=txtAd.getText();

                try {
                    a.pst = a.con.prepareStatement("update customer set Customer_name= ?,Mobile= ?,Address=? where Customer_id = ?");
                    a.pst.setString(1, name);
                    a.pst.setString(2, mobile);
                    a.pst.setString(3, ad);
                    a.pst.setString(4, cid);


                    a.pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Updateee!!!!!");
                    table_load();
                    txtName.setText("");
                    txtMobile.setText("");
                    txtAd.setText("");
                    txtid.setText("");
                    txtName.requestFocus();
                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
            }
        });
        DeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cid = txtid.getText();

                try {
                    a.pst = a.con.prepareStatement("delete from customer  where Customer_id = ?");

                    a.pst.setString(1,cid);

                    a.pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Deleteeeeee!!!!!");
                    table_load();
                    txtName.setText("");
                    txtMobile.setText("");
                    txtAd.setText("");
                    txtid.setText("");
                    txtName.requestFocus();
                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }
            }
        });
    }

    public static void cus() {
        JFrame frame = new JFrame("customer");
        frame.setContentPane(new Customers().customer);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    void table_load()
    {
        try
        {
            a.pst = a.con.prepareStatement("select * from customer");
            ResultSet rs = a.pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}

