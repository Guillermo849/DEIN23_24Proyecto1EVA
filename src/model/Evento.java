package model;

public class Evento {
	
	private int idEvento;
	private String nombreEvento;
	private Olimpiadas olimpiada;
	private Deporte deporte;
	
	public Evento(String nombreEvento, Olimpiadas olimpiada, Deporte deporte) {
		this.nombreEvento = nombreEvento;
		this.olimpiada = olimpiada;
		this.deporte = deporte;
	}

	public Evento(int idEvento, String nombreEvento, Olimpiadas olimpiada, Deporte deporte) {
		this.idEvento = idEvento;
		this.nombreEvento = nombreEvento;
		this.olimpiada = olimpiada;
		this.deporte = deporte;
	}

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	public String getNombreEvento() {
		return nombreEvento;
	}

	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}
	
	public Olimpiadas getOlimpiada() {
		return olimpiada;
	}

	public void setOlimpiada(Olimpiadas olimpiada) {
		this.olimpiada = olimpiada;
	}

	public Deporte getDeporte() {
		return deporte;
	}

	public void setDeporte(Deporte deporte) {
		this.deporte = deporte;
	}

	@Override
	public String toString() {
		return nombreEvento;
	}
}
