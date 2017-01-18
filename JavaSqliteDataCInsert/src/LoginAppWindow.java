import java.sql.*;
import javax.swing.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LoginAppWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginAppWindow window = new LoginAppWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	Connection connection=null;
	private JTextField textFieldUserName;
	private JPasswordField passwordField;
	public LoginAppWindow() {
		initialize();
		connection=sqlConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setBounds(162, 28, 86, 20);
		frame.getContentPane().add(textFieldUserName);
		textFieldUserName.setColumns(10);
		
		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setBounds(77, 31, 75, 14);
		frame.getContentPane().add(lblUsername);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(162, 59, 86, 20);
		frame.getContentPane().add(passwordField);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(77, 72, 61, 14);
		frame.getContentPane().add(lblPassword);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query="select * from Person where UserName=? and Password=?";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1,textFieldUserName.getText());
					pst.setString(2,	passwordField.getText());
					ResultSet rs=pst.executeQuery();
					
					
					  int count=0;
					   while(rs.next())
					   {
						   count=count+1;
					   }
					   
					     if(count==1)
					     {
					    	 JOptionPane.showMessageDialog(null, "correct process");
					    	 frame.dispose();
					    	 PersonInfo pinfo=new PersonInfo();
					    	 pinfo.setVisible(true);
					     }
					     else if(count>1)
					     {
					    	 JOptionPane.showMessageDialog(null, "Duplicate username and password");
					     }
					     else
					     {
					    	 JOptionPane.showMessageDialog(null, "is not correct process");
					     }
					     rs.close();
					     pst.close();
					
				}
				catch(Exception e)
				{
					 JOptionPane.showMessageDialog(null, e);
				}
			
			}
		});
		btnNewButton.setBounds(173, 122, 75, 44);
		frame.getContentPane().add(btnNewButton);
	}
}
