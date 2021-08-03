package Conexion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Button;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JLabel;

public class Interfaz {
	static Connection con;
	String cedula = "";
	String cedulaSQL ="";
	private JFrame frmCooperativaDeConsumo;
	private JTextField campoCedula;
	private JTextField campoSocio;
	private JTextField campoNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		con = DatabaseManager.obtenerConexionBD();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frmCooperativaDeConsumo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCooperativaDeConsumo = new JFrame();
		frmCooperativaDeConsumo.setTitle("COOPERATIVA DE CONSUMO POLICIAL");
		frmCooperativaDeConsumo.getContentPane().setBackground(new Color(135, 206, 250));
		frmCooperativaDeConsumo.getContentPane().setForeground(new Color(176, 196, 222));
		frmCooperativaDeConsumo.getContentPane().setLayout(null);
		
		// Aqui estamos dandole funcionamiento al boton buscar SOCIO
		JButton btnBuscarSocio = new JButton("BUSCAR SOCIO");
		btnBuscarSocio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hola");
				// Aqui extraemos el texto del campo y posteriormente le ponemos comillas para la sentencia SQL
				cedula = campoCedula.getText();
				System.out.println("La cedula ingresada es: " + cedula);
				System.out.println("'" +cedula + "'");
				cedulaSQL = "'" +cedula + "'";
				System.out.println(cedulaSQL);
				
				//Aqui caragamos la sentencia SQL y damos como parametro la cedula que ingresa el usuario con las dos comillas
				String sql = "SELECT NUMERO FROM Socios WHERE CEDULA =" +cedulaSQL;
				String sql2 = "SELECT NOMBRES FROM Socios WHERE CEDULA =" +cedulaSQL;
				String sql3 = "SELECT APELLIDOS FROM Socios WHERE CEDULA =" +cedulaSQL;
				try {
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(sql);
					ResultSet rs2 = st.executeQuery(sql2);
					ResultSet rs3 = st.executeQuery(sql3);
					
			/*	if (rs.getRow() ==null) {
					JOptionPane.showMessageDialog(null, "La cédula ingresada no es correcta");
				} */
					
				
						
					
			while(rs.next()) {
						
					String numSocio = rs.getString("NUMERO");
					
					System.out.println(numSocio);
					campoSocio.setText(numSocio);		
					
					while (rs2.next()) {
						String nomSocio = rs2.getString("NOMBRES");
						System.out.println(nomSocio);
					
						
						while (rs3.next()) {
							String apellSocio = rs3.getString("APELLIDOS");
							System.out.println(apellSocio);
							campoNombre.setText(apellSocio);
							campoNombre.setText(nomSocio+ " "+apellSocio);
							
							
						}
						
					
				}
							
					}
				
			
			
				
						} catch (SQLException e1) {
					
					e1.printStackTrace();
				}	
				
				
				
			}
		});
		btnBuscarSocio.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		btnBuscarSocio.setBounds(166, 121, 130, 31);
		frmCooperativaDeConsumo.getContentPane().add(btnBuscarSocio);
		
		campoCedula = new JTextField();
		campoCedula.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		campoCedula.setHorizontalAlignment(SwingConstants.CENTER);
		campoCedula.setToolTipText("");
		campoCedula.setBounds(180, 61, 96, 31);
		frmCooperativaDeConsumo.getContentPane().add(campoCedula);
		campoCedula.setColumns(10);
		
		campoSocio = new JTextField();
		campoSocio.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		campoSocio.setEditable(false);
		campoSocio.setHorizontalAlignment(SwingConstants.CENTER);
		campoSocio.setColumns(10);
		campoSocio.setBounds(180, 216, 96, 31);
		frmCooperativaDeConsumo.getContentPane().add(campoSocio);
		
		JLabel lblNewLabel = new JLabel("INGRESE C\u00C9DULA DEL SOCIO");
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		lblNewLabel.setBounds(151, 11, 183, 26);
		frmCooperativaDeConsumo.getContentPane().add(lblNewLabel);
		
		JLabel lblNumeroDeSocio = new JLabel("EL SOCIO ES");
		lblNumeroDeSocio.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		lblNumeroDeSocio.setBounds(196, 179, 183, 26);
		frmCooperativaDeConsumo.getContentPane().add(lblNumeroDeSocio);
		
		campoNombre = new JTextField();
		campoNombre.setHorizontalAlignment(SwingConstants.CENTER);
		campoNombre.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		campoNombre.setEditable(false);
		campoNombre.setColumns(10);
		campoNombre.setBounds(92, 258, 280, 31);
		frmCooperativaDeConsumo.getContentPane().add(campoNombre);
		frmCooperativaDeConsumo.setBounds(100, 100, 491, 342);
		frmCooperativaDeConsumo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
