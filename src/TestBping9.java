import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import javax.swing.*;

import java.util.*;
import java.text.*;

import javax.swing.table.*;


public class TestBping9 extends WindowAdapter implements ActionListener, Runnable
{
JFrame f;
TextField t1,t2,t3,t4,t5;
Label l1,l2,l3,l4,l5;
JButton b,b1,b2,b8,b3,button1;
TextArea ta;
private boolean b4,b5,b9;
JTable	ptable;
JScrollPane scrollPane;
JPanel	topPanel,p1,p2;
private String addr;
private int row, n, a;
private int ind=0, uc=1,dc=2;
private String s[]=new String[100];
private String w[]=new String[100];
boolean b6=false;
Thread[] h= new Thread[100];
Thread t;
private Double ptime;
Choice c1,c2;
File fl = null;
String filenames[]=new String[100];
File [] filename1;


TestBping9 tb8;

TestBping9()
{
f=new JFrame("BPing - A Multiple Ping Software");
l1=new Label("File name:");
l2=new Label("Total Up Frequency:");
l3=new Label("Total Down Frequency:");
l4=new Label("No.of times to Ping:");
l5=new Label("No. Hrs to Ping:");

t1=new TextField(23);
t2=new TextField(23);
t3=new TextField(23);
t4=new TextField(23);
t5=new TextField(2);

b=new JButton("Start Ping");
b1=new JButton("Reset");
b2=new JButton("Stop");
b8=new JButton("Save to DataBase");
b3=new JButton("Choose");
button1=new JButton("Select Time(Hrs.)");

ta=new TextArea();
String columnNames[] = { "Pump Name","Ip Address", "Up Frequency", "Down Frequency","Up Time","Down Time", "Ping Status" };
String dataValues[][]=new String[100][100];
ptable=new JTable(dataValues, columnNames);



try
{
c1=new Choice();
fl = new File("C:/Ping Files");

FilenameFilter fileNameFilter = new FilenameFilter() {
  public boolean accept(File dir, String name) {
       if(name.lastIndexOf('.')>0)
       {
          int lastIndex = name.lastIndexOf('.');
          
          String str = name.substring(lastIndex);
          
          if(str.equals(".txt"))
          {
             return true;
          }
       }
       return false;
    }
 };
 filename1= fl.listFiles(fileNameFilter);
 for(int i=0;i<filename1.length;i++)
	{
   filenames[i] = fl.list(fileNameFilter)[i];
	}

for(int i=0;i<filename1.length;i++)
	{
		System.out.println(filenames[i]);
		c1.add(filenames[i]);
	}

c2=new Choice();
for(int i=0;i<=10;i++)
{
	if(i==0)
	{
		c2.add("-----");
	}
	else
	{
		String time=Integer.toString(i);
		c2.add(time);
	}
}

	}catch(Exception e){
    // if any error occurs
    e.printStackTrace();
 }

topPanel = new JPanel();
topPanel.setLayout( new BorderLayout() );
scrollPane = new JScrollPane( ptable );
scrollPane.setPreferredSize(new Dimension(950,650));
topPanel.setSize(500,400);
f.setLayout(new FlowLayout());


f.add(c1);
f.add(b3);
f.add(l1);
f.add(t1);
f.add(l4);
f.add(t4);

f.add(c2);
f.add(button1);
f.add(l5);
f.add(t5);
//f.add(l2);
//f.add(t2);
//f.add(l3);
//f.add(t3);
f.add(b);
f.add(b1);
f.add(b2);
//f.add(ta);

f.add(b8);

f.add( topPanel );
topPanel.add( scrollPane, BorderLayout.CENTER );
t2.setEditable(false);
t3.setEditable(false);
ta.setEditable(false);
t5.setEditable(false);
ptable.setShowGrid(true);


b8.setEnabled(false);
b.addActionListener(this);
b1.addActionListener(this);
b2.addActionListener(this);
b8.addActionListener(this);
b3.addActionListener(this);
button1.addActionListener(this);



f.addWindowListener(this);
topPanel.setVisible(false);



f.setSize(1000,450);
f.setVisible(true);
}

public void actionPerformed(ActionEvent e)
{

try
{ 	
	t = new Thread(this);
	if(e.getSource()==b3)
	{
		String result1 = c1.getSelectedItem();
		//myTextField.setText("Your Choice: " + result);
		t1.setText(result1);
	}
	if(e.getSource()==button1)
	{
		String result2 = c2.getSelectedItem();
		if(result2.equals("-----"))
		{
			int em4=JOptionPane.ERROR_MESSAGE;
			JOptionPane.showMessageDialog(null, "Please select number of hours to ping.", "ERROR!!!", em4 );
		}
		if(!(result2.equals("-----")))
		{
		t5.setText(result2);
		t4.setText("200000000");
		t4.setEditable(false);
		}
	}
	if(e.getSource()==b)
	{
		if(t1.getText().equals(""))
		{
			int em1=JOptionPane.ERROR_MESSAGE;
			JOptionPane.showMessageDialog(null, "FILENAME cannot be EMPTY", "ERROR!!!", em1 );
			
		}
		if(t4.getText().equals(""))
		{
			int em2=JOptionPane.ERROR_MESSAGE;
			JOptionPane.showMessageDialog(null, "Ping count cannot be NULL", "ERROR!!!", em2 );
		}
		if(!(t4.getText().equals("200000000")) && !(t4.getText().equals("")))
		{
			t5.setText("--------------------");
			t5.setEditable(false);
			button1.setEnabled(false);
			c2.select(0);
		}
		if(t1.getText().equals("") && t5.getText().equals("") && t4.getText().equals(""))
		{
			button1.setEnabled(true);
		}
		if(t5.getText().equals("--------------------"))
		{
		ptime=0.00;
		}
		if(!(t5.getText().equals("--------------------"))&&!(t5.getText().equals("")))
		{
		String ptime1 =t5.getText();		
		Double ptime2=Double.parseDouble(ptime1);
		ptime=(ptime2*60);
		//System.out.println(ptime);
		}
		if(!(t1.getText().equals("")) && !(t4.getText().equals("")))
		{
		b4=true;
		b5=false;
		b8.setEnabled(false);
		b.setEnabled(false);
		topPanel.setVisible(true);

		String filename=t1.getText().toLowerCase();
		System.out.println(filename);
		//ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String filename2="C:/Ping Files/"+filename;
		InputStream file = new FileInputStream(filename2); //classLoader.getResourceAsStream(filename);
		BufferedReader br = new BufferedReader(new InputStreamReader(file));
		String line, line1;
		
		String s1=t4.getText();
		n=Integer.parseInt(s1);
		a=1;
		
		String title="BPing - "+filename;
		f.setTitle(title);
		
		while((line = br.readLine()) != null)
		{
			w[a]=line;
			a=a+1;
		}
	
		f.setSize(1200,700);
		a=a-1;
		System.out.println(a);
		
		String ipname[]=new String[100];
		
		for(int q=1;q<=a;q++)
		{	
			ipname[q] = w[q].replace(" ", "_");
		}
		
		for(int q=1;q<=a;q++)
		{
			System.out.println(ipname[q]);
		}
		
		for(int q=1;q<=a;q++)
		{	
			String[] splitline=ipname[q].split("\\s+");
			s[q]=splitline[1];
			ptable.setValueAt( splitline[0], q-1, 0);
			ptable.setValueAt( splitline[1], q-1, 1);
		}
		ptable.sizeColumnsToFit(0);
		ptable.sizeColumnsToFit(1);
		
		t.start();
				
		}
	}

	if(e.getSource()==b2)
	{	
		
		b4=false;
		b8.setEnabled(true);
		t.stop();
		for(int q=1;q<=a;q++)
		{
			h[q-1].stop();
		}
	}
	
	if(e.getSource()==b8)
	{
		if(t1.getText().equals(""))
		{
			int em1=JOptionPane.ERROR_MESSAGE;
			JOptionPane.showMessageDialog(null, "Cannot save to database. Select a file and ping please.", "ERROR!!!", em1 );
			b8.setEnabled(false);
		}
		else if(t4.getText().equals(""))
		{
			int em2=JOptionPane.ERROR_MESSAGE;
			JOptionPane.showMessageDialog(null, "Cannot save to database. Select a file and ping please.", "ERROR!!!", em2 );
			b8.setEnabled(false);
		}
		else if(b.isEnabled())
		{
			int em2=JOptionPane.ERROR_MESSAGE;
			JOptionPane.showMessageDialog(null, "Start the Pinging process first!", "ERROR!!!", em2 );
			b8.setEnabled(false);
		}
		else
		{
		String tname=t1.getText();
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy hh.mm aa");
		String d = f.format(date);
		String df = d.replace("/", "_");
		String df1 = df.replace(" ", "_");
		String df2 = df1.replace(".", "_");
		
		System.out.println(df2);
		String[] splitname= tname.split("\\.");
		
		String tbname = splitname[0]+"_"+df2;
		
		System.out.println(tbname);
		new TableCreate(tbname, ptable, a);
		}
	}

	if(e.getSource()==b1)
	{	
		b4=false;
		b5=true;
		topPanel.setVisible(false);
		f.setSize(1000,450);
		t1.setText("");
		t2.setText("");
		t3.setText("");
		t4.setText("");
		ta.setText("");
		t5.setText("");
		c2.select(0);
		f.setTitle("BPing - A Multiple Ping Software");
		t4.setEditable(true);
		t5.setEditable(false);
		b8.setEnabled(false);
		b.setEnabled(true);
		button1.setEnabled(true);
		 c1.removeAll();
		
			
			fl = new File("C:/Ping Files");

			FilenameFilter fileNameFilter = new FilenameFilter() {
			  public boolean accept(File dir, String name) {
			       if(name.lastIndexOf('.')>0)
			       {
			          int lastIndex = name.lastIndexOf('.');
			          
			          String str = name.substring(lastIndex);
			          
			          if(str.equals(".txt"))
			          {
			             return true;
			          }
			       }
			       return false;
			    }
			 };
			 filename1= fl.listFiles(fileNameFilter);
			 for(int i=0;i<filename1.length;i++)
				{
			   filenames[i] = fl.list(fileNameFilter)[i];
				}
			 System.out.println(filename1.length);
			
			for(int i=0;i<filename1.length;i++)
				{
					System.out.println(filenames[i]);
					c1.add(filenames[i]);
				}

			
			
		t.stop();
		
		for(int q=1;q<=a;q++)
		{
			h[q-1].stop();
		}
		for(int l=0;l<ptable.getRowCount();l++)
		{
		for(int m=0;m<(ptable.getColumnCount() - 1);m++)
			{
			ptable.setValueAt( "",l,m);
			}
		}
		for(int l=0;l<a;l++)
		{
			ptable.setValueAt(null,l,6);
		}
	}

}

catch(Exception ce)
{
	ce.printStackTrace();
}
}

public TestBping9(String address,int r, int num, int index, JTable tab, TextArea tarea, TextField t2field, TextField t3field, int fsize, TextField t1field, Choice c1field, String[] d) 
	{
			
		addr = address;
		row = r;		
		n = num;
		ind =index;
		ptable = tab;
		ta = tarea;
		t2 = t2field;
		t3 = t3field;
		a = fsize;
		t1 = t1field;
		c1 = c1field;
		for(int i=0;i<s.length;i++)
		{
		s[i] = d[i];
		}
	}


public void run() 
{
		
try
{			
	
			System.out.println("__");			
			/*for(int i=1;i<=a;i++)
			{
				System.out.println(s[i]);
			}*/
			System.out.println("__");
			
			/*if(b5==true)
			{
				b4=false;
				for(int l=0;l<ptable.getRowCount();l++)
				{
				for(int m=0;m<(ptable.getColumnCount() - 1);m++)
					{
					ptable.setValueAt( "",l,m);
					}
				}
				for(int l=0;l<ptable.getRowCount();l++)
				{
					ptable.setValueAt("0",l,6);
				}
				
			}*/
			
			if(b4==true) 
			{
				for(int q=1;q<=a;q++)
				{	
					addr = s[q];
					row =q-1;
					ind=ind+1;
					
					h[q-1]= new Thread(new Ping3(addr,row,n,ind,ptable,ta,t2,t3,ptime));
					
					TableColumn pstatusColumn = ptable.getColumn ("Ping Status");
					pstatusColumn.setCellRenderer(new Ping3(addr,row,n,ind,ptable,ta,t2,t3,ptime));
					
					h[q-1].start();
					
				}
				
				
			}
			while(true){
			int tcount=0;
			for(int q=1;q<=a;q++)
			{	
				if(h[q-1].isAlive()==false)
				{
					tcount=tcount+1;
				}
			}
			//System.out.println(tcount);
			if(b4==false)
			{	
				b8.setEnabled(true);
				break;
				
			}
			else if(tcount==a && b4==true)
			{
				int im2=JOptionPane.INFORMATION_MESSAGE;
				JOptionPane.showMessageDialog(null, "Pinging Completed.", "DONE!!!", im2 );
				b8.setEnabled(true);
				break;
			}
	}
			
			/*if(b4==false)
			{
				for(int q=1;q<=a;q++)
				{
					h[q-1].stop();
				}
								
			}*/
			/*if(b9==true)
			{
				//Create and Store Table as new table in SQL
			 

			}*/
			
}
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
}

	public void windowClosing(WindowEvent we)
	{
	System.exit(0);
	}

	public static void main(String args[]) 
	{
	new TestBping9();
	}
}
