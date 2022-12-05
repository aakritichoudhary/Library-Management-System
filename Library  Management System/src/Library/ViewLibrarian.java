package Library;


import javax.swing.*;
import java.sql.*;
import java.awt.*;
public class ViewLibrarian extends JFrame
{
   String x[] = {"Id", "Name", "Password", "Email ID", "Contact", "Address", "City"}; 
   String y[][] = new String[20][7];
   int i =0, j=0;
   JTable t;
   Font f;
   
   ViewLibrarian()
   {
      super("Librarian Information");
      setLocation(1, 1);
      setSize(1000, 400);
      
      f = new Font("Arial", Font.BOLD, 15);
      
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
      add(sp);
   }
   public static void main(String[] args)
   {
       new ViewLibrarian().setVisible(true);
   }
}
