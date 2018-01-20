import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import javax.swing.*;
import javax.swing.table.*;

import java.util.concurrent.*;
import java.text.DecimalFormat;

import javax.swing.table.DefaultTableCellRenderer;

class Ping3 extends DefaultTableCellRenderer implements Runnable 
{
	
	Ping3 p3;
JProgressBar progressBar = new JProgressBar();

JFrame f;
TextField t1,t2,t3,t4;
Label l1,l2,l3,l4;
Button b,b1,b2;
TextArea ta;
boolean b4,b5;
JTable	table;
JScrollPane scrollPane;
JPanel	topPanel;
private String addr;
private int row, n, a;
private int ind=0, uc=2,dc=3,utc=4,dtc=5,pbc=6,up,dwn;
private String s[]=new String[100];
private boolean b6,b7;
private double ptime;
//Thread[] jp = new Thread[100];

//int cnt=-1;
//int uu,dd;

public Ping3(String address,int r, int num, int index, JTable tab, TextArea tarea, TextField t2field, TextField t3field, Double pingtime)
{			
			addr = address;
			row = r;		
			n = num;
			ind =index;
			table = tab;
			ta = tarea;
			t2 = t2field;
			t3 = t3field;
			ptime = pingtime;
			
			  setOpaque(true);
			  //progressBar = new JProgressBar ();
		      progressBar.setMaximum (n);
		      progressBar.setStringPainted (true);
		      progressBar.setBorder(null);     
		      progressBar.setBorderPainted (true);
		      progressBar.setBorder (BorderFactory.createEmptyBorder (1, 1, 1, 1)); 
		      
			
}


public void run()
{
			
		try
		{	
			//System.out.println(ptime);
			table.setAutoResizeMode(4);
			//Thread[] pinging= new Thread[n];
			//pinging[0]=new Thread();
			//System.out.println(b4+" b4");
			String IP=addr;
			System.out.println("__"+IP);
			InetAddress inet = InetAddress.getByName(IP);
			//int up=0,dwn=0;
			String up1,dwn1,uptimes,dwntimes,totaltimes,cnt;
			double uptime=0 ,dwntime=0,tt=0;
    		System.out.println("Sending Ping Request to " + IP);
			
    		table.setValueAt( "0", row, 2 );
    		table.setValueAt( "0", row, 3 );
    		
    		table.setValueAt( "0", row, 4 );
    		table.setValueAt( "0", row, 5);
    		table.setValueAt( "0", row, 6);
    		
			TableColumn pstatusColumn = table.getColumn ("Ping Status");
			TableColumn gcolourColumn = table.getColumn("Up Frequency");
			TableColumn rcolourColumn = table.getColumn("Down Frequency");

			//pstatusColumn.setCellRenderer(new ProgressBarRenderer(n));
			//double tt=0;
			DecimalFormat twoDForm = new DecimalFormat("#.000");
			long startime = System.currentTimeMillis();
			
				System.out.println(n);
			long arry[]=new long[n];
			
			
			for(int q=1;q<=n;q++)
			{	
				long starttime = System.currentTimeMillis();
				
				if(inet.isReachable(3210))
				{
							
					long currenttime1 = System.currentTimeMillis();
					up=up+1;
					//System.out.println("Index "+(ind)+" Up Packet Number "+(up)+" for "+IP);
					
										
					long elapsedtime1 = currenttime1-starttime;
					//long minutes1 = TimeUnit.MILLISECONDS.toMinutes(elapsedtime1);
					//System.out.println(minutes1);
					uptime = uptime + (((double) elapsedtime1 ));
					double uptime1 = (uptime/1000)/60;
					
					
					//double uptime2 = twoDForm.format(uptime1);
					ta.append(IP+" Up Packet Number "+(up)+"\n");
					up1=Integer.toString(up);
					t2.setText(up1);
					// System.out.println(row+" "+uc+" "+up1);	
					table.setValueAt( up1, row, uc);

					uptimes = twoDForm.format(uptime1);//Double.toString(uptime1);
					table.setValueAt( uptimes, row, utc);
					
					table.setValueAt( up1 , row, pbc);
					//if(uptimes.equals(1.000)||uptime1>1.000)
						//break;
					arry[q-1]=1;
					
					b6=true;
					b7=false;
					
					//int cnt=q;
					//pstatusColumn.setCellRenderer(new ProgressBarRenderer(n, b6, b7, 1, 1));
					//pinging[q-1] = new Thread(new ProgressBarTable1(table,n,b6,b7,g1,g2,q,row));
					//pinging[q-1].start();
					/*pstatusColumn.setCellRenderer(new ProgressBarRenderer(n, b6, b7, up, dwn));
					cnt=Integer.toString(q);
					table.setValueAt( cnt , row, pbc);*/
					//pinging[q-1].stop();
					//System.out.println(g1+" "+g2);
					
					
				}
				else
				{
						
					long currenttime2 = System.currentTimeMillis();		
					dwn=dwn+1;
					//System.out.println("Index "+(ind)+" Down Packet Number "+(dwn)+" for "+IP);

										
					long elapsedtime2 = currenttime2-starttime;
					//long minutes2 = TimeUnit.MILLISECONDS.toMinutes(elapsedtime2);
					dwntime = dwntime + (((double) elapsedtime2 ));
					double dwntime1= (dwntime/1000)/60;
					
					ta.append(IP+" Down Packet Number "+(dwn)+"\n");
					dwn1=Integer.toString(dwn);
					t3.setText(dwn1);
					//System.out.println(row+" "+dc+" "+dwn1);
					table.setValueAt( dwn1, row, dc);

					dwntimes = twoDForm.format(dwntime1);//Double.toString(dwntime1);
					table.setValueAt( dwntimes, row, dtc);
					
					table.setValueAt( dwn1 , row, pbc);
					//if(dwntimes.equals(1.000)||dwntime1>1.000)
						//break;
					
					arry[q-1]=0;
					
					b7=true;
					b6=false;
					
					//int cnt=q;
					//pstatusColumn.setCellRenderer(new ProgressBarRenderer(n, b6, b7, 2, 2));
					//pinging[q-1]=new Thread(new ProgressBarTable1(table,n,b6,b7,r1,r2,q,row));
					//pinging[q-1].start();
					/*pstatusColumn.setCellRenderer(new ProgressBarRenderer1(n, b6, b7, up, dwn));
					cnt=Integer.toString(q);
					table.setValueAt( cnt , row, pbc);*/
					//pinging[q-1].stop();
					//System.out.println(r1+" "+r2);
			
				
				//System.out.println(b6+"    "+b7);
					
				}
				Object outc=table.getValueAt(row, utc);
				Object odtc=table.getValueAt(row, dtc);
				
				String utc1=outc.toString();
				String dtc1=odtc.toString();
				
				double utc2= Double.parseDouble(utc1);
				double dtc2= Double.parseDouble(dtc1);
				tt=  utc2 + dtc2;
				//double tt1 = (tt/1000)/60;
				if(ptime!=0.00)
				{
				if(tt>ptime)
				{
				//System.out.println(tt);
					break;
				}
				if(tt==ptime)
				{
				//System.out.println(tt);
					break;
				}
				}
				
					/*gcolourColumn.setCellRenderer(new ColourRenderer(up,dwn));
					rcolourColumn.setCellRenderer(new ColourRenderer(up,dwn));*/
				
				//pstatusColumn.setCellRenderer(new ProgressBarRenderer(n, b6, b7, up, dwn));
				/*if(up>dwn)
				{
					progressBar.setForeground(Color.green);
				}
				if(up<dwn)
				{
					progressBar.setForeground(Color.red);
				}
				if(up==dwn)
				{
					progressBar.setForeground(Color.yellow);
				}
				else{
					progressBar.setForeground(Color.blue);
				}
				*/
				//cnt=Integer.toString(q);
				//table.setValueAt( cnt , row, pbc);
				
					System.out.print(arry[q-1]);
				
				
			}
			
		}
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}


}
public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
{
  super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
  setText(null);
  
  if(value!=null){
  String val = value.toString();
	 
	int i=Integer.parseInt(val);
	//JProgressBar pbar= p3.getcolour();
	/*if(up!=dwn){
	if(i!=dwn){
		progressBar.setForeground(Color.green);
		progressBar.setValue(i);
			return progressBar;
  	}
	
	if(i!=up){
		progressBar.setForeground(Color.red);
		progressBar.setValue(i);
			return progressBar;
  	}
	}
  if(up==dwn)
  {
		progressBar.setForeground(Color.yellow);
		progressBar.setValue(i);
			return progressBar;
  }
  else{
  	progressBar.setForeground(Color.blue);
  	progressBar.setValue(i);
  	return progressBar;
  }*/
	int uu2,dd2;
	Object uu=table.getValueAt(row, uc);
	String uu1=uu.toString();
	if(uu1.equals(""))
	{
		uu2=0;
	}
	else
	{
		uu2=Integer.parseInt(uu1);
	}
	
	Object dd=table.getValueAt(row, dc);
	String dd1=dd.toString();
	if(dd1.equals(""))
	{
		dd2=0;
	}
	else
	{
		dd2=Integer.parseInt(dd1);
	}
		
//if(dd2!=uu2)
//{
	if(i==uu2)
	{
		progressBar.setBackground(Color.green);
		progressBar.setForeground(Color.green);
	}
	if(i==dd2)
	{
		progressBar.setBackground(Color.red);
		progressBar.setForeground(Color.red);
	}
//}
	if(uu2==dd2)
	{
		if(uu2==0 && dd2==0)
		progressBar.setBackground(Color.white);
		else{
		progressBar.setBackground(Color.yellow);
		progressBar.setForeground(Color.yellow);
		}
	}
	//else{
		//progressBar.setForeground(Color.blue);
	//}
	//progressBar.setForeground(null);
	int total= uu2 + dd2;
	String s = Integer.toString(total);
	progressBar.setString(s);
	progressBar.setValue(total);
	//return progressBar;
	return progressBar;
  }	
  else
	  return null;
}
	
}

