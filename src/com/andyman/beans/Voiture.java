/**
 * 
 */
package com.andyman.beans;

import com.andyman.data.TypeVoiture;

/**
 * @author AndyMan
 *
 */
public class Voiture {
	private Integer id;
	private TypeVoiture type;
	private String description;
	private boolean disponible;
	private Adresse adresse;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the type
	 */
	public TypeVoiture getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(TypeVoiture type) {
		this.type = type;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	public Voiture() {
		// TODO Auto-generated constructor stub
		this.disponible = true;
	}
	public Voiture(TypeVoiture type, String description) {
		this.type = type;
		this.description = description;
	}
	
	/**
	 * @return the disponible
	 */
	public boolean isDisponible() {
		return disponible;
	}
	/**
	 * @param disponible the disponible to set
	 */
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	
	/**
	 * @return the adresse
	 */
	public Adresse getAdresse() {
		return adresse;
	}
	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	/**
	 * @param type
	 * @param description
	 * @param disponible
	 * @param adresse
	 */
	public Voiture(TypeVoiture type, String description, boolean disponible, Adresse adresse) {
		this.type = type;
		this.description = description;
		this.disponible = disponible;
		this.adresse = adresse;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Voiture [id=").append(id).append(", type=").append(type).append(", description=")
				.append(description).append(", disponible=").append(disponible).append(", adresse=").append(adresse.toString())
				.append("]");
		return builder.toString();
	}
	
	
}
