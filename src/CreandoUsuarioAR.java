import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Statement;

public class CreandoUsuarioAR extends JFrame {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField passwordContraseña;
	private JPasswordField passwordContraseña2;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public CreandoUsuarioAR() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "Receptor"}));
		comboBox.setBounds(176, 214, 96, 20);
		contentPane.add(comboBox);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(176, 67, 162, 26);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		passwordContraseña = new JPasswordField();
		passwordContraseña.setBounds(176, 117, 162, 26);
		contentPane.add(passwordContraseña);
		
		passwordContraseña2 = new JPasswordField();
		passwordContraseña2.setBounds(176, 166, 162, 26);
		contentPane.add(passwordContraseña2);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
		lblUsuario.setBounds(57, 64, 68, 31);
		contentPane.add(lblUsuario);
		
		JLabel lblContraseña = new JLabel("Contrase\u00F1a");
		lblContraseña.setFont(new Font("Arial", Font.PLAIN, 12));
		lblContraseña.setBounds(57, 114, 83, 31);
		contentPane.add(lblContraseña);
		
		JLabel lblConfirmarContrasea = new JLabel("Confirmar contrase\u00F1a\r\n");
		lblConfirmarContrasea.setFont(new Font("Arial", Font.PLAIN, 12));
		lblConfirmarContrasea.setBounds(47, 163, 131, 37);
		contentPane.add(lblConfirmarContrasea);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String usuario = textUsuario.getText();
				String contraseña = passwordContraseña.getText();
				String contraseña2 = passwordContraseña2.getText();
				if(!usuario.equalsIgnoreCase("") && !contraseña.equals("") && !contraseña2.equals("") ) {
					if(contraseña.equals(contraseña2)) {
						if(comboBox.getSelectedItem().toString().equals("Administrador")) {
							try {
									
								ConexionSQL conex = new ConexionSQL();
								Statement stat = ConexionSQL.getConnection().createStatement();
								stat.executeUpdate("INSERT INTO ADMINISTRADOR(USUARIO,CONTRASEÑA) VALUES('"+usuario+"','"+contraseña+"');");
								dispose();
								if(Login.kind.equals("administrador")) {
									PantallaAdministrador pantadm = new PantallaAdministrador();
									pantadm.setVisible(true);
								}else {
									PantallaRecepcionista pantrecep = new PantallaRecepcionista();
									pantrecep.setVisible(true);
								}
							}catch(Exception exe) {
								System.out.println(exe);
								
							}
							
						}else {
							try {
				
								ConexionSQL conex = new ConexionSQL();
								Statement stat = ConexionSQL.getConnection().createStatement();
								stat.executeUpdate("INSERT INTO RECEPCIONISTA(USUARIO,CONTRASEÑA) VALUES('"+usuario+"','"+contraseña+"');");
								dispose();
								if(Login.kind.equals("administrador")) {
									PantallaAdministrador pantadm = new PantallaAdministrador();
									pantadm.setVisible(true);
								}else {
									PantallaRecepcionista pantrecep = new PantallaRecepcionista();
									pantrecep.setVisible(true);
								}
							}catch(Exception exe) {
								JOptionPane mensaje = new JOptionPane();
								mensaje.showMessageDialog(contentPane, "exe");
								
							}
							
						}
					}else {
						JOptionPane mensaje = new JOptionPane();
						mensaje.showMessageDialog(contentPane, "Las contraseñas no coinciden");
						
					}
		
				}else {
					JOptionPane mensaje = new JOptionPane();
					mensaje.showMessageDialog(contentPane, "Llene todos los campos");
					
				}
				
				
			}
		});
		btnRegistrar.setBounds(176, 271, 108, 31);
		contentPane.add(btnRegistrar);
		
		JLabel lblNewLabel = new JLabel("Registrar nuevo usuario");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 11, 231, 46);
		contentPane.add(lblNewLabel);
		setLocationRelativeTo(null);
	}
}
