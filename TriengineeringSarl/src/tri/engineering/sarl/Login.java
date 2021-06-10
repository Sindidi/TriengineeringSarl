package tri.engineering.sarl;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import tri.engineering.sarl.auto.ProduitAutoPanel;
import tri.engineering.sarl.dao.ConnexionMysql;
import tri.engineering.sarl.electric.ProduitElecPanel;
import tri.engineering.sarl.seeting.ParametrePanel;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JComboBox;

@SuppressWarnings("unused")
public class Login extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Image img_Logo = new ImageIcon(FrameDashBoard.class.getResource("/res/logo-tr-engineering2.png")).getImage().getScaledInstance(400, 90,Image.SCALE_SMOOTH);
	private Image img_user = new ImageIcon(FrameDashBoard.class.getResource("/res/icons8-user-80.png")).getImage().getScaledInstance(40, 40,Image.SCALE_SMOOTH);
	private Image img_pass = new ImageIcon(FrameDashBoard.class.getResource("/res/close_12890.png")).getImage().getScaledInstance(40, 40,Image.SCALE_SMOOTH);
	private Image img_btn = new ImageIcon(FrameDashBoard.class.getResource("/res/solicit_accept_check_ok_theaction_6340.png")).getImage().getScaledInstance(45, 45,Image.SCALE_SMOOTH);

	
	private JPanel contentPane;
	private JTextField tfUsername;
	private JPasswordField tfPassword;
	private JLabel lblMessage;
	
	Connection con = null;
	ResultSet rs = null;
	PreparedStatement  pst = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		con = ConnexionMysql.Connectedb();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(139, 69, 19));
		contentPane.setBorder(new LineBorder(new Color(210, 105, 30), 4, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setForeground(Color.WHITE);
		panel.setBounds(175, 139, 250, 44);
		contentPane.add(panel);
		panel.setLayout(null);
		
		tfUsername = new JTextField();
		tfUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(tfUsername.getText().equals("Username")) {
					tfUsername.setText("");
				}else {
					tfUsername.selectAll();
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(tfUsername.getText().equals(""))
					tfUsername.setText("Username");
			}
		});
		tfUsername.setBorder(null);
		tfUsername.setFont(new Font("Arial", Font.BOLD, 16));
		tfUsername.setText("Username");
		tfUsername.setBounds(10, 0, 167, 44);
		panel.add(tfUsername);
		tfUsername.setColumns(10);
		
		JLabel lblUser = new JLabel("");
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setBounds(181, 0, 59, 44);
		lblUser.setIcon(new ImageIcon(img_user));
		panel.add(lblUser);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setForeground(Color.WHITE);
		panel_1.setBounds(175, 205, 250, 44);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		tfPassword = new JPasswordField();
		tfPassword.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {
				if(tfPassword.getText().equals("Password")) {
					tfPassword.setEchoChar('●');
					tfPassword.setText("");
				}else {
					tfPassword.selectAll();
				}
			}
			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent e) {
				if(tfPassword.getText().equals(""))
					tfPassword.setEchoChar((char)0);
					tfPassword.setText("Password");		
			}
		});
		tfPassword.setBorder(null);
		tfPassword.setEchoChar((char)0);
		tfPassword.setFont(new Font("Arial", Font.BOLD, 16));
		tfPassword.setText("Password");
		tfPassword.setBounds(10, 0, 162, 44);
		panel_1.add(tfPassword);
		
		JLabel lblPassword = new JLabel("");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setIcon(new ImageIcon(img_pass));
		lblPassword.setBounds(182, 0, 58, 44);
		
		panel_1.add(lblPassword);
		
		JPanel panelBnt = new JPanel();
		panelBnt.setBorder(new LineBorder(new Color(139, 69, 19), 2, true));
		panelBnt.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				String tfU = tfUsername.getText();
				String tfP = tfPassword.getText();
				String sql = "select * from users where username=? and password=?";
				 
				
				try {
					pst = con.prepareStatement(sql);
					pst.setString(1, tfU);
					pst.setString(2, tfP);
					rs = pst.executeQuery();
					if(rs.next()) {
						
						String type = rs.getString("type");
						if(type.equals("Admin")) {
							Login.this.dispose();
							FrameDashBoard frameDashBoard = new FrameDashBoard();
							frameDashBoard.setVisible(true);
							
						} else if(type.equals("User")) {
							Login.this.dispose();
							FrameDashBoard frameDashBoard = new FrameDashBoard();
							frameDashBoard.setVisible(true);
							frameDashBoard.produitAutoPanel.btnModifier.setEnabled(false);
							frameDashBoard.produitAutoPanel.btnSupp.setEnabled(false);
							frameDashBoard.produitElecPanel.btnModifier.setEnabled(false);
							frameDashBoard.produitElecPanel.btnSupp.setEnabled(false);
							frameDashBoard.produitElecPanel.btnAjoute.setEnabled(false);
							frameDashBoard.produitAutoPanel.btnAjoute.setEnabled(false);
							frameDashBoard.panelParametre.setVisible(false);
							frameDashBoard.panelLogOut.setBounds(0, 304, 200, 50);
							
						}
					} else{
						JOptionPane.showMessageDialog(null, "Incorrect !");
						}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelBnt.setBackground(new Color(210, 90, 30));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelBnt.setBackground(new Color(210, 105, 30));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				panelBnt.setBackground(new Color(210, 55, 10));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				panelBnt.setBackground(new Color(210, 55, 30));
			}
		});
		panelBnt.setBackground(new Color(210, 105, 30));
		panelBnt.setBounds(175, 295, 250, 50);
		contentPane.add(panelBnt);
		panelBnt.setLayout(null);
		
		JLabel lblLogIn = new JLabel("Log In");
		lblLogIn.setForeground(Color.WHITE);
		lblLogIn.setFont(new Font("Arial", Font.BOLD, 14));
		lblLogIn.setBounds(60, 0, 110, 50);
		panelBnt.add(lblLogIn);
		
		JLabel lblBtn = new JLabel("");
		lblBtn.setHorizontalAlignment(SwingConstants.CENTER);
		lblBtn.setBounds(140, 0, 69, 50);
		lblBtn.setIcon(new ImageIcon(img_btn));
		panelBnt.add(lblBtn);
		
		
		JLabel lblExit = new JLabel("X");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment fermer cette application ?", "Confirmation", JOptionPane.YES_NO_OPTION) == 0){
					Login.this.dispose();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblExit.setForeground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblExit.setForeground(Color.WHITE);
			}
		});
		lblExit.setForeground(Color.WHITE);
		lblExit.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setBounds(570, 11, 20, 20);
		contentPane.add(lblExit);
		
		lblMessage = new JLabel("");
		lblMessage.setForeground(Color.WHITE);
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Arial", Font.BOLD, 15));
		lblMessage.setBounds(113, 260, 400, 14);
		contentPane.add(lblMessage);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(76, 16, 437, 123);
		lblLogo.setIcon(new ImageIcon(img_Logo));
		contentPane.add(lblLogo);
		setUndecorated(true);
		setLocationRelativeTo(null);
	}
}
