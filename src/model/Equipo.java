package model;

public class Equipo {
	private int idEquipo;
	private String nombreEquipo;
	private String iniciales;
	
	public Equipo(String nombreEquipo, String iniciales) {
		this.nombreEquipo = nombreEquipo;
		this.iniciales = iniciales;
	}
	
	public Equipo(int idEquipo, String nombreEquipo, String iniciales) {
		this.idEquipo = idEquipo;
		this.nombreEquipo = nombreEquipo;
		this.iniciales = iniciales;
	}

	public int getidEquipo() {
		return idEquipo;
	}

	public void setidEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getnombreEquipo() {
		return nombreEquipo;
	}

	public void setnombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}
	
	public String getIniciales() {
		return iniciales;
	}

	public void setIniciales(String iniciales) {
		this.iniciales = iniciales;
	}

	@Override
	public String toString() {
		return nombreEquipo;
	}
}
