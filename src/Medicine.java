import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Medicine {

    private JPanel main;
    private JTextField txtDrugid;
    private JTextField txtName;
    private JTextField textSname;
    private JTextField txtUnits;
    private JTextField txtPrice;
    private JTable table1;
    private JTextField txtTemp;
    private JTextField txtLevel;
    private JButton saveButton;
    private JButton searchButton;
    private JButton updateButton;
    private JButton deleteButton;
    LoginPage a = new LoginPage();
    String name,sciname,units,price,temp,level,drugid;




    public Medicine() {
        table_load();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name=txtName.getText();
                sciname=textSname.getText();
                units=txtUnits.getText();
                price=txtPrice.getText();
                temp=txtTemp.getText();
                level=txtLevel.getText();

                try {
                    a.pst = a.con.prepareStatement("insert into drugs(Drug_name,Scientific_name,no_of_units,unit_price,Storage_temp,Drug_category)values(?,?,?,?,?,?)");
                    a.pst.setString(1, name);
                    a.pst.setString(2, sciname);
                    a.pst.setString(3, units);
                    a.pst.setString(4, price);
                    a.pst.setString(5, temp);
                    a.pst.setString(6, level);
                    a.pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
                    table_load();
                    txtName.setText("");
                    textSname.setText("");
                    txtUnits.setText("");
                    txtPrice.setText("");
                    txtUnits.setText("");
                    txtTemp.setText("");
                    txtLevel.setText("");
                    txtName.requestFocus();
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

                    drugid = txtDrugid.getText();

                    a.pst = a.con.prepareStatement("select Drug_name,Scientific_name,no_of_units,unit_price,Storage_temp,Drug_category from drugs where Drug_id= ?");
                    a.pst.setString(1, drugid);
                    ResultSet rs = a.pst.executeQuery();

                    if(rs.next()==true)
                    {
                        name=rs.getString(1);
                        sciname=rs.getString(2);
                        units=rs.getString(3);
                        price=rs.getString(4);
                        temp=rs.getString(5);
                        level=rs.getString(6);

                        txtName.setText(name);
                        textSname.setText(sciname);
                        txtUnits.setText(units);
                        txtPrice.setText(price);
                        txtUnits.setText(units);
                        txtTemp.setText(temp);
                        txtLevel.setText(level);

                    }
                    else
                    {
                        txtName.setText("");
                        textSname.setText("");
                        txtUnits.setText("");
                        txtPrice.setText("");
                        txtUnits.setText("");
                        txtTemp.setText("");
                        txtLevel.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid Drug id");

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
                name=txtName.getText();
                sciname=textSname.getText();
                units=txtUnits.getText();
                price=txtPrice.getText();
                temp=txtTemp.getText();
                level=txtLevel.getText();
                drugid = txtDrugid.getText();

                try {
                    a.pst = a.con.prepareStatement("update drugs set Drug_name=?,Scientific_name=?,no_of_units=?,unit_price=?,Storage_temp=?,Drug_category=? where Drug_id = ?");
                    a.pst.setString(1, name);
                    a.pst.setString(2, sciname);
                    a.pst.setString(3, units);
                    a.pst.setString(4, price);
                    a.pst.setString(5, temp);
                    a.pst.setString(6, level);
                    a.pst.setString(7, drugid);

                    a.pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Updateee!!!!!");
                    table_load();
                    txtName.setText("");
                    textSname.setText("");
                    txtUnits.setText("");
                    txtPrice.setText("");
                    txtUnits.setText("");
                    txtTemp.setText("");
                    txtLevel.setText("");
                    txtDrugid.setText("");
                    txtName.requestFocus();
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
                drugid = txtDrugid.getText();

                try {
                    a.pst = a.con.prepareStatement("delete from drugs  where Drug_id = ?");

                    a.pst.setString(1,drugid);

                    a.pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Deleteeeeee!!!!!");
                    table_load();
                    txtName.setText("");
                    textSname.setText("");
                    txtUnits.setText("");
                    txtPrice.setText("");
                    txtUnits.setText("");
                    txtTemp.setText("");
                    txtLevel.setText("");
                    txtDrugid.setText("");
                    txtName.requestFocus();
                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }
            }

        });
    }

    public static void  med(){
        JFrame frame = new JFrame("Medicine");
        frame.setVisible(true);
        frame.setContentPane(new Medicine().main);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
    }

    void table_load()
        {
            try
            {
                a.pst = a.con.prepareStatement("select * from drugs");
                ResultSet rs = a.pst.executeQuery();
                table1.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
    }


}
