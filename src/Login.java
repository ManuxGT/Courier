import java.sql.Statement;
import java.awt.EventQueue;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class Login {

	private JFrame frame;
	private JPasswordField TextContraseña;
	private JTextField TextUsuario;
	public static String kind;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ConexionSQL conex = new ConexionSQL();
		Connection laconexion = ConexionSQL.getConnection();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ConexionSQL base= new ConexionSQL();
		ConexionSQL.getConnection();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Username");
		lblUsuario.setFont(new Font("Arial", Font.PLAIN, 13));
		lblUsuario.setBackground(Color.WHITE);
		lblUsuario.setSize(68, 35);
		lblUsuario.setLocation(107, 75);
		panel.add(lblUsuario);
		
		JLabel lblContraseña = new JLabel("Password");
		lblContraseña.setFont(new Font("Arial", Font.PLAIN, 13));
		lblContraseña.setBounds(107, 121, 77, 25);
		panel.add(lblContraseña);
		
		TextContraseña = new JPasswordField();
		TextContraseña.setBounds(185, 117, 133, 25);
		panel.add(TextContraseña);
		
		TextUsuario = new JTextField();
		TextUsuario.setBounds(185, 81, 133, 25);
		panel.add(TextUsuario);
		TextUsuario.setColumns(10);
		
		JLabel lblLogoFedex = new JLabel("");
		lblLogoFedex.setIcon(new ImageIcon(Login.class.getResource("/imagenes/FedEx.png")));
		lblLogoFedex.setBounds(10, 11, 165, 53);
		panel.add(lblLogoFedex);
		JCheckBoxMenuItem CheckAdministrador = new JCheckBoxMenuItem("Administrator\r\n");
		JButton ButtonLogin = new JButton("Login");
		ButtonLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*try {
					Statement stat = ConexionSQL.getConnection().createStatement();
					ResultSet resultado = stat.executeQuery("Select * from administrador");
					while (resultado.next()) {
						System.out.println(resultado.getString("USUARIO"));
					}
				}catch(Exception easd) {
					System.out.println(easd);
				}
				*/
				String usuario = TextUsuario.getText();
				String contraseña =	TextContraseña.getText();
				String sql = "SELECT * FROM ADMINISTRADOR";
				String sql2 = "SELECT * FROM RECEPCIONISTA";
				if(CheckAdministrador.isSelected()) {
					try {
						Statement stat = ConexionSQL.getConnection().createStatement();
						ResultSet result = stat.executeQuery(sql);
						boolean correcto = false;
						while(result.next()) {
							if(result.getString(2).equalsIgnoreCase(usuario) && result.getString(3).equals(contraseña)) {
								frame.dispose();
								PantallaAdministrador pantadm = new PantallaAdministrador();
								pantadm.setVisible(true);
								pantadm.setEnuso(true);
								kind="administrador";
								correcto = true;
								break;
								
							}else {}
							
						}
						if (correcto==false) {
							JOptionPane.showMessageDialog(frame, "Nombre de usuario y/o contraseña incorrecta");
						}
						
							
						result.close();
						}catch(Exception exe) {
							System.out.println(exe);
						}
				}else {
					try {
						
						Statement stat = ConexionSQL.getConnection().createStatement();
						ResultSet result = stat.executeQuery(sql2);
						boolean correcto = false;
						while(result.next()) {
							System.out.println(result.getString(1)+result.getString(2)+result.getString(3));
							if(result.getString(2).equalsIgnoreCase(usuario) && result.getString(3).equals(contraseña)) {
								frame.dispose();
								PantallaRecepcionista pantarecep = new PantallaRecepcionista();
								pantarecep.setVisible(true);
								pantarecep.setEnuso(true);
								correcto = true;
								kind="receptor";
								break;
								
								
							}
							
						}		
						if (correcto==false) {
							JOptionPane.showMessageDialog(frame, "Nombre de usuario y/o contraseña incorrecta");
						}
						
						result.close();
						}catch(Exception exe) {
							System.out.println(exe);
							System.out.println(exe.getMessage());
							
						}
					
				}
				
				
			}
		});
		ButtonLogin.setFont(new Font("Arial", Font.PLAIN, 13));
		ButtonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		ButtonLogin.setBounds(210, 231, 92, 35);
		panel.add(ButtonLogin);
		
		CheckAdministrador.setBackground(Color.WHITE);
		CheckAdministrador.setSelected(true);
		CheckAdministrador.setBounds(173, 157, 129, 22);
		panel.add(CheckAdministrador);
		
		JCheckBoxMenuItem CheckRecepcionista = new JCheckBoxMenuItem("Receiver");
		CheckRecepcionista.setBackground(Color.WHITE);
		CheckRecepcionista.setBounds(173, 190, 129, 22);
		panel.add(CheckRecepcionista);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(CheckRecepcionista);
		bg.add(CheckAdministrador);
		
		JLabel lblNewLabel = new JLabel("\r\n");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/imagenes/Close.png")));
		lblNewLabel.setBounds(401, 11, 39, 35);
		panel.add(lblNewLabel);
	}
}
