import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class CrearCliente extends JFrame {

	private JPanel contentPane;
	private JTextField textUbicacion;
	private JTextField textId_cliente;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public CrearCliente() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 788, 570);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel PanelFondoMor = new JPanel();
		PanelFondoMor.setBackground(new Color(75, 0, 130));
		PanelFondoMor.setBounds(0, 0, 772, 130);
		contentPane.add(PanelFondoMor);
		PanelFondoMor.setLayout(null);
		
		JLabel lblCrear = new JLabel("Crear nueva");
		lblCrear.setForeground(new Color(255, 255, 255));
		lblCrear.setFont(new Font("Arial", Font.BOLD, 24));
		lblCrear.setBounds(10, 21, 153, 50);
		PanelFondoMor.add(lblCrear);
		
		JLabel lblRecogida = new JLabel("recogida de paquete.\r\n");
		lblRecogida.setForeground(new Color(255, 255, 255));
		lblRecogida.setFont(new Font("Arial", Font.BOLD, 24));
		lblRecogida.setBounds(10, 47, 271, 72);
		PanelFondoMor.add(lblRecogida);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setIcon(new ImageIcon(CrearCliente.class.getResource("/imagenes/imagenes/58428e7da6515b1e0ad75ab5.png")));
		lblNewLabel.setBounds(543, 0, 240, 130);
		PanelFondoMor.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descripci\u00F3n");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_1.setBounds(18, 214, 81, 27);
		contentPane.add(lblNewLabel_1);
		
		JTextPane txtDescripcion = new JTextPane();
		txtDescripcion.setForeground(new Color(0, 0, 0));
		txtDescripcion.setText("Escriba la descripci\u00F3n...");
		txtDescripcion.setToolTipText("");
		txtDescripcion.setFont(new Font("Arial", Font.PLAIN, 13));
		txtDescripcion.setBounds(18, 252, 384, 181);
		contentPane.add(txtDescripcion);
		
		JComboBox comboBoxDia = new JComboBox();
		comboBoxDia.setModel(new DefaultComboBoxModel(new String[] {"Dia", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBoxDia.setBounds(426, 179, 50, 30);
		contentPane.add(comboBoxDia);
		
		JComboBox comboBoxMes = new JComboBox();
		comboBoxMes.setModel(new DefaultComboBoxModel(new String[] {"Mes", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}));
		comboBoxMes.setBounds(486, 179, 93, 30);
		contentPane.add(comboBoxMes);
		
		JComboBox comboBoxAño = new JComboBox();
		comboBoxAño.setModel(new DefaultComboBoxModel(new String[] {"A\u00F1o", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913"}));
		comboBoxAño.setBounds(589, 179, 60, 30);
		contentPane.add(comboBoxAño);
		
		JLabel lblFecha = new JLabel("Fecha de entrega");
		lblFecha.setFont(new Font("Arial", Font.BOLD, 16));
		lblFecha.setBounds(426, 147, 137, 21);
		contentPane.add(lblFecha);
		
		JComboBox comboBoxTipoPaquete = new JComboBox();
		comboBoxTipoPaquete.setModel(new DefaultComboBoxModel(new String[] {"Tipo de paquete", "General", "Fr\u00E1gil", "Perecedera", "A granel", "Peligrosa"}));
		comboBoxTipoPaquete.setBounds(426, 252, 137, 39);
		contentPane.add(comboBoxTipoPaquete);
		
		JComboBox comboBoxEstado = new JComboBox();
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] {"Estado", "Disponible", "No Encontrado", "En Tr\u00E1nsito", "No Entregado", "Entregado", "Alerta", "Expirado"}));
		comboBoxEstado.setBounds(589, 252, 124, 39);
		contentPane.add(comboBoxEstado);
		
		JLabel lblInformacin = new JLabel("Informaci\u00F3n");
		lblInformacin.setFont(new Font("Arial", Font.BOLD, 16));
		lblInformacin.setBounds(426, 220, 137, 21);
		contentPane.add(lblInformacin);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCrear.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
	
				try {
					String descripcion= txtDescripcion.getText();
					int mes = 0;
					if(comboBoxMes.getSelectedItem().toString().equals("Enero")) {
						mes =1;
					}else if(comboBoxMes.getSelectedItem().toString().equals("Febrero")) {
						mes =2;
					}else if(comboBoxMes.getSelectedItem().toString().equals("Marzo")) {
						mes =3;
					}else if(comboBoxMes.getSelectedItem().toString().equals("Abril")) {
						mes =4;
					}else if(comboBoxMes.getSelectedItem().toString().equals("Mayo")) {
						mes =5;
					}else if(comboBoxMes.getSelectedItem().toString().equals("Junio")) {
						mes =6;
					}else if(comboBoxMes.getSelectedItem().toString().equals("Julio")) {
						mes =7;
					}else if(comboBoxMes.getSelectedItem().toString().equals("Agosto")) {
						mes =8;
					}else if(comboBoxMes.getSelectedItem().toString().equals("Septiembre")) {
						mes =9;
					}else if(comboBoxMes.getSelectedItem().toString().equals("Octubre")) {
						mes =10;
					}else if(comboBoxMes.getSelectedItem().toString().equals("Noviembre")) {
						mes =11;
					}else if(comboBoxMes.getSelectedItem().toString().equals("Diciembre")) {
						mes =12;
					}
					String fecha = comboBoxAño.getSelectedItem().toString()+"-"+mes+"-"+comboBoxDia.getSelectedItem().toString();
					String estado = comboBoxEstado.getSelectedItem().toString();
					String tipo_paquete = comboBoxTipoPaquete.getSelectedItem().toString();
					String ubicacion = textUbicacion.getText();
					String id_cliente = textId_cliente.getText();
					String query="INSERT INTO PAQUETE(DESCRIPCION, FECHA, ESTADO, TIPO_PAQUETE, UBICACION, ID_CLIENTE) VALUES('"+descripcion+"','"+fecha+"','"+estado+"','"+tipo_paquete+"','"+ubicacion+"','"+id_cliente+"');";
					ConexionSQL conex = new ConexionSQL();
					Statement stat = ConexionSQL.getConnection().createStatement();
					stat.executeUpdate(query);
					dispose();
					PantallaAdministrador pantadm= new PantallaAdministrador();
					pantadm.setVisible(true);
					
				}catch(Exception exe) {
					JOptionPane error = new JOptionPane("Error al crear el paquete, rellene los campos correctamente ");
				}
				
			}
		});
		btnCrear.setBackground(new Color(75, 0, 130));
		btnCrear.setForeground(new Color(255, 255, 255));
		btnCrear.setBounds(404, 459, 89, 39);
		contentPane.add(btnCrear);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
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
		btnCancelar.setBackground(new Color(255, 165, 0));
		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.setBounds(253, 459, 100, 39);
		contentPane.add(btnCancelar);
		
		JLabel lbUbicacion = new JLabel("Ubicaci\u00F3n\r\n");
		lbUbicacion.setFont(new Font("Arial", Font.BOLD, 11));
		lbUbicacion.setBounds(412, 315, 81, 27);
		contentPane.add(lbUbicacion);
		
		textUbicacion = new JTextField();
		textUbicacion.setBounds(485, 315, 228, 27);
		contentPane.add(textUbicacion);
		textUbicacion.setColumns(10);
		
		JLabel lblidcliente = new JLabel("ID del Cliente");
		lblidcliente.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblidcliente.setBounds(18, 170, 93, 18);
		contentPane.add(lblidcliente);
		
		textId_cliente = new JTextField();
		textId_cliente.setBounds(119, 167, 89, 21);
		contentPane.add(textId_cliente);
		textId_cliente.setColumns(10);
		setLocationRelativeTo(null);
	}
}
