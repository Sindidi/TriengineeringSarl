package tri.engineering.sarl;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.SwingConstants;


import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.plaf.synth.SynthOptionPaneUI;

import tri.engineering.sarl.auto.ProduitAutoPanel;
import tri.engineering.sarl.dao.ConnexionMysql;




public class HomePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	Connection con = null;
	ResultSet rs = null;
	PreparedStatement  pst = null;
	
	Image img_accept = new ImageIcon(FrameDashBoard.class.getResource("/res/solicit_accept_check_ok_theaction_6340.png")).getImage().getScaledInstance(40, 45,Image.SCALE_SMOOTH);
	Image img_ligne1 = new ImageIcon(FrameDashBoard.class.getResource("/res/icons8-ligne-horizontale-96.png")).getImage().getScaledInstance(700, 80,Image.SCALE_SMOOTH);
	Image img_ligne2 = new ImageIcon(FrameDashBoard.class.getResource("/res/icons8-ligne-horizontale-96 (1).png")).getImage().getScaledInstance(700, 80,Image.SCALE_SMOOTH);
	Image img_panier = new ImageIcon(FrameDashBoard.class.getResource("/res/icons8-graphique-combiné-96.png")).getImage().getScaledInstance(100, 95,Image.SCALE_SMOOTH);
	Image img_ajoute = new ImageIcon(FrameDashBoard.class.getResource("/res/edit_add_10261.png")).getImage().getScaledInstance(70, 50,Image.SCALE_SMOOTH);
	Image img_vue = new ImageIcon(FrameDashBoard.class.getResource("/res/show_icon_153436.png")).getImage().getScaledInstance(48, 48,Image.SCALE_SMOOTH);

	Image img1 = new ImageIcon(FrameDashBoard.class.getResource("/res/auto-mechanic-using-measuring-equipment-tool-checking-car-battery_101448-1532.jpg")).getImage().getScaledInstance(770, 484,Image.SCALE_SMOOTH);
	
	public JLabel lblCunt;

	public JLabel lblVue_1;

	public JLabel lblCunt_1;

	public JLabel lblClock;

	public JLabel lblClock_1;
	
	public void clock() {
		
		Thread clock = new Thread() {
			public void run() {
				try {
					for(;;) {
					Calendar cal = new GregorianCalendar();
					int day = cal.get(Calendar.DAY_OF_MONTH);
					int month = cal.get(Calendar.MONTH);
					int year = cal.get(Calendar.YEAR);
					
					int second = cal.get(Calendar.SECOND);
					int minute = cal.get(Calendar.MINUTE);
					int hour = cal.get(Calendar.HOUR);
					lblClock.setText(""+hour+":"+minute+":"+second);
					lblClock_1.setText(""+day+"/"+month+"/"+year);
					sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		clock.start();
	}
	
	public HomePanel() {
		initialize();
		clock();
		getId();
		getIdd();
	}
	
	public void initialize() {
		con = ConnexionMysql.Connectedb();
		setBackground(new Color(112, 128, 133));
		setBounds(0,0,1126,560);
		setLayout(null);
		setVisible(true);

		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(10, 11, 1106, 538);
		add(panel_1);
		panel_1.setLayout(null);
		
		lblClock = new JLabel("");
		lblClock.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblClock.setHorizontalAlignment(SwingConstants.CENTER);
		lblClock.setBounds(995, 11, 111, 31);
		panel_1.add(lblClock);
		
		JPanel panelHome = new JPanel();
		panelHome.setBorder(new LineBorder(new Color(0, 0, 204), 2, true));
		panelHome.setBackground(new Color(255, 255, 255));
		panelHome.setBounds(10, 11, 288, 224);
		panel_1.add(panelHome);
		panelHome.setLayout(null);
		
		lblCunt = new JLabel("");
		lblCunt.setForeground(new Color(0, 0, 204));
		lblCunt.setHorizontalAlignment(SwingConstants.CENTER);
		lblCunt.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 37));
		lblCunt.setBackground(new Color(0, 0, 204));
		lblCunt.setBounds(55, 132, 164, 88);
		panelHome.add(lblCunt);
		
		JLabel lblVue = new JLabel("");
		lblVue.setHorizontalAlignment(SwingConstants.CENTER);
		lblVue.setIcon(new ImageIcon(img_ajoute));
		lblVue.setBounds(214, 153, 74, 64);
		panelHome.add(lblVue);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(img_ligne1));
		lblNewLabel.setBounds(55, 121, 170, 10);
		panelHome.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(img_panier));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(55, 0, 187, 97);
		panelHome.add(lblNewLabel_1);
		
		JLabel lblStokAutomobile = new JLabel("Stock en Automobile");
		lblStokAutomobile.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStokAutomobile.setForeground(new Color(0, 0, 204));
		lblStokAutomobile.setHorizontalAlignment(SwingConstants.CENTER);
		lblStokAutomobile.setBounds(39, 96, 199, 25);
		panelHome.add(lblStokAutomobile);
		
		JPanel panelHome_1 = new JPanel();
		panelHome_1.setLayout(null);
		panelHome_1.setBorder(new LineBorder(new Color(0, 0, 204), 2, true));
		panelHome_1.setBackground(Color.WHITE);
		panelHome_1.setBounds(10, 260, 288, 235);
		panel_1.add(panelHome_1);
		
		lblCunt_1 = new JLabel("");
		lblCunt_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCunt_1.setForeground(new Color(0, 153, 51));
		lblCunt_1.setFont(new Font("Tahoma", Font.BOLD, 45));
		lblCunt_1.setBackground(new Color(0, 153, 51));
		lblCunt_1.setBounds(67, 147, 154, 88);
		panelHome_1.add(lblCunt_1);
		
		lblVue_1 = new JLabel("");
		lblVue_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblVue_1.setIcon(new ImageIcon(img_ajoute));
		lblVue_1.setBounds(214, 171, 74, 64);
		panelHome_1.add(lblVue_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon(img_ligne2));
		lblNewLabel_2.setBounds(50, 126, 173, 10);
		panelHome_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setIcon(new ImageIcon(img_panier));
		lblNewLabel_1_1.setBounds(50, 0, 187, 97);
		panelHome_1.add(lblNewLabel_1_1);
		
		JLabel lblStokAutomobile_1 = new JLabel("Stock en Electrique");
		lblStokAutomobile_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblStokAutomobile_1.setForeground(new Color(0, 153, 51));
		lblStokAutomobile_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStokAutomobile_1.setBounds(28, 98, 232, 25);
		panelHome_1.add(lblStokAutomobile_1);
		
		lblClock_1 = new JLabel("");
		lblClock_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblClock_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblClock_1.setBounds(1005, 35, 91, 21);
		panel_1.add(lblClock_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(img1));
		lblNewLabel_3.setBounds(326, 11, 770, 484);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Version 1.0.0");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_4.setForeground(new Color(0, 0, 0));
		lblNewLabel_4.setBounds(999, 513, 97, 14);
		panel_1.add(lblNewLabel_4);
		
	}
	public void getId() {
		String sql = "select count(*) as id from automobile";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				lblCunt.setText(rs.getString("Id"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void getIdd() {
		String sql = "select count(*) as id from elecrique";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				lblCunt_1.setText(rs.getString("id"));
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
