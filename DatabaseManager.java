package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DatabaseManager {

	static Connection con = null;
	static String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
	
	//Definimos la ruta de la BD
	static String url = "jdbc:ucanaccess://C:\\Socios.accdb";
	
	public static Connection obtenerConexionBD() {
		
		try {
			if (con==null) {
				Class.forName(driver);
				con = DriverManager.getConnection(url);
				
	//Un mensaje que indica si se encontró la BD.			
				
				JOptionPane.showMessageDialog(null, "Conexion correcta");
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
			
			
		con = null;
		}
		return con;
		
	
	}
	
		
	
}
