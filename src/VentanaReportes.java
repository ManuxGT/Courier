import java.awt.BorderLayout;
import java.util.Date;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.csvreader.CsvWriter;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class VentanaReportes extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VentanaReportes() {
		setTitle("Generar reporte");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 725, 255);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(Color.LIGHT_GRAY);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Reportes", "Porcentaje de clientes hombres y mujeres", "Cantidad de clientes que han recogido paquetes por dia, semana y mes", "Porciento (%) de los clientes que han recogido paquetes y los que no han recogido", "Dias de la semana con mayor recogidas de paquetes"}));
		comboBox.setBounds(220, 56, 482, 34);
		contentPane.add(comboBox);
		
		JButton btnGenerar = new JButton("Generar");
		btnGenerar.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				String resultado = comboBox.getSelectedItem().toString();
				Date fecha = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
				SimpleDateFormat sdfmes = new SimpleDateFormat("MM");
				sdf.format(fecha);
				int mes = Integer.parseInt(sdfmes.format(fecha))-1;
				System.out.println(mes);
				if(resultado.equalsIgnoreCase("Porcentaje de clientes hombres y mujeres")) {
					String nombrefichero="PorcentajePorGenero-"+sdf.format(fecha)+".csv";
					CsvWriter archivo = new CsvWriter(nombrefichero); 
					try {
						ConexionSQL.getConnection();
						Statement stat = ConexionSQL.getConnection().createStatement();
						ResultSet Resultset1 = stat.executeQuery("SELECT * FROM CLIENTE WHERE SEXO = 'Hombre';");
						double hombres =0;
						while(Resultset1.next()) {
							hombres++;
						}
						Resultset1.close();
						
						ResultSet Resultset2 = stat.executeQuery("SELECT * FROM CLIENTE WHERE SEXO = 'Mujer';");
						double mujeres =0;
						while(Resultset2.next()) {
							mujeres++;
						}
						Resultset2.close();
						ResultSet Resultset3 = stat.executeQuery("SELECT * FROM CLIENTE;");
						double total =0;
						while(Resultset3.next()) {
							total++;
						}
						Resultset3.close();
						
						double porcientoh = (hombres/total)*100;
						double porcientom = (mujeres/total)*100;
						String [] titulo = {"Hombres","Mujeres"};
						String [] arreglo = {Double.toString(porcientoh)+"%", Double.toString(porcientom)+"%"};
						archivo.writeRecord(titulo);
						archivo.writeRecord(arreglo);
						archivo.close();
						JOptionPane mensaje = new JOptionPane();
						mensaje.showMessageDialog(contentPane, "Registro generado exitosamente.");
						
						
					}catch(Exception exe) {

						System.out.println(exe);
					}

					
				}else if(resultado.equalsIgnoreCase("Cantidad de clientes que han recogido paquetes por dia, semana y mes")){
					String nombrefichero="ReporteRecogidaDSM-"+sdf.format(fecha)+".csv";
					try {
						ConexionSQL.getConnection();
						int cdias=0;
						Statement stat = ConexionSQL.getConnection().createStatement();
						ResultSet result1;
						CsvWriter archivo = new CsvWriter(nombrefichero);
						String [] linea = {"Días","Cantidad"};
						archivo.writeRecord(linea);
						for(int x=1;x<=31;x++) {
							cdias=0;
							if(mes>9) {
								 result1 = stat.executeQuery("select fecha from paquete where fecha LIKE '%" +x+"' and fecha like '%-"+mes+"-%';");
							}else {
								 result1 = stat.executeQuery("select fecha from paquete where fecha LIKE '%" +x+"'and fecha like '%-0"+mes+"-%';");
							}
							
							while(result1.next()) {
								cdias++;
							}
							linea[0] = Integer.toString(x);
							linea[1] = Integer.toString(cdias);
							archivo.writeRecord(linea);
							result1.first();
							
						}
						String [] linea2 = {"Semana", "Cantidad"};
						int numerosemana=0;
						int csemanas=0;
						int dias=1;
						
						archivo.writeRecord(linea2);
						for(int x=1;x<5;x++) {
							csemanas=0;
							for(int i=1;i<=7;i++) {
								if(mes>9) {
									 result1 = stat.executeQuery("select fecha from paquete where fecha LIKE '%" +dias+"' and fecha like '%-"+mes+"-%';");
								}else {
									 result1 = stat.executeQuery("select fecha from paquete where fecha LIKE '%" +dias+"'and fecha like '%-0"+mes+"-%';");
								}
								
								while(result1.next()) {
									csemanas++;
								}
								dias++;
							}
							linea[0] = Integer.toString(x);
							linea[1] = Integer.toString(csemanas);
							archivo.writeRecord(linea);
							
							
							
						}
						mes++;
						String [] linea3 = {"Mes", "Cantidad"};
						cdias=0;
						archivo.writeRecord(linea3);
						for(;mes>0;mes--) {
							cdias=0;
							for(int x=1;x<=31;x++) {
						
								if(mes>9) {
									 result1 = stat.executeQuery("select fecha from paquete where fecha LIKE '%" +x+"' and fecha like '%-"+mes+"-%';");
								}else {
									 result1 = stat.executeQuery("select fecha from paquete where fecha LIKE '%" +x+"'and fecha like '%-0"+mes+"-%';");
								}
								
								while(result1.next()) {
									cdias++;
								}
								
								
								}
							String mess="";
							switch(mes) {
							case 1:
								mess ="Enero";
								break;
								
							case 2:
								mess ="Febrero";
								break;
								
							case 3:
								mess ="Marzo";
								break;
								
							case 4:
								mess ="Abril";
								break;
								
							case 5:
								 mess ="Mayo";
								break;
								
							case 6:
								 mess ="Junio";
								break;
								
							case 7:
								 mess ="Julio";
								break;
								
							case 8:
							 mess ="Agosto";
								break;
								
							case 9:
							 mess ="Septiembre";
								break;
								
							case 10:
								mess ="Octubre";
								break;
								
							case 11:
								 mess ="Noviembre";
								break;
								
							case 12:
								 mess ="Diciembre";
								break;
				
							}
							
								linea[0] = mess;
								
								
								
								linea[1] = Integer.toString(cdias);
								archivo.writeRecord(linea);
								
							
						}
						
						
						archivo.close();
						
						
						
						
						

						JOptionPane mensaje = new JOptionPane();
						mensaje.showMessageDialog(contentPane, "Registro generado exitosamente.");
			
						
					}catch(Exception exe) {
						System.out.println(exe);
					}
					
					
					
					
				}else if(resultado.equalsIgnoreCase("Porciento (%) de los clientes que han recogido paquetes y los que no han recogido")) {
					
					String nombrefichero="%ClientesHanRecogido-"+sdf.format(fecha)+".csv";
					CsvWriter archivo = new CsvWriter(nombrefichero); 
					try {
						Statement stat = ConexionSQL.getConnection().createStatement();
						
						ResultSet result = stat.executeQuery("SELECT * FROM CLIENTE;");
						double clientes = 0;
						while(result.next()) {
							clientes++;
						}
						result.close();
						ResultSet result2 = stat.executeQuery("SELECT * FROM CLIENTE INNER JOIN PAQUETE ON CLIENTE.ID = PAQUETE.ID_CLIENTE AND PAQUETE.ESTADO = 'Entregado'");
						double clientespaquete =0;
						while (result2.next()) {
							clientespaquete++;
						}
					 double clientespaqueteporcentaje= (clientespaquete/clientes)*100;
					 double clientessinpaqueteporcentaje = 100-clientespaqueteporcentaje;
					 String [] linea1 = {"Clientes que han recogido", "Clientes que no han recogido"};
					 String [] linea2 = {Double.toString(clientespaqueteporcentaje),Double.toString(clientessinpaqueteporcentaje) };
					 archivo.writeRecord(linea1);
					 archivo.writeRecord(linea2);
					 archivo.close();
					 JOptionPane mensaje = new JOptionPane();
					 mensaje.showMessageDialog(contentPane, "Registro generado exitosamente.");
						
					}catch(Exception exe){
						System.out.println(exe);
					}
					
					
					
					
					
				}else if(resultado.equalsIgnoreCase("Dias de la semana con mayor recogidas de paquetes")) {
					try {
						String nombrefichero="DiasMayorRecogida-"+sdf.format(fecha)+".csv";
						CsvWriter archivo = new CsvWriter(nombrefichero);
						Statement stat = ConexionSQL.getConnection().createStatement();
						SimpleDateFormat sdf1 = new SimpleDateFormat("u");
						int mes2 = Integer.parseInt(sdfmes.format(fecha))-1;
						int lunes =0, martes=0, miercoles=0,jueves=0,viernes=0,sabado=0, domingo=0;
						ResultSet Result = stat.executeQuery("SELECT * FROM PAQUETE");
						String [] linea = {"Día","Cantidad"};
						archivo.writeRecord(linea);
						while(Result.next()) {
							String f = sdf1.format(Result.getDate("FECHA"));
							System.out.println(f);
							if(f.equals("1")) {
								martes++;
							}else if(f.equals("2")) {
								miercoles++;
							}else if(f.equals("3")) {
								jueves++;
							}else if(f.equals("4")) {
								viernes++;
							}else if(f.equals("5")) {
								sabado++;
							}else if(f.equals("6")) {
								domingo++;
							}else if(f.equals("7")) {
								lunes++;
							}else {
								
							}		
						}
						
						String [] linea1 = {"Domingo",Integer.toString(domingo)};
						String [] linea2 = {"Lunes", Integer.toString(lunes) };
						String [] linea3 = {"Martes", Integer.toString(martes) };
						String [] linea4 = {"Miercoles", Integer.toString(miercoles) };
						String [] linea5 = {"Jueves", Integer.toString(jueves) };
						String [] linea6 = {"Viernes", Integer.toString(viernes) };
						String [] linea7 = {"Sábado", Integer.toString(sabado) };
						archivo.writeRecord(linea1);
						archivo.writeRecord(linea2);
						archivo.writeRecord(linea3);
						archivo.writeRecord(linea4);
						archivo.writeRecord(linea5);
						archivo.writeRecord(linea6);
						archivo.writeRecord(linea7);
						archivo.close();
						JOptionPane message = new JOptionPane();
						message.showMessageDialog(contentPane, "Reporte generado exitosamente");
						
					}catch(Exception e) {
						
					}
					 
					
					
					
					
					
					
					
					
				}else {
					JOptionPane opcion = new JOptionPane();
					opcion.showMessageDialog(contentPane, "Seleccione un reporte por favor.");
				}
				
				
				
			}
		});
		btnGenerar.setBackground(Color.LIGHT_GRAY);
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnGenerar.setBounds(307, 117, 89, 34);
		contentPane.add(btnGenerar);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(VentanaReportes.class.getResource("/imagenes/imagenes/el-hombre-de-negocios-de-dibujos-animados-presenta-a-la-tarjeta-grfica-by-Vexels.png")));
		lblNewLabel.setBounds(10, 9, 200, 200);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Volver al men\u00FA");
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
		btnNewButton.setBounds(440, 117, 107, 34);
		contentPane.add(btnNewButton);
	}
}
