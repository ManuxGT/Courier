
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.AbstractListModel;
import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;



public class PantallaAdministrador extends JFrame {
	private boolean enuso=false;
	private JPanel contentPane;
	private JTable table;
	private String[] columnas= {"ID","Tipo de Paquete", "Ubicación","Descripción","Fecha","Estado",};
	private JTextField textIDPaquete;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public PantallaAdministrador(int e) {
		
	}
	
	public PantallaAdministrador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 632);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		JButton ButtonLogin = new JButton("        Crear cliente");
		ButtonLogin.setBackground(Color.WHITE);
		ButtonLogin.setIcon(new ImageIcon(PantallaAdministrador.class.getResource("/imagenes/imagenes/Actions-user-group-new-icon.png")));
		ButtonLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				TablaCliente pantclient= new TablaCliente();
				pantclient.setVisible(true);
			}
		});
		ButtonLogin.setBounds(692, 29, 202, 65);
		contentPane.add(ButtonLogin);
		
		JLabel lblFedEx = new JLabel("");
		lblFedEx.setIcon(new ImageIcon(PantallaAdministrador.class.getResource("/imagenes/FedEx.png")));
		contentPane.add(lblFedEx, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("Crear paquete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		JButton btnNewButton_1 = new JButton("       Crear correo");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				tablacorreo tablacorreo = new tablacorreo();
				tablacorreo.setVisible(true);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(PantallaAdministrador.class.getResource("/imagenes/imagenes/send-mass-email.png")));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(692, 192, 202, 65);
		contentPane.add(btnNewButton_1);

		
		JButton btnNewButton_2 = new JButton("       Crear paquete");
		btnNewButton_2.setIcon(new ImageIcon(PantallaAdministrador.class.getResource("/imagenes/imagenes/FedEx-Shipping-Box-icon.png")));
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				CrearCliente CC = new CrearCliente();
				CC.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(692, 105, 202, 65);
		contentPane.add(btnNewButton_2);
		
		
		setTitle("Ventana del administrador");
		try {
			ConexionSQL conex = new ConexionSQL();
			Statement stat = ConexionSQL.getConnection().createStatement();
			
			Date fecha = new Date();
			SimpleDateFormat fechasql = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(fechasql.format(fecha));
			ResultSet resultado = stat.executeQuery("SELECT * FROM PAQUETE WHERE ESTADO = 'Disponible' and Fecha ='"+fechasql.format(fecha)+"';");
			
			int contador =0;
			while(resultado.next()) {
				contador++;
			}
			
			String [][] filas = new String [contador] [6];
			resultado.first();
			int contador2=0;
			while(resultado.next()) {
				filas [contador2][0]=resultado.getString(1) ;
				filas [contador2][1]=resultado.getString(2) ;
				filas [contador2][2]= resultado.getString(3);
				filas [contador2][3]= resultado.getString(4);
				filas [contador2][4]= resultado.getString(5);
				filas [contador2][5]= resultado.getString(6);
				
				contador2++;		
			}
			
			table = new JTable(filas,columnas);
			table.setEnabled(false);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setLocation(0, 0);
			scrollPane.setSize(649, 531);
			contentPane.add(scrollPane, BorderLayout.EAST);
			
			JButton btnRegistrarCuenta = new JButton("");
			btnRegistrarCuenta.setBorder(null);
			btnRegistrarCuenta.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					dispose();
					CreandoUsuarioAR cu = new CreandoUsuarioAR();
					cu.setVisible(true);
					
				}
			});
			btnRegistrarCuenta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnRegistrarCuenta.setIcon(new ImageIcon(PantallaAdministrador.class.getResource("/imagenes/imagenes/agregar-usuario.png")));
			btnRegistrarCuenta.setBounds(844, 532, 50, 50);
			btnRegistrarCuenta.setBorder(null);
			contentPane.add(btnRegistrarCuenta);
			
			JButton btnBuscarCliente = new JButton("");
			btnBuscarCliente.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent arg0) {
					dispose();
					BuscarCliente buscarcl = new BuscarCliente();
					buscarcl.setVisible(true);
				}
			});
			btnBuscarCliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnBuscarCliente.setIcon(new ImageIcon(PantallaAdministrador.class.getResource("/imagenes/imagenes/la-busqueda-de-empleo.png")));
			btnBuscarCliente.setBounds(772, 532, 50, 50);
			btnBuscarCliente.setBorder(null);
			contentPane.add(btnBuscarCliente);
			
			JLabel lblRecogidaDePaquete = new JLabel("Recogida de Paquete(Por ID)");
			lblRecogidaDePaquete.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
			lblRecogidaDePaquete.setBounds(692, 363, 199, 26);
			contentPane.add(lblRecogidaDePaquete);
			
			textIDPaquete = new JTextField();
			textIDPaquete.setBounds(705, 400, 141, 32);
			contentPane.add(textIDPaquete);
			textIDPaquete.setColumns(10);
			
			
			JButton btnEntregado = new JButton("");
			btnEntregado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnEntregado.setBackground(Color.WHITE);
			btnEntregado.setIcon(new ImageIcon(PantallaAdministrador.class.getResource("/imagenes/paquete.png")));
			btnEntregado.setBounds(862, 400, 32, 32);
			contentPane.add(btnEntregado);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Estado", "Entregado", "No recogido"}));
			comboBox.setBounds(733, 443, 89, 20);
			contentPane.add(comboBox);
			
			JButton btnBuscarPaquete = new JButton("");
			btnBuscarPaquete.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					dispose();
					BuscarPaquete bc = new BuscarPaquete();
					bc.setVisible(true);
					
				}
			});
			btnBuscarPaquete.setIcon(new ImageIcon(PantallaAdministrador.class.getResource("/imagenes/imagenes/tarjeta (2).png")));
			btnBuscarPaquete.setBounds(695, 532, 50, 50);
			contentPane.add(btnBuscarPaquete);
			
			
			btnEntregado.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					String estado = comboBox.getSelectedItem().toString();
					String idpaquete = textIDPaquete.getText().toString();
					System.out.println(idpaquete);
					if(comboBox.getSelectedItem().equals("Entregado")) {
						try {
							ConexionSQL conex = new ConexionSQL();
							Statement stat = ConexionSQL.getConnection().createStatement();
							stat.executeUpdate("UPDATE PAQUETE SET ESTADO ='Entregado' WHERE ID ="+idpaquete+";");

							
							JOptionPane mensaje = new JOptionPane();
							mensaje.showMessageDialog(contentPane, "Paquete entregado con exito");
							
							
						}catch(Exception exe) {
							JOptionPane error = new JOptionPane();
							error.showMessageDialog(contentPane, exe);
						}
						
					}else if(comboBox.getSelectedItem().equals("No entregado")) {
						
						try {
							ConexionSQL conex = new ConexionSQL();
							Statement stat = ConexionSQL.getConnection().createStatement();
							stat.executeUpdate("UPDATE PAQUETE SET ESTADO ='No Entregado' WHERE ID ="+idpaquete+";");

							
							JOptionPane mensaje = new JOptionPane();
							mensaje.showMessageDialog(contentPane, "Paquete entregado con exito");
							
							
						}catch(Exception exe) {
							JOptionPane error = new JOptionPane();
							error.showMessageDialog(contentPane, exe);
						}
						
						
					}else {
						JOptionPane mens = new JOptionPane();
						mens.showMessageDialog(contentPane, "Especifique el estado del paquete");
						
					}
				}
			});
			
			
			
			btnBuscarPaquete.setBorder(null);
			
			JButton btnNewButton_3 = new JButton("            Reportes");
			btnNewButton_3.setIcon(new ImageIcon(PantallaAdministrador.class.getResource("/imagenes/imagenes/depositphotos_77581986-stock-illustration-science-research-vector-concept.jpg")));
			btnNewButton_3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					dispose();
					VentanaReportes vr = new VentanaReportes();
					vr.setVisible(true);
				}
			});
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnNewButton_3.setBounds(692, 278, 202, 65);
			contentPane.add(btnNewButton_3);
			
		}catch(Exception exc) {
			System.out.println(exc);
		}
	}

	public boolean isEnuso() {
		return enuso;
	}

	public void setEnuso(boolean enuso) {
		this.enuso = enuso;
	}
}
