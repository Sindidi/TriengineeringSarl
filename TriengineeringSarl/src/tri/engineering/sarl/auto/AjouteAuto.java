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
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.text.MaskFormatter;

import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import com.jtattoo.plaf.bernstein.BernsteinLookAndFeel;

import tri.engineering.sarl.FrameDashBoard;
import tri.engineering.sarl.dao.ConnexionMysql;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JTextArea;

public class AjouteAuto extends JFrame {

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

	public String src;
	public FileInputStream avatar;
	public int len ;
	public JLabel lblPhoto;
	String filename=null;


	public MaskFormatter maskFormatter;
	public JFormattedTextField formattedtfAnnee;
	public JComboBox<String> comboBoxType;
	private JTextField textField;
	private JTextField textField_1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjouteAuto frame = new AjouteAuto();
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
	public AjouteAuto() {
		disgn();
		con = ConnexionMysql.Connectedb();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 482);
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
				produitAutoPanel.updateTable();
			}
		});
		btnAnnuler.setBounds(10, 425, 89, 42);
		btnAnnuler.setIcon(new ImageIcon(img_cencel));
		contentPane.add(btnAnnuler);

		JButton btnAjoute = new JButton("");
		btnAjoute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(src == null) {
					String qty1 = tfQty.getText();
					String type = (String) comboBoxType.getSelectedItem(); 
					String annee = formattedtfAnnee.getText(); 
					String ref1 = tfRef1.getText(); 
					String ref2 = tfRef2.getText(); 
					String marque = tfMarque.getText();
					if(!qty1.equals("") &&  !type.equals("") && !annee.equals("") && !ref1.equals("") && !ref2.equals("") && !marque.equals("")) {
						try {
							String sql = "INSERT INTO `automobile` (`Id`, `qt`, `annee`, `type`, `ref1`, `ref2`, `marque`) VALUES (NULL,? ,? ,? ,? ,? ,? )";
							pst = con.prepareStatement(sql);
							pst.setString(1, qty1);
							pst.setString(2, annee);
							pst.setString(3,type);
							pst.setString(4, ref1);
							pst.setString(5, ref2);
							pst.setString(6, marque);
							if(pst.executeUpdate()>0) {
								JOptionPane.showMessageDialog(null, "Nouvel produit ajouté");
								getId();
								produitAutoPanel.updateTable();
								tfQty.setText(null);
								comboBoxType.setSelectedItem(null); 
								formattedtfAnnee.setText(null); 
								tfRef1.setText(null); 
								tfRef2.setText(null); 
								tfMarque.setText(null);
								lblPhoto.setIcon(null);
								produitAutoPanel.updateTable();
						}else{
							JOptionPane.showMessageDialog(null, "Echec d'insertion");
						}
						} catch (Exception e)
						{ 
							e.printStackTrace(); 
						}	
					}else{
						JOptionPane.showMessageDialog(null, "Veillez remplire tous les champs");
					}
				}else {
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
							String sql = "INSERT INTO `automobile` (`Id`, `qt`, `annee`, `type`, `ref1`, `ref2`, `marque`, `photo`) VALUES (NULL,? ,? ,? ,? ,? ,? ,?)";							pst = con.prepareStatement(sql); 
							pst.setString(1, tfQty.getText().toString());
							pst.setString(2, formattedtfAnnee.getText().toString());
							pst.setString(3, comboBoxType.getSelectedItem().toString());
							pst.setString(4, tfRef1.getText().toString());
							pst.setString(5, tfRef2.getText().toString());
							pst.setString(6, tfMarque.getText().toString());
							pst.setBlob(7, avatar);
							pst.execute();
							JOptionPane.showMessageDialog(null, "Produit Ajouté");
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
		btnAjoute.setBounds(372, 425, 89, 42);
		btnAjoute.setIcon(new ImageIcon(img_accept));
		contentPane.add(btnAjoute);

		JLabel lblQty = new JLabel("Quantit\u00E9");
		lblQty.setHorizontalAlignment(SwingConstants.LEFT);
		lblQty.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblQty.setBounds(10, 48, 60, 23);
		contentPane.add(lblQty);

		tfQty = new JTextField();
		tfQty.setBounds(112, 49, 176, 23);
		contentPane.add(tfQty);
		tfQty.setColumns(10);

		lblId = new JLabel("Id");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblId.setBounds(270, 48, 60, 23);
		contentPane.add(lblId);

		tfId = new JTextField();
		tfId.setHorizontalAlignment(SwingConstants.CENTER);
		tfId.setColumns(10);
		tfId.setBounds(339, 49, 125, 23);
		tfId.disable();
		contentPane.add(tfId);

		lblType = new JLabel("Type");
		lblType.setHorizontalAlignment(SwingConstants.LEFT);
		lblType.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblType.setBounds(10, 96, 60, 23);
		contentPane.add(lblType);

		try {
			maskFormatter = new MaskFormatter("####");
			formattedtfAnnee = new JFormattedTextField(maskFormatter);
			formattedtfAnnee.setBounds(112, 145, 177, 21);
			contentPane.add(formattedtfAnnee);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		lblAnnee = new JLabel("Ann\u00E9e");
		lblAnnee.setHorizontalAlignment(SwingConstants.LEFT);
		lblAnnee.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblAnnee.setBounds(10, 144, 60, 23);
		contentPane.add(lblAnnee);

		comboBoxType = new JComboBox<String>();
		comboBoxType.setEditable(true);
		comboBoxType.setBounds(112, 97, 177, 22);
		comboBoxType.addItem("");
		comboBoxType.addItem("CONTINENTAL");
		comboBoxType.addItem("FOMOCO");
		comboBoxType.addItem("HITACHI");
		comboBoxType.addItem("SIEMENS");
		comboBoxType.addItem("AUTRE");
		contentPane.add(comboBoxType);

		JPanel panelPhoto = new JPanel();
		panelPhoto.setBackground(Color.WHITE);
		panelPhoto.setBounds(299, 195, 164, 183);
		contentPane.add(panelPhoto);
		panelPhoto.setLayout(null);

		lblPhoto = new JLabel("");
		lblPhoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhoto.setBounds(0, 0, 164, 183);
		panelPhoto.add(lblPhoto);

		JLabel lblRef1 = new JLabel("R\u00E9f 1");
		lblRef1.setHorizontalAlignment(SwingConstants.LEFT);
		lblRef1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblRef1.setBounds(10, 194, 60, 23);
		contentPane.add(lblRef1);

		tfRef1 = new JTextField();
		tfRef1.setColumns(10);
		tfRef1.setBounds(112, 194, 177, 23);
		contentPane.add(tfRef1);

		JLabel lblRef2 = new JLabel("R\u00E9f 2");
		lblRef2.setHorizontalAlignment(SwingConstants.LEFT);
		lblRef2.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblRef2.setBounds(10, 244, 60, 23);
		contentPane.add(lblRef2);

		tfRef2 = new JTextField();
		tfRef2.setColumns(10);
		tfRef2.setBounds(112, 245, 177, 23);
		contentPane.add(tfRef2);

		lblMarque = new JLabel("Marque");
		lblMarque.setHorizontalAlignment(SwingConstants.CENTER);
		lblMarque.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblMarque.setBounds(10, 297, 60, 23);
		contentPane.add(lblMarque);

		tfMarque = new JTextField();
		tfMarque.setColumns(10);
		tfMarque.setBounds(112, 298, 177, 23);
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
		btnParcourir.setBounds(299, 389, 165, 23);
		contentPane.add(btnParcourir);

		lblNewLabel = new JLabel("Ajouter un Produit  Auto");
		lblNewLabel.setForeground(new Color(205, 133, 63));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(82, 11, 325, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblMarque_1 = new JLabel("Prix");
		lblMarque_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblMarque_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblMarque_1.setBounds(10, 343, 60, 23);
		contentPane.add(lblMarque_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(112, 344, 177, 23);
		contentPane.add(textField);
		
		JLabel lblMarque_2 = new JLabel("Emplacement");
		lblMarque_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblMarque_2.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblMarque_2.setBounds(10, 387, 102, 23);
		contentPane.add(lblMarque_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(112, 387, 177, 23);
		contentPane.add(textField_1);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(301, 101, 164, 86);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setFont(new Font("Arial", Font.BOLD, 15));
		lblDescription.setBounds(309, 80, 154, 23);
		contentPane.add(lblDescription);
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
	private void disgn() {
		try {
			UIManager.setLookAndFeel(new AluminiumLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}

