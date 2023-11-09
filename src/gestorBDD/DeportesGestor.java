package gestorBDD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBDD;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import model.Deporte;

public class DeportesGestor {

	private ConexionBDD conexion;

	/**
	 * Devuelve una lista con los deportes de la Base de Datos
	 * 
	 * @return
	 */
	public ObservableList<Deporte> cargarDeportes() {

		ObservableList<Deporte> deportes = FXCollections.observableArrayList();
		try {
			conexion = new ConexionBDD();
			String consulta = "SELECT * FROM deporte";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int idDeporte = rs.getInt("id_deporte");
				String nombre = rs.getString("nombre");
				Deporte p = new Deporte(idDeporte, nombre);
				deportes.add(p);
			}
			rs.close();
			conexion.CloseConexion();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deportes;
	}

	/**
	 * Inserta un nuevo deporte a la Base de Datos
	 * 
	 * @param deporte
	 */
	public void insertDeporte(Deporte deporte) {

		String nom = deporte.getNombreDeporte();

		try {
			conexion = new ConexionBDD();
			String consulta = "INSERT INTO deporte(nombre) VALUES('" + nom + "');";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Modifica un deporte existente en la base de datos
	 * 
	 * @param deporte
	 */
	public void editarDeporte(Deporte deporte) {

		String nom = deporte.getNombreDeporte();

		try {
			conexion = new ConexionBDD();
			String consulta = "UPDATE deporte SET nombre = '" + nom + "' WHERE ID_deporte = " + deporte.getIdDeporte() + ";";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Elimina un deporte de la Base de Datos y cualquier dato relacionado a esté
	 * en otras tablas.
	 * 
	 * @param deporte
	 */
	public void eliminarDeporte(Deporte deporte) {

		int idDeporte = deporte.getIdDeporte();

		try {
			conexion = new ConexionBDD();
			String consulta = "DELETE FROM deporte WHERE ID_deporte = " + idDeporte + ";";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
