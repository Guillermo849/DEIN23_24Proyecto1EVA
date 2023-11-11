package gestorBDD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBDD;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import model.Equipo;
import model.Evento;
import model.Participacion;

public class ParticipacionesGestor {

	private ConexionBDD conexion;

	/**
	 * Devuelve una lista con los participacions de la Base de Datos
	 * 
	 * @return
	 */
	public ObservableList<Participacion> cargarparticipacions() {

		ObservableList<Participacion> participacions = FXCollections.observableArrayList();
		try {
			conexion = new ConexionBDD();
			String consulta = "SELECT * FROM participacion";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int idparticipacion = rs.getInt("id_participacion");
				Evento evento = obtenerEvento(rs.getInt("id_evento"));
				Equipo equipo = obtenerEquipo(rs.getInt("id_equipo"));
				int edad = rs.getInt("edad");
				String medalla = rs.getString("medalla");
				Participacion p = new Participacion(idparticipacion, evento, equipo, edad, medalla);
				participacions.add(p);
			}
			rs.close();
			conexion.CloseConexion();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return participacions;
	}

	/**
	 * Obtendrá la evento asociada al participacion
	 * 
	 * @param id
	 * @return
	 */
	private Evento obtenerEvento(int id) {
		Evento evento = null;
		try {
			conexion = new ConexionBDD();
			String consulta = "SELECT * FROM evento WHERE id_evento = " + id + ";";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();
			EventosGestor evgstr = new EventosGestor();
			rs.next();
			evento = new Evento(rs.getInt("id_evento"), rs.getString("nombre"), evgstr.obtenerOlimpiada(rs.getInt("id_olimpiada")),
					evgstr.obtenerDeporte(rs.getInt("id_deporte")));
			rs.close();
			conexion.CloseConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return evento;
	}

	/**
	 * Obtendrá el Equipo asociado al participacion
	 * 
	 * @param id
	 * @return
	 */
	private Equipo obtenerEquipo(int id) {
		Equipo equipo = null;
		try {
			conexion = new ConexionBDD();
			String consulta = "SELECT * FROM Equipo WHERE id_Equipo = " + id + ";";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			equipo = new Equipo(rs.getInt("id_Equipo"), rs.getString("nombre"), rs.getString("iniciales"));
			rs.close();
			conexion.CloseConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equipo;
	}

	/**
	 * Inserta un nuevo participacion a la Base de Datos
	 * 
	 * @param participacion
	 */
	public void insertParticipacion(Participacion participacion) {
		try {
			conexion = new ConexionBDD();
			String consulta = "INSERT INTO Participacion(id_evento, id_equipo, edad, medalla) VALUES(?,?,?,?);";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setInt(1, participacion.getEvento().getIdEvento());
			pstmt.setInt(2, participacion.getEquipo().getidEquipo());
			pstmt.setInt(3, participacion.getEdad());
			pstmt.setString(4, participacion.getMedalla());
			pstmt.executeUpdate();

			conexion.CloseConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Modifica un participacion existente en la base de datos
	 * 
	 * @param participacion
	 */
	public void editarParticipacion(Participacion participacion) {
		try {
			conexion = new ConexionBDD();
			String consulta = "UPDATE Participacion SET id_evento = ?, id_equipo = ?, edad = ?, medalla = ? WHERE ID_participacion = "
					+ participacion.getIdParticipacion() + ";";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setInt(1, participacion.getEvento().getIdEvento());
			pstmt.setInt(2, participacion.getEquipo().getidEquipo());
			pstmt.setInt(3, participacion.getEdad());
			pstmt.setString(4, participacion.getMedalla());
			pstmt.executeUpdate();

			conexion.CloseConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Elimina un participacion de la Base de Datos y cualquier dato relacionado a
	 * esté en otras tablas.
	 * 
	 * @param participacion
	 */
	public void eliminarParticipacion(Participacion participacion) {

		int idparticipacion = participacion.getIdParticipacion();

		try {
			conexion = new ConexionBDD();
			String consulta = "DELETE FROM Participacion WHERE ID_participacion = " + idparticipacion + ";";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
