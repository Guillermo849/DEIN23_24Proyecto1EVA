package controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Deporte;
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
	
	/**
	 * Indicará cual es su padre y que objeto le han pasado
	 * @param parent
	 * @param deporte
	 */
	public void setParent(OlimpiadasController parent, Deporte deporte) {
		this.mainController = parent;
		this.deporte = deporte;
		lblTituloAniadirEditar.setText("DATOS DEPORTE");
		lblDato1.setVisible(true);
		lblDato1.setText("Nombre:");
		txtFNombre.setVisible(true);

		if (deporte != null) {
			txtFNombre.setText(deporte.getNombreDeporte().toString());
		}
	}
	
	/**
	 * Cerrará esta ventana
	 * @param event
	 */
	@FXML
	void cancelar(ActionEvent event) {
		Node n = (Node) event.getSource();
		Stage stage = (Stage) n.getScene().getWindow();
		stage.close();
	}
	
	/**
	 * Mandará al gestor que guarde el objeto en la base de datos.
	 * Luego se cerrará y actualizará la tabla de la ventana padre.
	 * @param event
	 */
	@FXML
	void guardar(ActionEvent event) {
		Deporte dep = new Deporte(txtFNombre.getText());
		if (this.deporte != null) {
			if (deporte.getIdDeporte() == 0) {
				mainController.getDeportesGstr().insertDeporte(dep);
			} else {
				dep.setIdDeporte(deporte.getIdDeporte());
				mainController.getDeportesGstr().editarDeporte(dep);
			}
		}

		mainController.mostrarTablaDeporte(event);

		Node n = (Node) event.getSource();
		Stage stage = (Stage) n.getScene().getWindow();
		stage.close();
	}

	@FXML
	void subirImagen(ActionEvent event) {

	}

}
