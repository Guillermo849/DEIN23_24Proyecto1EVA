package controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Deporte;
import model.Equipo;
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
	private ComboBox<?> cmbxTemporada;

	@FXML
	private ComboBox<?> cmbxOlimpiada;

	@FXML
	private ComboBox<?> cmbxDeporte;

	@FXML
	private TextField txtFIniciales;

	@FXML
	private ComboBox<?> cmbxSexo;

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

	private String obj;

	/**
	 * Indicará cual es su padre y que objeto le han pasado
	 * 
	 * @param parent
	 * @param deporte
	 */
	public void setParent(OlimpiadasController parent, Deporte deporte, Equipo equipo, String objeto) {
		this.mainController = parent;
		this.deporte = deporte;
		this.equipo = equipo;
		obj = objeto;

		if (objeto.equals("Deporte")) {
			lblTituloAniadirEditar.setText("DATOS DEPORTE");
			lblDato1.setVisible(true);
			lblDato1.setText("Nombre:");
			txtFNombre.setVisible(true);
		}
		if (objeto.equals("Equipo")) {
			lblTituloAniadirEditar.setText("DATOS EQUIPO");
			lblDato1.setVisible(true);
			lblDato1.setText("Nombre:");
			txtFNombre.setVisible(true);
			lblDato2.setVisible(true);
			lblDato2.setText("Iniciales:");
			txtFIniciales.setVisible(true);
		}

		if (deporte != null) {
			txtFNombre.setText(deporte.getNombreDeporte().toString());
		}
		if (equipo != null) {
			txtFNombre.setText(equipo.getnombreEquipo().toString());
			txtFIniciales.setText(equipo.getIniciales());
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

		Node n = (Node) event.getSource();
		Stage stage = (Stage) n.getScene().getWindow();
		stage.close();
	}

	@FXML
	void subirImagen(ActionEvent event) {

	}

}
