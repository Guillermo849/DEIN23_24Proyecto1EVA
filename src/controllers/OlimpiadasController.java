package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
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
	
	private AniadirEditarOlimpiadasController aniadirEditarOlimpiadasController;
	
	private int indexObjeto;
	
	private Deporte dprt;
	
	/**
	 * Devuelve el Gestor de Deportes
	 * @return
	 */
	public DeportesGestor getDeportesGstr() {
		return deportesGstr;
	}
	
	/**
	 * Selecciona el objeto de la tabla seleccionada.
	 * @param event
	 */
	@FXML
	void selectObjeto(MouseEvent event) {
		if (tbViewDeportes.getSelectionModel().getSelectedItem() != null) {
			indexObjeto = tbViewDeportes.getSelectionModel().getSelectedIndex();
			dprt = tbViewDeportes.getItems().get(indexObjeto);
		}
	}
	
	/**
	 * Habrirá una nueva ventana para añadir un deporte
	 * @param event
	 */
	@FXML
	void aniadirDeporte(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/aniadirEditarOlimpiadas.fxml"));
			Parent root = loader.load();
			/* Le dice a la nueva ventana cual es su ventana padre */
			aniadirEditarOlimpiadasController = loader.getController();
			aniadirEditarOlimpiadasController.setParent(this, null);

			Stage agregarStage = new Stage();
			agregarStage.setScene(new Scene(root));
			agregarStage.setResizable(false);
			agregarStage.setTitle("Olimpiadas");
			agregarStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	/**
	 * Edita el objeto seleccionado de las tablas
	 * @param event
	 */
	@FXML
	void editarObjeto(ActionEvent event) {
		if (indexObjeto > -1) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/aniadirEditarOlimpiadas.fxml"));
				Parent root = loader.load();
				aniadirEditarOlimpiadasController = loader.getController();
				if (dprt != null) {
					aniadirEditarOlimpiadasController.setParent(this, dprt);
				}
				
				Stage agregarStage = new Stage();
				agregarStage.setScene(new Scene(root));
				agregarStage.setResizable(false);
				agregarStage.setTitle("Olimpiadas");
				agregarStage.showAndWait();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Elimina el objeto seleccionado de las tablas
	 * @param event
	 */
	@FXML
	void eliminarObjeto(ActionEvent event) {
		if (indexObjeto > -1) {
			if (dprt != null) {
				deportesGstr.eliminarDeporte(dprt);
				mostrarTablaDeporte(event);
			}
		}
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