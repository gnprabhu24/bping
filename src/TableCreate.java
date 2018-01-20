import java.sql.*;

import javax.swing.JTable;

public class TableCreate
{
DBConnect1 d;
String tbname;
JTable ptable;
int a;

TableCreate(String tablename, JTable tab, int fsize)
{

try
{
tbname=tablename;	
ptable = tab;	
a = fsize ;

d=new DBConnect1();
System.out.println(tbname);

String sql = "CREATE TABLE "+tbname+" ( Pump_Name VARCHAR(100), IP_Address VARCHAR(100), UP_Time VARCHAR(100), DOWN_Time VARCHAR(100));";
System.out.println(sql);

d.s.execute(sql);

System.out.println("Table created");

new TableInsert(ptable, tbname, a);

}
catch(Exception e)
{
e.printStackTrace();
}

}
}