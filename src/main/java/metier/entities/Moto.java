package metier.entities;

import java.io.Serializable;


public class Moto implements Serializable{
	private Long idMoto;
	private String nomMoto;
	private double prix;

	public Moto() {
		super();
	}

	public Moto(String nomMoto, double prix) {
		this.nomMoto = nomMoto;
		this.prix = prix;
	}

	public Long getIdMoto() {
		return idMoto;
	}

	public void setIdMoto(long idMoto) {
		this.idMoto = idMoto;
	}

	public String getNomMoto() {
		return nomMoto;
	}

	public void setNomMoto(String nomMoto) {
		this.nomMoto = nomMoto;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	@Override
	public String toString() {
		return "Moto [idMoto=" + idMoto + ", nomMoto=" + nomMoto + ", prix=" + prix + "]";
	}
	

	
	

}
	