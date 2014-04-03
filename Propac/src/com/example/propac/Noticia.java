package com.example.propac;

public class Noticia {
	
	private int id;
	private String tituto;
	private String descripicon;
	private String fechaPublicacion;
	private String autor;
	
	public Noticia(int id, String titulo, String descripcion, String fecha, String autor){
		this.id = id;
		this.tituto = titulo;
		this.descripicon = descripcion;
		this.fechaPublicacion = fecha;
		this.autor = autor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTituto() {
		return tituto;
	}

	public void setTituto(String tituto) {
		this.tituto = tituto;
	}

	public String getDescripicon() {
		return descripicon;
	}

	public void setDescripicon(String descripicon) {
		this.descripicon = descripicon;
	}

	public String getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(String fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
	

	
	
}
