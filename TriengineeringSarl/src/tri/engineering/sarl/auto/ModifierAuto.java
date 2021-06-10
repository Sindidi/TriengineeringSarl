package tri.engineering.sarl.auto;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;

import tri.engineering.sarl.FrameDashBoard;
import tri.engineering.sarl.dao.ConnexionMysql;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

public class ModifierAuto extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Image img_accept = new ImageIcon(FrameDashBoard.class.getResource("/res/solicit_accept_check_ok_theaction_6340.png")).getImage().getScaledInstance(40, 45,Image.SCALE_SMOOTH);
	Image img_cencel = new ImageIcon(FrameDashBoard.class.getResource("/res/restart_back_left_arrow_6022.png")).getImage().getScaledInstance(50, 40,Image.SCALE_SMOOTH);

	private JPanel contentPane;
	ProduitAutoPanel produitAutoPanel;

	Connection con = null;
	ResultSet rs = null;
	PreparedStatement  pst = null;
	public JButton btnAnnuler;
	public JTextField tfQty;
	public JLabel lblId;
	public JTextField tfId;
	public JLabel lblType;
	public JLabel lblAnnee;
	public JTextField tfRef1;
	public JTextField tfRef2;
	public JLabel lblMarque;
	public JTextField tfMarque;
	public JButton btnParcourir;
	public JLabel lblNewLabel;
	public File selectedFile;

	public int qty;
	public String qty1;
	public String type;
	public String annee;
	public String ref1;
	public String ref2;
	public String marque;


	public String lblPhotos;
	public JLabel lblPhoto;
	public String src;



	public MaskFormatter maskFormatter;

	public JFormattedTextField formattedtfAnnee;

	public JComboBox<String> comboBoxType;

	public JButton btnUpdate;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifierAuto frame = new ModifierAuto();
					frame.setVisible(true);
					frame.getId();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("deprecation")
	public ModifierAuto() {
		con = ConnexionMysql.Connectedb();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 432, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		produitAutoPanel = new ProduitAutoPanel();

		btnAnnuler = new JButton("");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAnnuler.setBounds(10, 333, 89, 42);
		btnAnnuler.setIcon(new ImageIcon(img_cencel));
		contentPane.add(btnAnnuler);

		btnUpdate = new JButton("");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(src == null) {
					int key = Integer.parseInt(tfId.getText());
					String qty1 = tfQty.getText();
					String type = (String) comboBoxType.getSelectedItem(); 
					String annee = formattedtfAnnee.getText(); 
					String ref1 = tfRef1.getText(); 
					String ref2 = tfRef2.getText(); 
					String marque = tfMarque.getText();
					if(!qty1.equals("") &&  !type.equals("") && !annee.equals("") && !ref1.equals("") && !ref2.equals("") && !marque.equals("")) {
						try {
							//String image = tfNamePhoto.getText();
							String sql = "UPDATE `automobile` SET `qt`= ? ,`annee`= ? ,`type`= ? ,`ref1`= ? ,`ref2`= ? ,`marque`= ? where Id='"+key+"'";
							pst = con.prepareStatement(sql); 
							pst.setString(1, tfQty.getText().toString());
							pst.setString(2, formattedtfAnnee.getText().toString());
							pst.setString(3, comboBoxType.getSelectedItem().toString());
							pst.setString(4, tfRef1.getText().toString());
							pst.setString(5, tfRef2.getText().toString());
							pst.setString(6, tfMarque.getText().toString());
							pst.execute();
							JOptionPane.showMessageDialog(null, "Produit modifié");
							produitAutoPanel.updateTable();
							tfQty.setText(null);
							comboBoxType.setSelectedItem(null); 
							formattedtfAnnee.setText(null); 
							tfRef1.setText(null); 
							tfRef2.setText(null); 
							tfMarque.setText(null);
							lblPhoto.setIcon(null);
						} catch (Exception e)
						{ 
							e.printStackTrace(); 
						}	
					}else{
						JOptionPane.showMessageDialog(null, "Veillez remplire tous les champs");
					}
				}else {
					int key = Integer.parseInt(tfId.getText());
					String qty1 = tfQty.getText();
					String type = (String) comboBoxType.getSelectedItem(); 
					String annee = formattedtfAnnee.getText(); 
					String ref1 = tfRef1.getText(); 
					String ref2 = tfRef2.getText(); 
					String marque = tfMarque.getText();
					if(!qty1.equals("") &&  !type.equals("") && !annee.equals("") && !ref1.equals("") && !ref2.equals("") && !marque.equals("")) {
						try {
							//int len = (int) selectedFile.length();
							InputStream avatar = new FileInputStream(src);
							String sql = "UPDATE `automobile` SET `qt`= ? ,`annee`= ? ,`type`= ? ,`ref1`= ? ,`ref2`= ? ,`marque`= ?,`photo`= ? where Id='"+key+"'";
							pst = con.prepareStatement(sql); 
							pst.setString(1, tfQty.getText().toString());
							pst.setString(2, formattedtfAnnee.getText().toString());
							pst.setString(3, comboBoxType.getSelectedItem().toString());
							pst.setString(4, tfRef1.getText().toString());
							pst.setString(5, tfRef2.getText().toString());
							pst.setString(6, tfMarque.getText().toString());
							pst.setBlob(7, avatar);
							pst.execute();
							JOptionPane.showMessageDialog(null, "Produit modifié");
							produitAutoPanel.updateTable();
							tfQty.setText(null);
							comboBoxType.setSelectedItem(null); 
							formattedtfAnnee.setText(null); 
							tfRef1.setText(null); 
							tfRef2.setText(null); 
							tfMarque.setText(null);
							lblPhoto.setIcon(null);
						} catch (Exception e)
						{ 
							e.printStackTrace(); 
						}	
					}else{
						JOptionPane.showMessageDialog(null, "Veillez remplire tous les champs");
					}

				}

			}
		});
		btnUpdate.setBounds(319, 333, 89, 42);
		btnUpdate.setIcon(new ImageIcon(img_accept));
		contentPane.add(btnUpdate);

		JLabel lblQty = new JLabel("Quantit\u00E9");
		lblQty.setHorizontalAlignment(SwingConstants.CENTER);
		lblQty.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblQty.setBounds(10, 49, 60, 23);
		contentPane.add(lblQty);

		tfQty = new JTextField();
		tfQty.setBounds(79, 50, 142, 23);
		contentPane.add(tfQty);
		tfQty.setColumns(10);

		lblId = new JLabel("Id");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblId.setBounds(214, 48, 60, 23);
		contentPane.add(lblId);

		tfId = new JTextField();
		tfId.setHorizontalAlignment(SwingConstants.CENTER);
		tfId.setColumns(10);
		tfId.setBounds(283, 49, 125, 23);
		tfId.disable();
		contentPane.add(tfId);

		lblType = new JLabel("Type");
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		lblType.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblType.setBounds(10, 100, 60, 23);
		contentPane.add(lblType);

		try {
			maskFormatter = new MaskFormatter("####");
			formattedtfAnnee = new JFormattedTextField(maskFormatter);
			formattedtfAnnee.setBounds(79, 153, 142, 21);
			contentPane.add(formattedtfAnnee);
		} catch (ParseException e) {
			e.printStackTrace();
		}



		lblAnnee = new JLabel("Ann\u00E9e");
		lblAnnee.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnee.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblAnnee.setBounds(10, 151, 60, 23);
		contentPane.add(lblAnnee);

		comboBoxType = new JComboBox<String>();
		comboBoxType.setBounds(79, 101, 142, 22);
		comboBoxType.addItem("");
		comboBoxType.addItem("CONTINENTAL");
		comboBoxType.addItem("FOMOCO");
		comboBoxType.addItem("HITACHI");
		comboBoxType.addItem("SIEMENS");
		comboBoxType.addItem("AUTRE");
		contentPane.add(comboBoxType);

		JPanel panelPhoto = new JPanel();
		panelPhoto.setBackground(Color.WHITE);
		panelPhoto.setBounds(244, 105, 164, 183);
		contentPane.add(panelPhoto);
		panelPhoto.setLayout(null);

		lblPhoto = new JLabel("");
		lblPhoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhoto.setBounds(0, 0, 164, 183);
		panelPhoto.add(lblPhoto);

		JLabel lblRef1 = new JLabel("R\u00E9f 1");
		lblRef1.setHorizontalAlignment(SwingConstants.CENTER);
		lblRef1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblRef1.setBounds(10, 204, 60, 23);
		contentPane.add(lblRef1);

		tfRef1 = new JTextField();
		tfRef1.setColumns(10);
		tfRef1.setBounds(79, 205, 142, 23);
		contentPane.add(tfRef1);

		JLabel lblRef2 = new JLabel("R\u00E9f 2");
		lblRef2.setHorizontalAlignment(SwingConstants.CENTER);
		lblRef2.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblRef2.setBounds(10, 254, 60, 23);
		contentPane.add(lblRef2);

		tfRef2 = new JTextField();
		tfRef2.setColumns(10);
		tfRef2.setBounds(79, 255, 142, 23);
		contentPane.add(tfRef2);

		lblMarque = new JLabel("Marque");
		lblMarque.setHorizontalAlignment(SwingConstants.CENTER);
		lblMarque.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblMarque.setBounds(10, 298, 60, 23);
		contentPane.add(lblMarque);

		tfMarque = new JTextField();
		tfMarque.setColumns(10);
		tfMarque.setBounds(79, 299, 142, 23);
		contentPane.add(tfMarque);

		btnParcourir = new JButton("Parcourir");
		btnParcourir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnParcourir.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Choisir une image");
				fileChooser.setApproveButtonText("Ajouter une image");
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("IMAGE", "JPG", "PNG" , "GIF", "JPEG");
				fileChooser.addChoosableFileFilter(filter);
				int result = fileChooser.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					selectedFile = fileChooser.getSelectedFile();
					String path = selectedFile.getAbsolutePath();
					ImageIcon myImage = new ImageIcon(path);
					Image img = myImage.getImage();
					Image newImage = img.getScaledInstance(lblPhoto.getWidth(), lblPhoto.getHeight(), Image.SCALE_SMOOTH);
					ImageIcon finalImage = new ImageIcon(newImage);
					lblPhoto.setIcon(finalImage);
					src = path;
				}
			}
		});
		btnParcourir.setBounds(243, 299, 165, 23);
		contentPane.add(btnParcourir);

		lblNewLabel = new JLabel("Modifier un Produit Auto");
		lblNewLabel.setForeground(new Color(205, 133, 63));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(51, 11, 325, 27);
		contentPane.add(lblNewLabel);
	}
	public void getId() {
		String sql = "select max(ID)+1 as id from automobile";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				tfId.setText(rs.getString("Id"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

