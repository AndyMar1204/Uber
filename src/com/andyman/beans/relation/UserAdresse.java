/**
 * 
 */
package com.andyman.beans.relation;

import com.andyman.beans.Adresse;
import com.andyman.beans.User;

/**
 * @author AndyMan
 *
 */
public class UserAdresse {
	private User user;
	private Adresse adresse;
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
	public UserAdresse(User user, Adresse adresse) {
		this.user = user;
		this.adresse = adresse;
	}
	public UserAdresse() {
	}
	
}
