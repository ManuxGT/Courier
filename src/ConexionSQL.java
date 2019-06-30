
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConexionSQL {
	private static Connection conexion;
	public static Connection getConnection() {
	try {
		//1. Creamos la conexion
		Class.forName("com.mysql.cj.jdbc.Driver");
		conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/administrador?autoReconnect=true&useSSL=false&maxReconnects=10&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","12345");
		//2. Creamos el objeto statement para consultar poder usar su metodo executequery.
		//3. Crear un objeto resultset para guardar lo que nos envie el query y usamos el metodo executequery para ejecutar el query
		/*4. Recorrer el resultset
		while(resultado.next()) {
			System.out.println(resultado.getString("USUARIO") + "" + resultado.getString("CONTRASEÑA")+ "");
		}*/
		
	}catch(Exception e) {
		System.out.println("Error");
		System.out.println(e);
		
	}
		
		
		
		return conexion;
	}
}
