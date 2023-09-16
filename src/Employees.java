import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Employees {
    private JTextField txtName;
    private JTable table1;
    private JPanel Emp;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton searchButton;
    private JTextField txtMobile;
    private JTextField txtSalary;
    private JTextField txtid;

    LoginPage a = new LoginPage();
    String eid, name,mobile,sl;


    public Employees() {
        table_load();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name=txtName.getText();
                mobile=txtMobile.getText();
                sl=txtSalary.getText();
                try {
                    a.pst = a.con.prepareStatement("insert into employee(Emp_name,Mobile,Salary)values(?,?,?)");
                    a.pst.setString(1, name);
                    a.pst.setString(2, mobile);
                    a.pst.setString(3, sl);
                    a.pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
                    table_load();
                    txtName.setText("");
                    txtMobile.setText("");
                    txtSalary.setText("");
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

                    eid = txtid.getText();

                    a.pst = a.con.prepareStatement("select Emp_name,Mobile,Salary from employee where Emp_id = ?");
                    a.pst.setString(1, eid);
                    ResultSet rs = a.pst.executeQuery();

                    if(rs.next()==true)
                    {
                        name=rs.getString(1);
                        mobile=rs.getString(2);
                        sl=rs.getString(3);


                        txtName.setText(name);
                        txtMobile.setText(mobile);
                        txtSalary.setText(sl);

                    }
                    else
                    {
                        txtName.setText("");
                        txtMobile.setText("");
                        txtSalary.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid employee id");

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
                eid=txtid.getText();
                name=txtName.getText();
                mobile=txtMobile.getText();
                sl=txtSalary.getText();

                try {
                    a.pst = a.con.prepareStatement("update employee set Emp_name= ?,Mobile= ?,Salary=? where Emp_id = ?");
                    a.pst.setString(1, name);
                    a.pst.setString(2, mobile);
                    a.pst.setString(3, sl);
                    a.pst.setString(4, eid);


                    a.pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Updateee!!!!!");
                    table_load();
                    txtName.setText("");
                    txtMobile.setText("");
                    txtSalary.setText("");
                    txtid.setText("");
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
                eid = txtid.getText();

                try {
                    a.pst = a.con.prepareStatement("delete from employee  where Emp_id = ?");

                    a.pst.setString(1,eid);

                    a.pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Deleteeeeee!!!!!");
                    table_load();
                    txtName.setText("");
                    txtMobile.setText("");
                    txtSalary.setText("");
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

    public static void emp() {
        JFrame frame = new JFrame("Emp");
        frame.setContentPane(new Employees().Emp);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    void table_load()
    {
        try
        {
            a.pst = a.con.prepareStatement("select * from employee");
            ResultSet rs = a.pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
