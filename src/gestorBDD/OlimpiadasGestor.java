package gestorBDD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBDD;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import model.Olimpiadas;

public class OlimpiadasGestor {

	private ConexionBDD conexion;

	/**
	 * Devuelve una lista con los Olimpiadas de la Base de Datos
	 * 
	 * @return
	 */
	public ObservableList<Olimpiadas> cargarOlimpiadas() {

		ObservableList<Olimpiadas> olimpiadas = FXCollections.observableArrayList();
		try {
			conexion = new ConexionBDD();
			String consulta = "SELECT * FROM Olimpiada";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int idOlimpiada = rs.getInt("id_olimpiada");
				String nombre = rs.getString("nombre");
				int anio = rs.getInt("anio");
				String temporada= rs.getString("temporada");
				String ciudad = rs.getString("ciudad");
				Olimpiadas p = new Olimpiadas(idOlimpiada, nombre, anio, temporada, ciudad);
				olimpiadas.add(p);
			}
			rs.close();
			conexion.CloseConexion();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return olimpiadas;
	}

	/**
	 * Inserta un nuevo Olimpiada a la Base de Datos
	 * 
	 * @param Olimpiada
	 */
	public void insertOlimpiada(Olimpiadas olimpiada) {
		try {
			conexion = new ConexionBDD();
			String consulta = "INSERT INTO Olimpiada(nombre, anio, temporada, ciudad) VALUES(?,?,?,?);";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setString(1, olimpiada.getNombreOlimpiada());
			pstmt.setInt(2, olimpiada.getAnio());
			pstmt.setString(3, olimpiada.getTemporada());
			pstmt.setString(4, olimpiada.getCiudad());
			pstmt.executeUpdate();
			
			conexion.CloseConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Modifica un Olimpiada existente en la base de datos
	 * 
	 * @param Olimpiada
	 */
	public void editarOlimpiada(Olimpiadas olimpiada) {
		try {
			conexion = new ConexionBDD();
			String consulta = "UPDATE Olimpiada SET nombre = ?, anio = ?, temporada = ?, ciudad = ? WHERE ID_Olimpiada = " + olimpiada.getIdOlimpiada() + ";";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setString(1, olimpiada.getNombreOlimpiada());
			pstmt.setInt(2, olimpiada.getAnio());
			pstmt.setString(3, olimpiada.getTemporada());
			pstmt.setString(4, olimpiada.getCiudad());
			pstmt.executeUpdate();
			
			conexion.CloseConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Elimina un Olimpiada de la Base de Datos y cualquier dato relacionado a esté
	 * en otras tablas.
	 * 
	 * @param Olimpiada
	 */
	public void eliminarOlimpiada(Olimpiadas olimpiada) {

		int idOlimpiada = olimpiada.getIdOlimpiada();

		try {
			conexion = new ConexionBDD();
			String consulta = "DELETE FROM Olimpiada WHERE ID_Olimpiada = " + idOlimpiada + ";";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
