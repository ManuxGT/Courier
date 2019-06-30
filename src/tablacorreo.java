
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.*;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.util.Properties;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class tablacorreo extends JFrame {

	private JPanel contentPane;
	private JTextField txtcorreo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tablacorreo frame = new tablacorreo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public tablacorreo() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("correo");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(22, 57, 46, 14);
		contentPane.add(lblNewLabel);
		
		txtcorreo = new JTextField();
		txtcorreo.setText("");
		txtcorreo.setToolTipText("correo");
		txtcorreo.setBounds(88, 56, 185, 20);
		contentPane.add(txtcorreo);
		txtcorreo.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Estado", "Disponible", "No Encontrado", "En Tr\u00E1nsito", "No Entregado", "Entregado", "Alerta", "Expirado"}));
		comboBox.setBounds(221, 112, 73, 20);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
				 Properties props=new Properties();
				 props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
				 props.setProperty("mail.smtp.host", "smtp.gmail.com");
				 props.setProperty("mail.smtp.starttls.enable", "true");
				 props.setProperty("mail.smtp.port", "587");
				 props.setProperty("mail.smtp.auth", "true");
				props.setProperty("mail.transport.protocol","smtp");		
				 
				 Session session =Session.getDefaultInstance(props);
				 
				 String Correoremitente ="josegabriel.jgcz@gmail.com";
				 String password ="buenaaventura19";
				 String Correoreceptor=txtcorreo.getText();
				 String Asunto= comboBox.getSelectedItem().toString();
				 String mensaje="estado de paquete: "+ Asunto;
				 
				 Statement stat = ConexionSQL.getConnection().createStatement();
				 stat.executeUpdate("INSERT INTO CORREO(DESCRIPCION) VALUES('"+mensaje+"');");
				 
	
					MimeMessage message = new MimeMessage(session);		
					message.setFrom(new InternetAddress(Correoremitente));
					
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(Correoreceptor));
					message.setText(Asunto);
					message.setText(mensaje);
					
					Transport t= session.getTransport("smtp");
					t.connect(Correoremitente, password);
					t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
					t.close();
					
					JOptionPane.showMessageDialog(null, "Correo enviado");
					
				} catch (AddressException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				System.out.println(e1.getMessage());
					
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println(e1.getMessage());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		btnNewButton.setBounds(321, 55, 89, 23);
		contentPane.add(btnNewButton);
		
		
	}
}
