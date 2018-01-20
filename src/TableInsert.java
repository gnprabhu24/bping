import java.sql.*;

import javax.swing.*;

public class TableInsert
{
DBConnect1 d;
String tbname;
JTable ptable;
int a;


TableInsert(JTable tab,String tablename,int fsize)
{

try
{
tbname = tablename;	
ptable = tab;	
a = fsize ;

d=new DBConnect1();

System.out.println(tbname);


for(int i=1;i<a;i++)
{
Object name = ptable.getValueAt(i, 0);
Object ipaddress = ptable.getValueAt(i, 1);
Object uptime = ptable.getValueAt(i, 4);
Object downtime = ptable.getValueAt(i, 5);

String name1= name.toString();
String ipaddress1= ipaddress.toString();
String uptime1= uptime.toString();
String downtime1= downtime.toString();

System.out.println(name1+" "+ipaddress1+" "+uptime1+" "+downtime1);


String sql = "INSERT INTO "+tbname+" ( Pump_Name , IP_Address , UP_Time, Down_Time) VALUES ( '"+name1+"','"+ipaddress1+"','" +uptime1+"','"+downtime1+"');";
System.out.println(sql);

d.s.execute(sql);

System.out.println("Row updated");
}

System.out.println("Table updated");

int im1=JOptionPane.INFORMATION_MESSAGE;
JOptionPane.showMessageDialog(null, "Table has been successfully saved to the database in the schema-'tfft'.", "DONE!!!", im1 );
}
catch(Exception e)
{
e.printStackTrace();
}

}
}