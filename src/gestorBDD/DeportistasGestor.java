package gestorBDD;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBDD;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import model.Deportista;

public class DeportistasGestor {

	private ConexionBDD conexion;

	/**
	 * Devuelve una lista con los Deportistas de la Base de Datos
	 * 
	 * @return
	 */
	public ObservableList<Deportista> cargarDeportistas() {

		ObservableList<Deportista> Deportistas = FXCollections.observableArrayList();
		try {
			conexion = new ConexionBDD();
			String consulta = "SELECT * FROM deportista";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int idDeportista = rs.getInt("id_deportista");
				String nombre = rs.getString("nombre");
				String sexo = rs.getString("sexo");
				int altura = rs.getInt("altura");
				int peso = rs.getInt("peso");
				InputStream imgStream = rs.getBinaryStream("foto");

				Deportista p = new Deportista(idDeportista, nombre, sexo, altura, peso, imgStream);
				Deportistas.add(p);
			}
			rs.close();
			conexion.CloseConexion();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Deportistas;
	}

	/**
	 * Inserta un nuevo Deportista a la Base de Datos
	 * 
	 * @param Deportista
	 */
	public void insertDeportista(Deportista deportista) {

		try {
			conexion = new ConexionBDD();
			String consulta = "INSERT INTO deportista(nombre, sexo, peso, altura, foto) VALUES(?,?,?,?,?);";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setString(1, deportista.getNombreDeportista());
			pstmt.setString(2, deportista.getSexo());
			pstmt.setInt(3, deportista.getPeso());
			pstmt.setInt(4, deportista.getAltura());
			pstmt.setBinaryStream(5, deportista.getFoto());
			pstmt.executeUpdate();

			conexion.CloseConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Modifica un Deportista existente en la base de datos
	 * 
	 * @param Deportista
	 */
	public void editarDeportista(Deportista deportista) {
		try {
			conexion = new ConexionBDD();
			String consulta = "UPDATE deportista SET nombre = ?, sexo = ?, peso = ?, altura = ?, foto = ? WHERE ID_Deportista = "
					+ deportista.getIdDeportista() + ";";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setString(1, deportista.getNombreDeportista());
			pstmt.setString(2, deportista.getSexo());
			pstmt.setInt(3, deportista.getPeso());
			pstmt.setInt(4, deportista.getAltura());
			pstmt.setBinaryStream(5, deportista.getFoto());
			pstmt.executeUpdate();

			conexion.CloseConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Elimina un Deportista de la Base de Datos y cualquier dato relacionado a esté
	 * en otras tablas.
	 * 
	 * @param Deportista
	 */
	public void eliminarDeportista(Deportista deportista) {
		int idDeportista = deportista.getIdDeportista();
		try {
			conexion = new ConexionBDD();
			String consulta = "DELETE FROM deportista WHERE id_deportista = " + idDeportista + ";";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();
			
			conexion.CloseConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
