package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

import gestorBDD.DeportesGestor;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import model.Deporte;

/**
 * Programa que mustra la información de la base de datos de la olimpiada. Esta
 * información también se podrá modificar y eliminar.
 */
public class OlimpiadasController implements Initializable {

	@FXML
	private Menu menuAniadir;

	@FXML
	private MenuItem mnItemDeporte;

	@FXML
	private MenuItem mnItemDeportista;

	@FXML
	private MenuItem mnItemEquipo;

	@FXML
	private MenuItem mnItemEvento;

	@FXML
	private MenuItem mnItemOlimpiada;

	@FXML
	private MenuItem mnItemParticipacion;

	@FXML
	private Menu menuEditar;

	@FXML
	private MenuItem mnItemEditar;

	@FXML
	private MenuItem mnItemEliminar;

	@FXML
	private Menu menuIdioma;

	@FXML
	private MenuItem mnItemEspaniol;

	@FXML
	private MenuItem mnItemIngles;

	@FXML
	private Label lblNomTabla;

	@FXML
	private TableView<Deporte> tbViewDeportes;

	@FXML
	private TableColumn<Deporte, String> tbColumnDeporteNombre;

	@FXML
	private TableView<?> tbViewDeportistas;

	@FXML
	private TableColumn<?, ?> tbColumnDeportistaNombre;

	@FXML
	private TableColumn<?, ?> tbColumnSexo;

	@FXML
	private TableColumn<?, ?> tbColumnPeso;

	@FXML
	private TableColumn<?, ?> tbColumnAltura;

	@FXML
	private TableView<?> tbViewEquipo;

	@FXML
	private TableColumn<?, ?> tbColumnEquipoNombre;

	@FXML
	private TableColumn<?, ?> tbColumnIniciales;

	@FXML
	private TableView<?> tbViewEvento;

	@FXML
	private TableColumn<?, ?> tbColumnEventoNombre;

	@FXML
	private TableColumn<?, ?> tbColumnOlimpiada;

	@FXML
	private TableColumn<?, ?> tbColumnDeporte;

	@FXML
	private TableView<?> tbViewOlimpiadas;

	@FXML
	private TableColumn<?, ?> tbColumnOlimpiadaNombre;

	@FXML
	private TableColumn<?, ?> tbColumnAnio;

	@FXML
	private TableColumn<?, ?> tbColumnTemporada;

	@FXML
	private TableColumn<?, ?> tbColumnCiudad;

	@FXML
	private TableView<?> tbViewParticipacion;

	@FXML
	private TableColumn<?, ?> tbColumnDeportista;

	@FXML
	private TableColumn<?, ?> tbColumnEvento;

	@FXML
	private TableColumn<?, ?> tbColumnEquipo;

	@FXML
	private TableColumn<?, ?> tbColumnEdad;

	@FXML
	private TableColumn<?, ?> tbColumnMedalla;

	@FXML
	private Button btnDeporte;

	@FXML
	private Button btnDeportista;

	@FXML
	private Button btnEquipo;

	@FXML
	private Button btnEvento;

	@FXML
	private Button btnOlimpiada;

	@FXML
	private Button btnParticipacion;

	private DeportesGestor deportesGstr;

	@FXML
	void selectObjeto(MouseEvent event) {

	}

	@FXML
	void aniadirDeporte(ActionEvent event) {

	}

	@FXML
	void aniadirDeportista(ActionEvent event) {

	}

	@FXML
	void aniadirEquipo(ActionEvent event) {

	}

	@FXML
	void aniadirEvento(ActionEvent event) {

	}

	@FXML
	void aniadirOlimpiada(ActionEvent event) {

	}

	@FXML
	void aniadirParticipacion(ActionEvent event) {

	}

	@FXML
	void cambiarIdiomaAEnglish(ActionEvent event) {

	}

	@FXML
	void cambiarIdiomaAEspaniol(ActionEvent event) {

	}

	@FXML
	void editarObjeto(ActionEvent event) {

	}

	@FXML
	void eliminarObjeto(ActionEvent event) {

	}

	/**
	 * Muestra la tabla de deportes y su información
	 * 
	 * @param event
	 */
	@FXML
	void mostrarTablaDeporte(ActionEvent event) {
		tbViewDeportes.setVisible(true);
		tbViewDeportistas.setVisible(false);
		tbViewEquipo.setVisible(false);
		tbViewEvento.setVisible(false);
		tbViewOlimpiadas.setVisible(false);
		tbViewParticipacion.setVisible(false);

		lblNomTabla.setText("DEPORTES");

		tbColumnDeporteNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombreDeporte()));

		deportesGstr = new DeportesGestor();

		tbViewDeportes.setItems(deportesGstr.cargarDeportes());

	}

	@FXML
	void mostrarTablaDeportista(ActionEvent event) {

	}

	@FXML
	void mostrarTablaEquipo(ActionEvent event) {

	}

	@FXML
	void mostrarTablaEvento(ActionEvent event) {

	}

	@FXML
	void mostrarTablaOlimpiada(ActionEvent event) {

	}

	@FXML
	void mostrarTablaParticipacion(ActionEvent event) {

	}

	/**
	 * Se inicializará la aplicación mostrando la tabla de deportes
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		lblNomTabla.setText("DEPORTES");

		tbColumnDeporteNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombreDeporte()));

		deportesGstr = new DeportesGestor();

		tbViewDeportes.setItems(deportesGstr.cargarDeportes());

	}
}
