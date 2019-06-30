import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Statement;

public class TablaCliente extends JFrame {
	public Image imagen;
	private JFrame frame;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textDireccion;
	private JTextField textCorreo;
	private JTextField textUsuario;
	private JPasswordField textContrasena;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public TablaCliente() {
		initialize();
		setTitle("Crear nuevo cliente");
		Image imagen = new ImageIcon("fedex-wallpapers-30669-744668.jpg").getImage();
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		getContentPane().add(panel, BorderLayout.CENTER);
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblNombre.setBounds(44, 110, 46, 26);
		panel.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(113, 110, 293, 28);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Datos del Cliente");
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel.setBounds(25, 39, 169, 26);
		panel.add(lblNewLabel);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblApellido.setBounds(44, 170, 59, 25);
		panel.add(lblApellido);
		
		JLabel lblDirección = new JLabel("Direcci\u00F3n");
		lblDirección.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblDirección.setBounds(45, 230, 59, 14);
		panel.add(lblDirección);
		
		textApellido = new JTextField();
		textApellido.setBounds(113, 169, 293, 28);
		panel.add(textApellido);
		textApellido.setColumns(10);
		
		textDireccion = new JTextField();
		textDireccion.setBounds(113, 224, 293, 28);
		panel.add(textDireccion);
		textDireccion.setColumns(10);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblCorreo.setBounds(45, 290, 59, 14);
		panel.add(lblCorreo);
		
		textCorreo = new JTextField();
		textCorreo.setBounds(113, 288, 293, 28);
		panel.add(textCorreo);
		textCorreo.setColumns(10);
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblSexo.setBounds(44, 350, 59, 14);
		panel.add(lblSexo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Hombre", "Mujer", "Otros"}));
		comboBox.setBounds(113, 348, 81, 20);
		panel.add(comboBox);
		
		JLabel lblRegistro = new JLabel("Reg\u00EDstrate");
		lblRegistro.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblRegistro.setBounds(44, 412, 169, 26);
		panel.add(lblRegistro);
		
		JLabel lblNombreDeUsuario = new JLabel("Usuario\r\n");
		lblNombreDeUsuario.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblNombreDeUsuario.setBounds(56, 475, 46, 14);
		panel.add(lblNombreDeUsuario);
		
		textUsuario = new JTextField();
		textUsuario.setColumns(10);
		textUsuario.setBounds(147, 469, 224, 28);
		panel.add(textUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a\r\n");
		lblContrasea.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblContrasea.setBounds(56, 526, 72, 14);
		panel.add(lblContrasea);
		
		textContrasena = new JPasswordField();
		textContrasena.setBounds(147, 520, 224, 28);
		panel.add(textContrasena);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				if(Login.kind.equals("administrador")) {
					PantallaAdministrador pantadm = new PantallaAdministrador();
					pantadm.setVisible(true);
				}else {
					PantallaRecepcionista pantrecep = new PantallaRecepcionista();
					pantrecep.setVisible(true);
				}
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBackground(new Color(255, 140, 0));
		btnNewButton.setBounds(44, 594, 150, 44);
		panel.add(btnNewButton);
		
		JButton btnCrearUsuario = new JButton("Crear usuario");
		btnCrearUsuario.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				String nombre=textNombre.getText() ;
				String apellido=textApellido.getText();
				String direccion=textDireccion.getText();
				String correo=textCorreo.getText();
				String sexo= comboBox.getSelectedItem().toString();
				String usuario=textUsuario.getText();
				String contrasena=textContrasena.getText();
				String queryinsercion = "INSERT INTO CLIENTE(NOMBRE, APELLIDO, DIRECCION, CORREO, SEXO"
						+ ", USUARIO, CONTRASEÑA) VALUES ('"+nombre+"','"+apellido+"','"+direccion+"','"+correo+"','"+sexo+"','"+usuario+"','"+contrasena+"');";
					
				try {
					ConexionSQL conex = new ConexionSQL();
					Statement stat = ConexionSQL.getConnection().createStatement();
					stat.executeUpdate(queryinsercion);
					dispose();
					if(Login.kind.equals("administrador")) {
						PantallaAdministrador pantadm = new PantallaAdministrador();
						pantadm.setVisible(true);
					}else {
						PantallaRecepcionista pantrecep = new PantallaRecepcionista();
						pantrecep.setVisible(true);
					}
				}catch(Exception error) {
					System.out.println(error);
				}
			}
		});
		btnCrearUsuario.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCrearUsuario.setBackground(new Color(75, 0, 130));
		btnCrearUsuario.setForeground(new Color(255, 255, 255));
		btnCrearUsuario.setBounds(257, 594, 132, 44);
		panel.add(btnCrearUsuario);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(75, 0, 130));
		panel_1.setBounds(447, 0, 383, 680);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TablaCliente.class.getResource("/imagenes/imagenes/FXP_Socio_FedEx_600x436px.jpg")));
		lblNewLabel_1.setBounds(50, 74, 300, 213);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u00BFNo est\u00E1s registrado en Fedex?");
		lblNewLabel_2.setForeground(new Color(255, 140, 0));
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2.setBounds(50, 333, 323, 56);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblquEsperas = new JLabel("\u00BFQu\u00E9 esperas?");
		lblquEsperas.setHorizontalAlignment(SwingConstants.CENTER);
		lblquEsperas.setForeground(new Color(255, 140, 0));
		lblquEsperas.setFont(new Font("Arial", Font.BOLD, 18));
		lblquEsperas.setBounds(76, 400, 274, 56);
		panel_1.add(lblquEsperas);
		
		JLabel lblAprovechaNuestrosServicios = new JLabel("Aprovecha nuestros servicios y soluciones ");
		lblAprovechaNuestrosServicios.setHorizontalAlignment(SwingConstants.CENTER);
		lblAprovechaNuestrosServicios.setForeground(new Color(255, 140, 0));
		lblAprovechaNuestrosServicios.setFont(new Font("Arial", Font.BOLD, 14));
		lblAprovechaNuestrosServicios.setBounds(50, 467, 323, 56);
		panel_1.add(lblAprovechaNuestrosServicios);
		
		JLabel lblDiseadosParaSatisfacer = new JLabel("\u00A1Comienza ya!");
		lblDiseadosParaSatisfacer.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiseadosParaSatisfacer.setForeground(new Color(255, 140, 0));
		lblDiseadosParaSatisfacer.setFont(new Font("Arial", Font.BOLD, 23));
		lblDiseadosParaSatisfacer.setBounds(50, 613, 323, 56);
		panel_1.add(lblDiseadosParaSatisfacer);
		
		JLabel label = new JLabel("dise\u00F1ados para satisfacer tus necesidades");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(255, 140, 0));
		label.setFont(new Font("Arial", Font.BOLD, 14));
		label.setBounds(50, 489, 323, 56);
		panel_1.add(label);
		
		JLabel lblDeEnvoAl = new JLabel("de env\u00EDo, al registrarte con una cuenta");
		lblDeEnvoAl.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeEnvoAl.setForeground(new Color(255, 140, 0));
		lblDeEnvoAl.setFont(new Font("Arial", Font.BOLD, 14));
		lblDeEnvoAl.setBounds(50, 522, 323, 46);
		panel_1.add(lblDeEnvoAl);
		
		JLabel lblFedexHoy = new JLabel("Fedex hoy.");
		lblFedexHoy.setHorizontalAlignment(SwingConstants.CENTER);
		lblFedexHoy.setForeground(new Color(255, 140, 0));
		lblFedexHoy.setFont(new Font("Arial", Font.BOLD, 14));
		lblFedexHoy.setBounds(50, 545, 323, 46);
		panel_1.add(lblFedexHoy);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		setBounds(100, 100, 846, 718);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);


	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}

