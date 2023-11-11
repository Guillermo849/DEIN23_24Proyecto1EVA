package gestorBDD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBDD;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import model.Deportista;
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
	public ObservableList<Participacion> cargarParticipacions() {

		ObservableList<Participacion> participacions = FXCollections.observableArrayList();
		try {
			conexion = new ConexionBDD();
			String consulta = "SELECT * FROM participacion";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Deportista deportista = obtenerDeportista(rs.getInt("id_deportista"));
				Evento evento = obtenerEvento(rs.getInt("id_evento"));
				Equipo equipo = obtenerEquipo(rs.getInt("id_equipo"));
				int edad = rs.getInt("edad");
				String medalla = rs.getString("medalla");
				Participacion p = new Participacion(deportista, evento, equipo, edad, medalla);
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
	 * Obtendrá el deportista asociado al participacion
	 * 
	 * @param id
	 * @return
	 */
	private Deportista obtenerDeportista(int id) {
		Deportista deportista = null;
		try {
			conexion = new ConexionBDD();
			String consulta = "SELECT * FROM deportista WHERE id_deportista = " + id + ";";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			deportista  = new Deportista(rs.getInt("id_deportista"), rs.getString("nombre"), rs.getString("sexo"), rs.getInt("altura"), rs.getInt("peso"), rs.getBinaryStream("foto"));
			rs.close();
			conexion.CloseConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deportista;
	}

	/**
	 * Inserta un nuevo participacion a la Base de Datos
	 * 
	 * @param participacion
	 */
	public void insertParticipacion(Participacion participacion) {
		try {
			conexion = new ConexionBDD();
			String consulta = "INSERT INTO Participacion VALUES(?,?,?,?,?);";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setInt(1, participacion.getDeportista().getIdDeportista());
			pstmt.setInt(2, participacion.getEvento().getIdEvento());
			pstmt.setInt(3, participacion.getEquipo().getidEquipo());
			pstmt.setInt(4, participacion.getEdad());
			pstmt.setString(5, participacion.getMedalla());
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
	public void editarParticipacion(Participacion viejaParticipacion,Participacion nuevaParticipacion) {
		try {
			conexion = new ConexionBDD();
			String consulta = "UPDATE Participacion SET id_deportista = ?, id_evento = ?, id_equipo = ?, edad = ?, medalla = ? WHERE id_deportista = ? AND id_evento = ? AND id_equipo = ?;";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setInt(1, nuevaParticipacion.getDeportista().getIdDeportista());
			pstmt.setInt(2, nuevaParticipacion.getEvento().getIdEvento());
			pstmt.setInt(3, nuevaParticipacion.getEquipo().getidEquipo());
			pstmt.setInt(4, nuevaParticipacion.getEdad());
			pstmt.setString(5, nuevaParticipacion.getMedalla());
			pstmt.setInt(6, viejaParticipacion.getDeportista().getIdDeportista());
			pstmt.setInt(7, viejaParticipacion.getEvento().getIdEvento());
			pstmt.setInt(8, viejaParticipacion.getEquipo().getidEquipo());
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

		try {
			conexion = new ConexionBDD();
			String consulta = "DELETE FROM Participacion WHERE id_deportista = ? AND id_evento = ? AND id_equipo = ?;";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setInt(1, participacion.getDeportista().getIdDeportista());
			pstmt.setInt(2, participacion.getEvento().getIdEvento());
			pstmt.setInt(3, participacion.getEquipo().getidEquipo());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
