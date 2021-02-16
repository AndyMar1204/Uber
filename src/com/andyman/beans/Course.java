/**
 * 
 */
package com.andyman.beans;
import com.andyman.data.Etat;
import com.andyman.data.Longueur;
import com.andyman.utils.UserUtils;
/**
 * @author AndyMan
 *
 */
public class Course {
	private int id;
	private User user;
	private Adresse adresse_destination;
	private double prix;
	private Etat etat;
	private Voiture voiture;
	
	
	private double prixGetters() {
		return 1000*getDistance();
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the adresse_destination
	 */
	public Adresse getAdresse_destination() {
		return adresse_destination;
	}

	/**
	 * @param adresse_destination the adresse_destination to set
	 */
	public void setAdresse_destination(Adresse adresse_destination) {
		this.adresse_destination = adresse_destination;
	}

	/**
	 * @return the prix
	 */
	public double getPrix() {
		return prix;
	}

	/**
	 * @param prix the prix to set
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}

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
	public double getDistance() {
		double x = UserUtils.distanceEntrePosition(this.adresse_destination.getPosition(), this.getUser().getAdresse().getPosition());
		System.out.print(x);
		return UserUtils.arrondiLongueur(x, Longueur.KILOMETRE);
	}

	/**
	 * 
	 */
	public Course() {
		this.prix = this.prixGetters();
	}


	


	/**
	 * @return the etat
	 */
	public Etat getEtat() {
		return etat;
	}


	/**
	 * @param etat the etat to set
	 */
	public void setEtat(Etat etat) {
		this.etat = etat;
	}


	/**
	 * @param user
	 * @param adresse_destination
	 * @param prix
	 * @param etat
	 * @param voiture
	 * @param Utiliser pour base de donnees vers control
	 */
	public Course(User user, Adresse adresse_destination, double prix, Etat etat, Voiture voiture) {
		this.user = user;
		this.adresse_destination = adresse_destination;
		this.prix = prix;
		this.etat = etat;
		this.voiture = voiture;
	}


	/**
	 * @param user
	 * @param adresse_destination
	 * @param etat
	 * @param voiture
	 * @param utiliser de controll to base de données, le calcul du prix est fait automatiquement
	 */
	public Course(User user, Adresse adresse_destination, Etat etat, Voiture voiture) {
		this.user = user;
		this.adresse_destination = adresse_destination;
		this.etat = etat;
		this.voiture = voiture;
		this.prix = prixGetters();
	}


	/**
	 * @param user
	 * @param adresse_destination pour checker le prix avant de valider
	 */
	public Course(User user, Adresse adresse_destination) {
		this.user = user;
		this.adresse_destination = adresse_destination;
		this.prix = prixGetters();
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Course [id=").append(id).append(", user=").append(user.toString()).append(", adresse_destination=")
				.append(adresse_destination.toString()).append(", prix=").append(prix).append(", etat=").append(etat)
				.append(", voiture=").append(voiture.toString()).append("] ").append(getDistance()).append(" est la distance en km");
		return builder.toString();
	}
	
	
}
