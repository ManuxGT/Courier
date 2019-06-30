import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class BuscarPaquete extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public BuscarPaquete() {
		setLocationRelativeTo(null);
		setTitle("Filtrar paquetes por estado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 608, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Estado", "Disponible", "No Encontrado", "En Tr\u00E1nsito", "Recogido", "No Entregado", "Entregado", "Alerta", "Expirado"}));
		contentPane.add(comboBox);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String estado = comboBox.getSelectedItem().toString();
				try{
					ConexionSQL conex = new ConexionSQL();
					Statement stat = ConexionSQL.getConnection().createStatement();
			
					ResultSet result = stat.executeQuery("SELECT * FROM PAQUETE WHERE ESTADO = '"+estado+"';");
					String [] columnas = {"ID","TIPO_PAQUETE","UBICACION","DESCRIPCION","FECHA","ESTADO"};
					int contador=0;
					while(result.next()) {
						contador++;
					}
					
					if(contador==0) {
						JOptionPane nada = new JOptionPane();
						nada.showMessageDialog(contentPane, "¡No hay resultados!");
						
					}else {
						result.first();
						String[] [] filas = new String [contador] [6];
						int contador2=0;
						while(result.next()) {
							filas[contador2][0] = result.getString("ID");
							filas[contador2][1] = result.getString("TIPO_PAQUETE");
							filas[contador2][2] = result.getString("UBICACION");
							filas[contador2][3] = result.getString("DESCRIPCION");
							filas[contador2][4] = result.getString("FECHA");
							filas[contador2][5] = result.getString("ESTADO");
							contador2++;
						}
						
						table = new JTable(filas,columnas);
						table.setEnabled(false);
						table.setVisible(true);
						contentPane.add(new JScrollPane(table));
						paintAll(getGraphics());
						contentPane.updateUI();
						
					}
					
					
					}catch(Exception exe) {
					
					JOptionPane mensaje = new JOptionPane();
					mensaje.showMessageDialog(contentPane, exe);
				}
			}
		});
		contentPane.add(btnBuscar);
		
		JButton btnVolverAlMen = new JButton("Volver al men\u00FA");
		btnVolverAlMen.addMouseListener(new MouseAdapter() {
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
		contentPane.add(btnVolverAlMen);
	}

}
