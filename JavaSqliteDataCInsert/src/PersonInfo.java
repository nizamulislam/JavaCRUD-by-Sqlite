import java.sql.*;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.sql.Connection;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class PersonInfo extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBoxName;
	private JList listName;
	private JComboBox comboBoxSelection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonInfo frame = new PersonInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	Connection connection=null;
	private JTable table;
	private JTextField textFieldId;
	private JTextField textFieldAge;
	private JTextField textFieldName;
	private JPasswordField passwordField;
	private JTextField textFieldSearch;
	
	
	public void refreshTable()
	{
		try{
			String query="select * from Person";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void fillComboBox()
	{
		try{
			String query="select * from Person";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				comboBoxName.addItem(rs.getString("UserName"));
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	
	  public void loadJlist()
	  {
			try{
				String query="select * from Person";
				PreparedStatement pst=connection.prepareStatement(query);
				ResultSet rs=pst.executeQuery();
				DefaultListModel DLM=new DefaultListModel();
				
				while(rs.next())
				{
					DLM.addElement(rs.getString("UserName"));
				}
				listName.setModel(DLM);
				pst.close();
				rs.close();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	  }
	
	
	
	
	
	
	public PersonInfo() {
		
		connection=sqlConnection.dbConnector();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 423);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("New");
		mnNewMenu.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Java Project\r\n");
		mnNewMenu_1.add(mntmNewMenuItem);
		
		JMenuItem menuItem = new JMenuItem("New menu item");
		mnNewMenu_1.add(menuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("OpenFile");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("SeparateItem");
		mnNewMenu.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnPersoninfodata = new JButton("PersonInfoData");
		btnPersoninfodata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query="select * from Person";
					PreparedStatement pst=connection.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}
		});
		btnPersoninfodata.setBounds(250, 129, 136, 23);
		contentPane.add(btnPersoninfodata);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(310, 161, 164, 125);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				
				try{
					int row=table.getSelectedRow();
					String ID=(table.getModel().getValueAt(row, 0)).toString();
					String query="select * from Person where Id='"+ID+"' ";
					PreparedStatement pst=connection.prepareStatement(query);
					
					
					ResultSet rs=pst.executeQuery();
				      
					   while(rs.next())
					   {
						   textFieldId.setText(rs.getString("Id"));
						   textFieldAge.setText(rs.getString("Age"));
						   textFieldName.setText(rs.getString("UserName"));
						   passwordField.setText(rs.getString("Password"));
					   
					   }
					
				
				
				
					
					pst.close();
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(90, 171, 86, 20);
		contentPane.add(textFieldId);
		textFieldId.setColumns(10);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(76, 174, 46, 14);
		contentPane.add(lblId);
		
		textFieldAge = new JTextField();
		textFieldAge.setBounds(90, 202, 86, 20);
		contentPane.add(textFieldAge);
		textFieldAge.setColumns(10);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(60, 202, 63, 32);
		contentPane.add(lblAge);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(90, 235, 86, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(60, 238, 46, 14);
		contentPane.add(lblName);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(90, 266, 86, 20);
		contentPane.add(passwordField);
		
		JLabel lblPass = new JLabel("Pass");
		lblPass.setBounds(60, 266, 46, 14);
		contentPane.add(lblPass);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query="insert into Person (Id,Age,UserName,Password) values (?,?,?,?)";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1, textFieldId.getText());
					pst.setString(2, textFieldAge.getText());
					pst.setString(3, textFieldName.getText());
					pst.setString(4, passwordField.getText());
					
				
					pst.execute();
					JOptionPane.showMessageDialog(null, "Datasaved successfully");
					
					pst.close();
					//rs.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				refreshTable();
			}
		});
		btnSave.setBounds(89, 297, 77, 23);
		contentPane.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query="update Person set Id='"+textFieldId.getText()+"', Age='"+textFieldAge.getText()+"', UserName='"+textFieldName.getText()+"' ,Password='"+passwordField.getText()+"' where Id='"+ textFieldId.getText()+"' ";
					PreparedStatement pst=connection.prepareStatement(query);
				
					
				
					pst.execute();
					JOptionPane.showMessageDialog(null, "Updated successfully");
					
					pst.close();
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				refreshTable();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUpdate.setBounds(77, 331, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int action=JOptionPane.showConfirmDialog(null, "Do You want to Delete Item","delete",JOptionPane.YES_NO_CANCEL_OPTION);
		if(action==0){		 
				try{
					String query="delete from Person where Id='"+textFieldId.getText()+"' ";
					PreparedStatement pst=connection.prepareStatement(query);
				
					
				
					pst.execute();
					JOptionPane.showMessageDialog(null, "Delete successfully");
					
					pst.close();
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				refreshTable();
		     }		
	   }
	});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDelete.setBounds(77, 128, 89, 23);
		contentPane.add(btnDelete);
		
		comboBoxName = new JComboBox();
		comboBoxName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query="select * from Person where UserName=? ";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1,(String)comboBoxName.getSelectedItem());
					
					ResultSet rs=pst.executeQuery();
				      
					   while(rs.next())
					   {
						   textFieldId.setText(rs.getString("Id"));
						   textFieldAge.setText(rs.getString("Age"));
						   textFieldName.setText(rs.getString("UserName"));
						   passwordField.setText(rs.getString("Password"));
					   
					   }
					
				
				
				
					
					pst.close();
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}
		});
		comboBoxName.setBounds(149, 115, 91, 20);
		contentPane.add(comboBoxName);
		
		 listName = new JList();
		listName.setBounds(222, 171, 63, 149);
		contentPane.add(listName);
		
		JButton btnJList = new JButton("JLIST");
		btnJList.setBounds(214, 332, 89, 23);
		contentPane.add(btnJList);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				try{
					String selection=(String)comboBoxSelection.getSelectedItem();
					
					String query="select * from Person where "+selection+"=? ";
					System.out.println(query);
					
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1,textFieldSearch.getText());
					
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				      
					
				
				
				
					
					pst.close();
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				
			}
		});
		textFieldSearch.setBounds(398, 319, 86, 20);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		 comboBoxSelection = new JComboBox();
		comboBoxSelection.setModel(new DefaultComboBoxModel(new String[] {"Id", "Age", "UserName", "Password"}));
		comboBoxSelection.setBounds(310, 319, 76, 35);
		contentPane.add(comboBoxSelection);
		refreshTable();
		fillComboBox();
		loadJlist();
		
	}
}
