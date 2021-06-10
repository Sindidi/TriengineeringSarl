package tri.engineering.sarl.electric;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.SwingConstants;
import net.proteanit.sql.DbUtils;
import tri.engineering.sarl.FrameDashBoard;
import tri.engineering.sarl.dao.ConnexionMysql;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;



public class ProduitElecPanel extends JPanel {
	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	protected static final JLabel tfFer2 = null;
	public JTextField tfSearch;
	Connection con = null;
	ResultSet rs = null;
	PreparedStatement  pst = null;
	Statement ps;
	public File selectedfile;
	public String src;
	public JButton btnAjoute;
	public JButton btnModifier;

	public int qty;
	public String qty1;
	public String type;
	public String annee;
	public String ref1;
	public String ref2;
	public String marque;
	public String lblPhotos;
	public JTable table;
	public ImageIcon imageIcon;
	public JLabel lblAvatar;
	public JButton btnSupp;
	public byte[] img;


	/**
	 * Create the panel.
	 */
	public ProduitElecPanel() {
		setBackground(Color.WHITE);
		con = ConnexionMysql.Connectedb();
		setBounds(0,0,1126,560);

		Image img_ajoute = new ImageIcon(FrameDashBoard.class.getResource("/res/edit_add_10261.png")).getImage().getScaledInstance(50, 35,Image.SCALE_SMOOTH);
		Image img_modifier = new ImageIcon(FrameDashBoard.class.getResource("/res/edit_icon-icons.com_52382.png")).getImage().getScaledInstance(45, 35,Image.SCALE_SMOOTH);
		Image img_supprimer = new ImageIcon(FrameDashBoard.class.getResource("/res/recycle-recycling-remove-trash_81361.png")).getImage().getScaledInstance(40, 32,Image.SCALE_SMOOTH);
		Image img_recherche = new ImageIcon(FrameDashBoard.class.getResource("/res/Search_find_magnifier_248.png")).getImage().getScaledInstance(40, 35,Image.SCALE_SMOOTH);
		Image img_actu = new ImageIcon(FrameDashBoard.class.getResource("/res/arrow_refresh_15732.png")).getImage().getScaledInstance(50, 35,Image.SCALE_SMOOTH);

		setLayout(null);
		
		 
		JButton btnSearch = new JButton();
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search = tfSearch.getText().toString();
				String sql1 = "SELECT * FROM `elecrique` WHERE nom = '"+search+"' "
						+ "or  marque = '"+search+"' or  ref = '"+search+"' ";
				try {
					pst = con.prepareStatement(sql1);
					rs = pst.executeQuery();
				} catch (Exception e1) {
					// TODO: handle exception
				}
						table.setModel(DbUtils.resultSetToTableModel(rs));
						
				
			}	
		});
		btnSearch.setBounds(846, 11, 60, 36);
		btnSearch.setIcon(new ImageIcon(img_recherche));
		add(btnSearch);
		tfSearch = new JTextField();
		tfSearch.setBounds(914, 11, 202, 36);
		add(tfSearch);
		tfSearch.setColumns(10);

		btnAjoute = new JButton("");
		btnAjoute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		
		btnAjoute.setBounds(916, 361, 60, 50);
		btnAjoute.setIcon(new ImageIcon(img_ajoute));
		add(btnAjoute);

		btnModifier = new JButton("");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		btnModifier.setBounds(986, 361, 60, 50);
		btnModifier.setIcon(new ImageIcon(img_modifier));
		add(btnModifier);

		btnSupp = new JButton("");
		btnSupp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ligne = table.getSelectedRow();
				while(ligne == -1) {
					JOptionPane.showMessageDialog(null, "Sélectionner un produit");
					return;
				}

				String id = table.getModel().getValueAt(ligne, 0).toString();
				String sql = "DELETE FROM `elecrique` WHERE Id = '"+id+"'";
				try {
					pst = con.prepareStatement(sql);
					if(JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer cette produit ?", "Supprimer produit", JOptionPane.YES_NO_OPTION) == 0){
						pst.execute();
						updateTable();
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
		btnSupp.setBounds(1056, 361, 60, 50);
		btnSupp.setIcon(new ImageIcon(img_supprimer));
		add(btnSupp);

		JButton btnLister = new JButton("");
		btnLister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateTable();
				lblAvatar.setIcon(null);
				tfSearch.setText(null);
			}
		});
		btnLister.setIcon(new ImageIcon(img_actu));
		btnLister.setBounds(846, 361, 60, 50);
		add(btnLister);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Arial", Font.PLAIN, 10));
		scrollPane.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(10, 11, 817, 538);
		add(scrollPane);
		table = new JTable();
		table.setBorder(UIManager.getBorder("Table.scrollPaneBorder"));
		table.setGridColor(new Color(210, 180, 140));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ligne = table.getSelectedRow();
				String id = table.getModel().getValueAt(ligne, 0).toString();
				ModifierElect modifierElect = new ModifierElect();
				lblAvatar.setIcon(null);
				String sql = "select * from elecrique where Id='"+id+"'";
				try {
					pst = con.prepareStatement(sql);

					rs = pst.executeQuery();
					if(rs.next()) {
						modifierElect.tfQty.setText(rs.getString("qt"));
						modifierElect.tfName.setText(rs.getString("nom"));
						modifierElect.tfMarque.setText(rs.getString("marque"));
						modifierElect.tfRef.setText(rs.getString("ref"));
						modifierElect.textArea.setText(rs.getString("description"));
						
						
						byte[] img = rs.getBytes("photo");
						ImageIcon image = new ImageIcon(img);
						java.awt.Image im = image.getImage();
						java.awt.Image myImg = im.getScaledInstance(modifierElect.lblPhoto.getWidth(), modifierElect.lblPhoto.getHeight(), java.awt.Image.SCALE_SMOOTH);
						imageIcon = new ImageIcon(myImg);
						lblAvatar.setIcon(imageIcon);
						
					}
				} catch (Exception e1) {
					//e1.printStackTrace();
				}
			
			}
		});
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		table.setRowHeight(30);
		table.setAutoCreateRowSorter(true);
		table.setSelectionForeground(Color.white);
		table.setSelectionBackground(new Color(205,133,63));
		table.getTableHeader().setBackground(Color.black);
		table.getTableHeader().setForeground(Color.white);
		table.setFont(new Font("Tahoma",Font.PLAIN,17));

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setBackground(Color.WHITE);
		panel.setBounds(885, 83, 202, 216);
		add(panel);
		panel.setLayout(null);

		lblAvatar = new JLabel("");
		lblAvatar.setHorizontalAlignment(SwingConstants.CENTER);
		lblAvatar.setBounds(0, 0, 202, 216);
		panel.add(lblAvatar);
	}
	public void updateTable() {

		String sql = "select `Id`,`qt`,`nom`,`marque`,`ref`,`description`,`fiche` FROM `elecrique`";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
