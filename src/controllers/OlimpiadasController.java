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
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gestorBDD.DeportesGestor;
import gestorBDD.DeportistasGestor;
import gestorBDD.EquipoGestor;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import model.Deporte;
import model.Deportista;
import model.Equipo;

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
	private TableView<Deportista> tbViewDeportistas;

	@FXML
	private TableColumn<Deportista, String> tbColumnDeportistaNombre;

	@FXML
	private TableColumn<Deportista, String> tbColumnSexo;

	@FXML
	private TableColumn<Deportista, Integer> tbColumnPeso;

	@FXML
	private TableColumn<Deportista, Integer> tbColumnAltura;

	@FXML
	private TableView<Equipo> tbViewEquipo;

	@FXML
	private TableColumn<Equipo, String> tbColumnEquipoNombre;

	@FXML
	private TableColumn<Equipo, String> tbColumnIniciales;

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

	/**
	 * Gestores de Base de Datos
	 */
	private DeportesGestor deportesGstr;
	private EquipoGestor equipoGstr;
	private DeportistasGestor deportistaGstr;

	private AniadirEditarOlimpiadasController aniadirEditarOlimpiadasController;

	private int indexObjeto;

	private Deporte dprt;

	private Equipo equp;

	private Deportista dprtst;

	/**
	 * Devuelve el Gestor de Deportes
	 * 
	 * @return
	 */
	public DeportesGestor getDeportesGstr() {
		return deportesGstr;
	}

	/**
	 * Devuelve el Gestor de Equipos
	 * 
	 * @return
	 */
	public EquipoGestor getEquipoGestor() {
		return equipoGstr;
	}

	/**
	 * Devuelve el Gestor de Deeportistas
	 * 
	 * @return
	 */
	public DeportistasGestor getDeportistasGestor() {
		return deportistaGstr;
	}

	/**
	 * Selecciona el objeto de la tabla seleccionada.
	 * 
	 * @param event
	 */
	@FXML
	void selectObjeto(MouseEvent event) {
		if (tbViewDeportes.getSelectionModel().getSelectedItem() != null) {
			indexObjeto = tbViewDeportes.getSelectionModel().getSelectedIndex();
			dprt = tbViewDeportes.getItems().get(indexObjeto);
			equp = null;
			dprtst = null;
		}
		if (tbViewEquipo.getSelectionModel().getSelectedItem() != null) {
			indexObjeto = tbViewEquipo.getSelectionModel().getSelectedIndex();
			dprt = null;
			equp = tbViewEquipo.getItems().get(indexObjeto);
			dprtst = null;
		}
		if (tbViewDeportistas.getSelectionModel().getSelectedItem() != null) {
			indexObjeto = tbViewDeportistas.getSelectionModel().getSelectedIndex();
			dprt = null;
			equp = null;
			dprtst = tbViewDeportistas.getItems().get(indexObjeto);
		}
	}

	/**
	 * Habrirá una nueva ventana para añadir un deporte
	 * 
	 * @param event
	 */
	@FXML
	void aniadirDeporte(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/aniadirEditarOlimpiadas.fxml"));
			Parent root = loader.load();
			aniadirEditarOlimpiadasController = loader.getController();
			aniadirEditarOlimpiadasController.setParent(this, null, null, null, "Deporte");

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
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/aniadirEditarOlimpiadas.fxml"));
			Parent root = loader.load();
			aniadirEditarOlimpiadasController = loader.getController();
			aniadirEditarOlimpiadasController.setParent(this, null, null, null, "Deportista");

			Stage agregarStage = new Stage();
			agregarStage.setScene(new Scene(root));
			agregarStage.setResizable(false);
			agregarStage.setTitle("Olimpiadas");
			agregarStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Habrirá una nueva ventana para añadir un equipo
	 * 
	 * @param event
	 */
	@FXML
	void aniadirEquipo(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/aniadirEditarOlimpiadas.fxml"));
			Parent root = loader.load();
			aniadirEditarOlimpiadasController = loader.getController();
			aniadirEditarOlimpiadasController.setParent(this, null, null, null, "Equipo");

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
	 * 
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
					aniadirEditarOlimpiadasController.setParent(this, dprt, null, null, "Deporte");
				}
				if (equp != null) {
					aniadirEditarOlimpiadasController.setParent(this, null, null, equp, "Equipo");
				}
				if (dprtst != null) {
					aniadirEditarOlimpiadasController.setParent(this, null, dprtst, null, "Deportista");
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
	 * 
	 * @param event
	 */
	@FXML
	void eliminarObjeto(ActionEvent event) {
		if (indexObjeto > -1) {
			/* Eliminar Deporte */
			if (dprt != null) {
				deportesGstr.eliminarDeporte(dprt);
				mostrarTablaDeporte(event);
			}
			/* Eliminar Equipo */
			if (equp != null) {
				equipoGstr.eliminarequipo(equp);
				mostrarTablaEquipo(event);
			}
			/* Eliminar Deportista */
			if (dprtst != null) {
				deportistaGstr.eliminarDeportista(dprtst);
				mostrarTablaDeportista(event);
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

	/**
	 * Mustra la tabla de deportistas y su información. Cuando se haga doble click
	 * en una de la filas de la tabla se mostrará l aimagen del deportista si esté
	 * tiene
	 * 
	 * @param event
	 */
	@FXML
	void mostrarTablaDeportista(ActionEvent event) {
		tbViewDeportes.setVisible(false);
		tbViewDeportistas.setVisible(true);
		tbViewEquipo.setVisible(false);
		tbViewEvento.setVisible(false);
		tbViewOlimpiadas.setVisible(false);
		tbViewParticipacion.setVisible(false);

		/**
		 * Evento que cada vez que se haga un doble click en una linea muestre la imagen
		 * en una nueva ventana
		 */
		tbViewDeportistas.setOnMouseClicked(e -> {
			Deportista deportistaSeleccionado = tbViewDeportistas.getSelectionModel().getSelectedItem();
			if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 1) {
				selectObjeto(e);
			}
			if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
				if (deportistaSeleccionado != null) {
					if (deportistaSeleccionado.getFoto() != null) {
						Image image = new Image(deportistaSeleccionado.getFoto());
						ImageView imageView = new ImageView(image);

						StackPane imagePane = new StackPane();
						imagePane.getChildren().add(imageView);

						Scene imageScene = new Scene(imagePane, image.getWidth(), image.getHeight());

						Stage imageStage = new Stage();
						imageStage.setScene(imageScene);
						imageStage.showAndWait();
					}
				}
			}
		});

		tbViewDeportistas.setOnMouseExited(e -> Tooltip.uninstall(tbViewDeportistas, null));

		lblNomTabla.setText("DEPORTISTAS");
		tbColumnDeportistaNombre
				.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombreDeportista()));
		tbColumnSexo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSexo()));
		tbColumnPeso.setCellValueFactory(new PropertyValueFactory<Deportista, Integer>("Peso"));
		tbColumnAltura.setCellValueFactory(new PropertyValueFactory<Deportista, Integer>("Altura"));
		deportistaGstr = new DeportistasGestor();
		tbViewDeportistas.setItems(deportistaGstr.cargarDeportistas());
	}

	/**
	 * Muestra la tabla de equipos y su información
	 * 
	 * @param event
	 */
	@FXML
	void mostrarTablaEquipo(ActionEvent event) {
		tbViewDeportes.setVisible(false);
		tbViewDeportistas.setVisible(false);
		tbViewEquipo.setVisible(true);
		tbViewEvento.setVisible(false);
		tbViewOlimpiadas.setVisible(false);
		tbViewParticipacion.setVisible(false);

		lblNomTabla.setText("EQUIPOS");
		tbColumnEquipoNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getnombreEquipo()));
		tbColumnIniciales.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIniciales()));
		equipoGstr = new EquipoGestor();
		tbViewEquipo.setItems(equipoGstr.cargarEquipos());
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

		deportistaGstr = new DeportistasGestor();

		equipoGstr = new EquipoGestor();

		tbViewDeportes.setItems(deportesGstr.cargarDeportes());

	}
}
