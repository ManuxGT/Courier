import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import java.awt.Dimension;
public class BuscarCliente extends JFrame {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JTable table;
	private JTable table_1;
	private JTable table_2;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public BuscarCliente() {
		
		setTitle("Filtrar usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 250);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String usuario = textUsuario.getText();
				try{
					ConexionSQL conex = new ConexionSQL();
					Statement stat = ConexionSQL.getConnection().createStatement();
			
					ResultSet result = stat.executeQuery("SELECT * FROM CLIENTE WHERE USUARIO = '"+usuario+"';");
					String [] columnas = {"ID","NOMBRE","APELLIDO","DIRECCION","CORREO","SEXO","USUARIO"};
					Object [][] filas = new String[1][7];
					
					if(result.next()) {
						filas[0][0]=result.getString(1);
						filas[0][1]=result.getString(2);
						filas[0][2]=result.getString(3);
				        filas[0][3]=result.getString(4);
						filas[0][4]=result.getString(6);					
						filas[0][5]=result.getString(7);	
						filas[0][6]=result.getString(8);
						table = new JTable(filas, columnas);
						table.setEnabled(false);
						Dimension minimo = new Dimension(664,63);
						table.setMaximumSize(minimo);
						table.setMinimumSize(minimo);
						table.setBackground(Color.WHITE);
						
						JScrollPane js = new JScrollPane(table);
						contentPane.add(js);
						paintAll(getGraphics());
						contentPane.updateUI();

						
					}else {
						JOptionPane mensaje = new JOptionPane();
						mensaje.showMessageDialog(contentPane, "¡Resultado no encontrado!");
					}
					
				}catch(Exception exe) {
					
					JOptionPane mensaje = new JOptionPane();
					mensaje.showMessageDialog(contentPane, exe);
				}
			}
		});
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblUsuario = new JLabel("Nombre de usuario");
		lblUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.add(lblUsuario);
		
		textUsuario = new JTextField();
		textUsuario.setBackground(UIManager.getColor("CheckBox.light"));
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		contentPane.add(btnBuscar);
		
		JButton btnSalir = new JButton("Volver al men\u00FA");
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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

		contentPane.add(btnSalir);
		setLocationRelativeTo(null);
		setResizable(false);
		
		
	}
}
