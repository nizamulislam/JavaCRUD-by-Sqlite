import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractListModel;
import java.awt.Font;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class HelloworldClass extends JFrame {

	private JPanel contentPane;
	private JLabel lblclock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelloworldClass frame = new HelloworldClass();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	   public void clock()
	   {
		    Thread clock=new Thread()
		    {
		    	public void run()
		    	{
		    		try {
			    			  for(;;){
			    			 Calendar cal=new GregorianCalendar();
			    			 int day=cal.get(Calendar.DAY_OF_MONTH);
			    			 int month=cal.get(Calendar.MONTH);
			    			 int year=cal.get(Calendar.YEAR);
			    			 
			    			 
			    			 int second=cal.get(Calendar.SECOND);
			    			 int minute=cal.get(Calendar.MINUTE);
			    			 int hour=cal.get(Calendar.HOUR);
			    			 
			    			 lblclock.setText("Time "+hour+":"+minute+":"+second+"  Date"+year+"/"+month+"/"+day);
			    			 
							sleep(1000);
			    			  }
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	}
		    };
		
		 clock.start();
		 
	   }

	/**
	 * Create the frame.
	 */
	public HelloworldClass()
	{
		initialize();
		clock();
	}
	
	
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 526, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(80, 11, 65, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(24, 15, 46, 14);
		contentPane.add(lblNewLabel);
		
		final JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Terbina", "Moklas"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(303, 14, 182, 94);
		contentPane.add(list);
		
		JButton btnNewButton_1 = new JButton("Load value");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultListModel DLM=new DefaultListModel();
				DLM.addElement("Mark");
				DLM.addElement("July");
				DLM.addElement("John");
				DLM.addElement("Markas");
				DLM.addElement("Peter");
				list.setModel(DLM);
				
				
			}
		});
		btnNewButton_1.setBounds(39, 83, 89, 23);
		contentPane.add(btnNewButton_1);
		
		
		
		 lblclock = new JLabel("Clock");
		lblclock.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblclock.setBounds(54, 158, 348, 130);
		contentPane.add(lblclock);
	}
}
