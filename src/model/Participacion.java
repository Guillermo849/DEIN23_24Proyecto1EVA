package model;

public class Participacion {
	private int idParticipacion;
	private Evento evento;
	private Equipo equipo;
	private int edad;
	private String medalla;
	
	public Participacion(Evento evento, Equipo equipo, int edad, String medalla) {
		this.evento = evento;
		this.equipo = equipo;
		this.edad = edad;
		this.medalla = medalla;
	}

	public Participacion(int idParticipacion, Evento evento, Equipo equipo, int edad, String medalla) {
		this.idParticipacion = idParticipacion;
		this.evento = evento;
		this.equipo = equipo;
		this.edad = edad;
		this.medalla = medalla;
	}

	public int getIdParticipacion() {
		return idParticipacion;
	}

	public void setIdParticipacion(int idParticipacion) {
		this.idParticipacion = idParticipacion;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getMedalla() {
		return medalla;
	}

	public void setMedalla(String medalla) {
		this.medalla = medalla;
	}
}
