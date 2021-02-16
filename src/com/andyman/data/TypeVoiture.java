/**
 * 
 */
package com.andyman.data;

/**
 * @author AndyMan
 *
 */
public enum TypeVoiture {
	TAXI(1,"Voiture taxi"),BUS(2,"Voiture bus pour plusieurs"),AUTRES(3,"Autres types de voiture");
	private String details;
	private int id;
	private TypeVoiture( int id,String details) {
		this.details = details;
		this.id = id;
	}
	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	
}
