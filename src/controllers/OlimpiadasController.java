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
import gestorBDD.EventosGestor;
import gestorBDD.OlimpiadasGestor;
import gestorBDD.ParticipacionesGestor;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import model.Deporte;
import model.Deportista;
import model.Equipo;
import model.Evento;
import model.Olimpiadas;
import model.Participacion;

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
	private TableView<Evento> tbViewEvento;

	@FXML
	private TableColumn<Evento, String> tbColumnEventoNombre;

	@FXML
	private TableColumn<Evento, Olimpiadas> tbColumnOlimpiada;

	@FXML
	private TableColumn<Evento, Deporte> tbColumnDeporte;

	@FXML
	private TableView<Olimpiadas> tbViewOlimpiadas;

	@FXML
	private TableColumn<Olimpiadas, String> tbColumnOlimpiadaNombre;

	@FXML
	private TableColumn<Olimpiadas, Integer> tbColumnAnio;

	@FXML
	private TableColumn<Olimpiadas, String> tbColumnTemporada;

	@FXML
	private TableColumn<Olimpiadas, String> tbColumnCiudad;

	@FXML
	private TableView<Participacion> tbViewParticipacion;

	@FXML
	private TableColumn<Participacion, Deportista> tbColumnDeportista;

	@FXML
	private TableColumn<Participacion, Evento> tbColumnEvento;

	@FXML
	private TableColumn<Participacion, Equipo> tbColumnEquipo;

	@FXML
	private TableColumn<Participacion, Integer> tbColumnEdad;

	@FXML
	private TableColumn<Participacion, String> tbColumnMedalla;

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
	private OlimpiadasGestor olimpiadasGstr;
	private EventosGestor eventosGstr;
	private ParticipacionesGestor participacionesGstr;

	private AniadirEditarOlimpiadasController aniadirEditarOlimpiadasController;

	private int indexObjeto;

	private Deporte dprt;
	private Equipo equp;
	private Deportista dprtst;
	private Olimpiadas olmpd;
	private Evento evnt;
	private Participacion prtcpcn;

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
	 * Devuelve el Gestor de Olimpiadas
	 * 
	 * @return
	 */
	public OlimpiadasGestor getOlimpiadasGstr() {
		return olimpiadasGstr;
	}

	/**
	 * Devuelve el Gestor de Eventos
	 * 
	 * @return
	 */
	public EventosGestor getEventosGstr() {
		return eventosGstr;
	}

	/**
	 * Devuelve el Gestor de Participaciones
	 * 
	 * @return
	 */
	public ParticipacionesGestor getParticipacionesGstr() {
		return participacionesGstr;
	}

	/**
	 * Selecciona el objeto de la tabla seleccionada.
	 * 
	 * @param event
	 */
	@FXML
	void selectObjeto(MouseEvent event) {
		dprt = null;
		equp = null;
		dprtst = null;
		olmpd = null;
		prtcpcn = null;

		if (tbViewDeportes.getSelectionModel().getSelectedItem() != null) {
			indexObjeto = tbViewDeportes.getSelectionModel().getSelectedIndex();
			dprt = tbViewDeportes.getItems().get(indexObjeto);
		}
		if (tbViewEquipo.getSelectionModel().getSelectedItem() != null) {
			indexObjeto = tbViewEquipo.getSelectionModel().getSelectedIndex();
			equp = tbViewEquipo.getItems().get(indexObjeto);
		}
		if (tbViewDeportistas.getSelectionModel().getSelectedItem() != null) {
			indexObjeto = tbViewDeportistas.getSelectionModel().getSelectedIndex();
			dprtst = tbViewDeportistas.getItems().get(indexObjeto);
		}

		if (tbViewOlimpiadas.getSelectionModel().getSelectedItem() != null) {
			indexObjeto = tbViewOlimpiadas.getSelectionModel().getSelectedIndex();
			olmpd = tbViewOlimpiadas.getItems().get(indexObjeto);
		}
		if (tbViewEvento.getSelectionModel().getSelectedItem() != null) {
			indexObjeto = tbViewEvento.getSelectionModel().getSelectedIndex();
			evnt = tbViewEvento.getItems().get(indexObjeto);
		}
		if (tbViewParticipacion.getSelectionModel().getSelectedItem() != null) {
			indexObjeto = tbViewParticipacion.getSelectionModel().getSelectedIndex();
			prtcpcn = tbViewParticipacion.getItems().get(indexObjeto);
		}
	}

	/**
	 * Abrirá una ventana nueva para añadir el tipo de objeto que le hemos pasado
	 * 
	 * @param tipo
	 */
	private void abrirAniadir(String tipo) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/aniadirEditarOlimpiadas.fxml"));
			Parent root = loader.load();
			aniadirEditarOlimpiadasController = loader.getController();
			aniadirEditarOlimpiadasController.setParent(this, null, null, null, null, null, null, tipo);

			Stage agregarStage = new Stage();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/css/flatered.css").toExternalForm());
			agregarStage.setScene(scene);
			agregarStage.setResizable(false);
			agregarStage.setTitle("Olimpiadas");
			agregarStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Habrirá una nueva ventana para añadir un deporte
	 * 
	 * @param event
	 */
	@FXML
	void aniadirDeporte(ActionEvent event) {
		abrirAniadir("Deporte");
	}

	/**
	 * Habrirá una nueva ventana para añadir un deportista
	 * 
	 * @param event
	 */
	@FXML
	void aniadirDeportista(ActionEvent event) {
		abrirAniadir("Deportista");
	}

	/**
	 * Habrirá una nueva ventana para añadir un equipo
	 * 
	 * @param event
	 */
	@FXML
	void aniadirEquipo(ActionEvent event) {
		abrirAniadir("Equipo");
	}

	/**
	 * Habrirá una nueva ventana para añadir un evento
	 * 
	 * @param event
	 */
	@FXML
	void aniadirEvento(ActionEvent event) {
		abrirAniadir("Evento");
	}

	/**
	 * Habrirá una nueva ventana para añadir una olimpiada
	 * 
	 * @param event
	 */
	@FXML
	void aniadirOlimpiada(ActionEvent event) {
		abrirAniadir("Olimpiada");
	}

	/**
	 * Habrirá una nueva ventana para añadir un evento
	 * 
	 * @param event
	 */
	@FXML
	void aniadirParticipacion(ActionEvent event) {
		abrirAniadir("Participacion");
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
					aniadirEditarOlimpiadasController.setParent(this, dprt, null, null, null, null, null, "Deporte");
				}
				if (equp != null) {
					aniadirEditarOlimpiadasController.setParent(this, null, null, equp, null, null, null, "Equipo");
				}
				if (dprtst != null) {
					aniadirEditarOlimpiadasController.setParent(this, null, dprtst, null, null, null, null,
							"Deportista");
				}
				if (olmpd != null) {
					aniadirEditarOlimpiadasController.setParent(this, null, null, null, olmpd, null, null, "Olimpiada");
				}
				if (evnt != null) {
					aniadirEditarOlimpiadasController.setParent(this, null, null, null, null, evnt, null, "Evento");
				}
				if (prtcpcn != null) {
					aniadirEditarOlimpiadasController.setParent(this, null, null, null, null, null, prtcpcn,
							"Participacion");
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
			/* Eliminar Olimpiada */
			if (olmpd != null) {
				olimpiadasGstr.eliminarOlimpiada(olmpd);
				mostrarTablaOlimpiada(event);
			}
			/* Eliminar Evento */
			if (evnt != null) {
				eventosGstr.eliminarEvento(evnt);
				mostrarTablaEvento(event);
			}
			/* Eliminar Participacion */
			if (prtcpcn != null) {
				participacionesGstr.eliminarParticipacion(prtcpcn);
				mostrarTablaParticipacion(event);
			}
		}
	}
	
	/**
	 * Metodo que muestra la tabla del tipo pasado
	 * @param tabla
	 */
	private void mostrarTabla(String tabla) {

		tbViewDeportes.setVisible(false);
		tbViewDeportistas.setVisible(false);
		tbViewEquipo.setVisible(false);
		tbViewEvento.setVisible(false);
		tbViewOlimpiadas.setVisible(false);
		tbViewParticipacion.setVisible(false);

		if (tabla.equals("Deporte"))
			tbViewDeportes.setVisible(true);
		if (tabla.equals("Deportista"))
			tbViewDeportistas.setVisible(true);
		if (tabla.equals("Equipo"))
			tbViewEquipo.setVisible(true);
		if (tabla.equals("Evento"))
			tbViewEvento.setVisible(true);
		if (tabla.equals("Olimpiada"))
			tbViewOlimpiadas.setVisible(true);
		if (tabla.equals("Participacion"))
			tbViewParticipacion.setVisible(true);

	}

	/**
	 * Muestra la tabla de deportes y su información
	 * 
	 * @param event
	 */
	@FXML
	void mostrarTablaDeporte(ActionEvent event) {
		mostrarTabla("Deporte");
		lblNomTabla.setText("DEPORTES");
		tbColumnDeporteNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombreDeporte()));
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
		mostrarTabla("Deportista");

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
		tbViewDeportistas.setItems(deportistaGstr.cargarDeportistas());
	}

	/**
	 * Muestra la tabla de equipos y su información
	 * 
	 * @param event
	 */
	@FXML
	void mostrarTablaEquipo(ActionEvent event) {
		mostrarTabla("Equipo");

		lblNomTabla.setText("EQUIPOS");
		tbColumnEquipoNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getnombreEquipo()));
		tbColumnIniciales.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIniciales()));
		tbViewEquipo.setItems(equipoGstr.cargarEquipos());
	}

	/**
	 * Muestra la tabla de eventos y su información
	 * 
	 * @param event
	 */
	@FXML
	void mostrarTablaEvento(ActionEvent event) {
		mostrarTabla("Evento");

		lblNomTabla.setText("EVENTOS");
		tbColumnEventoNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombreEvento()));
		tbColumnOlimpiada.setCellValueFactory(new PropertyValueFactory<Evento, Olimpiadas>("Olimpiada"));
		tbColumnDeporte.setCellValueFactory(new PropertyValueFactory<Evento, Deporte>("Deporte"));
		tbViewEvento.setItems(eventosGstr.cargarEventos());
	}

	/**
	 * Muestra la tabla de olimpiadas y su información
	 * 
	 * @param event
	 */
	@FXML
	void mostrarTablaOlimpiada(ActionEvent event) {
		mostrarTabla("Olimpiada");

		lblNomTabla.setText("OLIMPIADAS");
		tbColumnOlimpiadaNombre
				.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombreOlimpiada()));
		tbColumnAnio.setCellValueFactory(new PropertyValueFactory<Olimpiadas, Integer>("Anio"));
		tbColumnTemporada.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTemporada()));
		tbColumnCiudad.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCiudad()));
		tbViewOlimpiadas.setItems(olimpiadasGstr.cargarOlimpiadas());
	}

	/**
	 * Muestra la tabla de participacion y su información
	 * 
	 * @param event
	 */
	@FXML
	void mostrarTablaParticipacion(ActionEvent event) {
		mostrarTabla("Participacion");

		lblNomTabla.setText("PARTICIPACIONES");
		tbColumnDeportista.setCellValueFactory(new PropertyValueFactory<Participacion, Deportista>("Deportista"));
		tbColumnEvento.setCellValueFactory(new PropertyValueFactory<Participacion, Evento>("Evento"));
		tbColumnEquipo.setCellValueFactory(new PropertyValueFactory<Participacion, Equipo>("Equipo"));
		tbColumnEdad.setCellValueFactory(new PropertyValueFactory<Participacion, Integer>("Edad"));
		tbColumnMedalla.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMedalla()));
		tbViewParticipacion.setItems(participacionesGstr.cargarParticipacions());
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
		olimpiadasGstr = new OlimpiadasGestor();
		eventosGstr = new EventosGestor();
		participacionesGstr = new ParticipacionesGestor();

		tbViewDeportes.setItems(deportesGstr.cargarDeportes());

	}
}
