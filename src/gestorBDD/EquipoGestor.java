package gestorBDD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBDD;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import model.Equipo;

public class EquipoGestor {

	private ConexionBDD conexion;

	/**
	 * Devuelve una lista con los equipos de la Base de Datos
	 * 
	 * @return
	 */
	public ObservableList<Equipo> cargarEquipos() {

		ObservableList<Equipo> equipos = FXCollections.observableArrayList();
		try {
			conexion = new ConexionBDD();
			String consulta = "SELECT * FROM equipo";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int idequipo = rs.getInt("id_equipo");
				String nombre = rs.getString("nombre");
				String iniciales = rs.getString("iniciales");
				Equipo p = new Equipo(idequipo, nombre, iniciales);
				equipos.add(p);
			}
			rs.close();
			conexion.CloseConexion();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equipos;
	}

	/**
	 * Inserta un nuevo equipo a la Base de Datos
	 * 
	 * @param equipo
	 */
	public void insertequipo(Equipo equipo) {

		String nom = equipo.getnombreEquipo();

		try {
			conexion = new ConexionBDD();
			String consulta = "INSERT INTO equipo(nombre, iniciales) VALUES('" + nom + "', '" + equipo.getIniciales() +"');";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();
			
			conexion.CloseConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Modifica un equipo existente en la base de datos
	 * 
	 * @param equipo
	 */
	public void editarequipo(Equipo equipo) {

		String nom = equipo.getnombreEquipo();
		String iniciales = equipo.getIniciales();

		try {
			conexion = new ConexionBDD();
			String consulta = "UPDATE equipo SET nombre = '" + nom + "', iniciales = '" + iniciales + "' WHERE id_equipo = " + equipo.getidEquipo() + ";";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();
			
			conexion.CloseConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Elimina un equipo de la Base de Datos y cualquier dato relacionado a esté
	 * en otras tablas.
	 * 
	 * @param equipo
	 */
	public void eliminarequipo(Equipo equipo) {

		int idequipo = equipo.getidEquipo();

		try {
			conexion = new ConexionBDD();
			String consulta = "DELETE FROM equipo WHERE id_equipo = " + idequipo + ";";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
