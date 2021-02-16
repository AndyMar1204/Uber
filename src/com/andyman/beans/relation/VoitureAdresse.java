/**
 * 
 */
package com.andyman.beans.relation;

import com.andyman.beans.Adresse;
import com.andyman.beans.Voiture;

/**
 * @author AndyMan
 *
 */
public class VoitureAdresse {
	private Voiture voiture;
	private Adresse adresse;
	/**
	 * @return the voiture
	 */
	public Voiture getVoiture() {
		return voiture;
	}
	/**
	 * @param voiture the voiture to set
	 */
	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
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
	public VoitureAdresse(Voiture voiture, Adresse adresse) {
		this.voiture = voiture;
		this.adresse = adresse;
	}
	public VoitureAdresse() {
	}
	
	
}
