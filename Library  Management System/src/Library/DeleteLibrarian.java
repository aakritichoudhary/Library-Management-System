package Library;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.awt.*;

public class DeleteLibrarian extends JFrame implements ActionListener
{
   String x[] = {"Id", "Name", "Password", "Email ID", "Contact", "Address", "City"}; 
   JButton bt1;
   String y[][] = new String[20][7];
   int i =0, j=0;
   JTable t;
   Font f, f1;
   JTextField tf1;
   JPanel p1;
   JLabel l1;
   
   DeleteLibrarian()
   {
       super("Delete Librarian");
       setLocation(1, 1);
      setSize(1000, 400);
      
     f = new Font("Arial", Font.BOLD, 15);
      f1 = new Font("Arial", Font.BOLD, 20);
      
      try
      {
          ConnectionClass obj  = new ConnectionClass();
          String q = "select * from librarian";
          ResultSet rest = obj.stm .executeQuery(q);
          
          while(rest.next())
          {
            y[i][j++] = rest.getString("Lid");
            y[i][j++] = rest.getString("Name");
            y[i][j++] = rest.getString("Password");
            y[i][j++] = rest.getString("Email");
            y[i][j++] = rest.getString("Contact");
            y[i][j++] = rest.getString("Address");
            y[i][j++] = rest.getString("City");
            
            i++;
            j =0;
             
            
          }
          t = new JTable(y, x);
          t.setFont(f);
      }
      catch(Exception ex)
              {
                  ex.printStackTrace();
              }
      
      JScrollPane sp = new JScrollPane(t);
      
      l1 = new JLabel("Delete Librarian");
      tf1 = new JTextField();
      bt1 = new JButton("Delete");
      bt1.addActionListener(this);
      
      l1.setFont(f1);
      tf1.setFont(f);
      bt1.setFont(f);
      
      p1 = new JPanel();
      p1.setLayout(new GridLayout(1, 3, 10, 10));
      p1.add(l1);
      p1.add(tf1);
      p1.add(bt1);
      
      setLayout(new BorderLayout(10, 10));
      add(sp, "Center");
      add(p1, "South");
      
       
   }
   
   public void actionPerformed(ActionEvent e)
   {
     if(e.getSource() == bt1)
     {
         int id = Integer.parseInt(tf1.getText());
         try
         {
           ConnectionClass obj = new ConnectionClass();
           String q = "delete from librarian where Lid = '"+id+"'";
           int res = obj.stm.executeUpdate(q);
           if(res == 1)
           {
               JOptionPane.showMessageDialog(null, "Record successfully deleted");
               this.setVisible(false);
               new DeleteLibrarian().setVisible(true);
           }
           else
           {
             JOptionPane.showMessageDialog(null, "Record not deleted");  
           this.setVisible(false);
               new DeleteLibrarian().setVisible(true);
           }
         }
         catch(Exception ex)
         {
             ex.printStackTrace();
         }
     }
   }
   public static void main(String[] args)
   {
       new DeleteLibrarian().setVisible(true);
   }
}
