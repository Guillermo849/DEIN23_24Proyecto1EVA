package gestorBDD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBDD;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import model.Deporte;
import model.Evento;
import model.Olimpiadas;

public class EventosGestor {

	private ConexionBDD conexion;

	/**
	 * Devuelve una lista con los Eventos de la Base de Datos
	 * 
	 * @return
	 */
	public ObservableList<Evento> cargarEventos() {

		ObservableList<Evento> eventos = FXCollections.observableArrayList();
		try {
			conexion = new ConexionBDD();
			String consulta = "SELECT * FROM Evento";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int idEvento = rs.getInt("id_Evento");
				String nombre = rs.getString("nombre");
				Olimpiadas olimpiada = obtenerOlimpiada(rs.getInt("id_olimpiada"));
				Deporte deporte = obtenerDeporte(rs.getInt("id_deporte"));
				Evento p = new Evento(idEvento, nombre, olimpiada, deporte);
				eventos.add(p);
			}
			rs.close();
			conexion.CloseConexion();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eventos;
	}

	/**
	 * Obtendrá la Olimpiada asociada al evento
	 * 
	 * @param id
	 * @return
	 */
	public Olimpiadas obtenerOlimpiada(int id) {
		Olimpiadas olimpiada = null;
		try {
			conexion = new ConexionBDD();
			String consulta = "SELECT * FROM olimpiada WHERE id_olimpiada = " + id + ";";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			olimpiada = new Olimpiadas(rs.getInt("id_olimpiada"), rs.getString("nombre"), rs.getInt("anio"),
					rs.getString("temporada"), rs.getString("ciudad"));
			rs.close();
			conexion.CloseConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return olimpiada;
	}

	/**
	 * Obtendrá el Deporte asociado al evento
	 * 
	 * @param id
	 * @return
	 */
	public Deporte obtenerDeporte(int id) {
		Deporte deporte = null;
		try {
			conexion = new ConexionBDD();
			String consulta = "SELECT * FROM deporte WHERE id_deporte = " + id + ";";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			deporte = new Deporte(rs.getInt("id_deporte"), rs.getString("nombre"));
			rs.close();
			conexion.CloseConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deporte;
	}

	/**
	 * Inserta un nuevo Evento a la Base de Datos
	 * 
	 * @param Evento
	 */
	public void insertEvento(Evento evento) {
		try {
			conexion = new ConexionBDD();
			String consulta = "INSERT INTO evento(nombre, id_olimpiada, id_deporte) VALUES(?,?,?);";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setString(1, evento.getNombreEvento());
			pstmt.setInt(2, evento.getOlimpiada().getIdOlimpiada());
			pstmt.setInt(3, evento.getDeporte().getIdDeporte());
			pstmt.executeUpdate();

			conexion.CloseConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Modifica un Evento existente en la base de datos
	 * 
	 * @param Evento
	 */
	public void editarEvento(Evento evento) {
		try {
			conexion = new ConexionBDD();
			String consulta = "UPDATE evento SET nombre = ?, id_olimpiada = ?, id_deporte = ? WHERE ID_Evento = " + evento.getIdEvento() + ";";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setString(1, evento.getNombreEvento());
			pstmt.setInt(2, evento.getOlimpiada().getIdOlimpiada());
			pstmt.setInt(3, evento.getDeporte().getIdDeporte());
			pstmt.executeUpdate();

			conexion.CloseConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Elimina un Evento de la Base de Datos y cualquier dato relacionado a esté en
	 * otras tablas.
	 * 
	 * @param Evento
	 */
	public void eliminarEvento(Evento evento) {

		int idEvento = evento.getIdEvento();

		try {
			conexion = new ConexionBDD();
			String consulta = "DELETE FROM Evento WHERE ID_Evento = " + idEvento + ";";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
