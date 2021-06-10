package tri.engineering.sarl;


import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;

import tri.engineering.sarl.auto.AjouteAuto;
import tri.engineering.sarl.auto.ModifierAuto;
import tri.engineering.sarl.auto.ProduitAutoPanel;
import tri.engineering.sarl.electric.AjouteElect;
import tri.engineering.sarl.electric.ModifierElect;
import tri.engineering.sarl.electric.ProduitElecPanel;
import tri.engineering.sarl.seeting.ParametrePanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;


import java.awt.Font;

public class FrameDashBoard extends JFrame {

	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	public Image img_logo = new ImageIcon(FrameDashBoard.class.getResource("/res/logo-tr-engineering2.png")).getImage().getScaledInstance(400, 90,Image.SCALE_SMOOTH);
	public Image img_home = new ImageIcon(FrameDashBoard.class.getResource("/res/home_house_6165.png")).getImage().getScaledInstance(50, 50,Image.SCALE_SMOOTH);
	public Image img_auto = new ImageIcon(FrameDashBoard.class.getResource("/res/Cabriolet_Red_icon-icons.com_54906.png")).getImage().getScaledInstance(50, 50,Image.SCALE_SMOOTH);
	public Image img_elec = new ImageIcon(FrameDashBoard.class.getResource("/res/bulb_idea_6103.png")).getImage().getScaledInstance(50, 50,Image.SCALE_SMOOTH);
	public Image img_para = new ImageIcon(FrameDashBoard.class.getResource("/res/apps_preferences_15748.png")).getImage().getScaledInstance(50, 50,Image.SCALE_SMOOTH);

	public Image img_homes = new ImageIcon(FrameDashBoard.class.getResource("/res/home_house_6165.png")).getImage().getScaledInstance(80, 80,Image.SCALE_SMOOTH);
	public Image img_autos = new ImageIcon(FrameDashBoard.class.getResource("/res/Cabriolet_Red_icon-icons.com_54906.png")).getImage().getScaledInstance(80, 80,Image.SCALE_SMOOTH);
	public Image img_elecs = new ImageIcon(FrameDashBoard.class.getResource("/res/bulb_idea_6103.png")).getImage().getScaledInstance(80, 80,Image.SCALE_SMOOTH);
	public Image img_paras = new ImageIcon(FrameDashBoard.class.getResource("/res/apps_preferences_15748.png")).getImage().getScaledInstance(80, 95,Image.SCALE_SMOOTH);


	public Image img_exit = new ImageIcon(FrameDashBoard.class.getResource("/res/exit_closethesession_close_6317.png")).getImage().getScaledInstance(50, 50,Image.SCALE_SMOOTH);

	Connection con = null;
	ResultSet rs = null;
	PreparedStatement  pst = null;

	public JPanel contentPane;
	public HomePanel homePanel;
	public ProduitAutoPanel produitAutoPanel;
	public ProduitElecPanel produitElecPanel;
	public ParametrePanel parametrePanel;
	public JLabel lblTitre;
	public JPanel panelParametre;
	public JPanel panelLogOut;
	public JPanel panelMenu;
	public JPanel panelProduitAuto;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameDashBoard frame = new FrameDashBoard();
					frame.setVisible(true);
					frame.homePanel.getId();
					frame.homePanel.getIdd();
					frame.produitAutoPanel.updateTable();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameDashBoard() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1360, 740);
		//setUndecorated(true);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 10, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		homePanel = new HomePanel();
		produitAutoPanel = new ProduitAutoPanel();
		produitElecPanel = new ProduitElecPanel();
		parametrePanel = new ParametrePanel();

		JPanel panelHaut = new JPanel();
		panelHaut.setBounds(10, 10, 1325, 122);
		panelHaut.setBackground(new Color(205, 133, 63));
		contentPane.add(panelHaut);
		panelHaut.setLayout(null);

		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.LEFT);
		lblLogo.setBounds(0, 0, 496, 122);
		lblLogo.setIcon(new ImageIcon(img_logo));
		panelHaut.add(lblLogo);

		lblTitre = new JLabel("");
		lblTitre.setForeground(new Color(0, 0, 0));
		lblTitre.setFont(new Font("Gill Sans MT", Font.BOLD | Font.ITALIC, 40));
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setBounds(647, 40, 358, 82);
		panelHaut.add(lblTitre);

		panelMenu = new JPanel();
		panelMenu.setBounds(10, 132, 200, 560);
		panelMenu.setBackground(new Color(112, 128, 133));
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);

		////////////////////////////////////////////////////////////////////////////////////////////////////
		produitAutoPanel.btnAjoute.addActionListener(new ActionListener() {	
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AjouteAuto ajouteAuto = new AjouteAuto();
				FrameDashBoard.this.disable();
				ajouteAuto.getId();
				ajouteAuto.setVisible(true);
				ajouteAuto.btnAnnuler.addActionListener(new ActionListener() {
					@Override public void actionPerformed(ActionEvent arg0) {
						FrameDashBoard.this.enable();
						produitAutoPanel.updateTable();
						ajouteAuto.dispose();
					} });

			}
		});
		////////////////////////////////////////////////////////////////////////////////////////////////////
		produitElecPanel.btnAjoute.addActionListener(new ActionListener() {	
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AjouteElect ajouteElect = new AjouteElect();
				FrameDashBoard.this.disable();
				ajouteElect.getId();
				ajouteElect.setVisible(true);
				ajouteElect.btnAnnuler.addActionListener(new ActionListener() {
					@Override public void actionPerformed(ActionEvent arg0) {
						FrameDashBoard.this.enable();
						produitElecPanel.updateTable();
						ajouteElect.dispose();
					} });

			}
		});
		//////////////////////////////////////////////////////////////////////////////////////////////
		produitAutoPanel.btnModifier.addActionListener(new ActionListener() {	
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int ligne = produitAutoPanel.table.getSelectedRow();
				while(ligne == -1) {
					JOptionPane.showMessageDialog(null, "Sélectionner un produit");
					return;
				}
				ModifierAuto modifierAuto = new ModifierAuto();
				FrameDashBoard.this.disable();
				modifierAuto.setVisible(true);

				int line = produitAutoPanel.table.getSelectedRow();
				String a0 =  produitAutoPanel.table.getModel().getValueAt(line, 0).toString();
				String a1 =  produitAutoPanel.table.getModel().getValueAt(line, 1).toString();
				String a2 =  produitAutoPanel.table.getModel().getValueAt(line, 2).toString();
				String a3 =  produitAutoPanel.table.getModel().getValueAt(line, 3).toString();
				String a4 =  produitAutoPanel.table.getModel().getValueAt(line, 4).toString();
				String a5 =  produitAutoPanel.table.getModel().getValueAt(line, 5).toString();
				String a6 =  produitAutoPanel.table.getModel().getValueAt(line, 6).toString();

				modifierAuto.tfId.setText(a0);
				modifierAuto.tfQty.setText(a1);
				modifierAuto.formattedtfAnnee.setText(a2);
				modifierAuto.comboBoxType.setSelectedItem(a3);
				modifierAuto.tfRef1.setText(a4);
				modifierAuto.tfRef2.setText(a5);
				modifierAuto.tfMarque.setText(a6); 
				modifierAuto.lblPhoto.setIcon(produitAutoPanel.lblAvatar.getIcon());

				modifierAuto.btnAnnuler.addActionListener(new ActionListener() {
					@Override public void actionPerformed(ActionEvent arg0) {
						FrameDashBoard.this.enable();
						produitAutoPanel.updateTable();
						modifierAuto.dispose();
						produitAutoPanel.lblAvatar.setIcon(null);
						produitAutoPanel.tfSearch.setText(null);
					} });

			}
		});
		//////////////////////////////////////////////////////////////////////////////////////////////
		produitElecPanel.btnModifier.addActionListener(new ActionListener() {	
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int ligne = produitElecPanel.table.getSelectedRow();
				while(ligne == -1) {
					JOptionPane.showMessageDialog(null, "Sélectionner un produit");
					return;
				}
				ModifierElect modifierElect = new ModifierElect();
				FrameDashBoard.this.disable();
				modifierElect.setVisible(true);

				int line = produitElecPanel.table.getSelectedRow();
				String a0 =  produitElecPanel.table.getModel().getValueAt(line, 0).toString();
				String a1 =  produitElecPanel.table.getModel().getValueAt(line, 1).toString();
				String a2 =  produitElecPanel.table.getModel().getValueAt(line, 2).toString();
				String a3 =  produitElecPanel.table.getModel().getValueAt(line, 3).toString();
				String a4 =  produitElecPanel.table.getModel().getValueAt(line, 4).toString();
				String a5 =  produitElecPanel.table.getModel().getValueAt(line, 5).toString();
				String a6 =  produitElecPanel.table.getModel().getValueAt(line, 6).toString();

				modifierElect.tfId.setText(a0);
				modifierElect.tfQty.setText(a1);
				modifierElect.tfName.setText(a2);
				modifierElect.tfMarque.setText(a3);
				modifierElect.tfRef.setText(a4);
				modifierElect.textArea.setText(a5);
				modifierElect.tfFicheTech.setText(a6);
				modifierElect.lblPhoto.setIcon(produitElecPanel.lblAvatar.getIcon());

				modifierElect.btnAnnuler.addActionListener(new ActionListener() {
					@Override public void actionPerformed(ActionEvent arg0) {
						FrameDashBoard.this.enable();
						produitElecPanel.updateTable();
						modifierElect.dispose();
						produitElecPanel.lblAvatar.setIcon(null);
						produitElecPanel.tfSearch.setText(null);
					} });

			}
		});		

		/////////////////////////////////////////////////////////////////////////////////////
		JPanel panelHome = new JPanel();
		panelHome.addMouseListener(new PanelButtonMouseAdapter(panelHome) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(homePanel);
				lblTitre.setText("ACCUEIL");
				lblTitre.setIcon(new ImageIcon(img_homes));
				homePanel.getId();
				homePanel.getIdd();
			}
		});
		panelHome.setBorder(new LineBorder(new Color(210, 105, 30), 1, true));
		panelHome.setBackground(new Color(205, 133, 63));
		panelHome.setBounds(0, 158, 200, 50);
		panelMenu.add(panelHome);
		panelHome.setLayout(null);

		JLabel lblHome = new JLabel("ACCUEIL");
		lblHome.setForeground(new Color(255, 255, 255));
		lblHome.setFont(new Font("Dialog", Font.BOLD, 15));
		lblHome.setHorizontalAlignment(SwingConstants.LEFT);
		lblHome.setBounds(70, 0, 120, 50);
		panelHome.add(lblHome);

		JLabel lblIconHome = new JLabel("");
		lblIconHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconHome.setBounds(0, 0, 70, 50);
		lblIconHome.setIcon(new ImageIcon(img_home));
		panelHome.add(lblIconHome);

		panelProduitAuto = new JPanel();
		panelProduitAuto.addMouseListener(new PanelButtonMouseAdapter(panelProduitAuto){
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(produitAutoPanel);
				produitAutoPanel.updateTable();
				lblTitre.setText("AUTOMOBILE");
				lblTitre.setIcon(new ImageIcon(img_autos));
			}
		});
		panelProduitAuto.setBorder(new LineBorder(new Color(210, 105, 30), 1, true));
		panelProduitAuto.setLayout(null);
		panelProduitAuto.setBackground(new Color(205, 133, 63));
		panelProduitAuto.setBounds(0, 208, 200, 50);
		panelMenu.add(panelProduitAuto);

		JLabel lblProduitAuto = new JLabel("AUTOMOBILE");
		lblProduitAuto.setHorizontalAlignment(SwingConstants.LEFT);
		lblProduitAuto.setForeground(Color.WHITE);
		lblProduitAuto.setFont(new Font("Dialog", Font.BOLD, 15));
		lblProduitAuto.setBounds(70, 0, 110, 50);
		panelProduitAuto.add(lblProduitAuto);

		JLabel lblIconProduitAuto = new JLabel("");
		lblIconProduitAuto.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconProduitAuto.setBounds(0, 0, 70, 50);
		lblIconProduitAuto.setIcon(new ImageIcon(img_auto));
		panelProduitAuto.add(lblIconProduitAuto);

		JPanel panelProduitElec = new JPanel();
		panelProduitElec.addMouseListener(new PanelButtonMouseAdapter(panelProduitElec){
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(produitElecPanel);
				produitElecPanel.updateTable();
				lblTitre.setText("ELECTRICITE");
				lblTitre.setIcon(new ImageIcon(img_elecs));
			}
		});
		panelProduitElec.setBorder(new LineBorder(new Color(210, 105, 30), 1, true));
		panelProduitElec.setLayout(null);
		panelProduitElec.setBackground(new Color(205, 133, 63));
		panelProduitElec.setBounds(0, 256, 200, 50);
		panelMenu.add(panelProduitElec);

		JLabel lblProduitElec = new JLabel("ELECTRICITE");
		lblProduitElec.setHorizontalAlignment(SwingConstants.LEFT);
		lblProduitElec.setForeground(Color.WHITE);
		lblProduitElec.setFont(new Font("Dialog", Font.BOLD, 15));
		lblProduitElec.setBounds(70, 0, 120, 50);
		panelProduitElec.add(lblProduitElec);

		JLabel lblIconProduitElec = new JLabel("");
		lblIconProduitElec.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconProduitElec.setBounds(0, 0, 70, 50);
		lblIconProduitElec.setIcon(new ImageIcon(img_elec));
		panelProduitElec.add(lblIconProduitElec);

		panelParametre = new JPanel();
		panelParametre.addMouseListener(new PanelButtonMouseAdapter(panelParametre){
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(parametrePanel);
				lblTitre.setText("PARAMETRE");
				parametrePanel.updateTable();
				lblTitre.setIcon(new ImageIcon(img_paras));
			}
		});
		panelParametre.setBorder(new LineBorder(new Color(210, 105, 30), 1, true));
		panelParametre.setLayout(null);
		panelParametre.setBackground(new Color(205, 133, 63));
		panelParametre.setBounds(0, 304, 200, 50);
		panelMenu.add(panelParametre);

		JLabel lblParametre = new JLabel("PARAMETRE");
		lblParametre.setHorizontalAlignment(SwingConstants.LEFT);
		lblParametre.setForeground(Color.WHITE);
		lblParametre.setFont(new Font("Dialog", Font.BOLD, 15));
		lblParametre.setBounds(70, 0, 120, 50);
		panelParametre.add(lblParametre);

		JLabel lblIconParametre = new JLabel("");
		lblIconParametre.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconParametre.setBounds(0, 0, 70, 50);
		lblIconParametre.setIcon(new ImageIcon(img_para));
		panelParametre.add(lblIconParametre);

		panelLogOut = new JPanel();
		panelLogOut.addMouseListener(new PanelButtonMouseAdapter(panelLogOut){
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir fermer cette application ?", "Confirmation", JOptionPane.YES_NO_OPTION) == 0){
					Login login = new Login();
					login.setVisible(true);
					FrameDashBoard.this.dispose();
				}
			}
		});
		panelLogOut.setBorder(new LineBorder(new Color(210, 105, 30), 1, true));
		panelLogOut.setLayout(null);
		panelLogOut.setBackground(new Color(205, 133, 63));
		panelLogOut.setBounds(0, 354, 200, 50);
		panelMenu.add(panelLogOut);

		JLabel lblLogOut = new JLabel("DECONNEXION");
		lblLogOut.setHorizontalAlignment(SwingConstants.LEFT);
		lblLogOut.setForeground(Color.WHITE);
		lblLogOut.setFont(new Font("Dialog", Font.BOLD, 15));
		lblLogOut.setBounds(70, 0, 120, 50);
		panelLogOut.add(lblLogOut);

		JLabel lblIconLogOut = new JLabel("");
		lblIconLogOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconLogOut.setBounds(0, 0, 70, 50);
		lblIconLogOut.setIcon(new ImageIcon(img_exit));
		panelLogOut.add(lblIconLogOut);

		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(Color.WHITE);
		panelCentral.setBounds(209, 132, 1126, 560);
		contentPane.add(panelCentral);
		panelCentral.setLayout(null);

		panelCentral.add(homePanel);
		homePanel.setVisible(true);
		panelCentral.add(produitAutoPanel);
		panelCentral.add(produitElecPanel);
		panelCentral.add(parametrePanel);

		menuClicked(homePanel);

		JLabel lblNewLabel = new JLabel("Version 1.0.0");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel.setBounds(874, 437, 114, 23);
		homePanel.add(lblNewLabel);
	}

	public void menuClicked(JPanel panel) {
		homePanel.setVisible(false);
		produitAutoPanel.setVisible(false);
		produitElecPanel.setVisible(false);
		parametrePanel.setVisible(false);
		panel.setVisible(true);

		panel.setBackground(new Color(112, 128, 133));
	}

	public class PanelButtonMouseAdapter extends MouseAdapter{

		JPanel panel;
		public PanelButtonMouseAdapter(JPanel panel) {
			this.panel = panel;
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			panel.setBackground(new Color(13, 43, 122));
			panel.setSize(255, 50);

		}
		@Override
		public void mouseExited(MouseEvent e) {
			panel.setBackground(new Color(205, 133, 63));
			panel.setSize(200, 50);
		}
		@Override
		public void mousePressed(MouseEvent e) {
			panel.setBackground(new Color(000,000,000));
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			panel.setBackground(new Color(000,000,000));
		}

	}
}
