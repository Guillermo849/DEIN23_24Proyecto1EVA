package model;

import java.io.InputStream;

public class Deportista {
	private int idDeportista;
	private String nombreDeportista;
	private String sexo;
	private int peso;
	private int altura;
	private InputStream foto;
	
	public Deportista(String nombreDeportista, String sexo, int peso, int altura, InputStream foto) {
		this.nombreDeportista = nombreDeportista;
		this.sexo = sexo;
		this.peso = peso;
		this.altura = altura;
		this.foto = foto;
	}

	public Deportista(int idDeportista, String nombreDeportista, String sexo, int peso, int altura,
			InputStream foto) {
		this.idDeportista = idDeportista;
		this.nombreDeportista = nombreDeportista;
		this.sexo = sexo;
		this.peso = peso;
		this.altura = altura;
		this.foto = foto;
	}

	public int getIdDeportista() {
		return idDeportista;
	}

	public void setIdDeportista(int idDeportista) {
		this.idDeportista = idDeportista;
	}

	public String getNombreDeportista() {
		return nombreDeportista;
	}

	public void setNombreDeportista(String nombreDeportista) {
		this.nombreDeportista = nombreDeportista;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public InputStream getFoto() {
		return foto;
	}

	public void setFoto(InputStream foto) {
		this.foto = foto;
	}
	
	@Override
	public String toString() {
		return nombreDeportista;
	}
}
