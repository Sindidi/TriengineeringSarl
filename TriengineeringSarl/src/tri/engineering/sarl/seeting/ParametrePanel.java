package tri.engineering.sarl.seeting;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.SwingConstants;

import tri.engineering.sarl.FrameDashBoard;
import tri.engineering.sarl.dao.ConnexionMysql;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;


import net.proteanit.sql.DbUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("unused")
public class ParametrePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */

	private Image img_user = new ImageIcon(FrameDashBoard.class.getResource("/res/icons8-user-80.png")).getImage().getScaledInstance(40, 40,Image.SCALE_SMOOTH);
	private Image img_pass = new ImageIcon(FrameDashBoard.class.getResource("/res/close_12890.png")).getImage().getScaledInstance(40, 40,Image.SCALE_SMOOTH);
	public Image img_para = new ImageIcon(FrameDashBoard.class.getResource("/res/apps_preferences_15748.png")).getImage().getScaledInstance(400, 300,Image.SCALE_SMOOTH);
	Image img_ajoute = new ImageIcon(FrameDashBoard.class.getResource("/res/edit_add_10261.png")).getImage().getScaledInstance(50, 35,Image.SCALE_SMOOTH);
	Image img_modifier = new ImageIcon(FrameDashBoard.class.getResource("/res/edit_icon-icons.com_52382.png")).getImage().getScaledInstance(45, 35,Image.SCALE_SMOOTH);
	Image img_supprimer = new ImageIcon(FrameDashBoard.class.getResource("/res/recycle-recycling-remove-trash_81361.png")).getImage().getScaledInstance(40, 32,Image.SCALE_SMOOTH);
	Image img_recherche = new ImageIcon(FrameDashBoard.class.getResource("/res/Search_find_magnifier_248.png")).getImage().getScaledInstance(40, 35,Image.SCALE_SMOOTH);
	Image img_actu = new ImageIcon(FrameDashBoard.class.getResource("/res/arrow_refresh_15732.png")).getImage().getScaledInstance(50, 35,Image.SCALE_SMOOTH);

	Connection con = null;
	ResultSet rs = null;
	PreparedStatement  pst = null;
	Statement ps;

	private JTextField tfuser;
	private JPasswordField password;
	private JTable table;

	@SuppressWarnings("rawtypes")
	public JComboBox comboBoxType;
	private JTextField tfId;

	public JButton btn_actu;

	public JButton btn_add;

	public JButton btn_del;

	public JButton btn_sup;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ParametrePanel() {
		con = ConnexionMysql.Connectedb();
		setBounds(0,0,1126,560);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(69, 26, 1047, 504);
		add(panel);
		panel.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(551, 71, 295, 294);
		panel.add(panel_2);
		panel_2.setLayout(null);

		btn_del = new JButton("");
		btn_del.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				int ligne = table.getSelectedRow();
				while(ligne == -1) {
					JOptionPane.showMessageDialog(null, "S??lectionner une ligne");
					return;
				}
				String ids = tfId.getText();
				String sql = "UPDATE `users` SET `username`=?,`password`=?,`type`=? WHERE id = '"+ids+"'";
				try {
					pst = con.prepareStatement(sql);
					pst.setString(1, tfuser.getText().toString());
					pst.setString(2, password.getText().toString());
					pst.setString(3, comboBoxType.getSelectedItem().toString());
					pst.execute();
					JOptionPane.showMessageDialog(null, "Utilisateur modifi??");
					updateTable();
					tfuser.setText(null);
					password.setText(null);
					comboBoxType.setSelectedItem("Admin");
					tfId.setText(null);
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btn_del.setBounds(151, 214, 62, 47);
		btn_del.setIcon(new ImageIcon(img_modifier));
		panel_2.add(btn_del);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(24, 141, 246, 44);
		panel_2.add(panel_1_1);
		panel_1_1.setLayout(null);
		panel_1_1.setForeground(Color.WHITE);
		panel_1_1.setBackground(Color.WHITE);

		password = new JPasswordField();
		password.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {
				if(password.getText().equals("Password")) {
					password.setEchoChar('???');
					password.setText("");
				}else {
					password.selectAll();
				}
			}
			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent e) {
				if(password.getText().equals(""))
					password.setEchoChar((char)0);
				//password.setText("Password");		
			}
		});
		password.setFont(new Font("Arial", Font.BOLD, 16));
		password.setEchoChar(' ');
		password.setBorder(null);
		password.setBounds(10, 0, 189, 44);
		panel_1_1.add(password);

		JLabel lblPassword = new JLabel("");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setIcon(new ImageIcon(img_pass));
		lblPassword.setBounds(192, 0, 58, 44);
		panel_1_1.add(lblPassword);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(20, 75, 250, 44);
		panel_2.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setForeground(Color.WHITE);
		panel_1.setBackground(Color.WHITE);

		tfuser = new JTextField();
		tfuser = new JTextField();
		tfuser.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(tfuser.getText().equals("Username")) {
					tfuser.setText("");
				}else {
					tfuser.selectAll();
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(tfuser.getText().equals(""))
					tfuser.setText("Username");
			}
		});
		tfuser.setFont(new Font("Arial", Font.BOLD, 16));
		tfuser.setColumns(10);
		tfuser.setBorder(null);
		tfuser.setBounds(10, 0, 185, 44);
		panel_1.add(tfuser);

		JLabel lblUser = new JLabel("");
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setIcon(new ImageIcon(img_user));
		lblUser.setBounds(191, 0, 59, 44);
		panel_1.add(lblUser);

		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBounds(20, 11, 250, 44);
		panel_2.add(panel_1_1_1);
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setForeground(Color.WHITE);
		panel_1_1_1.setBackground(Color.WHITE);

		comboBoxType = new JComboBox();
		comboBoxType.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBoxType.setBackground(Color.WHITE);
		comboBoxType.setBounds(0, 0, 199, 44);
		comboBoxType.addItem("Admin");
		comboBoxType.addItem("User");
		panel_1_1_1.add(comboBoxType);

		JLabel lblPassword_2 = new JLabel("");
		lblPassword_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword_2.setIcon(new ImageIcon(img_user));
		lblPassword_2.setBounds(192, 0, 58, 44);
		panel_1_1_1.add(lblPassword_2);

		btn_add = new JButton("");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user = tfuser.getText();
				@SuppressWarnings("deprecation")
				String pass = password.getText();
				String type = comboBoxType.getSelectedItem().toString();
				if(!user.equals("") &&  !type.equals("") && !pass.equals("")) {
					String sql = "INSERT INTO `users` (`id`, `username`, `password`, `type`) VALUES (NULL,? ,? ,?)";
					try {
						pst = con.prepareStatement(sql);
						pst.setString(1, user);
						pst.setString(2, pass);
						pst.setString(3,type);
						if(pst.executeUpdate()>0) {
							JOptionPane.showMessageDialog(null, "Nouvel utilisateur ajout??");
							updateTable();
							tfuser.setText(null);
							comboBoxType.setSelectedItem(null); 
							password.setText(null);
							
				    }
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}else {
			    	JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs svp. !!!");
			    }


			}
		});
		btn_add.setBounds(79, 214, 62, 47);
		btn_add.setIcon(new ImageIcon(img_ajoute));
		panel_2.add(btn_add);
		
		btn_sup = new JButton("");
		btn_sup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ligne = table.getSelectedRow();
				while(ligne == -1) {
					JOptionPane.showMessageDialog(null, "S??lectionner un produit");
					return;
				}
				String id = table.getModel().getValueAt(ligne, 0).toString();
				String sql = "DELETE FROM `users` WHERE `id` = '"+id+"'";
				try {
					pst = con.prepareStatement(sql);
					if(JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer cette utilisateur ?", "Supprimer produit", JOptionPane.YES_NO_OPTION) == 0){
						pst.execute();
						updateTable();
						tfuser.setText(null);
						password.setText(null);
						comboBoxType.setSelectedItem(null);
						tfId.setText(null);
						comboBoxType.setSelectedItem("Admin");
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});

		btn_sup.setIcon(new ImageIcon(img_supprimer));
		btn_sup.setBounds(223, 214, 62, 47);
		panel_2.add(btn_sup);
		
		tfId = new JTextField();
		tfId.setBounds(24, 272, 96, 20);
		panel_2.add(tfId);
		tfId.setColumns(10);
		
		btn_actu = new JButton("");
		btn_actu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();
				tfuser.setText(null);
				comboBoxType.setSelectedItem("Admin"); 
				password.setText(null);
			}
		});
		btn_actu.setIcon(new ImageIcon(img_actu));
		btn_actu.setBounds(10, 214, 59, 47);
		panel_2.add(btn_actu);
		tfId.setVisible(false);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 71, 425, 294);
		scrollPane.setBackground(Color.WHITE);
		panel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ligne = table.getSelectedRow();
				String a0 = table.getModel().getValueAt(ligne, 0).toString();
				String a1 = table.getModel().getValueAt(ligne, 1).toString();
				String a2 = table.getModel().getValueAt(ligne, 2).toString();
				String a3 = table.getModel().getValueAt(ligne, 3).toString();
				
				tfuser.setText(a1);
				password.setText(a2);
				comboBoxType.setSelectedItem(a3);
				tfId.setText(a0);
			}
		});
		table.setBackground(getBackground());
		table.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		table.setRowHeight(30);
		table.setAutoCreateRowSorter(true);
		table.setSelectionForeground(Color.white);
		table.setSelectionBackground(new Color(205,133,63));
		table.getTableHeader().setBackground(Color.black);
		table.getTableHeader().setForeground(Color.white);
		table.setFont(new Font("Tahoma",Font.PLAIN,17));
		
		
		scrollPane.setViewportView(table);

	}
	public void updateTable() {

		String sql = "select * from users";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
