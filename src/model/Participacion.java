package model;

public class Participacion {
	private int idParticipacion;
	private Evento evento;
	private Equipo equipo;
	private int anio;
	private String temporada;
	
	public Participacion(Evento evento, Equipo equipo, int anio, String temporada) {
		this.evento = evento;
		this.equipo = equipo;
		this.anio = anio;
		this.temporada = temporada;
	}

	public Participacion(int idParticipacion, Evento evento, Equipo equipo, int anio, String temporada) {
		this.idParticipacion = idParticipacion;
		this.evento = evento;
		this.equipo = equipo;
		this.anio = anio;
		this.temporada = temporada;
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

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getTemporada() {
		return temporada;
	}

	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}
}
