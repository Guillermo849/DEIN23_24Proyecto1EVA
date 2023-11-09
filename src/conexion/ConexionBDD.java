package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

/**
 * Clase que se encargará de conectarnos a la base de datos
 */

public class ConexionBDD {
    private Connection conexion;
    /**
     * Constructor de la clase que crea la conexion a la Base de Datos
     */
    public ConexionBDD()  {
    	try {
    		String baseDatos = "olimpiadasdein";
            String host = "localhost";
            String usuario = "user3";
            String password = "user3";
            String cadenaConexion = "jdbc:mysql://" + host + "/" + baseDatos+ "?serverTimezone=" + TimeZone.getDefault().getID();
            conexion = DriverManager.getConnection(cadenaConexion, usuario, password);
            conexion.setAutoCommit(true);
    	} catch (SQLException e) {
    		e.printStackTrace();
		}
        
    }
    
    /**
     * Devuelve la conexion establecida
     * @return
     */
    public Connection getConexion() {
        return conexion;
    }
    
    /**
     * Cierra la conexion con la Base de Datos
     */
    public void CloseConexion() {
    	try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
