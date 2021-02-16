/**
 * 
 */
package com.andyman.beans;

import com.andyman.utils.UserUtils;

/**
 * @author AndyMan
 *
 */
public class Adresse {
	private Integer id;
	private String Ville,Commune,Quartier,Avenue;
	private int numero;
	private Position position;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	public String details() {
		return Commune+" "+Quartier+" "+Avenue+" numero: "+numero;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the ville
	 */
	public String getVille() {
		return Ville;
	}
	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		Ville = ville;
	}
	/**
	 * @return the commune
	 */
	public String getCommune() {
		return Commune;
	}
	/**
	 * @param commune the commune to set
	 */
	public void setCommune(String commune) {
		Commune = commune;
	}
	/**
	 * @return the quartier
	 */
	public String getQuartier() {
		return Quartier;
	}
	/**
	 * @param quartier the quartier to set
	 */
	public void setQuartier(String quartier) {
		Quartier = quartier;
	}
	/**
	 * @return the avenue
	 */
	public String getAvenue() {
		return Avenue;
	}
	/**
	 * @param avenue the avenue to set
	 */
	public void setAvenue(String avenue) {
		Avenue = avenue;
	}
	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}
	/**
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Adresse(String ville, String commune, String quartier, String avenue, int numero) {
		Ville = ville;
		Commune = commune;
		Quartier = quartier;
		Avenue = avenue;
		this.numero = numero;
	}
	public Adresse() {
		// TODO Auto-generated constructor stub
		this.setPosition(UserUtils.autoPositionFinder());
	}
	
	
	public Position convertToPosition() {
		return UserUtils.autoPositionFinder();
	}
	/**
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(Position position) {
		this.position = position;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Adresse [id=").append(id).append(", Ville=").append(Ville).append(", Commune=").append(Commune)
				.append(", Quartier=").append(Quartier).append(", Avenue=").append(Avenue).append(", numero=")
				.append(numero).append(", position=").append(position.toString()).append("]");
		return builder.toString();
	}
}
