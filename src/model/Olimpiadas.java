package model;

public class Olimpiadas {
	private int idOlimpiada;
	private String nombreOlimpiada;
	private int anio;
	private String temporada;
	private String ciudad;

	public Olimpiadas(String nombreOlimpiada, int anio, String temporada, String ciudad) {
		super();
		this.nombreOlimpiada = nombreOlimpiada;
		this.anio = anio;
		this.temporada = temporada;
		this.ciudad = ciudad;
	}

	public Olimpiadas(int idOlimpiada, String nombreOlimpiada, int anio, String temporada, String ciudad) {
		super();
		this.idOlimpiada = idOlimpiada;
		this.nombreOlimpiada = nombreOlimpiada;
		this.anio = anio;
		this.temporada = temporada;
		this.ciudad = ciudad;
	}

	public int getIdOlimpiada() {
		return idOlimpiada;
	}

	public void setIdOlimpiada(int idOlimpiada) {
		this.idOlimpiada = idOlimpiada;
	}
	
	public String getNombreOlimpiada() {
		return nombreOlimpiada;
	}

	public void setNombreOlimpiada(String nombreOlimpiada) {
		this.nombreOlimpiada = nombreOlimpiada;
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

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public String toString() {
		return nombreOlimpiada;
	}
	
}
