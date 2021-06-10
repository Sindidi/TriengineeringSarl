package tri.engineering.sarl.electric;

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
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

@SuppressWarnings("unused")
public class AjouteElect extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Image img_accept = new ImageIcon(FrameDashBoard.class.getResource("/res/solicit_accept_check_ok_theaction_6340.png")).getImage().getScaledInstance(40, 45,Image.SCALE_SMOOTH);
	Image img_cencel = new ImageIcon(FrameDashBoard.class.getResource("/res/restart_back_left_arrow_6022.png")).getImage().getScaledInstance(50, 40,Image.SCALE_SMOOTH);

	private JPanel contentPane;
	ProduitElecPanel produitElecPanel;
	Connection con = null;
	ResultSet rs = null;
	PreparedStatement  pst = null;
	public JButton btnAnnuler;
	public JTextField tfQty;
	public JLabel lblId;
	public JTextField tfId;
	public JTextField tfName;
	public JTextField tfRef;
	public JLabel lblMarque;
	public JTextField tfMarque;
	public JButton btnParcourir;
	public JLabel lblNewLabel;
	public File selectedFile;

	public int qty;
	public String marque;
	public String lblPhotos;


	public JLabel lblPhoto;
	public String src;


	public MaskFormatter maskFormatter;
	private JTextField tfFicheTech;

	public JTextArea textArea;
	private JScrollPane scrollPane;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjouteElect frame = new AjouteElect();
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
	public AjouteElect() {
		con = ConnexionMysql.Connectedb();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 432, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		setLocationRelativeTo(null);

		produitElecPanel = new ProduitElecPanel();
		
		btnAnnuler = new JButton("");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnAnnuler.setBounds(10, 333, 89, 42);
		btnAnnuler.setIcon(new ImageIcon(img_cencel));
		contentPane.add(btnAnnuler);

		JButton btnAjoute = new JButton("");
		btnAjoute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(src == null) {
					String qty = tfQty.getText();
					String name = tfName.getText(); 
					String marque = tfMarque.getText();
					String ref = tfRef.getText(); 
					String desc = textArea.getText();
					String fiche = tfFicheTech.getText();
				    if(!qty.equals("") &&  !name.equals("") && !marque.equals("") && !ref.equals("") && !desc.equals("")) {
						try {
					    	String sql = "INSERT INTO `elecrique`(`Id`, `qt`, `nom`, `marque`, `ref`, `description`, `fiche`) VALUES (NULL,? ,? ,? ,? ,? ,? )";
					    	pst = con.prepareStatement(sql);
							pst.setString(1, qty);
							pst.setString(2, name);
							pst.setString(3,marque);
							pst.setString(4, ref);
							pst.setString(5, desc);
							pst.setString(6, fiche);
							if(pst.executeUpdate()>0) {
								JOptionPane.showMessageDialog(null, "Nouvel produit ajouté");
								getId();
								tfQty.setText(null);
								tfName.setText(null); 
								tfMarque.setText(null); 
								tfRef.setText(null); 
								textArea.setText(null); 
								tfFicheTech.setText(null);
								lblPhoto.setIcon(null);
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
					String qty = tfQty.getText();
					String name = tfName.getText(); 
					String marque = tfMarque.getText();
					String ref = tfRef.getText(); 
					String desc = textArea.getText();
					String fiche = tfFicheTech.getText();
				    if(!qty.equals("") &&  !name.equals("") && !marque.equals("") && !ref.equals("") && !desc.equals("")) {
						try {
							int len = (int) selectedFile.length();
							InputStream avatar = new FileInputStream(src);
					    	String sql = "INSERT INTO `elecrique`(`Id`, `qt`, `nom`, `marque`, `ref`, `description`, `fiche`, `photo`) VALUES (NULL,? ,? ,? ,? ,? ,? ,?)";
					    	pst = con.prepareStatement(sql);
							pst.setString(1, qty);
							pst.setString(2, name);
							pst.setString(3,marque);
							pst.setString(4, ref);
							pst.setString(5, desc);
							pst.setString(6, fiche);
							pst.setBlob(7, avatar);
							if(pst.executeUpdate()>0) {
								JOptionPane.showMessageDialog(null, "Nouvel produit ajouté");
								getId();
								tfQty.setText(null);
								tfName.setText(null); 
								tfMarque.setText(null); 
								tfRef.setText(null); 
								textArea.setText(null); 
								tfFicheTech.setText(null);
								lblPhoto.setIcon(null);
							}
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
		btnAjoute.setBounds(333, 333, 89, 42);
		btnAjoute.setIcon(new ImageIcon(img_accept));
		contentPane.add(btnAjoute);

		JLabel lblQty = new JLabel("Quantit\u00E9");
		lblQty.setHorizontalAlignment(SwingConstants.CENTER);
		lblQty.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblQty.setBounds(10, 49, 60, 23);
		contentPane.add(lblQty);

		tfQty = new JTextField();
		tfQty.setBounds(89, 50, 159, 23);
		contentPane.add(tfQty);
		tfQty.setColumns(10);

		lblId = new JLabel("Id");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblId.setBounds(298, 48, 40, 23);
		contentPane.add(lblId);

		tfId = new JTextField();
		tfId.setHorizontalAlignment(SwingConstants.CENTER);
		tfId.setColumns(10);
		tfId.setBounds(348, 49, 60, 23);
		tfId.disable();
		contentPane.add(tfId);

		try {
			maskFormatter = new MaskFormatter("####");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		JPanel panelPhoto = new JPanel();
		panelPhoto.setBackground(Color.WHITE);
		panelPhoto.setBounds(258, 103, 164, 183);
		contentPane.add(panelPhoto);
		panelPhoto.setLayout(null);

		lblPhoto = new JLabel("");
		lblPhoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhoto.setBounds(0, 0, 164, 183);
		panelPhoto.add(lblPhoto);

		JLabel lblName = new JLabel("Nom");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblName.setBounds(10, 83, 60, 23);
		contentPane.add(lblName);

		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(88, 84, 160, 23);
		contentPane.add(tfName);

		JLabel lblRef2 = new JLabel("R\u00E9f\u00E9rence");
		lblRef2.setHorizontalAlignment(SwingConstants.LEFT);
		lblRef2.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblRef2.setBounds(10, 151, 71, 23);
		contentPane.add(lblRef2);

		tfRef = new JTextField();
		tfRef.setColumns(10);
		tfRef.setBounds(89, 152, 159, 23);
		contentPane.add(tfRef);

		lblMarque = new JLabel("Marque");
		lblMarque.setHorizontalAlignment(SwingConstants.LEFT);
		lblMarque.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblMarque.setBounds(10, 117, 60, 23);
		contentPane.add(lblMarque);

		tfMarque = new JTextField();
		tfMarque.setColumns(10);
		tfMarque.setBounds(89, 118, 159, 23);
		contentPane.add(tfMarque);

		btnParcourir = new JButton("Parcourir");
		btnParcourir.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnParcourir.addActionListener(new ActionListener() {

			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Choisir une image");
				fileChooser.setApproveButtonText("Ajouter une image");
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("IMAGE", "JPG", "PNG" , "GIF", "JPEG");
				fileChooser.addChoosableFileFilter(filter);
				int result = fileChooser.showOpenDialog(null);
				if(result == fileChooser.APPROVE_OPTION) {
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
		btnParcourir.setBounds(258, 299, 164, 23);
		contentPane.add(btnParcourir);

		lblNewLabel = new JLabel("Ajouter un Produit Electrique");
		lblNewLabel.setForeground(new Color(205, 133, 63));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(51, 11, 325, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Arial", Font.BOLD, 15));
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setBounds(61, 181, 154, 23);
		contentPane.add(lblDescription);
		
		textArea = new JTextArea();
		
		JButton btnParcourirF = new JButton("Fiche Tech");
		btnParcourirF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(null);
				File f = fileChooser.getSelectedFile();
				String fileName = f.getAbsolutePath();
				tfFicheTech.setText(fileName);
			}
		});
		btnParcourirF.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnParcourirF.setBounds(10, 299, 102, 23);
		contentPane.add(btnParcourirF);
		
		tfFicheTech = new JTextField();
		tfFicheTech.setBounds(122, 297, 126, 23);
		contentPane.add(tfFicheTech);
		tfFicheTech.setColumns(10);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(10, 202, 239, 86);
		contentPane.add(scrollPane);
	}
	public void getId() {
		String sql = "select max(ID)+1 as id from elecrique";
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

