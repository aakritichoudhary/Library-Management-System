package Library;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class IssueBook extends JFrame implements ActionListener {

    JLabel l1, l2, l3, l4, l5, l6, l7, l8;
    JButton bt1, bt2;
    JTextField tf1, tf2, tf3, tf4, tf5, tf6;
    Choice cb;
    JPanel p1, p2;
    Font f, f1;

    IssueBook() {
        super("Issue Books");
        setLocation(450, 400);
        setSize(650, 400);

        f = new Font("Arial", Font.BOLD, 25);
        f1 = new Font("Arial", Font.BOLD, 20);

        l1 = new JLabel("Issue Book");
        l2 = new JLabel("Book Id");
        l3 = new JLabel("Book No");
        l4 = new JLabel("Book Name");
        l5 = new JLabel("Student Id");
        l6 = new JLabel("Student Name");
        l7 = new JLabel("Student Contact");
        l8 = new JLabel("Book Quantity");

        l1.setForeground(Color.WHITE);
        l1.setHorizontalAlignment(JLabel.CENTER);

        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();
        tf5 = new JTextField();
        tf6 = new JTextField();

        l1.setFont(f);
        l2.setFont(f1);
        l3.setFont(f1);
        l4.setFont(f1);
        l5.setFont(f1);
        l6.setFont(f1);
        l7.setFont(f1);
        l8.setFont(f1);

        tf1.setFont(f1);
        tf2.setFont(f1);
        tf3.setFont(f1);
        tf4.setFont(f1);
        tf5.setFont(f1);
        tf6.setFont(f1);

        tf6.setEditable(false);
        tf1.setEditable(false);
        tf2.setEditable(false);
        tf6.setForeground(Color.RED);
        tf1.setForeground(Color.RED);
        tf2.setForeground(Color.RED);

        bt1 = new JButton("Issue Book");
        bt2 = new JButton("Cancel");

        bt1.setFont(f1);
        bt2.setFont(f1);

        bt1.addActionListener(this);
        bt2.addActionListener(this);

        cb = new Choice();
        cb.setFont(f1);
        try {
            ConnectionClass obj = new ConnectionClass();
            String q = "select id from addbook";
            ResultSet rest = obj.stm.executeQuery(q);
            while (rest.next()) {
                cb.add(rest.getString("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1, 10, 10));
        p1.add(l1);
        p1.setBackground(Color.BLUE);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(8, 2, 10, 10));
        p2.add(l2);
        p2.add(cb);
        p2.add(l3);
        p2.add(tf1);
        p2.add(l4);
        p2.add(tf2);
        p2.add(l5);
        p2.add(tf3);
        p2.add(l6);
        p2.add(tf4);
        p2.add(l7);
        p2.add(tf5);
        p2.add(l8);
        p2.add(tf6);
        p2.add(bt1);
        p2.add(bt2);

        setLayout(new BorderLayout(10, 10));
        add(p1, "North");
        add(p2, "Center");

        //Adding Mouse Action Listener so that the value changes agter selecting particular Book Id
        cb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                try {
                    //to fetch value from database
                    ConnectionClass obj1 = new ConnectionClass();
                    int id = Integer.parseInt(cb.getSelectedItem());
                    String q1 = "select * from addbook where id = '" + id + "'";
                    ResultSet rest1 = obj1.stm.executeQuery(q1);
                    while (rest1.next()) {
                        tf1.setText(rest1.getString("BookNo"));
                        tf2.setText(rest1.getString("BookName"));
                        tf6.setText(rest1.getString("Quantity"));
                    }
                } catch (Exception exx) {
                    exx.printStackTrace();
                }
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) {
            int qnt = 0;
            int id = Integer.parseInt(cb.getSelectedItem());
            String bookno = tf1.getText();
            String bookname = tf2.getText();
            int stuid = Integer.parseInt(tf3.getText());
            String stuname = tf4.getText();
            String stucount = tf5.getText();
            String date = new java.util.Date().toString();
            try {
                ConnectionClass obj2 = new ConnectionClass();
                String q2 = "select quantity from addbook where id = '" + id + "'";
                ResultSet rest2 = obj2.stm.executeQuery(q2);
                while (rest2.next()) {
                    qnt = Integer.parseInt(rest2.getString("Quantity"));
                }
                if (qnt <= 0) {
                    JOptionPane.showMessageDialog(null, "Book not available");
                    this.setVisible(false);
                } else {
                    String q0 = "insert into issuebook(BookId, BookNo, BookName, StudentId, StudentName, StudentContact, Date ) values ('" + id + "','" + bookno + "','" + bookname + "','" + stuid + "','" + stuname + "','" + stucount + "','" + date + "')";
                    String q3 = "update addbook set IssueBook = IssueBook +1 where id = '" + id + "'";
                    String q4 = "update addbook set Quantity = Quantity-1 where id = '" + id + "'";
                    int aa = obj2.stm.executeUpdate(q0);
                    int aaa = obj2.stm.executeUpdate(q3);
                    int aaaa = obj2.stm.executeUpdate(q4);
                    if (aa == 1) {
                        if (aaa == 1) {
                            if (aaaa == 1) {
                                JOptionPane.showMessageDialog(null, "Data successfully updated");
                                this.setVisible(false);
                            } else {
                                JOptionPane.showMessageDialog(null, "Please fill correct details");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Please fill correct details");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Please fill correct details");
                    }

                }
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }

        }
        if(e.getSource() == bt2)
        {
            JOptionPane.showMessageDialog(null, "Are you sure?");
            this.setVisible(false);
        }
    }
    public static void main(String[] args)
    {
        new IssueBook().setVisible(true);
    }
}
