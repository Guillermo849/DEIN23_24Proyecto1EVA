package controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.Deporte;
import model.Deportista;
import model.Equipo;
import model.Evento;
import model.Olimpiadas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Paths;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class AniadirEditarOlimpiadasController {

	@FXML
	private Button btnGuardar;

	@FXML
	private Button btnCancelar;

	@FXML
	private Label lblTituloAniadirEditar;

	@FXML
	private Label lblDato1;

	@FXML
	private Label lblDato2;

	@FXML
	private Label lblDato3;

	@FXML
	private Label lblDato4;

	@FXML
	private Label lblDato5;

	@FXML
	private TextField txtFEdad;

	@FXML
	private TextField txtFMedalla;

	@FXML
	private ComboBox<?> cmbxDeportista;

	@FXML
	private ComboBox<?> cmbxEvento;

	@FXML
	private ComboBox<?> cmbxEquipo;

	@FXML
	private TextField txtFNombre;

	@FXML
	private TextField txtFAnio;

	@FXML
	private TextField txtFCiudad;

	@FXML
	private ComboBox<String> cmbxTemporada;

	@FXML
	private ComboBox<Olimpiadas> cmbxOlimpiada;

	@FXML
	private ComboBox<Deporte> cmbxDeporte;

	@FXML
	private TextField txtFIniciales;

	@FXML
	private ComboBox<String> cmbxSexo;

	@FXML
	private TextField txtFPeso;

	@FXML
	private TextField txtFAltura;

	@FXML
	private Button btnSubirImagen;

	@FXML
	private Label lblNombreImagen;

	private OlimpiadasController mainController;

	private Deporte deporte;

	private Equipo equipo;

	private Deportista deportista;

	private Olimpiadas olimpiadas;

	private Evento evento;

	private String obj;

	private InputStream img;

	/**
	 * Indicará cual es su padre y que objeto le han pasado
	 * 
	 * @param parent
	 * @param deporte
	 */
	public void setParent(OlimpiadasController parent, Deporte deporte, Deportista deportista, Equipo equipo,
			Olimpiadas olimpiadas, Evento evento, String objeto) {
		this.mainController = parent;
		this.deporte = deporte;
		this.equipo = equipo;
		this.deportista = deportista;
		this.olimpiadas = olimpiadas;
		this.evento = evento;
		obj = objeto;

		if (objeto.equals("Deporte")) {
			lblTituloAniadirEditar.setText("DATOS DEPORTE");
			lblDato1.setVisible(true);
			lblDato1.setText("Nombre:");
			txtFNombre.setVisible(true);
			if (deporte != null) {
				txtFNombre.setText(deporte.getNombreDeporte());
			}
		}

		if (objeto.equals("Equipo")) {
			lblTituloAniadirEditar.setText("DATOS EQUIPO");
			lblDato1.setVisible(true);
			lblDato1.setText("Nombre:");
			txtFNombre.setVisible(true);
			lblDato2.setVisible(true);
			lblDato2.setText("Iniciales:");
			txtFIniciales.setVisible(true);
			if (equipo != null) {
				txtFNombre.setText(equipo.getnombreEquipo());
				txtFIniciales.setText(equipo.getIniciales());
			}
		}

		if (objeto.equals("Deportista")) {
			lblTituloAniadirEditar.setText("DATOS DEPORTISTA");
			lblDato1.setVisible(true); // Nombre
			lblDato1.setText("Nombre:");
			txtFNombre.setVisible(true);
			lblDato2.setVisible(true); // Sexo
			lblDato2.setText("Sexo:");
			ObservableList<String> obsLst = FXCollections.observableArrayList();
			obsLst.add("M");
			obsLst.add("F");
			cmbxSexo.setItems(obsLst);
			cmbxSexo.setVisible(true);
			lblDato3.setVisible(true); // Peso
			lblDato3.setText("Peso:");
			txtFPeso.setVisible(true);
			lblDato4.setVisible(true); // Altura
			lblDato4.setText("ALtura:");
			txtFAltura.setVisible(true);
			lblDato5.setVisible(true); // Imagen
			lblDato5.setText("Imagen:");
			btnSubirImagen.setVisible(true);
			lblNombreImagen.setVisible(true);
			lblNombreImagen.setText("");
			if (deportista != null) {
				txtFNombre.setText(deportista.getNombreDeportista());
				cmbxSexo.getSelectionModel().select(deportista.getSexo());
				txtFPeso.setText(deportista.getPeso() + "");
				txtFAltura.setText(deportista.getAltura() + "");
				lblNombreImagen.setText(deportista.getFoto().toString());
			}
		}

		if (objeto.equals("Olimpiada")) {
			lblTituloAniadirEditar.setText("DATOS OLIMPIADAS");
			lblDato1.setVisible(true); // Nombre
			lblDato1.setText("Nombre:");
			txtFNombre.setVisible(true);
			lblDato2.setVisible(true); // Año
			lblDato2.setText("Anio:");
			txtFAnio.setVisible(true);
			lblDato3.setVisible(true); // Temporada
			lblDato3.setText("Temporada:");
			ObservableList<String> obsLst = FXCollections.observableArrayList();
			obsLst.add("Summer");
			obsLst.add("Winter");
			cmbxTemporada.setItems(obsLst);
			cmbxTemporada.setVisible(true);
			lblDato4.setVisible(true); // Ciudad
			lblDato4.setText("Ciudad:");
			txtFCiudad.setVisible(true);
			if (olimpiadas != null) {
				txtFNombre.setText(olimpiadas.getNombreOlimpiada());
				txtFAnio.setText(olimpiadas.getAnio() + "");
				cmbxTemporada.getSelectionModel().select(olimpiadas.getTemporada());
				txtFCiudad.setText(olimpiadas.getCiudad());
			}
		}

		if (objeto.equals("Evento")) {
			lblTituloAniadirEditar.setText("DATOS EVENTO");
			lblDato1.setVisible(true); // Nombre
			lblDato1.setText("Nombre:");
			txtFNombre.setVisible(true);
			lblDato2.setVisible(true); // Olimpiada
			lblDato2.setText("Olimpiada:");
			cmbxOlimpiada.setVisible(true);
			cmbxOlimpiada.setItems(mainController.getOlimpiadasGstr().cargarOlimpiadas());
			lblDato3.setVisible(true); // Olimpiada
			lblDato3.setText("Deporte:");
			cmbxDeporte.setVisible(true);
			cmbxDeporte.setItems(mainController.getDeportesGstr().cargarDeportes());
			if (evento != null) {
				txtFNombre.setText(evento.getNombreEvento());
				cmbxOlimpiada.getSelectionModel().select(evento.getOlimpiada());
				cmbxDeporte.getSelectionModel().select(evento.getDeporte());
			}
		}

	}

	/**
	 * Cerrará esta ventana
	 * 
	 * @param event
	 */
	@FXML
	void cancelar(ActionEvent event) {
		Node n = (Node) event.getSource();
		Stage stage = (Stage) n.getScene().getWindow();
		stage.close();
	}

	/**
	 * Mandará al gestor que guarde el objeto en la base de datos. Luego se cerrará
	 * y actualizará la tabla de la ventana padre.
	 * 
	 * @param event
	 */
	@FXML
	void guardar(ActionEvent event) {
		if (obj.equals("Deporte")) {
			Deporte dep = new Deporte(txtFNombre.getText());
			if (this.deporte == null) {
				mainController.getDeportesGstr().insertDeporte(dep);
			} else {
				dep.setIdDeporte(deporte.getIdDeporte());
				mainController.getDeportesGstr().editarDeporte(dep);
			}
			mainController.mostrarTablaDeporte(event);
		}

		if (obj.equals("Deportista")) {
			Deportista depista = new Deportista(txtFNombre.getText(), cmbxSexo.getSelectionModel().getSelectedItem(),
					Integer.parseInt(txtFPeso.getText().toString()), Integer.parseInt(txtFAltura.getText().toString()),
					img);
			if (this.deportista == null) {
				mainController.getDeportistasGestor().insertDeportista(depista);
			} else {
				depista.setIdDeportista(deportista.getIdDeportista());
				mainController.getDeportistasGestor().editarDeportista(depista);
			}
			mainController.mostrarTablaDeportista(event);
		}

		if (obj.equals("Equipo")) {
			Equipo equi = new Equipo(txtFNombre.getText(), txtFIniciales.getText());
			if (this.equipo == null) {
				mainController.getEquipoGestor().insertequipo(equi);
			} else {
				equi.setidEquipo(equipo.getidEquipo());
				mainController.getEquipoGestor().editarequipo(equi);
			}
			mainController.mostrarTablaEquipo(event);
		}

		if (obj.equals("Olimpiada")) {
			Olimpiadas olimp = new Olimpiadas(txtFNombre.getText(), Integer.parseInt(txtFAnio.getText().toString()),
					cmbxTemporada.getSelectionModel().getSelectedItem(), txtFCiudad.getText());
			if (this.olimpiadas == null) {
				mainController.getOlimpiadasGstr().insertOlimpiada(olimp);
			} else {
				olimp.setIdOlimpiada(olimpiadas.getIdOlimpiada());
				mainController.getOlimpiadasGstr().editarOlimpiada(olimp);
			}
			mainController.mostrarTablaOlimpiada(event);
		}

		if (obj.equals("Evento")) {
			Evento even = new Evento(txtFNombre.getText(), cmbxOlimpiada.getSelectionModel().getSelectedItem(), cmbxDeporte.getSelectionModel().getSelectedItem());
			if (this.evento == null) {
				mainController.getEventosGstr().insertEvento(even);
			} else {
				even.setIdEvento(evento.getIdEvento());
				mainController.getEventosGstr().editarEvento(even);
			}
			mainController.mostrarTablaEvento(event);
		}

		Node n = (Node) event.getSource();
		Stage stage = (Stage) n.getScene().getWindow();
		stage.close();
	}

	@FXML
	void subirImagen(ActionEvent event) {
		FileChooser fc = new FileChooser();
		String currentPath = Paths.get(".").toAbsolutePath().normalize().toString() + "/resources/img";
		fc.setInitialDirectory(new File(currentPath));

		fc.setTitle("Select Image");
		fc.getExtensionFilters().add(new ExtensionFilter("Imagenes", "*.png", "*.jpg"));

		File imagenElegida = fc.showOpenDialog(null);

		if (imagenElegida != null) {
			try {
				img = new FileInputStream(new File(imagenElegida.getAbsolutePath().toString()));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			lblNombreImagen.setText(imagenElegida.getName().toString());
		}
	}

}
